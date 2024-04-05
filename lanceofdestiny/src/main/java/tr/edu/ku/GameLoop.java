package tr.edu.ku;

public class GameLoop implements Runnable {

    private GamePanel gamePanel;

    private boolean running;
    private final double updateRate = 1.0d/60.0d;
    private final double frameTime = 1.0d/60.0d;

    private long nextStatTime;
    private int fps, ups;


    public GameLoop(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    @Override
    public void run() {
        
        running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();
        nextStatTime = System.currentTimeMillis() + 1000;

        while(running) {
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;

            while(accumulator > updateRate) {
                update();
                accumulator -= updateRate;
            }

            render();
            printStats();

             // Delay to limit FPS
            long sleepTime = (long) ((frameTime - lastRenderTimeInSeconds) * 1000);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    private void printStats() {
        if(System.currentTimeMillis() > nextStatTime) {
            System.out.println(String.format("FPS: %d, UPS: %d", fps, ups));
            fps = 0;
            ups = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }


    private void update() {
        gamePanel.updateGameState();
        ups++;
    }


    private void render() {
        gamePanel.repaint();
        fps++;
    }

    
}
