package com.loader.ninja;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class NativeBridge {
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

    public static void main(String[] args) {
        System.load("C:\\Users\\Kaspek\\IdeaProjects\\LoaderNinjaJavaSDK\\src\\main\\resources\\lib\\windows_64\\libtestEnv.dll");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();

            byte[] enc = encryptString(s.getBytes(), "dawdwad", 12);
            System.out.println(HexBin.encode(enc));

            byte[] dec = decryptString(enc, "dawdwad", 12);
            System.out.println(new String(dec));
        }
    }
}
