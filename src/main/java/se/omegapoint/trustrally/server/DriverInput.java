package se.omegapoint.trustrally.server;

import se.omegapoint.trustrally.common.io.DriverInputMessage;

public class DriverInput {

    void update(DriverInputMessage message) {
        System.out.println(String.format("Received driver input: %s", message.getKey()));
    }
}
