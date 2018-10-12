package se.omegapoint.trustrally.server.game;

import static org.apache.commons.lang3.Validate.notNull;

public class GameLoop implements Runnable {

    private static final long FRAME_TIME_MS = 10_000; // 100 FPS

    private final GameLogic gameLogic;

    private boolean running = true;

    public GameLoop(GameLogic gameLogic) {
        this.gameLogic = notNull(gameLogic);
    }

    @Override
    public void run() {
        while (running) {
            long startTime = System.currentTimeMillis();

            // TODO: Get latest player input from buffer

            gameLogic.update();

            // TODO: Send game state to clients

            long currentTime = System.currentTimeMillis();
            long frameDuration = currentTime - startTime;

            try {
                Thread.sleep(FRAME_TIME_MS - frameDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
