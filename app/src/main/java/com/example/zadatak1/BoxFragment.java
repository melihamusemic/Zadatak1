package com.example.zadatak1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;

public class BoxFragment extends Fragment {
    private ViewGroup root = null;
    private static final String ARG_PARAM1 = "NUMBER";
    private String numberText;

    public BoxFragment() {
        // Required empty public constructor
    }

    public static BoxFragment newInstance(String numberText) {
        BoxFragment fragment = new BoxFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, numberText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            numberText = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_box, container, false);
        ((TextView) root.findViewById(R.id.box_textview)).setText(numberText);
        // Inflate the layout for this fragment
//        MaterialButton button = (MaterialButton) root.findViewById(R.id.back_button);
//        button.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//
//                FragmentManager fm = getActivity().getSupportFragmentManager();
//                fm.popBackStack();
//            }
//        });
        return root;
    }
}