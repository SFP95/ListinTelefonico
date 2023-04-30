package com.example.listintelefonico;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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
                startActivityForResult(intent, REQUEST_CODE_EDIT_CONTACT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Si el resultado es de añadir un nuevo contacto
        if (requestCode == REQUEST_CODE_ADD_CONTACT && resultCode == RESULT_OK) {
            String firstName = data.getStringExtra("firstName");
            String lastName = data.getStringExtra("lastName");
            String phone = data.getStringExtra("phone");
            Contacto contacto = new Contacto(firstName, lastName, phone);
            contactList.add(contacto);
            Collections.sort(contactList);
            contactAdapter.notifyDataSetChanged();
        }

        // Si el resultado es de editar o borrar un contacto existente
        if (requestCode == REQUEST_CODE_EDIT_CONTACT && resultCode == RESULT_OK) {
            boolean delete = data.getBooleanExtra("delete", false);
            Contacto contacto = (Contacto) data.getSerializableExtra("contact");

            if (delete) {
                contactList.remove(contacto);
            } else {
                String firstName = data.getStringExtra("firstName");
                String lastName = data.getStringExtra("lastName");
                String phone = data.getStringExtra("phone");
                Contacto newContact = new Contacto(firstName, lastName, phone);
                contactList.remove(contacto);
                contactList.add(newContact);
            }

            Collections.sort(contactList);
            contactAdapter.notifyDataSetChanged();
        }
    }

    public void onAgregar(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_CONTACT);
    }
}