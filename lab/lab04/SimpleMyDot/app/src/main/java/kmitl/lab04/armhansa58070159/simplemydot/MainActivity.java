package kmitl.lab04.armhansa58070159.simplemydot;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import kmitl.lab04.armhansa58070159.simplemydot.model.Dot;
import kmitl.lab04.armhansa58070159.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity
implements Dot.DotChangedListener{

    private DotView dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY()-200;

        if(event.getAction() == MotionEvent.ACTION_DOWN)
            new Dot(this, touchX, touchY, 50);

        return super.onTouchEvent(event);
    }



    public void onClickRandomDot(View view) {
        // Random a Dot
        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        new Dot(this, centerX, centerY, 30);
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






    public void onClickShare(View view) throws IOException {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            String imgFile = Environment.getExternalStorageDirectory().toString() + "/MyDot.png";

            View rootView = getWindow().getDecorView().getRootView();
            rootView.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
            rootView.setDrawingCacheEnabled(false);

            File imageFile = new File(imgFile);

            try (FileOutputStream outputStream = new FileOutputStream(imageFile)) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
            }

            Intent intent;
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/png");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imageFile));
            startActivity(Intent.createChooser(intent, "Share to"));
        }
    }


}
