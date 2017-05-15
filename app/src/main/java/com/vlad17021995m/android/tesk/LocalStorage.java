package com.vlad17021995m.android.tesk;

import android.content.SharedPreferences;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by qwerty on 15.05.2017.
 */

public class LocalStorage {
    private static LocalStorage locacStorage;//static value

    private LocalStorage(){//close constructor

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

    public String currentUser(SharedPreferences preferences, String key){
        return preferences.getString(key, "");
    }

    public void setCurrentUser(SharedPreferences preferences, String key, String mail){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, mail);
        editor.commit();
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

    public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }
}
