package com.github.crummish.legallyme.document;

import java.io.Serializable;

public enum RecordType implements Serializable {
    BIRTH_CERTIFICATE("Birth certificate"),
    DRIVERS_LICENSE("Drivers license"),
    PASSPORT("Passport"),
    SOCIAL_SECURITY("Social security");

    private String name;

    RecordType(String name)  {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
