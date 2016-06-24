package com.dakstraede.jciphers.ciphers;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Alex on 22/06/2016.
 */
public class HomoCipher
{


    public static String cipher(String message, HashMap<Character, KeyLooper> key)
    {
        int length = message.length();
        StringBuilder result = new StringBuilder(message);

        for(int i=0; i < length; i++) {
            if (key.containsKey(result.charAt(i))) {
                result.setCharAt(i, key.get(result.charAt(i)).next());
            }
        }

        return result.toString();
    }

    public static String decipher(String encryptedMessage, HashMap<Character, KeyLooper> key)
    {
        int length = encryptedMessage.length();
        StringBuilder result = new StringBuilder(encryptedMessage);
        HashMap<Character, Character> decipherMap = getDecipherMapFromKey(key);

        for(int i=0; i < length; i++){
            if (decipherMap.containsKey(result.charAt(i))) {
                result.setCharAt(i, decipherMap.get(result.charAt(i)));
            }
        }

        return result.toString();
    }

    /*public static HashMap<Character, KeyLooper> generateKey(String message)
    {
        int length = message.length();
        HashMap<Character, Integer> frequencies = new HashMap<>();

        for(int i=0; i < length; i++){
            if (frequencies.containsKey(message.charAt(i))){

            }
        }
    }*/

    public static HashMap<Character, KeyLooper> generateStaticFrenchKey()
    {
        HashMap<Character, Integer> characterSet = new HashMap<Character, Integer>(){{
            put('e', 10);

            char[] cs = {'s','a','i','t','n','r','u','l','o'};
            for(char c : cs)
                put(c, 5);

            put('d', 4);
            put('c', 4);
            put('p', 4);
            put('m', 4);

            put('é', 3);
            put('v', 3);
            put('q', 3);
            put('f', 3);
            put('b', 3);

            char[] cs2 = {'g','h','j','à','x','y','è','ê','z','w','ç','ù','k','î','œ','ï','ë'};
            for(char c : cs2)
                put(c, 2);
        }};

        HashMap<Character, KeyLooper> key = new HashMap<>();
        ArrayList<Character> used = new ArrayList<>();

        Random rnd = ThreadLocalRandom.current();
        int index;
        for(Map.Entry<Character, Integer> elem : characterSet.entrySet()){
            KeyLooper looper = new KeyLooper();
            for(int i=0; i < elem.getValue(); i++){
                do{
                    index = rnd.nextInt(256);
                }while(used.contains((char)index));
                used.add((char)index);
                looper.addElement((char)index);
            }
            key.put(elem.getKey(), looper);
        }

        return key;
    }

    private static HashMap<Character, Character> getDecipherMapFromKey(HashMap<Character, KeyLooper> key)
    {
        HashMap<Character, Character> result = new HashMap<>();

        for(Map.Entry<Character, KeyLooper> elem : key.entrySet()){
            for(Character c : elem.getValue().elementsToArray()){
                result.put(c, elem.getKey());
            }
        }

        return result;
    }

    public static class KeyLooper
    {
        private ArrayList<Character> elements = new ArrayList<>();
        private int size = 0;
        private int current = 0;

        KeyLooper(List<Character> elements)
        {
            this.elements = new ArrayList<>(elements);
            size = this.elements.size();

        }

        KeyLooper(){};

        void addElement(Character element)
        {
            elements.add(element);
            size++;
        }

        Character current()
        {
            if(size > 0){
                return elements.get(current);
            }
            return null;
        }

        Character next()
        {
            if(size > 0){
                if((current+1) < size){
                    current++;
                    return elements.get(current);
                }

                current = 0;
                return elements.get(current);
            }

            return null;
        }

        Character[] elementsToArray()
        {
            Object[] ar = elements.toArray();
            return Arrays.copyOf(ar, ar.length, Character[].class);
        }
    }
    
}
