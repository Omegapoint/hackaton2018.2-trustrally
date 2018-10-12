package se.omegapoint.trustrally;

import se.omegapoint.trustrally.client.Client;
import se.omegapoint.trustrally.client.PlayerType;
import se.omegapoint.trustrally.server.Server;

import static org.apache.commons.lang3.Validate.isTrue;

public class TrustRally {

    public static void main(String[] args) {
        isTrue(args.length <= 1);

        if (args.length == 0) {
            startServer();
        } else {
            PlayerType playerType = PlayerType.valueOf(args[0].toUpperCase());
            startClient(playerType);
        }
    }

    private static void startServer() {
        Server server = new Server();
        new Thread(server).start();
    }

    private static void startClient(PlayerType playerType) {
        Client client = new Client(playerType);
        new Thread(client).start();
    }
}
