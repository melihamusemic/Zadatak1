package com.example.zadatak1.BoxHandling;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zadatak1.R;
import com.example.zadatak1.loginHandling.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class BoxActivity extends AppCompatActivity {
    MyRecyclerViewAdapter adapter;
    TableControllerBox tableControllerBox;
    int boxesCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box);
        tableControllerBox = new TableControllerBox(this);

        initBoxes();
    }

    public void onBoxClick(View v) {
        findViewById(R.id.new_box).setEnabled(false);
        final String numberText = ((TextView) v.findViewById(R.id.box_name)).getText().toString();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BoxFragment boxFragment = BoxFragment.newInstance(numberText);
        ft.addToBackStack("Fragment").add(R.id.activity_container, boxFragment);
        ft.commit();
    }

    public void goBack(View v) {
        if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();

        findViewById(R.id.new_box).setEnabled(true);

        RecyclerView recycleView = findViewById(R.id.main_container);
        for (int i = 0; i < recycleView.getChildCount(); ++i) {
            View child = recycleView.getChildAt(i);
            child.setVisibility(View.VISIBLE);
        }
    }

    public void addNewBox(View v) {
        v.setEnabled(false);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        NewBoxFragment newBoxFragment = new NewBoxFragment();
        ft.addToBackStack("New Fragment").replace(R.id.activity_container, newBoxFragment);
        ft.commit();
    }

    public void createBox(View v) {
        EditText boxNameField = (EditText) findViewById(R.id.name_input);
        EditText boxWidthField = (EditText) findViewById(R.id.width_input);
        EditText boxHeightField = (EditText) findViewById(R.id.height_input);
        EditText boxCountField = (EditText) findViewById(R.id.count_input);

        if (!areBoxInputsValid(boxNameField, boxWidthField, boxHeightField, boxCountField))
            return;

        String boxName = boxNameField.getText().toString();
        int boxWidth = Integer.parseInt(boxWidthField.getText().toString());
        int boxHeight = Integer.parseInt(boxHeightField.getText().toString());
        int boxCount = Integer.parseInt(boxCountField.getText().toString());

        Box boxToAdd = new Box(boxName, boxWidth, boxHeight, boxCount);
        tableControllerBox.create(boxToAdd);

        initBoxes();
        goBack(v);

        RecyclerView recycleView = findViewById(R.id.main_container);
        for (int i = 0; i < recycleView.getChildCount(); ++i) {
            View child = recycleView.getChildAt(i);
            child.setVisibility(View.VISIBLE);
        }
    }

    private void initBoxes() {
        ArrayList<Box> boxes;

        if (tableControllerBox.count() == 0) {
            Box box1 = new Box("Box A", 500, 350, 1);
            Box box2 = new Box("Box B", 600, 250, 2);
            Box box3 = new Box("Box C", 450, 400, 3);
            Box box4 = new Box("Box D", 400, 300, 4);
            Box boxArr[] = {box1, box2, box3, box4};

            for (Box box : boxArr) {
                tableControllerBox.create(box);
            }

            boxes = new ArrayList<Box>(Arrays.asList(boxArr));
        } else {
            boxes = (ArrayList<Box>) tableControllerBox.read();
        }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.main_container);
        recyclerView.setLayoutManager(new LinearLayoutManager(BoxActivity.this));
        adapter = new MyRecyclerViewAdapter(BoxActivity.this, boxes);

        recyclerView.setAdapter(adapter);

        boxesCount = tableControllerBox.count();
    }

    private boolean areBoxInputsValid(EditText name, EditText width, EditText height, EditText count) {
        if (!Utils.isNameValid(name.getText())) {
            name.setError("Input field should not be empty");
            return false;
        }
        if (!Utils.isNumberValid(width.getText())) {
            width.setError("Input field should not be empty");
            return false;
        }
        if (!Utils.isNumberValid(height.getText())) {
            height.setError("Input field should not be empty");
            return false;
        }
        if (!Utils.isNumberValid(count.getText())) {
            count.setError("Input field should not be empty");
            return false;
        }
        return true;
    }
}