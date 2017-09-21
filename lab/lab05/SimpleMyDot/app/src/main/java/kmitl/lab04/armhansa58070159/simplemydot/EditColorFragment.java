package kmitl.lab04.armhansa58070159.simplemydot;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import kmitl.lab04.armhansa58070159.simplemydot.model.Dot;
import kmitl.lab04.armhansa58070159.simplemydot.view.ViewColor;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditColorFragment extends Fragment
implements SeekBar.OnSeekBarChangeListener {

    public interface OnDotChangedListener {
        void onDotChangedListener(Dot dot);
    }

    OnDotChangedListener onDotChangedListener;

    public void setOnDotChangedListener(
            OnDotChangedListener onDotChangedListener) {
        this.onDotChangedListener = onDotChangedListener;
    }

    ViewColor viewColor;

    SeekBar seekBarRed;
    SeekBar seekBarGreen;
    SeekBar seekBarBlue;

    Dot dot;

    public EditColorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_color, container, false);

        seekBarRed = rootView.findViewById(R.id.seekBar6);
        seekBarRed.setOnSeekBarChangeListener(this);
        seekBarGreen = rootView.findViewById(R.id.seekBar7);
        seekBarGreen.setOnSeekBarChangeListener(this);
        seekBarBlue = rootView.findViewById(R.id.seekBar5);
        seekBarBlue.setOnSeekBarChangeListener(this);

        dot = getArguments().getParcelable("dot");
        viewColor = rootView.findViewById(R.id.viewColor);
        viewColor.setDot(dot);
        // dot.changeColor();

        return rootView;
    }


    public static EditColorFragment newInstance(Dot dot) {
        Bundle args = new Bundle();

        EditColorFragment fragment = new EditColorFragment();
        args.putParcelable("dot", dot);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        if(seekBar == seekBarRed) {
            Log.wtf("", "R1"+i);
            dot.setRed(i);

        } else if(seekBar == seekBarGreen) {
            Log.wtf("", "G1"+i);
            dot.setGreen(i);
        } else {
            Log.wtf("", "B1"+i);
            dot.setBlue(i);
        }
        viewColor.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        Log.wtf("", "2");
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Log.wtf("", "3");
    }
}
