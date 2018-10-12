package se.omegapoint.trustrally.common.io;

import static org.apache.commons.lang3.Validate.isTrue;

public class DriverInputMessage implements Message {

    private int key;

    public DriverInputMessage(int key) {
        this.key = key;
    }

    DriverInputMessage(byte[] bytes) {
        isTrue(MessageType.fromByte(bytes[0]) == MessageType.DRIVER_INPUT);
        this.key = Byte.toUnsignedInt(bytes[1]);
    }

    @Override
    public byte[] getBytes() {
        return new byte[]{
                MessageType.DRIVER_INPUT.toByte(),
                (byte) key};
    }
}
