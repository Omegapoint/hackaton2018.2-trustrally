package se.omegapoint.trustrally.common;

public enum PlayerType {
    DRIVER,
    NAVIGATOR;

    public static PlayerType fromByte(byte b) {
        return values()[Byte.toUnsignedInt(b)];
    }

    public byte toByte() {
        return (byte) ordinal();
    }
}
