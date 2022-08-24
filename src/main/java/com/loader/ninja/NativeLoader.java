package com.loader.ninja;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class NativeLoader {
    private enum OsType {

        MAC("Mac", "native"),
        WINDOWS("Windows", "native.dll"),
        LINUX("Linux", "native.so"),
        UNKNOWN("N/A", "");

        private static final OsType DETECTED = detectOS();

        private final String name;
        private final String nativeName;

        OsType(String name, String nativeName) {
            this.name = name;
            this.nativeName = nativeName;
        }

        public static OsType detectOS() {
            String name = System.getProperty("os.name");
            for (OsType value : values()) {
                if (name.startsWith(value.getName())) {
                    return value;
                }
            }

            return OsType.UNKNOWN;
        }

        public static OsType getOsType() {
            return DETECTED;
        }

        public String getName() {
            return name;
        }

        public String getNativeName() {
            return nativeName;
        }

        public InputStream stream() {
            return Objects.requireNonNull(OsType.class.getResourceAsStream("/" + nativeName));
        }
    }

    private static final OsType os = OsType.detectOS();
    private static final File baseLibPath = new File("lib/" + os.getNativeName());

    public static void load() {
        try {
            extractFromJar();

            //check if update package is available
            String updatePath = getUpdatePath(os);
            if (updatePath != null) {
                try {
                    Files.move(new File(updatePath).toPath(), baseLibPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Updated " + os.getNativeName() + " library");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            //load library
            System.load(baseLibPath.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void extractFromJar() throws IOException {
        if (baseLibPath.exists()) {
            return;
        }
        Files.createDirectories(baseLibPath.toPath());

        Files.copy(Objects.requireNonNull(NativeLoader.class.getResourceAsStream("/lib/" + os.getNativeName())), baseLibPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Updated " + os.getNativeName() + " library");
    }

    public static String getUpdatePath(OsType osType) {
        String[] paths = {"lib/" + osType.getNativeName() + ".new", ""};
        if (Objects.equals(osType.name, "Windows")) {
            paths[1] = System.getProperty("java.io.tmpdir") + osType.getNativeName() + ".new";
        } else {
            paths[1] = System.getProperty("java.io.tmpdir") + "/" + osType.getNativeName() + ".new";
        }

        for (String path : paths) {
            System.out.println(path);
            if (Files.exists(new File(path).toPath())) {
                return path;
            }
        }
        return null;
    }
}
