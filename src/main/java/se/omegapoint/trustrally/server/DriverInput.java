package se.omegapoint.trustrally.server;

import se.omegapoint.trustrally.common.io.DriverInputMessage;

public class DriverInput {

    public void update(DriverInputMessage message) {
        System.out.println("Received driver input!");
    }
}
