package kmitl.lab04.armhansa58070159.simplemydot.model;

import java.util.ArrayList;

public class ListDot {
    public interface OnDotsChangedListener {
        void onDotsChangedListener(ListDot dots);
    }

    private OnDotsChangedListener onDotsChangedListener;
    private ArrayList<Dot> dots;

    public ListDot() { dots = new ArrayList<>(); }

    public ListDot(OnDotsChangedListener dotsChangedListener) {
        dots = new ArrayList<>();
        this.onDotsChangedListener = dotsChangedListener;
    }

    public ArrayList<Dot> getDots() {
        return dots;
    }

    public void addDot() {
        this.dots.add(new Dot())
    }

    public int findDotPressed(int centerX, int centerY) {
        double distanceX;
        double distanceY;
        for (int i=dots.size()-1; i>=0; i--) {
            distanceX = Math.pow(dots.get(i).getCenterX()-centerX, 2);
            distanceY = Math.pow(dots.get(i).getCenterY()-centerY, 2);
            if(Math.sqrt(distanceX+distanceY) <= dots.get(i).getRadius()) {
                return i;
            }
        }
        return -1;
    }

    public void removeDot(int index) {
        dots.remove(index);
    }
}
