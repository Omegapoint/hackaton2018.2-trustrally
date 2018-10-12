package se.omegapoint.trustrally.server.game.road;

public class RoadTile {

    private final float inAngle;
    private final float outAngle;

    public RoadTile(float inAngle, Curve curve) {
        this.inAngle = inAngle;
        this.outAngle = inAngle + curve.getAngle();
    }

    public float getInAngle() {
        return inAngle;
    }

    public float getOutAngle() {
        return outAngle;
    }
}
