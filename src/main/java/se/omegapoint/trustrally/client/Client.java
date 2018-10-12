package se.omegapoint.trustrally.client;

import static org.apache.commons.lang3.Validate.notNull;

public class Client implements Runnable {

    private final PlayerType type;

    public Client(PlayerType type) {
        this.type = notNull(type);
    }

    @Override
    public void run() {
        System.out.println(String.format("Running %s client...", type));
    }
}
