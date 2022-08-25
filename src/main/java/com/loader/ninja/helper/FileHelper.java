package com.loader.ninja.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileHelper {

    private static final Path DIRECTORY = Paths.get("lib");
    private static final Path NATIVE_PATH = DIRECTORY.resolve("native.lib");
    private static final Path NATIVE_UPDATE_PATH = DIRECTORY.resolve("native.update");

    static {
        try {
            if (Files.notExists(DIRECTORY))
                Files.createDirectory(DIRECTORY);
        } catch (Exception e) {
            throw new RuntimeException("Can't create directories", e);
        }
    }

    private FileHelper() {
    }

    public static Path fetchNative() {
        return NATIVE_PATH;
    }

    public static Path fetchNativeUpdate() {
        return NATIVE_UPDATE_PATH;
    }

    public static String checksum(byte[] bytes) {
        return new BigInteger(1, bytes).toString(16);
    }

    public static byte[] readAllBytes(InputStream inputStream) throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int size;

            while ((size = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, size);
            }

            return outputStream.toByteArray();
        }
    }
}
