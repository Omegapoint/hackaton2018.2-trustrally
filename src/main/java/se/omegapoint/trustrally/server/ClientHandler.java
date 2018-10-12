package se.omegapoint.trustrally.server;

import se.omegapoint.trustrally.common.io.DriverInputMessage;
import se.omegapoint.trustrally.common.io.Message;
import se.omegapoint.trustrally.common.io.MessageParser;
import se.omegapoint.trustrally.common.io.NavigatorInputMessage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static org.apache.commons.lang3.Validate.notNull;

public class ClientHandler implements Runnable {

    private final DatagramSocket socket;
    private final InetAddress address;
    private final int port;

    private final DriverInput driverInput;
    private final NavigatorInput navigatorInput;

    private boolean running = true;

    public ClientHandler(DatagramSocket socket, DatagramPacket connectionPacket, DriverInput driverInput) {
        this(socket, connectionPacket, driverInput, null);
    }

    public ClientHandler(DatagramSocket socket, DatagramPacket connectionPacket, NavigatorInput navigatorInput) {
        this(socket, connectionPacket, null, navigatorInput);
    }

    private ClientHandler(DatagramSocket socket,
                          DatagramPacket connectionPacket,
                          DriverInput driverInput,
                          NavigatorInput navigatorInput) {
        this.socket = notNull(socket);
        this.address = notNull(connectionPacket.getAddress());
        this.port = notNull(connectionPacket.getPort());
        this.driverInput = driverInput;
        this.navigatorInput = navigatorInput;
    }

    @Override
    public void run() {
        int bufferSize = 256;

        while (running) {
            DatagramPacket packet = new DatagramPacket(new byte[bufferSize], bufferSize);

            try {
                socket.receive(packet);
                System.out.println(String.format("Received packet: %s:%s", packet.getAddress(), packet.getPort()));
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

            Message message = MessageParser.parse(packet.getData());
            if (driverInput != null) {
                driverInput.update((DriverInputMessage) message);
            } else {
                navigatorInput.update((NavigatorInputMessage) message);
            }
        }
    }

    public void stop() {
        running = false;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
