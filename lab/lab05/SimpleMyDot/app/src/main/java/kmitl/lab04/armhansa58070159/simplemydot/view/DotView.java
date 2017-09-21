package kmitl.lab04.armhansa58070159.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import kmitl.lab04.armhansa58070159.simplemydot.model.Dot;
import kmitl.lab04.armhansa58070159.simplemydot.model.ListDot;

public class
DotView extends View {

    public interface OnDotViewPressedListener {
        void onDotViewPressed(int touchX, int touchY);
        void onDotViewLongPressed(int touchX, int touchY);
    }

    OnDotViewPressedListener onDotViewPressedListener;

    public void setOnDotViewPressedListener(
            OnDotViewPressedListener onDotViewPressedListener) {
        this.onDotViewPressedListener = onDotViewPressedListener;
    }

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
        if(dots != null) {
            for (Dot i : dots.getDots()) {
                paint.setColor(Color.rgb(i.getRed(), i.getGreen(), i.getBlue()));
                canvas.drawCircle(i.getCenterX(), i.getCenterY(), i.getRadius(), paint);
            }
        }
    }

    public GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
        public void onLongPress(MotionEvent e) {
            DotView.this.onDotViewPressedListener.onDotViewLongPressed((int) e.getX(), (int) e.getY());
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            DotView.this.onDotViewPressedListener.onDotViewPressed((int) e.getX(), (int) e.getY());
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    });

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    public void setDots(ListDot dots) {
        this.dots = dots;
    }

}
