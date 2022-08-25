package com.loader.ninja.system;

import java.io.InputStream;
import java.util.Objects;

public enum OsType {

    MAC("Mac", "lib/native"),
    WINDOWS("Windows", "lib/native.dll"),
    LINUX("Linux", "lib/native.so"),
    UNKNOWN("N/A", "");

    private static final OsType DETECTED = detectOS();

    private final String name;
    private final String nativeName;

    OsType(String name, String nativeName) {
        this.name = name;
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

    public String getNativeName() {
        return nativeName;
    }

    public InputStream stream() {
        return Objects.requireNonNull(OsType.class.getResourceAsStream("/" + nativeName));
    }
}
