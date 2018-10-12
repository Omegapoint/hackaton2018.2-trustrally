package se.omegapoint.trustrally.server;

public class Game implements Runnable {

    private static final long FRAME_TIME_MS = 10_000; // 100 FPS

    private boolean running = true;

    @Override
    public void run() {
        while (running) {
            long startTime = System.currentTimeMillis();

            // TODO: Game logic

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
