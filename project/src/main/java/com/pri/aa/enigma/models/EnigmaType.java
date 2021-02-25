package com.pri.aa.enigma.models;

public enum EnigmaType {
    TAG("Пятнашки"),
    GALLOWS("Виселица"),
    ENCRYPTION("Шифрование перестановкой");

    private final String displayValue;

    private EnigmaType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}