package com.loader.ninja.system;

import java.io.InputStream;
import java.util.Objects;

public enum OsType {

    MAC("Mac", "lib/native", "native.dylib"),
    WINDOWS("Windows", "lib/native.dll", "native.dll"),
    LINUX("Linux", "lib/native.so", "native.so"),
    UNKNOWN("N/A", "", "");

    public static final OsType DETECTED = detectOS();

    private final String name;
    private final String nativePath;
    private final String nativeName;

    OsType(String name, String nativePath, String nativeName) {
        this.name = name;
        this.nativePath = nativePath;
        this.nativeName = nativeName;
    }

    @Deprecated
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

    public String getNativePath() {
        return nativePath;
    }

    public InputStream stream() {
        return Objects.requireNonNull(OsType.class.getResourceAsStream("/" + nativePath));
    }

    public String getNativeName() {
        return nativeName;
    }
}
