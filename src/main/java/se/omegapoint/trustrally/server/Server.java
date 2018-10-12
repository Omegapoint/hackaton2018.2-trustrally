package se.omegapoint.trustrally.server;

import se.omegapoint.trustrally.common.io.ClientConnectMessage;
import se.omegapoint.trustrally.common.io.Message;
import se.omegapoint.trustrally.common.io.MessageParser;
import se.omegapoint.trustrally.server.game.GameLogic;
import se.omegapoint.trustrally.server.game.GameLoop;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server implements Runnable {

    private static final int PORT = 4555;

    private final DatagramSocket socket;

    private ClientHandler driver;
    private ClientHandler navigator;
    private DriverInput driverInput = new DriverInput();
    private NavigatorInput navigatorInput = new NavigatorInput();

    public Server() throws IOException {
        socket = new DatagramSocket(PORT);
    }

    @Override
    public void run() {
        System.out.println("Running server...");

        acceptClients();
        System.out.println("Both players connected!");

        GameLogic gameLogic = new GameLogic();
        GameLoop gameLoop = new GameLoop(gameLogic, driverInput, navigatorInput);

        new Thread(driver).start();
        new Thread(navigator).start();

        gameLoop.start();
    }

    private void acceptClients() {
        int bufferSize = 256;

        while (driver == null || navigator == null) {
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
                    driver = new ClientHandler(socket, packet, driverInput);
                    break;
                case NAVIGATOR:
                    System.out.println(String.format("Navigator %s:%s connected.", packet.getAddress(), packet.getPort()));
                    navigator = new ClientHandler(socket, packet, navigatorInput);
                    break;
            }
        }
    }
}
