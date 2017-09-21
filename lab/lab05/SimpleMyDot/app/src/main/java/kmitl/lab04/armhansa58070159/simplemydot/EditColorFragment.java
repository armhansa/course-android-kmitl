package kmitl.lab04.armhansa58070159.simplemydot;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        void onDotChangedListener(Dot dot, int index);
    }

    OnDotChangedListener onDotChangedListener;

    public void setOnDotChangedListener(
            OnDotChangedListener onDotChangedListener) {
        this.onDotChangedListener = onDotChangedListener;
    }

    ViewColor viewColor;

    private Button exitEdit;
    private SeekBar seekBarRed;
    private SeekBar seekBarGreen;
    private SeekBar seekBarBlue;
    private SeekBar seekBarSize;

    private Dot dot;
    private int index;

    public EditColorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_color, container, false);

        dot = getArguments().getParcelable("dot");

        seekBarRed = rootView.findViewById(R.id.seekBar6);
        seekBarRed.setProgress(dot.getRed());
        seekBarRed.setOnSeekBarChangeListener(this);
        seekBarGreen = rootView.findViewById(R.id.seekBar7);
        seekBarGreen.setProgress(dot.getGreen());
        seekBarGreen.setOnSeekBarChangeListener(this);
        seekBarBlue = rootView.findViewById(R.id.seekBar5);
        seekBarBlue.setProgress(dot.getBlue());
        seekBarBlue.setOnSeekBarChangeListener(this);
        seekBarSize = rootView.findViewById(R.id.seekBar);
        seekBarSize.setProgress(dot.getRadius());
        seekBarSize.setOnSeekBarChangeListener(this);
        exitEdit = rootView.findViewById(R.id.exitEdit);
        exitEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickExit();
            }
        });


        index = getArguments().getInt("index");
        viewColor = rootView.findViewById(R.id.viewColor);
        viewColor.setDot(dot);


        return rootView;
    }

    public void onClickExit() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .remove(this)
                .commit();
    }

    public static EditColorFragment newInstance(Dot dot, int index) {
        Bundle args = new Bundle();

        EditColorFragment fragment = new EditColorFragment();
        args.putParcelable("dot", dot);
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if(seekBar == seekBarSize) {
            this.dot.setSize(i);
        } else if(seekBar == seekBarRed) {
            this.dot.setRed(i);
        } else if(seekBar == seekBarGreen) {
            this.dot.setGreen(i);
        } else {
            this.dot.setBlue(i);
        }
        viewColor.invalidate();
        onDotChangedListener.onDotChangedListener(this.dot, index);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { }
}
