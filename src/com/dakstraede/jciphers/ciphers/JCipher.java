package com.dakstraede.jciphers.ciphers;

import java.security.InvalidParameterException;
import java.util.*;

/**
 * Created by Alex on 21/06/2016.
 */
public class JCipher
{

    public static final Character[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public enum Type
    {
        Substitution,
        Scytale,
        Caesar
    }

    public static Object generateKey(Type type, Object param)
    {
        Object key = null;
        switch(type){
            case Substitution:
                Character[] alphabet = (Character[])param;
                HashMap<Character, Character> correspondences = new HashMap<>();
                Character[] shuffledAlphabet = alphabet.clone();
                Helper.shuffleCharArray(shuffledAlphabet);
                for(int i = 0; i < alphabet.length; i++){
                    correspondences.put(alphabet[i], shuffledAlphabet[i]);
                }
                key = correspondences;
                break;
            case Scytale:
                String message = (String)param;
                key = Helper.getGreatestDivisor(message.length());
                break;
            case Caesar:

        }

        return key;
    }

    public static String cipher(String message, Type type, Object key)
    {
        String errorMessage = "";
        String result = "";
        try {
            switch (type) {
                case Substitution:
                    errorMessage = "To proceed to a substitution cipher, the key must be of type Map<String, String>";
                    Map map = (Map<Character, Character>) key;

                    result =  substitution(message, map);
                    break;
                case Scytale:
                    errorMessage = "To proceed to a scytale cipher, the key must be of type int";
                    int shift = (int)key;
                    if(message.length()%shift != 0){
                        throw new InvalidParameterException(String.format("%d can't be a proper key for scytale cipher with a message length of %d", shift, message.length()));
                    }

                    result = scytale(message, shift);
                    break;
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
            System.out.println(errorMessage);
        }

        return result;
    }

    public static String decipher(String encryptedMessage, Type type, Object key)
    {
        String errorMessage = "";
        String result = "";
        try {
            switch (type) {
                case Substitution:
                    errorMessage = "To proceed to a substitution decipher, the key must be of type Map<String, String>";
                    Map<Character, Character> map = (Map<Character, Character>) key;

                    HashMap invertedMap = new HashMap<String, String>();
                    for (Map.Entry<Character, Character> elem : map.entrySet()) {
                        invertedMap.put(elem.getValue(), elem.getKey());
                    }
                    result = substitution(encryptedMessage, invertedMap);
                    break;
                case Scytale:
                    errorMessage = "To proceed to a scytale cipher, the key must be of type int";
                    int shift = (int)key, length = encryptedMessage.length();
                    if(length%shift != 0){
                        throw new InvalidParameterException(String.format("%d can't be a proper key for scytale cipher with a message length of %d", shift, length));
                    }

                    result = scytale(encryptedMessage, length/shift);
                    break;
            }
        }
        catch (ClassCastException | InvalidParameterException e) {
            e.printStackTrace();
            System.out.println(errorMessage);
        }
        return result;
    }

    private static String substitution(String message, Map<Character, Character> charsCorrespondences)
    {
        StringBuilder result = new StringBuilder(message);

        int length = result.length() - 1;
        for (int i = 0; i < length; i++) {
            if (charsCorrespondences.containsKey(result.charAt(i))) {
                result.setCharAt(i, charsCorrespondences.get(result.charAt(i)));
            }
        }

        return result.toString();
    }

    private static String scytale(String message, int shift)
    {
        StringBuilder result = new StringBuilder();
        int i, loop = 0, length = message.length();

        while(loop < shift) {
            i = loop;
            while(i < length){
                result.append(message.charAt(i));
                i += shift;
            }
            loop++;
        }

        return result.toString();
    }

}
