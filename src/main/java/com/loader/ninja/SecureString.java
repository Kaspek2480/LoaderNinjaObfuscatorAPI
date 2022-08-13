package com.loader.ninja;

import javax.crypto.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class SecureString {
    //https://github.com/dwp/secure-strings
    private Cipher cipherEncrypt = null;
    private Cipher cipherDecrypt = null;
    private SealedObject object;

    /**
     * Default constructor. Assumes AES encryption, generates a key and sets up the internal ciphers
     *
     * @throws NoSuchPaddingException   : cipher exception
     * @throws NoSuchAlgorithmException : cipher exception
     * @throws InvalidKeyException      : cipher exception
     */
    public SecureString(String strToEnc)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        initialiseCiphers(strToEnc);
    }

    /**
     * initialiseCiphers Takes in crypto Type, generates a key and sets up the internal ciphers.
     *
     * @throws NoSuchAlgorithmException - if the input string is not a valid crypto type
     * @throws NoSuchPaddingException   - if the input string is not a valid crypto type
     * @throws InvalidKeyException      - if the input string is not a valid crypto type
     */
    private void initialiseCiphers(String strToEnc)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        cipherEncrypt = Cipher.getInstance("AES");
        cipherDecrypt = Cipher.getInstance("AES");

        Key tempKey = KeyGenerator.getInstance("AES").generateKey();
        cipherEncrypt.init(Cipher.ENCRYPT_MODE, tempKey);
        cipherDecrypt.init(Cipher.DECRYPT_MODE, tempKey);
        try {
            this.object = this.sealString(strToEnc);
        } catch (IOException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * sealString Takes a string and seals it within a SealedObject using the auto-generated ciphers.
     *
     * @param input the string to encapsulate within the returned SealedObject
     * @return SealedObject Object containing encrypted input
     * @throws IOException               - if an error occurs during serialization
     * @throws IllegalBlockSizeException if the given cipher is a block cipher, no padding has been
     *                                   requested, and the total input length (i.e., the length of the serialized object contents)
     *                                   is not a multiple of the cipher's block size
     */
    public SealedObject sealString(String input) throws IOException, IllegalBlockSizeException {
        return new SealedObject(input, cipherEncrypt);
    }

    /**
     * revealString Uses the auto generated ciphers to retrieve a string from the SealedObject.
     *
     * @return String, null on error.
     */
    public String revealString() {
        try {
            return (String)
                    object.getObject(cipherDecrypt); // using cast to allow for null string input
        } catch (IOException
                 | BadPaddingException
                 | IllegalBlockSizeException
                 | ClassNotFoundException e) {
            return null;
        }
    }
}