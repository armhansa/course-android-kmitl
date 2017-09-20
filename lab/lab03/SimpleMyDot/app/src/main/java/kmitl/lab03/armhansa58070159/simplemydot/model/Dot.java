package kmitl.lab03.armhansa58070159.simplemydot.model;

import android.graphics.Color;

import java.util.Random;

public class Dot {


    public interface DotChangedListener {
        void onDotChanged(Dot dot);
    }

    private DotChangedListener dotChangedListener;

    private float centerX;
    private float centerY;
    private int radius;

    private int red;
    private int green;
    private int blue;

    public Dot(DotChangedListener dotChangedListener, float centerX, float centerY, int radius) {
        this.dotChangedListener = dotChangedListener;
        Random random = new Random();
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.red = random.nextInt(255);
        this.green = random.nextInt(255);
        this.blue = random.nextInt(255);

        this.dotChangedListener.onDotChanged(this);
    }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
}
