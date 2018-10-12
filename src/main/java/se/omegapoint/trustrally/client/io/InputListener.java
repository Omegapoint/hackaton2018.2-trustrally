package se.omegapoint.trustrally.client.io;

import se.omegapoint.trustrally.common.io.Message;
import se.omegapoint.trustrally.common.io.MessageParser;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import static org.apache.commons.lang3.Validate.notNull;

public class InputListener implements Runnable {

    private static final int BUFFER_SIZE = 256;

    private final DatagramSocket socket;

    private boolean running = true;

    public InputListener(DatagramSocket socket) {
        this.socket = notNull(socket);
    }

    @Override
    public void run() {
        while (running) {
            DatagramPacket packet = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);

            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

            Message message = MessageParser.parse(packet.getData());

            // TODO: Update client state
        }
    }

    public void stop() {
        running = false;
    }
}
