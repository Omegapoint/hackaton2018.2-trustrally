package se.omegapoint.trustrally.client.io;

import org.lwjgl.glfw.GLFWKeyCallback;

import java.util.function.Consumer;

import static org.apache.commons.lang3.Validate.notNull;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class Keyboard extends GLFWKeyCallback {

    private final Consumer<Integer> keyConsumer;

    public Keyboard(Consumer<Integer> keyConsumer) {
        this.keyConsumer = notNull(keyConsumer);
    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (action != GLFW_PRESS) {
            return;
        }

        keyConsumer.accept(key);
    }
}
