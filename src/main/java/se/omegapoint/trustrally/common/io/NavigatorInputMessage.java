package se.omegapoint.trustrally.common.io;

import static org.apache.commons.lang3.Validate.isTrue;

public class NavigatorInputMessage implements Message {

    NavigatorInputMessage(byte[] bytes) {
        isTrue(MessageType.fromByte(bytes[0]) == MessageType.NAVIGATOR_INPUT);
    }

    @Override
    public byte[] getBytes() {
        return new byte[]{
                MessageType.NAVIGATOR_INPUT.toByte()};
    }
}
