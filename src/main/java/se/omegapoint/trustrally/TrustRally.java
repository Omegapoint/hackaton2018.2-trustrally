package se.omegapoint.trustrally;

import org.lwjgl.Version;
import se.omegapoint.trustrally.client.Client;
import se.omegapoint.trustrally.client.ClientType;
import se.omegapoint.trustrally.server.Server;

import static org.apache.commons.lang3.Validate.isTrue;

public class TrustRally {

    public static void main(String[] args) {
        isTrue(args.length <= 1);

        System.out.println(String.format("LWJGL version: %s", Version.getVersion()));

        Runnable application;
        if (args.length == 0) {
            application = new Server();
        } else {
            ClientType clientType = ClientType.valueOf(args[0].toUpperCase());
            application = new Client(clientType);
        }

        new Thread(application).start();
    }
}
