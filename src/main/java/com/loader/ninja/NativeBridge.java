package com.loader.ninja;


import com.loader.ninja.exception.LoaderNinjaException;
import com.loader.ninja.exception.SecretNotFoundException;
import com.loader.ninja.helper.FileHelper;
import com.loader.ninja.object.SecureString;
import com.loader.ninja.system.OsType;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class NativeBridge {

    static {
        try {
            if (Files.exists(FileHelper.fetchNativeUpdate())) {
                System.load(FileHelper.fetchNativeUpdate().toAbsolutePath().toString());
                Files.delete(FileHelper.fetchNative());
            } else {
                try (InputStream inputStream = OsType.getOsType().stream()) {
                    if (Files.exists(FileHelper.fetchNative())) {
                        byte[] bytes = FileHelper.readAllBytes(inputStream);
                        byte[] fileBytes = Files.readAllBytes(FileHelper.fetchNative());

                        String checkSum = FileHelper.checksum(bytes);
                        String fileCheckSum = FileHelper.checksum(fileBytes);

                        if (!checkSum.equals(fileCheckSum))
                            Files.write(FileHelper.fetchNative(), bytes);
                    } else {
                        Files.copy(inputStream, FileHelper.fetchNative());
                    }
                }

                if (Files.exists(FileHelper.fetchNative()))
                    System.load(FileHelper.fetchNative().toAbsolutePath().toString());
            }
        } catch (Exception e) {
            throw new LoaderNinjaException("Can't load native", e);
        }
    }

    public static String bakeCake(int rounds, byte... bytes) {
        return new String(NativeBridge.bake(bytes, rounds), StandardCharsets.UTF_8).intern();
    }

    public static native SecureString getSecret(String key) throws SecretNotFoundException;

    public static native byte[] decryptString(byte[] data, String appSecret, int rounds);

    public static native Class<?> findClass(String className, ClassLoader loader);

    public static native void setupConsole(boolean colors, boolean mc);

    public static native byte[] bake(byte[] bytes, int rounds);

    public static native byte[] getSecretVariable(String key);

    public static native String loadFromBytes(byte[] bytes);

    public static native byte[] getResource(String name);

    public static native int getNumber(String key);

    public static native void unload();
}
