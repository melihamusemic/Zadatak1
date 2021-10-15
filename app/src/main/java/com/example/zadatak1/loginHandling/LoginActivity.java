package com.example.zadatak1.loginHandling;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zadatak1.BoxHandling.BoxActivity;
import com.example.zadatak1.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class LoginActivity extends AppCompatActivity implements JsonHttpTaskCompleteListener {
    SharedPreferences pref = null;
    SharedPreferences.Editor edit = null;
    EditText usernameField = null;
    EditText passwordField = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("Session Data", MODE_PRIVATE);
        edit = pref.edit();
        usernameField = ((EditText) findViewById(R.id.username));
        passwordField = ((EditText) findViewById(R.id.password));

        ((FloatingActionButton) findViewById(R.id.back_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void logIn(View button) {
        if (!areCredentialsValid())
            return;

        proccessLogin();
    }

    private boolean areCredentialsValid() {
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

    private void proccessLogin() {
        LoginRestClientUsage loginRestClientUsage = new LoginRestClientUsage(this);
        try {
            loginRestClientUsage.getUsers(); // proccess login when task is completed
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskCompleted(JSONArray users) {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        User loggedUser = findUser(users, username, password);

        if (loggedUser == null) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Username or password incorrect", Toast.LENGTH_LONG);
            toast.show();
        } else {
            Intent boxActivity = new Intent(LoginActivity.this, BoxActivity.class);
            startActivity(boxActivity);
            saveCredentials(username, password);
        }
    }

    private User findUser(JSONArray users, String username, String password) {
        User userToLogin = null;

        for (int i = 0; i < users.length(); i++) {
            User user = null;

            try {
                JSONObject u = (JSONObject) users.get(i);
                user = new User(u.getString("username"), u.getString("password"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            boolean userExists = user != null && user.getUsername().equals(username) && user.getPassword().equals(password);
            if (userExists) {
                userToLogin = user;
                break;
            }
        }

        return userToLogin;
    }

    private void saveCredentials(String username, String password) {
        edit.putString("Username", username);
        edit.putString("Password", password);
        edit.putLong("Timestamp", new Date().getTime());

        edit.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        long currentTimestamp = new Date().getTime();
        long savedTimestamp = pref.getLong("Timestamp", 0);

        // 15 min = 15 * 60 * 1000;
        long timeout = 15 * 60 * 1000;
        if (currentTimestamp - savedTimestamp > timeout) {
            // delete credentials
            edit.clear().apply();
            usernameField.setText("");
            passwordField.setText("");
        }
    }
}
