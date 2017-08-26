package kmitl.lab03.armhansa58070159.simplemydot;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import kmitl.lab03.armhansa58070159.simplemydot.model.Dot;
import kmitl.lab03.armhansa58070159.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity
implements Dot.DotChangedListener{

    private DotView dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);
    }

    public void onClickRandomDot(View view) {
        // Random a Dot
        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        new Dot(this, centerX, centerY);
    }

    public void onClickResetDot(View view) {
        dotView.clearDot();
        dotView.invalidate();
    }

    @Override
    public void onDotChanged(Dot dot) {
        // Draw dot model to view
        dotView.setDot(dot);
        dotView.invalidate();
    }

}
