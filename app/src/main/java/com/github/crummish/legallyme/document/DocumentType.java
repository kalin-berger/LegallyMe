package com.github.crummish.legallyme.document;

import java.io.Serializable;

public enum DocumentType implements Serializable {
    BIRTH_CERTIFICATE("Birth certificate"),
    DRIVERS_LICENSE("Drivers license"),
    PASSPORT("Passport");

    private String name;

    DocumentType(String name)  {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
