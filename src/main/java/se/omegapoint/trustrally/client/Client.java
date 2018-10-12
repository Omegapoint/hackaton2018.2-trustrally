package se.omegapoint.trustrally.client;

import static org.apache.commons.lang3.Validate.notNull;

public class Client implements Runnable {

    private final ClientType type;

    public Client(ClientType type) {
        this.type = notNull(type);
    }

    @Override
    public void run() {
        System.out.println(String.format("Running %s client...", type));
    }
}
