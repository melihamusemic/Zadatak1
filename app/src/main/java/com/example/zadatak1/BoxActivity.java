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
    }

    public void onBoxClick(View v) {
        final String numberText = ((TextView) v).getText().toString();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BoxFragment boxFragment = BoxFragment.newInstance(numberText);
        ft.addToBackStack("Fragment").replace(R.id.activity_container, boxFragment);
        ft.commit();
    }

    public void goBack(View v) {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}