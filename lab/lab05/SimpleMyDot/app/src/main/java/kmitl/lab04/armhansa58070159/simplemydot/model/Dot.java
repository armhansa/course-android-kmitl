package kmitl.lab04.armhansa58070159.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

public class Dot implements Parcelable{
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

    protected Dot(Parcel in) {
        centerX = in.readFloat();
        centerY = in.readFloat();
        radius = in.readInt();
        red = in.readInt();
        green = in.readInt();
        blue = in.readInt();
    }

    public static final Creator<Dot> CREATOR = new Creator<Dot>() {
        @Override
        public Dot createFromParcel(Parcel in) {
            return new Dot(in);
        }

        @Override
        public Dot[] newArray(int size) {
            return new Dot[size];
        }
    };

    public void changeColor() {
        Random random = new Random();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(centerX);
        parcel.writeFloat(centerY);
        parcel.writeInt(radius);
        parcel.writeInt(red);
        parcel.writeInt(green);
        parcel.writeInt(blue);
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}
