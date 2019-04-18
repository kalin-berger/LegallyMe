package com.github.crummish.legallyme.document;

import java.io.Serializable;

public enum RecordField implements Serializable {
    GENDER_MARKER("Gender marker"),
    NAME("Name");

    private String name;

    RecordField(String name) {
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
