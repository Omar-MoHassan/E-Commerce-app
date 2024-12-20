package com.example.project.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.R;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    final String USERS = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
        Button button1;
        TextView signupRedirect;
        EditText username = findViewById(R.id.login_username);
        EditText password = findViewById(R.id.login_password);


        button1 = findViewById(R.id.login_button);
        signupRedirect = findViewById(R.id.signupRedirectText);
        Gson gson = new Gson();



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userTxt = username.getText().toString().trim();
                String passTxt = password.getText().toString().trim();
                if (userTxt.isEmpty() && passTxt.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "ENTER USERNAME AND PASSWORD!!", Toast.LENGTH_LONG).show();
                } else if (sharedPreferences.getAll().containsKey(USERS)) {
                    String jsonText = sharedPreferences.getString(USERS, null);
                    HashMap<String, Object> person = gson.fromJson(jsonText, HashMap.class);
                    if (userTxt.equals(person.get("username").toString().trim()) && passTxt.equals(person.get("password").toString().trim())) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, "Wrong Username Or Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong Username Or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        signupRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this , SignupActivity.class);
                startActivity(intent);

            }
        });


    }



}