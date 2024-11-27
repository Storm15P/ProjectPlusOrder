package com.example.plusorderfinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class verUsuariosAdmin extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private ListView ListUsers;
    private ArrayAdapter<User> userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_usuarios_admin);

        databaseHelper = new DatabaseHelper(this);

        ListUsers = findViewById(R.id.ListUsers);

        //Ver Lista de usuarios

        List<User> userList = databaseHelper.getAllUsers();
        userAdapter = new ArrayAdapter<>(this, R.layout.simple_list_item, R.id.text1, userList);
        ListUsers.setAdapter(userAdapter);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(verUsuariosAdmin.this, InicioAdmin.class);
                startActivity(intent);
            }
        });
    }
}