package se.omegapoint.trustrally.client;

public enum PlayerType {
    DRIVER,
    NAVIGATOR;

    public static PlayerType valueOf(byte b) {
        return values()[Byte.toUnsignedInt(b)];
    }

    public byte toByte() {
        return (byte) ordinal();
    }
}
