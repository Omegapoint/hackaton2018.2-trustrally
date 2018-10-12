package se.omegapoint.trustrally.common.io;

import static org.apache.commons.lang3.Validate.isTrue;

public class NavigatorInputMessage implements Message {

    private final int key;

    public NavigatorInputMessage(int key) {
        this.key = key;
    }

    NavigatorInputMessage(byte[] bytes) {
        isTrue(MessageType.fromByte(bytes[0]) == MessageType.NAVIGATOR_INPUT);
        this.key = Byte.toUnsignedInt(bytes[1]);
    }

    @Override
    public byte[] getBytes() {
        return new byte[]{
                MessageType.NAVIGATOR_INPUT.toByte(),
                (byte) key};
    }

    public int getKey() {
        return key;
    }
}
