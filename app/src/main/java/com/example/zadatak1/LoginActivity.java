package com.example.zadatak1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((MaterialButton) findViewById(R.id.back_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void logIn(View button) {
        if (!areCredentialsValid())
            return;
        Intent boxActivity = new Intent(LoginActivity.this, BoxActivity.class);
        startActivity(boxActivity);
    }

    private boolean areCredentialsValid() {
        EditText usernameField = ((EditText) findViewById(R.id.username));
        EditText passwordField = ((EditText) findViewById(R.id.password));

        CharSequence usernameText = usernameField.getText();
        CharSequence password = passwordField.getText();

        if (!Utils.isUsernameValid(usernameText)) {
            usernameField.setError("Username is required!");
            return false;
        }
        if (!Utils.isPasswordValid((password))) {
            passwordField.setError("Password is required and must be at least 6 characters long!");
            return false;
        }
        return true;
    }
}
