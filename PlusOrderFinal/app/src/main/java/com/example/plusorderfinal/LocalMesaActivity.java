package com.example.plusorderfinal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class LocalMesaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_mesa);


        Button btnCatalogo = findViewById(R.id.btnCatalogo);
        btnCatalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalMesaActivity.this, CatalogoActivity.class);
                startActivity(intent);
            }
        });

        Button btnWhatsApp = findViewById(R.id.btnWhatsApp);
        btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "3128182195";
                String message = "Buenas, ¿Hay mesas disponibles para reservar?";

                Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
                whatsappIntent.setData(Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + Uri.encode(message)));

                // Verificar si WhatsApp está instalado
                if (whatsappIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(whatsappIntent);
                } else {
                    Toast.makeText(LocalMesaActivity.this, "WhatsApp no está instalado en tu dispositivo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnIrReclamo = findViewById(R.id.btnIrReclamo);
        btnIrReclamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "3128182195";
                String message = "Buenas, Solo voy a ir a reclamar mi pedido";

                Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
                whatsappIntent.setData(Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + Uri.encode(message)));

                // Verificar si WhatsApp está instalado
                if (whatsappIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(whatsappIntent);
                } else {
                    Toast.makeText(LocalMesaActivity.this, "WhatsApp no está instalado en tu dispositivo", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}