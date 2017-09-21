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
import android.util.Log;
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
implements ListDot.DotsChangedListener, DotView.OnDotViewPressedListener{

    private DotView dotView;
    private ListDot dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dots = new ListDot();

        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressedListener(this);
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
        dots.addDot(new Dot(centerX, centerY, 40));
        dotView.invalidate();
    }

    public void onClickResetDot(View view) {
        dotView.clearDot();
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

    @Override
    public void onDotsChangedListener(ListDot dots) {
        dotView.invalidate();
    }

    @Override
    public void onDotViewPressed(int touchX, int touchY) {
        int index = dots.findDotPressed(touchX, touchY);
        Log.wtf("", "Up");
        if(index == -1) {
            dots.addDot(new Dot(touchX, touchY, 50));
        } else {
            dots.removeDot(index);
        }
        dotView.invalidate();
    }

    @Override
    public void onDotViewLongPressed(int touchX, int touchY) {
        int index = dots.findDotPressed(touchX, touchY);

        if(index != -1) {
            dots.getDots().get(index).changeColor();
        } else {
            Log.wtf("", "LongPress");
        }
        dotView.invalidate();
    }
}
