package se.omegapoint.trustrally.client;

import se.omegapoint.trustrally.common.PlayerType;

import static org.apache.commons.lang3.Validate.notNull;

public class Client implements Runnable {

    private final PlayerType playerType;

    public Client(PlayerType playerType) {
        this.playerType = notNull(playerType);
    }

    @Override
    public void run() {
        System.out.println(String.format("Running %s client...", playerType));
    }
}
