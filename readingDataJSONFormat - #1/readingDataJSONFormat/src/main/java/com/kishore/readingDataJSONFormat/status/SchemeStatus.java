package com.kishore.readingDataJSONFormat.status;

public enum SchemeStatus {

    UNKNOWN(-127),
    ACTIVE(10),
    EMPTY(0)
    ;
    private final byte value;

    SchemeStatus (int value) {
        this.value = (byte) value;
    }
    public static SchemeStatus of(Integer i) {
        if(i == null) return UNKNOWN;
        return switch (i){
            case 10 -> ACTIVE;
            case 0 -> EMPTY;
        };
    }
    public byte value(){
        return value;
    }
}
