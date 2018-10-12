package se.omegapoint.trustrally.server;

import se.omegapoint.trustrally.common.io.ClientConnectMessage;
import se.omegapoint.trustrally.common.io.Message;
import se.omegapoint.trustrally.common.io.MessageParser;
import se.omegapoint.trustrally.server.game.GameLogic;
import se.omegapoint.trustrally.server.game.GameLoop;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

    private static final int PORT = 4555;

    private final DatagramSocket socket;

    private DriverInput driverInput = new DriverInput();
    private NavigatorInput navigatorInput = new NavigatorInput();

    public Server() throws IOException {
        socket = new DatagramSocket(PORT);
    }

    public void run() {
        System.out.println("Running server...");

        acceptClients();
        InputListener inputListener = new InputListener(socket, driverInput, navigatorInput);

        GameLogic gameLogic = new GameLogic();
        GameLoop gameLoop = new GameLoop(gameLogic, driverInput, navigatorInput);

        new Thread(inputListener).start();
        gameLoop.start();

        inputListener.stop();
    }

    private void acceptClients() {
        int bufferSize = 256;
        boolean driverConnected = false;
        boolean navigatorConnected = false;

        while (!driverConnected || !navigatorConnected) {
            DatagramPacket packet = new DatagramPacket(new byte[bufferSize], bufferSize);

            try {
                System.out.println("Waiting for client...");
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

            Message message = MessageParser.parse(packet.getData());
            if (!(message instanceof ClientConnectMessage)) {
                continue;
            }

            switch (((ClientConnectMessage) message).getPlayerType()) {
                case DRIVER:
                    System.out.println(String.format("Driver %s:%s connected.", packet.getAddress(), packet.getPort()));
                    driverConnected = true;
                    break;
                case NAVIGATOR:
                    System.out.println(String.format("Navigator %s:%s connected.", packet.getAddress(), packet.getPort()));
                    navigatorConnected = true;
                    break;
            }
        }

        System.out.println("Both players connected!");
    }
}
