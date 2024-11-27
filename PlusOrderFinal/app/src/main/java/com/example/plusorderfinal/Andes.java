package com.example.plusorderfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class Andes extends AppCompatActivity {

    private int totalAndesPrecio = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_andes);

        NumberPicker numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(100);
        RadioGroup meatRadioGroup = findViewById(R.id.meatRadioGroup);
        RadioGroup friesRadioGroup = findViewById(R.id.friesRadioGroup);

        meatRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                Toast.makeText(Andes.this, "Seleccionado: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        friesRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                Toast.makeText(Andes.this, "Seleccionado: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        Button btnAtras = findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Andes.this, CatalogoActivity.class);
                startActivity(intent);
            }
        });

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup meatRadioGroup = findViewById(R.id.meatRadioGroup);
                RadioGroup friesRadioGroup = findViewById(R.id.friesRadioGroup);
                NumberPicker numberPicker = findViewById(R.id.numberPicker);

                int selectedMeatId = meatRadioGroup.getCheckedRadioButtonId();
                if (selectedMeatId == -1) {
                    Toast.makeText(Andes.this, "Por favor, selecciona un tipo de carne", Toast.LENGTH_SHORT).show();
                    return;
                }

                int selectedFriesId = friesRadioGroup.getCheckedRadioButtonId();
                if (selectedFriesId == -1) {
                    Toast.makeText(Andes.this, "Por favor, selecciona un tipo de papas", Toast.LENGTH_SHORT).show();
                    return;
                }

                int selectedQuantity = numberPicker.getValue();
                if (selectedQuantity == 0) {
                    Toast.makeText(Andes.this, "Por favor, selecciona una cantidad mayor que 0", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton selectedMeatRadioButton = findViewById(selectedMeatId);
                RadioButton selectedFriesRadioButton = findViewById(selectedFriesId);
                String selectedMeat = selectedMeatRadioButton.getText().toString();
                String selectedFries = selectedFriesRadioButton.getText().toString();

                totalAndesPrecio = selectedQuantity * 31900;

                SharedPreferences prefs = getSharedPreferences("selected_items", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                String itemText = "Andes - Carne: " + selectedMeat + ", Papas: " + selectedFries + ", Cantidad: " + selectedQuantity + ", Precio: " + (selectedQuantity*31900) + " COP";

                Set<String> items = prefs.getStringSet("items", new HashSet<String>());
                items.add(itemText);

                editor.putStringSet("items", items);
                editor.apply();

                Intent intent = new Intent(Andes.this, Lista.class);
                intent.putExtra("totalAndesPrecio", totalAndesPrecio); // Pasar solo el precio total de Andes
                startActivity(intent);
            }
        });
    }
}
