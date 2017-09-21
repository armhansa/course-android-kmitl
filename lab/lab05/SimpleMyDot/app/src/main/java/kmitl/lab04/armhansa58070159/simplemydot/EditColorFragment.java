package kmitl.lab04.armhansa58070159.simplemydot;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kmitl.lab04.armhansa58070159.simplemydot.model.Dot;
import kmitl.lab04.armhansa58070159.simplemydot.view.ViewColor;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditColorFragment extends Fragment {

    ViewColor viewColor;

    public EditColorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_color, container, false);

        Dot dot = getArguments().getParcelable("dot");
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

}
