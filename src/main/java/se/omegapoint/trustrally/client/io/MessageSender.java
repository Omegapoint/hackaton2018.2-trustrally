package se.omegapoint.trustrally.client.io;

import se.omegapoint.trustrally.common.io.Message;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static org.apache.commons.lang3.Validate.notNull;

public class MessageSender {

    private final DatagramSocket socket;
    private final InetAddress serverAddress;
    private final int serverPort;

    public MessageSender(DatagramSocket socket, InetAddress serverAddress, int serverPort) {
        this.socket = notNull(socket);
        this.serverAddress = notNull(serverAddress);
        this.serverPort = notNull(serverPort);
    }

    public void sendMessage(Message message) {
        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);

        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
