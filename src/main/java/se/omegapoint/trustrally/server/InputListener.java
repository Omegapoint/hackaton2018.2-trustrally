package se.omegapoint.trustrally.server;

import se.omegapoint.trustrally.common.io.DriverInputMessage;
import se.omegapoint.trustrally.common.io.Message;
import se.omegapoint.trustrally.common.io.MessageParser;
import se.omegapoint.trustrally.common.io.NavigatorInputMessage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import static org.apache.commons.lang3.Validate.notNull;

public class InputListener implements Runnable {

    private final DatagramSocket socket;
    private final DriverInput driverInput;
    private final NavigatorInput navigatorInput;

    private boolean running = true;

    InputListener(DatagramSocket socket, DriverInput driverInput, NavigatorInput navigatorInput) {
        this.socket = notNull(socket);
        this.driverInput = notNull(driverInput);
        this.navigatorInput = notNull(navigatorInput);
    }

    @Override
    public void run() {
        int bufferSize = 256;

        while (running) {
            DatagramPacket packet = new DatagramPacket(new byte[bufferSize], bufferSize);

            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

            Message message = MessageParser.parse(packet.getData());
            if (message instanceof DriverInputMessage) {
                driverInput.update((DriverInputMessage) message);
            } else if (message instanceof NavigatorInputMessage) {
                navigatorInput.update((NavigatorInputMessage) message);
            } else {
                throw new IllegalArgumentException("Invalid message type");
            }
        }
    }

    void stop() {
        running = false;
    }
}
