package com.example.plusorderfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clearSharedPreferences();

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        Button btnIniciar = findViewById(R.id.btnIniciar);
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Por favor, ingresa tu usuario y contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (username.equals("admin") && password.equals("admin123")) {
                    Intent intent = new Intent(MainActivity.this, InicioAdmin.class);
                    startActivity(intent);
                    finish();
                    return;
                }

                if (databaseHelper.authenticateUser(username, password)) {
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    intent.putExtra("USERNAME", username);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Ingreso correcto", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearSharedPreferences() {
        SharedPreferences prefs = getSharedPreferences("selected_items", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }
}
