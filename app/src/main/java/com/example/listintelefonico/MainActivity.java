package com.example.listintelefonico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView listContactos;
    private Button bagregar;
    private listaContactos contactList = new listaContactos();
    private ArrayAdapter<Contacto> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listContactos = findViewById(R.id.lvListaContactos);
        bagregar = findViewById(R.id.bAgregar);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactList.getContactsSortedByName());
        listContactos.setAdapter(adapter);
    }

    public void onAgregar(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivityForResult(intent, 1);
    }
}