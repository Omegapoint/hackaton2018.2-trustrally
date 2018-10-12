package se.omegapoint.trustrally.client;

import se.omegapoint.trustrally.client.graphics.Window;
import se.omegapoint.trustrally.common.PlayerType;
import se.omegapoint.trustrally.common.io.MessageType;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import static org.apache.commons.lang3.Validate.notNull;
import static org.lwjgl.opengl.GL11.*;

public class Client implements Runnable {

    private static final String WINDOW_TITLE = "TrustRally Client - %s";
    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;

    private final PlayerType playerType;
    private final Window window;

    private DatagramSocket socket;
    private byte[] buf;

    public Client(PlayerType playerType) {
        this.playerType = notNull(playerType);
        this.window = new Window(String.format(WINDOW_TITLE, playerType), WINDOW_WIDTH, WINDOW_HEIGHT);

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
        System.out.println(String.format("Running %s client...", playerType));

        try {
            window.init();
            renderLoop();
            window.release();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            window.terminate();
        }
    }

    private void renderLoop() {
        while (!window.shouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // TODO: Render game state

            window.update();
        }
    }
}
