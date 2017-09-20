package kmitl.lab04.armhansa58070159.simplemydot.model;

import java.util.Random;

public class Dot {
    private float centerX;
    private float centerY;
    private int radius;

    private int red;
    private int green;
    private int blue;

    public Dot(float centerX, float centerY, int radius) {
        Random random = new Random();
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.red = 100+random.nextInt(155);
        this.green = 100+random.nextInt(155);
        this.blue = 100+random.nextInt(155);

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
