package com.dakstraede.jciphers.ciphers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Alex on 21/06/2016.
 */
public class Helper {

    public static final Character[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void shuffleCharArray(Character[] ar)
    {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);

            Character a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public static void prettyPrintMap(HashMap<Character, Character> map)
    {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<Character, Character>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Character, Character> entry = iter.next();
            sb.append(entry.getKey());
            sb.append('=').append('"');
            sb.append(entry.getValue());
            sb.append('"');
            if (iter.hasNext()) {
                sb.append(',').append(' ');
            }
        }

        System.out.println(sb);
    }

    public static int getGreatestDivisor(int number)
    {
        int result = 1;
        for(int i =1; i <= number/2; i++){
            if(number%i == 0)
                result = i;
        }

        return result;
    }

}
