package se.omegapoint.trustrally.client;

import se.omegapoint.trustrally.common.PlayerType;
import se.omegapoint.trustrally.common.io.MessageType;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import static org.apache.commons.lang3.Validate.notNull;

public class Client implements Runnable {

    private final PlayerType type;
    private final InetAddress address;
    private final DatagramSocket socket;

    private byte[] buf;

    public Client(PlayerType type) throws SocketException {
        this.type = notNull(type);
        socket = new DatagramSocket();
        address = InetAddress.getLoopbackAddress();

    }

    public String sendMessage(MessageType messageType) throws IOException {
        String message = messageType.toString();
        buf = message.getBytes();
        DatagramPacket packetToGameServer = new DatagramPacket(buf, buf.length, 4555); //TODO: Send to address
        socket.send(packetToGameServer);

        packetToGameServer = new DatagramPacket(buf, buf.length);
        socket.receive(packetToGameServer);
        String received = new String(packetToGameServer.getData(), 0, packetToGameServer.getLength());
        return received;


    }

    @Override
    public void run() {
        System.out.println(String.format("Running %s client...", type));
    }
}
