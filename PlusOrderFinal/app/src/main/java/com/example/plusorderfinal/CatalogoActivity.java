package com.example.plusorderfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CatalogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        Button btnChamaster = findViewById(R.id.btnChamaster);
        btnChamaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatalogoActivity.this, Chamaster.class);
                startActivity(intent);
            }
        });
        Button btnTamesis = findViewById(R.id.btnTamesis);
        btnTamesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatalogoActivity.this, Tamesis.class);
                startActivity(intent);
            }
        });
        Button btnJardin = findViewById(R.id.btnJardin);
        btnJardin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatalogoActivity.this, Jardin.class);
                startActivity(intent);
            }
        });
        Button btnAndes = findViewById(R.id.btnAndes);
        btnAndes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatalogoActivity.this, Andes.class);
                startActivity(intent);
            }
        });
        Button btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatalogoActivity.this, PedidosActivity.class);
                startActivity(intent);
            }
        });
    }
}