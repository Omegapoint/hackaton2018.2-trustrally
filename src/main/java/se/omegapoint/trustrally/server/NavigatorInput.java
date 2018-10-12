package se.omegapoint.trustrally.server;

import se.omegapoint.trustrally.common.io.NavigatorInputMessage;

public class NavigatorInput {

    void update(NavigatorInputMessage message) {
        System.out.println(String.format("Received navigator input: %s", message.getKey()));
    }
}
