package DES;
import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class DESCBCDecrypt
{
    /**
     * Decrypts message provided in file and writes to standard output
     * @param args
     */
    public static void main(String args[])
    {
        try
        {
            // File containing secret DES key
            FileInputStream keyFIS = new FileInputStream("DESKeyFile");
            ObjectInputStream keyOIS = new ObjectInputStream(keyFIS);

            // Read in the DES key
            SecretKey desKey = (SecretKey) keyOIS.readObject();
            keyOIS.close();
            keyFIS.close();
            
            // set IV (required for CBC)
            byte[] iv = new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
            IvParameterSpec ips = new IvParameterSpec(iv);

           // Create DES cipher instance
            Cipher desCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            
            // Initialize the cipher for decryption
            desCipher.init(Cipher.DECRYPT_MODE, desKey, ips);
            
            // Read ciphertext from file and decrypt it
            FileInputStream fis = new FileInputStream("scrambled");
            BufferedInputStream bis = new BufferedInputStream(fis);
            CipherInputStream cis = new CipherInputStream(bis, desCipher);
            
            StringBuffer plaintext = new StringBuffer();
            int c;
            while ((c = cis.read()) != -1)
                plaintext.append((char) c);
            cis.close();
            bis.close();
            fis.close();
            
            System.out.println("Plaintext: " + plaintext.toString());
            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}