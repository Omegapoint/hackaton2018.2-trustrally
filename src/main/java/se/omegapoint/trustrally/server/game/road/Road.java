package se.omegapoint.trustrally.server.game.road;

import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;
import static org.apache.commons.lang3.Validate.isTrue;

public class Road {

    private final RoadTile[] tiles;

    public Road(int[] tileIndices) {
        isTrue(isNotEmpty(tileIndices));

        tiles = new RoadTile[tileIndices.length];
        tiles[0] = new RoadTile(0.0f, Curve.fromIndex(tileIndices[0]));

        for (int i = 1; i < tileIndices.length; i++) {
            tiles[i] = new RoadTile(tiles[i - 1].getOutAngle(), Curve.fromIndex(tileIndices[i]));
        }
    }

    public RoadTile[] getTiles() {
        return tiles;
    }
}
