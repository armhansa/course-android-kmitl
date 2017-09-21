package kmitl.lab04.armhansa58070159.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import kmitl.lab04.armhansa58070159.simplemydot.model.Dot;

public class ViewColor extends View {

    private Paint paint;

    public void setDot(Dot dot) {
        this.dot = dot;
    }

    private Dot dot;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(dot != null) {
            paint.setColor(Color.rgb(dot.getRed(), dot.getGreen(), dot.getBlue()));
            canvas.drawCircle(0, 0, 40, paint);
        }
    }

    public ViewColor(Context context) {
        super(context);
        paint = new Paint();
    }

    public ViewColor(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public ViewColor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    public ViewColor(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        paint = new Paint();
    }
}
