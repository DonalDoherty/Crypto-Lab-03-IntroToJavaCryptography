package DES;
import java.io.*;
import javax.crypto.*;

public class DESKeyGen
{
    /**
     * Generates AES key and writes to file
     * N.B. This is just for illustration.
     * Private keys should not be stored in an unprotected form like this.
     * Better to use Keystore
     * @param args
     */
    public static void main(String args[])
    {
        try
        {
            // File for writing output
            FileOutputStream keyFOS = new FileOutputStream("DESKeyFile");
            ObjectOutputStream keyOOS = new ObjectOutputStream(keyFOS);
            
            // Generate random AES key
            KeyGenerator keygen = KeyGenerator.getInstance("DES");
            SecretKey desKey = keygen.generateKey();
            keyOOS.writeObject(desKey);
            
            System.out.println("DES key generated and written to file: DESKeyFile");
             
            keyOOS.close();
            keyFOS.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}