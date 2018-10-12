package se.omegapoint.trustrally.common.io;

public class MessageParser {

    public static Message parse(byte[] bytes) {
        MessageType type = MessageType.fromByte(bytes[0]);

        switch (type) {
            case CLIENT_CONNECT:
                return new ClientConnectMessage(bytes);
            case DRIVER_INPUT:
                return new DriverInputMessage(bytes);
            case NAVIGATOR_INPUT:
                return new NavigatorInputMessage(bytes);
            default:
                throw new IllegalArgumentException();
        }
    }
}
