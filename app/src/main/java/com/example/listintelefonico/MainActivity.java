package com.example.listintelefonico;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ADD_CONTACT = 1;
    private static final int REQUEST_CODE_EDIT_CONTACT = 2;

    private ListView contactListView;
    private ArrayList<Contacto> contactList;
    private ArrayAdapter<Contacto> contactAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar la lista de contactos vacía
        contactList = new ArrayList<>();

        // añadimos contactos ala lista para comprobar que funciona el list view
        contactList.add(new Contacto("Jessica", "Lopez", "999999999"));
        contactList.add(new Contacto("Sonia", "Fernandez", "999999999"));
        contactList.add(new Contacto("Alba", "Brox", "999999999"));

        // Obtener referencia al ListView
        contactListView = findViewById(R.id.lvListacontactos);

        // Inicializar el adaptador de la lista de contactos y asignarlo al ListView
        contactAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, android.R.id.text1, contactList);
        contactListView.setAdapter(contactAdapter);

        // Agregar listener para el clic en un elemento de la lista de contactos
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contacto contacto = contactAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                intent.putExtra("contact", contacto);
                startActivity(intent);
            }
        });

        // Mostrar mensaje de lista vacía en caso de estarlo
        TextView textView = findViewById(R.id.tvListVacia);
        if (contactList.isEmpty()) {
            textView.setVisibility(View.VISIBLE);
            contactListView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.GONE);
            contactListView.setVisibility(View.VISIBLE);
        }
    }



    public void onAgregar(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}
