package com.example.listintelefonico;

import androidx.appcompat.app.AppCompatActivity;

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
    private static final int ADD_contacto_REQUEST = 1;
    private static final int EDIT_contacto_REQUEST = 2;
    private ListView listcontactoos;
    private Button bagregar;
    private listacontactoos contactoList = new listacontactoos();
    private ArrayAdapter<contactoo> adapter;
    private ArrayList<contactoo> contactoos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listcontactoos = findViewById(R.id.lvListacontactoos);
        bagregar = findViewById(R.id.bAgregar);

        contactoos = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactoList.getcontactosSortedByName());
        listcontactoos.setAdapter(adapter);

        listcontactoos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("contactoo", (CharSequence) contactoos.get(position));
                startActivityForResult(intent, EDIT_contacto_REQUEST);
            }
        });
    }

    public void onAgregar(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivityForResult(intent, ADD_contacto_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_contacto_REQUEST) {
                String nombre = data.getStringExtra("nombre");
                String apellidos = data.getStringExtra("apellidos");
                String telefono = data.getStringExtra("telefono");
                contactoo contacto = new contactoo(nombre, apellidos, telefono);
                contactoos.add(contacto);
            } else if (requestCode == EDIT_contacto_REQUEST) {
                String nombre = data.getStringExtra("nombre");
                String apellidos = data.getStringExtra("apellidos");
                String telefono = data.getStringExtra("telefono");
                contactoo contacto = (contactoo) data.getSerializableExtra("contacto");
                contacto.setNombre(nombre);
                contacto.setApellidos(apellidos);
                contacto.setTelefono(Integer.parseInt(telefono));
            }

            // Ordenar la lista por nombre y actualizar la vista
            Collections.sort(contactoos, new Comparator<contactoo>() {
                @Override
                public int compare(contactoo c1, contactoo c2) {
                    return c1.getFullName().compareToIgnoreCase(c2.getFullName());
                }
            });
            adapter.notifyDataSetChanged();
        }
    }
}