package com.pri.aa.enigma.models;

public enum KeyType {
    NUM("Число"),
    NUMS("Цифры"),
    LETTERS("Буквы");

    private final String displayValue;

    private KeyType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}