package com.example.project.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.R;
import com.google.gson.Gson;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    final String USERS = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        TextView loginRedirect = findViewById(R.id.loginRedirectText);;
        EditText username = findViewById(R.id.signup_username);
        EditText password = findViewById(R.id.signup_password);
        EditText name = findViewById(R.id.signup_name);
        EditText email = findViewById(R.id.signup_email);
        Button signupBtn = findViewById(R.id.signup_button);
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty() || name.getText().toString().isEmpty() || email.getText().toString().isEmpty()){
                    Toast.makeText(SignupActivity.this, "ENTER DATA!", Toast.LENGTH_SHORT).show();
                }else{
                    HashMap<String, Object> person = new HashMap<String, Object>();
                    person.put("name", name.getText().toString().trim());
                    person.put("email", email.getText().toString().trim());
                    person.put("username", username.getText().toString().trim());
                    person.put("password", password.getText().toString().trim());
                    Gson gson = new Gson();
                    String jsonList = gson.toJson(person);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(USERS, jsonList);
                    editor.apply();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });



        loginRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this , LoginActivity.class);
            startActivity(intent);

        });

    }
}