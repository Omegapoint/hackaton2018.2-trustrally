package se.omegapoint.trustrally.server.game.road;

import java.util.Arrays;

public enum Curve {
    NONE(0),

    RIGHT45(1),
    RIGHT90(2),
    RIGHT135(3),

    LEFT45(-1),
    LEFT90(-2),
    LEFT135(-3);

    public static final float BASE_ANGLE = 45.0f;

    private final int index;

    Curve(int index) {
        this.index = index;
    }

    public static Curve fromIndex(int index) {
        return Arrays.stream(values())
                .filter(curve -> curve.index == index)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int getIndex() {
        return index;
    }

    public float getAngle() {
        return BASE_ANGLE * index;
    }
}
