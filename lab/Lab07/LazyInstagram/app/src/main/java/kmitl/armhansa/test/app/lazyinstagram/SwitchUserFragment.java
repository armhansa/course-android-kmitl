package kmitl.armhansa.test.app.lazyinstagram;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SwitchUserFragment extends Fragment {


    public SwitchUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_switch_user, container, false);




        return rootView;
    }

    public static SwitchUserFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SwitchUserFragment fragment = new SwitchUserFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
