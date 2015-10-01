package edu.temple.mikem.checkerboardmaker;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GridSelector.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GridSelector#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridSelector extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int chosenValue;
    Spinner gridSizeSpinner;
    Button displayButton;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GridSelector.
     */
    // TODO: Rename and change types and number of parameters
    public static GridSelector newInstance(String param1, String param2) {
        GridSelector fragment = new GridSelector();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public GridSelector() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //initializing
        View v = inflater.inflate(R.layout.fragment_grid_selector, container, false);
        String[] gridSize = new String[] {"2", "4", "6", "8", "10"};

        // Create Spinner
        gridSizeSpinner = (Spinner)v.findViewById(R.id.grid_spinner);
        ArrayAdapter<String> gridAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item, gridSize);
        gridSizeSpinner.setAdapter(gridAdapter);

        // Create button
        displayButton = (Button) v.findViewById(R.id.display_button);
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenValue = (gridSizeSpinner.getSelectedItemPosition() + 1)* 2;
                mListener.onButtonPressed(chosenValue);
            }
        });

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onButtonPressed(int colValue);
    }

}
