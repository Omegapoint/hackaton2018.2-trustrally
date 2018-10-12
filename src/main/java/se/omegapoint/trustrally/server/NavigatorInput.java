package se.omegapoint.trustrally.server;

import se.omegapoint.trustrally.common.io.NavigatorInputMessage;

public class NavigatorInput {

    public void update(NavigatorInputMessage message) {
        System.out.println("Received navigator input!");
    }
}
