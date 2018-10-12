package se.omegapoint.trustrally.client.io;

import org.lwjgl.glfw.GLFWKeyCallback;
import se.omegapoint.trustrally.common.PlayerType;
import se.omegapoint.trustrally.common.io.DriverInputMessage;
import se.omegapoint.trustrally.common.io.MessageSender;
import se.omegapoint.trustrally.common.io.NavigatorInputMessage;

import static org.apache.commons.lang3.Validate.notNull;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class Keyboard extends GLFWKeyCallback {

    private final MessageSender output;
    private final PlayerType playerType;

    public Keyboard(MessageSender output, PlayerType playerType) {
        this.output = notNull(output);
        this.playerType = notNull(playerType);
    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (action != GLFW_PRESS) {
            return;
        }

        if (playerType == PlayerType.DRIVER) {
            output.sendMessage(new DriverInputMessage(key));
        } else if (playerType == PlayerType.NAVIGATOR) {
            output.sendMessage(new NavigatorInputMessage(key));
        }
    }
}
