package kmitl.armhansa.game.mygame;

import android.graphics.Canvas;
import android.provider.Settings;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    public static final int MAX_FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    private int i;
    public static Canvas canvas;

    public void setRunning(boolean running) {
        this.running = running;
    }

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
        i = 0;
    }

    @Override
    public void run() {
        super.run();
        long startTime;
        long timeMs = 1000/MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while(running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if(canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception ex) { ex.printStackTrace(); }
                }
            }
            timeMs = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMs;
            try {
                if(waitTime > 0)
                    this.sleep(waitTime);
            } catch (Exception ex) { ex.printStackTrace(); }

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if(frameCount == MAX_FPS) {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }
    }
}
