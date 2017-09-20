package kmitl.lab04.armhansa58070159.simplemydot;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import kmitl.lab04.armhansa58070159.simplemydot.model.Dot;
import kmitl.lab04.armhansa58070159.simplemydot.model.ListDot;
import kmitl.lab04.armhansa58070159.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity
implements Dot.DotChangedListener{

    private DotView dotView;
    private ListDot dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dots = new ListDot();

        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int touchX = (int) event.getX();
                int touchY = (int) event.getY();
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN :
                        int index = dots.findDotPressed(touchX, touchY);
                        if(index == -1) {
                            new Dot(MainActivity.this, touchX, touchY, 50);
                        } else {
                            dots.removeDot(index);
                            dotView.invalidate();
                        }
                }

                return false;
            }
        });

        dotView.setDots(dots);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }


    }




    public void onClickRandomDot(View view) {
        // Random a Dot
        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        new Dot(this, centerX, centerY, 40);
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


    public void onClickShare(View view) {
        View rootView = getWindow().getDecorView().getRootView();
        rootView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);

        Intent share = new Intent(Intent.ACTION_SEND);

        share.setType("image/jpeg");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
        Uri imageUri = Uri.parse(path);
        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(share, "Select"));
    }
}
