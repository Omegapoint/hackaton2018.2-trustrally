package se.omegapoint.trustrally.client;

import se.omegapoint.trustrally.common.PlayerType;
import se.omegapoint.trustrally.common.io.MessageType;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import static org.apache.commons.lang3.Validate.notNull;

public class Client implements Runnable {

    private final PlayerType type;
    private DatagramSocket socket;

    private byte[] buf;

    public Client(PlayerType type) {
        this.type = notNull(type);

        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public String sendMessage(MessageType messageType) {
        String message = messageType.toString();
        buf = message.getBytes();
        DatagramPacket packetToGameServer = new DatagramPacket(buf, buf.length, 4555); //TODO: Send to address

        try {
            socket.send(packetToGameServer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        packetToGameServer = new DatagramPacket(buf, buf.length);

        try {
            socket.receive(packetToGameServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String received = new String(packetToGameServer.getData(), 0, packetToGameServer.getLength());
        return received;

    }

    @Override
    public void run() {
        System.out.println(String.format("Running %s client...", type));
    }
}
