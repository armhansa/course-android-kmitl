package kmitl.armhansa.game.mygame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by armha on 10/1/2017.
 */

public class RectPlayer implements GameObject {

    private Rect rectagle;
    private int color;

    public RectPlayer(Rect rectagle, int color) {
        this.rectagle = rectagle;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectagle, paint);

    }

    @Override
    public void update() {

    }

    public void update(Point point) {
        // l, t, r, b
        rectagle.set(point.x - rectagle.width()/2, point.y - rectagle.height()
                , point.x + rectagle.width()/2, point.y + rectagle.height());
    }
}
