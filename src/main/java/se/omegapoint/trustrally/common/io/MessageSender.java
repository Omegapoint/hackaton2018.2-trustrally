package se.omegapoint.trustrally.common.io;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static org.apache.commons.lang3.Validate.notNull;

public class MessageSender {

    private final DatagramSocket socket;
    private final InetAddress address;
    private final int port;

    public MessageSender(DatagramSocket socket, InetAddress address, int port) {
        this.socket = notNull(socket);
        this.address = notNull(address);
        this.port = notNull(port);
    }

    public void sendMessage(Message message) {
        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);

        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
