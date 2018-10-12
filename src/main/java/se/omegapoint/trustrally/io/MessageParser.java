package se.omegapoint.trustrally.io;

public class MessageParser {

    public static Message parse(byte[] bytes) {
        MessageType type = MessageType.valueOf(bytes[0]);

        switch (type) {
            case CLIENT_CONNECT:
                return new ClientConnectMessage(bytes);
            case DRIVER_INPUT:
                // TODO: Implement!
            case NAVIGATOR_INPUT:
                // TODO: Implement!
            default:
                throw new IllegalArgumentException();
        }
    }
}
