package se.omegapoint.trustrally;

import se.omegapoint.trustrally.client.Client;
import se.omegapoint.trustrally.common.PlayerType;
import se.omegapoint.trustrally.server.Server;

import java.io.IOException;
import java.net.InetAddress;

import static org.apache.commons.lang3.Validate.isTrue;

public class TrustRally {

    public static void main(String[] args) {
        if (args.length == 0) {
            startServer();
        } else {
            try {
                startClient(args);
            } catch (IOException e) {
                System.err.println("Failed to start client");
                e.printStackTrace();
            }
        }
    }

    private static void startServer() {
        Server server = new Server();
        new Thread(server).start();
    }

    private static void startClient(String[] args) throws IOException {
        isTrue(args.length == 3);

        PlayerType playerType = PlayerType.valueOf(args[0].toUpperCase());
        InetAddress serverAddress = InetAddress.getByName(args[1]);
        int serverPort = Integer.parseInt(args[2]);

        Client client = new Client(playerType, serverAddress, serverPort);
        new Thread(client).start();
    }
}
