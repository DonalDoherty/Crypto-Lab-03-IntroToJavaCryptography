package DES;
import java.io.*;
import javax.crypto.*;


public class DESECBEncrypt
{
    /**
     * Encrypts message provided on command line and writes to file
     * @param args
     */
    public static void main(String args[])
    {
        try
        {
            if (args.length != 1)
            {
                System.out.println("Please provide input as one argument. Use quotation marks if needed.");
                throw new Exception();
            }
            
            // File containing secret DES key
            FileInputStream keyFIS = new FileInputStream("DESKeyFile");
            ObjectInputStream keyOIS = new ObjectInputStream(keyFIS);
            
            // Read in the DES key
            SecretKey desKey = (SecretKey) keyOIS.readObject();
            keyOIS.close();
            keyFIS.close();
            
            // Create DES cipher instance
            Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            
            // Initialize the cipher for encryption
            desCipher.init(Cipher.ENCRYPT_MODE, desKey);
            
            // File for writing output
            FileOutputStream fos = new FileOutputStream("scrambled");
            
            // Read first command-line arg into a buffer.
            // This is the messge to be encrypted
            byte plaintext[] = args[0].getBytes();
            
            // Encrypt the plaintext
            byte[] ciphertext = desCipher.doFinal(plaintext);
            
            // Write ciphertext to file
            fos.write(ciphertext);
            fos.close();
            
            // Also display it in Hex on stdout
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < ciphertext.length; i++)
            {
                hexString.append(Integer.toHexString(0xF & ciphertext[i]>>4));
                hexString.append(Integer.toHexString(0xF & ciphertext[i]));
                hexString.append(" ");
            }
            System.out.println("Plaintext: " + args[0]);
            System.out.println("Ciphertext: " + hexString.toString());
            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}