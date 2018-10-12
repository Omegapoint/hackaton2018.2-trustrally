package se.omegapoint.trustrally.common.io;

import se.omegapoint.trustrally.common.PlayerType;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

public class ClientConnectMessage implements Message {

    private final PlayerType playerType;

    public ClientConnectMessage(PlayerType playerType) {
        this.playerType = notNull(playerType);
    }

    ClientConnectMessage(byte[] bytes) {
        isTrue(MessageType.fromByte(bytes[0]) == MessageType.CLIENT_CONNECT);
        this.playerType = PlayerType.fromByte(bytes[1]);
    }

    @Override
    public byte[] getBytes() {
        return new byte[]{
                MessageType.CLIENT_CONNECT.toByte(),
                playerType.toByte()};
    }
}
