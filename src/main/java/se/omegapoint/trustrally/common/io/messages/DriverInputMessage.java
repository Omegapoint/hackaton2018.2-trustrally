package se.omegapoint.trustrally.common.io.messages;

import se.omegapoint.trustrally.common.io.Message;
import se.omegapoint.trustrally.common.io.MessageType;

import static org.apache.commons.lang3.Validate.isTrue;

public class DriverInputMessage implements Message {

    private final int key;

    public DriverInputMessage(int key) {
        this.key = key;
    }

    public DriverInputMessage(byte[] bytes) {
        isTrue(MessageType.fromByte(bytes[0]) == MessageType.DRIVER_INPUT);
        this.key = Byte.toUnsignedInt(bytes[1]);
    }

    @Override
    public byte[] getBytes() {
        return new byte[]{
                MessageType.DRIVER_INPUT.toByte(),
                (byte) key};
    }

    public int getKey() {
        return key;
    }
}
