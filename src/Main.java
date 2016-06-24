import com.dakstraede.cryptoweek.ciphers.*;
import com.dakstraede.jciphers.ciphers.*;

import java.util.HashMap;

/**
 * Created by Alex on 21/06/2016.
 */
public class Main
{

    public static void main(String[] args)
    {

        String test = "ahahahah";
        TransCipher.modify(test);
        System.out.println(test);

        System.out.println("SUBSTITUTION :");
        String message = "je veux me coder ce super message.";
        HashMap<Character, Character> key = ( HashMap<Character, Character>) JCipher.generateKey(JCipher.Type.Substitution, JCipher.ALPHABET);
        String encryptedMessage = JCipher.cipher(message, JCipher.Type.Substitution, key);
        System.out.println(encryptedMessage);
        System.out.println(JCipher.decipher(encryptedMessage, JCipher.Type.Substitution, key));
        System.out.println();


        System.out.println("SCYTALE");
        message = "kill_king_tomorrow_midnight_and_chill";
        int scytaleKey = (int)JCipher.generateKey(JCipher.Type.Scytale, message);
        System.out.println(scytaleKey);
        encryptedMessage = JCipher.cipher(message, JCipher.Type.Scytale, scytaleKey);
        System.out.println(encryptedMessage);
        System.out.println(JCipher.decipher(encryptedMessage, JCipher.Type.Scytale, scytaleKey));


        System.out.println("HOMOLOL");
        message = "je veux me coder ce super message.";
        HashMap<Character, HomoCipher.KeyLooper> keyHomo = HomoCipher.generateStaticFrenchKey();
        encryptedMessage = HomoCipher.cipher(message, keyHomo);
        System.out.println(encryptedMessage);
        System.out.println(HomoCipher.decipher(encryptedMessage, keyHomo));


        System.out.println("VIginère");
        message= "essai de cryptage de super message";
        String vigKey = "abx";
        encryptedMessage = VigiCipher.cipher(message, vigKey);
        System.out.println(encryptedMessage);
        System.out.println(VigiCipher.decipher(encryptedMessage, vigKey));


        System.out.println("VERNAM");
        message = "codage de super tonton Vernam";
        String vernKey = VernamCipher.generateKey(message);
        encryptedMessage = VernamCipher.vernam(message, vernKey);
        System.out.println(encryptedMessage);
        System.out.println(VernamCipher.vernam(encryptedMessage, vernKey));

    }
}
