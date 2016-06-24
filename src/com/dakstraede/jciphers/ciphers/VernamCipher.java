package com.dakstraede.jciphers.ciphers;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Alex on 24/06/2016.
 */
public class VernamCipher
{

    public static String generateKey(String message)
    {
        int length = message.length();
        Random rdn = ThreadLocalRandom.current();
        StringBuilder result = new StringBuilder();

        for(int i=0; i < length; i++){
            result.append((char)rdn.nextInt(256));
        }

        return result.toString();
    }

    public static String vernam(String message, String key)
    {
        StringBuilder result = new StringBuilder();

        for(int i =0; i < message.length(); i++){
            result.append((char)(message.charAt(i) ^ key.charAt(i)));
        }

        return result.toString();
    }

}
