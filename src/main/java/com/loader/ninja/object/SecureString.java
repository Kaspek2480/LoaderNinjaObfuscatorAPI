package com.loader.ninja.object;

import com.loader.ninja.exception.SecureStringException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import java.security.Key;
import java.util.Optional;

public class SecureString {

    private final Cipher cipherDecrypt;
    private final SealedObject object;

    public SecureString(String string) {
        try {
            Key tempKey = KeyGenerator.getInstance("AES").generateKey();
            Cipher cipherEncrypt = Cipher.getInstance("AES");

            cipherDecrypt = Cipher.getInstance("AES");
            cipherEncrypt.init(Cipher.ENCRYPT_MODE, tempKey);
            cipherDecrypt.init(Cipher.DECRYPT_MODE, tempKey);

            object = new SealedObject(string, cipherEncrypt);
        } catch (Exception e) {
            throw new SecureStringException("Can't create secure string", e);
        }
    }

    public Optional<String> safeRevealString() {
        try {
            return Optional.ofNullable(revealString());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public String revealString() throws Exception {
        return (String) object.getObject(cipherDecrypt);
    }

    public String tryRevealString() {
        try {
            return revealString();
        } catch (Exception e) {
            throw new SecureStringException("Can't reveal string", e);
        }
    }
}