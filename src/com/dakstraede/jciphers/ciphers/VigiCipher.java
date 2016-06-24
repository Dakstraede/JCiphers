package com.dakstraede.jciphers.ciphers;

/**
 * Created by Alex on 23/06/2016.
 */
public class VigiCipher
{
    public static String cipher(String message, String key)
    {
        int[] shifts = new int[key.length()];

        for(int i = 0; i < key.length(); i++){
            shifts[i] = (int)key.charAt(i) - 97;
        }

        StringBuilder result = new StringBuilder(message);

        char current;
        int shift, count = 0;
        for(int i = 0; i < message.length(); i++){
            current = result.charAt(i);
            if (current >= 97 && current <= 122) {
                shift = shifts[count%key.length()];
                result.setCharAt(i, (char)(((current+shift-97)%26)+97));
                count++;
            }
        }

        return result.toString();
    }

    public static String decipher(String message, String key)
    {
        int[] shifts = new int[key.length()];

        for(int i = 0; i < key.length(); i++){
            shifts[i] = (int)key.charAt(i) - 97;
        }

        StringBuilder result = new StringBuilder(message);

        char current;
        int shift, count = 0;
        for(int i = 0; i < message.length(); i++) {
            current = result.charAt(i);
            if (current >= 97 && current <= 122) {
                shift = 26 - shifts[count%key.length()];
                char newChar = (char)(((current+shift-97)%26)+97);
                result.setCharAt(i, newChar);
                count++;
            }
        }

        return result.toString();
    }
}
