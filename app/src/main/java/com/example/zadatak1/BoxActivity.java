package com.example.zadatak1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class BoxActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box);
        initBoxes();
    }

    private void initBoxes() {
        LinearLayout container = findViewById(R.id.container);
        String numberText = "";
        for (int i = 0; i < 4; ++i) {
            LayoutInflater inflater = (LayoutInflater) BoxActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TextView box = (TextView) inflater.inflate(R.layout.box_item, container, false);
            numberText = String.valueOf(i + 1);
            box.setText(numberText);
            String finalNumberText = numberText;
            box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    BoxFragment boxFragment = BoxFragment.newInstance(finalNumberText);
                    ft.addToBackStack("Fragment").replace(R.id.activity_container, boxFragment);
                    ft.commit();
                }
            });
            container.addView(box);
        }
    }

    public void goBack(View v) {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}