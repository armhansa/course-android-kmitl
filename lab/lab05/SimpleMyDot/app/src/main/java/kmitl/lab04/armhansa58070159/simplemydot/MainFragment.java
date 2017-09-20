package kmitl.lab04.armhansa58070159.simplemydot;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import kmitl.lab04.armhansa58070159.simplemydot.model.Dot;
import kmitl.lab04.armhansa58070159.simplemydot.model.ListDot;
import kmitl.lab04.armhansa58070159.simplemydot.view.DotView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment
implements Dot.DotChangedListener{

    private DotView dotView;
    private ListDot dots;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        dots = new ListDot();

        dotView = (DotView) rootView.findViewById(R.id.dotView);
        dotView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int touchX = (int) event.getX();
                int touchY = (int) event.getY();
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN :
                        int index = dots.findDotPressed(touchX, touchY);
                        if(index == -1) {
                            new Dot((Dot.DotChangedListener) getActivity(), touchX, touchY, 50);
                        } else {
                            dots.removeDot(index);
                            dotView.invalidate();
                        }
                }

                return false;
            }
        });

        dotView.setDots(dots);

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        return rootView;
    }

    public void onClickRandomDot(View view) {
        // Random a Dot
        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        new Dot(centerX, centerY, 40);
    }

    public void onClickResetDot(View view) {
        dotView.clearDot();
        dotView.invalidate();
    }

    @Override
    public void onDotsChanged(Dot dot) {
        // Draw dot model to view
        dotView.setDot(dot);
        dotView.invalidate();
    }


    public void onClickShare(View view) {
        View rootView = getActivity().getWindow().getDecorView().getRootView();
        rootView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);

        Intent share = new Intent(Intent.ACTION_SEND);

        share.setType("image/jpeg");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "Title", null);
        Uri imageUri = Uri.parse(path);
        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(share, "Select"));
    }


}
