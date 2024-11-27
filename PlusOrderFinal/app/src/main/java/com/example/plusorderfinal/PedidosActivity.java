package com.example.plusorderfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PedidosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        Button btnLocal = findViewById(R.id.btnlocal);
        btnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PedidosActivity.this, LocalMesaActivity.class);
                startActivity(intent);
            }
        });

        Button btnAtras1 = findViewById(R.id.btnAtras1);
        btnAtras1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PedidosActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }
}