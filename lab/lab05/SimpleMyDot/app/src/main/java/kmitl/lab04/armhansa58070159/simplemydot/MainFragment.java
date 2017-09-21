package kmitl.lab04.armhansa58070159.simplemydot;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import kmitl.lab04.armhansa58070159.simplemydot.model.Dot;
import kmitl.lab04.armhansa58070159.simplemydot.model.ListDot;
import kmitl.lab04.armhansa58070159.simplemydot.view.DotView;

public class MainFragment extends Fragment
implements ListDot.DotsChangedListener, DotView.OnDotViewPressedListener{

    private DotView dotView;
    private ListDot dots;

    EditColorFragment editColorFragment;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Button btnRandom = rootView.findViewById(R.id.random);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRandomDot();
            }
        });
        Button btnReset = rootView.findViewById(R.id.reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickResetDot();
            }
        });
        Button btnShare = rootView.findViewById(R.id.share);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickShare();
            }
        });

        dots = new ListDot();

        dotView = (DotView) rootView.findViewById(R.id.dotView);
        dotView.setOnDotViewPressedListener(this);
        dotView.setDots(dots);

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        return rootView;
    }

    public void onClickRandomDot() {
        // Random a Dot
        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        dots.addDot(new Dot(centerX, centerY, 40));
        dotView.invalidate();
    }

    public void onClickResetDot() {
        dotView.clearDot();
        dotView.invalidate();
    }

    public void onClickShare() {
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

    @Override
    public void onDotsChangedListener(ListDot dots) {
        dotView.invalidate();
    }

    @Override
    public void onDotViewPressed(int touchX, int touchY) {
        int index = dots.findDotPressed(touchX, touchY);
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
            FragmentManager fragmentManager = getFragmentManager();

            if(editColorFragment != null)
                fragmentManager.beginTransaction()
                        .remove(editColorFragment)
                        .commit();
            editColorFragment = new EditColorFragment().newInstance(dots.getDots().get(index));
            fragmentManager.beginTransaction()
                    .add(R.id.mainFragment, editColorFragment)
                    .commit();
        }
        else {

        }
    }

}
