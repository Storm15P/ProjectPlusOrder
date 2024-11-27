package com.example.plusorderfinal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME");

        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        String welcomeMessage = "Bienvenido " + username + " a PlusOrder";
        welcomeTextView.setText(welcomeMessage);

        Button btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //welcome
        Button btnPedido = findViewById(R.id.btnPedido);
        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, PedidosActivity.class);
                startActivity(intent);
            }
        });


    }
}
