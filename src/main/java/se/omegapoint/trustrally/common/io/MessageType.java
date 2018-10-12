package se.omegapoint.trustrally.common.io;

public enum MessageType {
    CLIENT_CONNECT,
    DRIVER_INPUT,
    NAVIGATOR_INPUT;

    public static MessageType fromByte(byte b) {
        return values()[Byte.toUnsignedInt(b)];
    }

    public byte toByte() {
        return (byte) ordinal();
    }
}
