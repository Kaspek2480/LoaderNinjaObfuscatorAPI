package com.loader.ninja;


import com.loader.ninja.exceptions.LoaderNinjaException;
import com.loader.ninja.exceptions.SecretNotFoundException;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NativeBridge {
    private static final Logger logger = Logger.getLogger(NativeBridge.class.getName());
    public static native byte[] bake(byte[] bytes, int rounds);

    public static native byte[] bake1(String[] bytes, int rounds);

    public static native String loadFromBytes(byte[] bytes);

    public static native Class<?> findClass(String className, ClassLoader loader);

    public static native byte[] getResource(String name);

    public static native byte[] getSecretVariable(String key);

    public static String bakeCake(int rounds, byte ... bytes) {
        return new String(NativeBridge.bake(bytes, rounds), StandardCharsets.UTF_8).intern();
    }

    public static native void unload();

    public static native byte[] encryptString(byte[] data, String appSecret, int rounds);

    public static native byte[] decryptString(byte[] data, String appSecret, int rounds);

    protected static native String getSecret(String key) throws SecretNotFoundException;

    public static String getSecretString(String key) {
        try {
            return getSecret(key);
        } catch (SecretNotFoundException e) {
            logger.log(Level.WARNING, "", e);
            return null;
        }
    }

    public static void main(String[] args) {
//        System.load("C:\\Users\\Kaspek\\IdeaProjects\\LoaderNinjaJavaSDK\\src\\main\\resources\\lib\\windows_64\\libsalt.dll");
        System.load("C:\\Users\\Kaspek\\IdeaProjects\\LoaderNinjaJavaSDK\\src\\main\\resources\\lib\\windows_64\\libtestEnv.dll");

        String abc = getSecretString("abc");


        /*new Thread(() -> {
            while (true) {
                System.out.println("Date: " + new Date());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();*/

        String callerMethod = CallMethodSpy.getCallerMethod();
        System.out.println("Caller: " + callerMethod);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();

            byte[] enc = encryptString(s.getBytes(), "dawdwad", 12);
//            System.out.println(Hex.encode(enc));

            byte[] dec = decryptString(enc, "dawdwad", 12);
            System.out.println(new String(dec));
        }
    }
}
