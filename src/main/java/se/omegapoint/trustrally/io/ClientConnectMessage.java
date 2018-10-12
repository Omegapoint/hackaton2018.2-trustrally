package se.omegapoint.trustrally.io;

import se.omegapoint.trustrally.client.ClientType;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

public class ClientConnectMessage implements Message {

    private final ClientType clientType;

    public ClientConnectMessage(ClientType clientType) {
        this.clientType = notNull(clientType);
    }

    ClientConnectMessage(byte[] bytes) {
        isTrue(MessageType.valueOf(bytes[0]) == MessageType.CLIENT_CONNECT);
        this.clientType = ClientType.valueOf(bytes[1]);
    }

    @Override
    public byte[] getBytes() {
        return new byte[]{
                MessageType.CLIENT_CONNECT.toByte(),
                clientType.toByte()};
    }
}
