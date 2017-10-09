package kmitl.armhansa.game.mygame.model;

import android.graphics.Bitmap;

public class Movie {
    private int number;
    private int name;
    private int price;
    private int time;
    private Bitmap poster;

    public Movie(int number, int name, int price, int time, Bitmap poster) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.time = time;
        this.poster = poster;
    }

    public void changePrice(int newPrice) {
        this.price = newPrice;
    }

    public int getNumber() {
        return number;
    }

    public int getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getTime() {
        return time;
    }

    public Bitmap getPoster() {
        return poster;
    }
}
