package com.example.zadatak1.BoxHandling;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zadatak1.R;

public class NewBoxFragment extends Fragment {
    private ViewGroup root = null;

    public NewBoxFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_new_box, container, false);

        RecyclerView recycleView = getActivity().findViewById(R.id.main_container);

        for (int i = 0; i < recycleView.getChildCount(); ++i) {
            View child = recycleView.getChildAt(i);
            child.setVisibility(View.INVISIBLE);
        }

        return root;
    }
}