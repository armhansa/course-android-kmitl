package kmitl.lab04.armhansa58070159.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import kmitl.lab04.armhansa58070159.simplemydot.model.Dot;
import kmitl.lab04.armhansa58070159.simplemydot.model.ListDot;

public class DotView extends View {

    private Paint paint;

    private ListDot dots;

    public void setDot(Dot dot) {
        dots.getDots().add(dot);
    }

    public void clearDot() {
        dots.getDots().clear();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Dot i: dots.getDots()) {
            paint.setColor(Color.rgb(i.getRed(), i.getGreen(), i.getBlue()));
            canvas.drawCircle(i.getCenterX(), i.getCenterY(), i.getRadius(), paint);
        }
    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
        dots = new ListDot();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        dots = new ListDot();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        dots = new ListDot();
    }

}
