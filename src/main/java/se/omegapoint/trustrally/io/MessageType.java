package se.omegapoint.trustrally.io;

public enum MessageType {
    CLIENT_CONNECT,
    DRIVER_INPUT,
    NAVIGATOR_INPUT;

    public static MessageType valueOf(byte b) {
        return values()[Byte.toUnsignedInt(b)];
    }

    public byte toByte() {
        return (byte) ordinal();
    }
}
