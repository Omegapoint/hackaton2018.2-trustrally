package se.omegapoint.trustrally.common.io;

import static org.apache.commons.lang3.Validate.isTrue;

public class DriverInputMessage implements Message {

    DriverInputMessage(byte[] bytes) {
        isTrue(MessageType.fromByte(bytes[0]) == MessageType.DRIVER_INPUT);
    }

    @Override
    public byte[] getBytes() {
        return new byte[]{
                MessageType.DRIVER_INPUT.toByte()};
    }
}
