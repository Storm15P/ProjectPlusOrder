package com.example.plusorderfinal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lista extends AppCompatActivity {

    private List<String> selectedItems = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private int total = 0;
    private int totaldiv = 0;
    private List<String> dataToSave = new ArrayList<>();  // Nuevo arreglo para guardar los datos
    private TextView textViewData;  // TextView para mostrar los datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        // Inicializar el adaptador y la lista
        ListView listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, R.layout.simple_list_item, selectedItems);
        listView.setAdapter(adapter);


        // Obtener los elementos seleccionados guardados en SharedPreferences
        SharedPreferences prefs = getSharedPreferences("selected_items", MODE_PRIVATE);
        Set<String> items = prefs.getStringSet("items", new HashSet<String>());
        selectedItems.addAll(items);
        adapter.notifyDataSetChanged();

        // Botón para agregar más elementos
        Button btnAddMore = findViewById(R.id.btnAddMore);
        btnAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lista.this, CatalogoActivity.class);
                startActivity(intent);
            }
        });

        // Botón para confirmar la orden
        Button btnUpload = findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToArray();
                showConfirmationDialog(totaldiv);
            }
        });

        Button btnAtras = findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        if (savedInstanceState == null) {
            calculateTotal();
        }

        // Actualizar el total cuando se recibe un nuevo elemento
        if (getIntent().hasExtra("totalTamesisPrecio")) {
            total += getIntent().getIntExtra("totalTamesisPrecio", 0);
        }
        if (getIntent().hasExtra("totalChamasterPrecio")) {
            total += getIntent().getIntExtra("totalChamasterPrecio", 0);
        }
        if (getIntent().hasExtra("totalJardinPrecio")) {
            total += getIntent().getIntExtra("totalJardinPrecio", 0);
        }
        if (getIntent().hasExtra("totalAndesPrecio")) {
            total += getIntent().getIntExtra("totalAndesPrecio", 0);
        }
    }

    // Método para calcular el total inicial
    private void calculateTotal() {
        total = 0;
        totaldiv = 0;
        for (String item : selectedItems) {
            String[] parts = item.split("Precio: ");
            total += (Integer.parseInt(parts[1].split(" COP")[0]));
            totaldiv = total;
        }
    }

    private void saveDataToArray() {
        dataToSave.clear();
        dataToSave.addAll(selectedItems);
    }


    private void showConfirmationDialog(int totaldiv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar Orden");
        builder.setMessage("El total a pagar es $" + totaldiv + " COP. ¿Desea enviar la orden?");
        builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Lista.this, "Orden enviada", Toast.LENGTH_SHORT).show();

                // Construir el mensaje a partir de los datos guardados en dataToSave
                StringBuilder messageBuilder = new StringBuilder();
                for (String data : dataToSave) {
                    messageBuilder.append(data).append("\n");
                }
                String message = messageBuilder.toString();

                String phoneNumber = "3057773652";
                Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
                whatsappIntent.setData(Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + Uri.encode(message)));

                // Verificar si WhatsApp está instalado
                if (whatsappIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(whatsappIntent);
                } else {
                    Toast.makeText(Lista.this, "WhatsApp no está instalado en tu dispositivo", Toast.LENGTH_SHORT).show();
                }
            }

        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showAlertDialog() {
        new AlertDialog.Builder(Lista.this)
                .setTitle("¿Desea regresar?")
                .setMessage("Su listado se eliminará")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences prefs = getSharedPreferences("selected_items", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.clear();
                        editor.apply();

                        Intent intent = new Intent(Lista.this, CatalogoActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}