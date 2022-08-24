package com.loader.ninja;


import com.loader.ninja.exceptions.SecretNotFoundException;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NativeBridge {
    static {
//        NativeLoader.load();
        System.load("C:\\Users\\Kaspek\\IdeaProjects\\LoaderNinjaJavaSDK\\src\\main\\resources\\lib\\windows_64\\libtestEnv.dll");
    }

    private static final Logger LOGGER = Logger.getLogger(NativeBridge.class.getName());
    public static native byte[] bake(byte[] bytes, int rounds);

    public static native byte[] bake1(String[] bytes, int rounds);

    public static native String loadFromBytes(byte[] bytes);

    public static native Class<?> findClass(String className, ClassLoader loader);

    public static native byte[] getResource(String name);

    public static native byte[] getSecretVariable(String key);

    public static native void setupConsole(boolean colors, boolean mc);

    public static String bakeCake(int rounds, byte ... bytes) {
        return new String(NativeBridge.bake(bytes, rounds), StandardCharsets.UTF_8).intern();
    }

    public static native void unload();

    public static native byte[] encryptString(byte[] data, String appSecret, int rounds);

    public static native byte[] decryptString(byte[] data, String appSecret, int rounds);

    public static native int getNumber(String key);

    protected static native SecureString getSecret(String key) throws SecretNotFoundException;

    public static String getSecretString(String key) {
        try {
            return getSecret(key).revealString();
        } catch (SecretNotFoundException e) {
            LOGGER.log(Level.WARNING, "", e);
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            System.out.println(line + " -> " + getNumber(line));
        }
    }
}
