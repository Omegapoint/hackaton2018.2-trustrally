package se.omegapoint.trustrally.client;

import se.omegapoint.trustrally.client.graphics.Window;
import se.omegapoint.trustrally.client.io.InputListener;
import se.omegapoint.trustrally.client.io.Keyboard;
import se.omegapoint.trustrally.common.PlayerType;
import se.omegapoint.trustrally.common.io.ClientConnectMessage;
import se.omegapoint.trustrally.common.io.DriverInputMessage;
import se.omegapoint.trustrally.common.io.MessageSender;
import se.omegapoint.trustrally.common.io.NavigatorInputMessage;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import static org.apache.commons.lang3.Validate.notNull;
import static org.lwjgl.opengl.GL11.*;

public class Client {

    private static final String WINDOW_TITLE = "TrustRally Client - %s";
    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;

    private final PlayerType playerType;
    private final Window window;

    private final InputListener inputListener;
    private final MessageSender output;
    private final Keyboard keyboard;

    public Client(PlayerType playerType, InetAddress serverAddress, int serverPort) throws SocketException {
        this.playerType = notNull(playerType);
        this.window = new Window(String.format(WINDOW_TITLE, playerType), WINDOW_WIDTH, WINDOW_HEIGHT);

        DatagramSocket socket = new DatagramSocket();
        inputListener = new InputListener(socket);
        output = new MessageSender(socket, serverAddress, serverPort);
        keyboard = new Keyboard(playerType == PlayerType.DRIVER ? this::driverInput : this::navigatorInput);
    }

    private void driverInput(int key) {
        output.sendMessage(new DriverInputMessage(key));
    }

    private void navigatorInput(int key) {
        output.sendMessage(new NavigatorInputMessage(key));
    }

    public void run() {
        System.out.println(String.format("Running %s client...", playerType));

        output.sendMessage(new ClientConnectMessage(playerType));
        new Thread(inputListener).start();

        try {
            window.init(keyboard);
            renderLoop();
            window.release();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputListener.stop();
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
