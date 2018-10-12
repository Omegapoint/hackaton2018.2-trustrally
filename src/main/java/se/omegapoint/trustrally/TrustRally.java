package se.omegapoint.trustrally;

import se.omegapoint.trustrally.client.Client;
import se.omegapoint.trustrally.common.PlayerType;
import se.omegapoint.trustrally.server.Server;

import java.io.IOException;
import java.net.InetAddress;

import static org.apache.commons.lang3.Validate.isTrue;

public class TrustRally {

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                startServer();
            } else {
                startClient(args);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startServer() throws IOException {
        new Server().run();
    }

    private static void startClient(String[] args) throws IOException {
        isTrue(args.length == 3);

        PlayerType playerType = PlayerType.valueOf(args[0].toUpperCase());
        InetAddress serverAddress = InetAddress.getByName(args[1]);
        int serverPort = Integer.parseInt(args[2]);

        new Client(playerType, serverAddress, serverPort).run();
    }
}
