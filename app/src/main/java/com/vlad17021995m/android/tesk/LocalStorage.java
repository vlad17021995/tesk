package com.vlad17021995m.android.tesk;

import android.content.SharedPreferences;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by qwerty on 15.05.2017.
 */

public class LocalStorage {
    private static LocalStorage locacStorage;

    private LocalStorage(){

    }

    public static LocalStorage getInstance(){
        if (locacStorage == null){
            locacStorage = new LocalStorage();
        }
        return locacStorage;
    }

    public int registerAccount(SharedPreferences preferences, String email, String pass){// 1 - user already exist
        if (preferences.getString(email, "").equals("")){//0 - register succesfully
            SharedPreferences.Editor editor = preferences.edit();
            String md5 = md5Custom(pass);
            editor.putString(email, md5);
            editor.commit();
            return 0;
        }
        return 1;
    }

    public int loginAccount(SharedPreferences preferences, String email, String pass){//2 - not correct password
        //1 - user not exist
        //0 - login succesfully
        String hash = preferences.getString(email, "");
        if (hash.equals("")){
            return 1;
        }else {
            String eqHash = md5Custom(pass);
            if (eqHash.equals(hash)){
                return 0;
            }else {
                return 2;
            }
        }
    }

    public static String md5Custom(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();

    }
}
