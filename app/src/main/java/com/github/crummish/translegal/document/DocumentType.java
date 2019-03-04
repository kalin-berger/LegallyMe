package com.github.crummish.translegal.document;

import java.io.Serializable;

public enum DocumentType implements Serializable {
    NAME_CHANGE("Name change"),
    DRIVERS_LICENSE("Driver's license"),
    BIRTH_CERTIFICATE("Birth certificate");

    private String name;

    DocumentType(String name)  {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
