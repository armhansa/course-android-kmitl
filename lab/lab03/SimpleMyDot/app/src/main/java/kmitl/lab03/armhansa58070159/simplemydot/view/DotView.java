package kmitl.lab03.armhansa58070159.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kmitl.lab03.armhansa58070159.simplemydot.model.Dot;

public class DotView extends View {

    private Paint paint;
    private List<Dot> dot = new ArrayList<>();
    private Random random = new Random();

    public void setDot(Dot dot) {
        this.dot.add(dot);
    }

    public void clearDot() {
        dot.clear();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0; i<dot.size(); i++) {
            paint.setColor(Color.rgb(dot.get(i).getRed(), dot.get(i).getGreen(), dot.get(i).getBlue()));
            canvas.drawCircle(dot.get(i).getCenterX(),
                    dot.get(i).getCenterY(),
                    dot.get(i).getRadius(),
            paint);
        }
//        if(dot != null) {
//            paint.setColor(Color.RED);
//            canvas.drawCircle(dot.getCenterX(),
//                    dot.getCenterY(),
//                    dot.getRadius(),
//                    paint);
//        }
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

}
