package ru.rrozhkov.lib.android.crypt;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rrozhkov on 6/15/2017.
 */

public class AndroidEncrypter {
    public static String encrypt(String text){
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("SHA");
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        try {
            md.update(text.getBytes("UTF-8"));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        byte raw[] = md.digest();
        String hash = new String(Base64.encode(raw, Base64.DEFAULT)).replace("\n",""); //step 5
        return hash;
    }

    public static void main(String[] args) {
        String password = "123";
        System.out.println(AndroidEncrypter.encrypt(password));
    }
}
