package se.omegapoint.trustrally.client;

public enum ClientType {
    DRIVER,
    NAVIGATOR;

    public static ClientType valueOf(byte b) {
        return values()[Byte.toUnsignedInt(b)];
    }

    public byte toByte() {
        return (byte) ordinal();
    }
}
