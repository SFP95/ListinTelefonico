package com.example.listintelefonico;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity2 extends AppCompatActivity {
    private static final int ADD_CONTACT_REQUEST = 1;
    private static final int EDIT_CONTACT_REQUEST = 2;
    private ListView listcontactos;
    private Button bagregar;
    private ArrayList<Contacto> contactos;
    private ArrayAdapter<Contacto> adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listcontactos = findViewById(R.id.lvListacontactos);
        bagregar = findViewById(R.id.bAgregar);

        contactos = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactos);
        listcontactos.setAdapter(adapter);

        listcontactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("contact", contactos.get(position));
                startActivityForResult(intent, EDIT_CONTACT_REQUEST);
            }
        });
    }

    public void onAgregar(View view) {
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        startActivityForResult(intent, ADD_CONTACT_REQUEST);
    }

    public void onCancelar(View view) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_CONTACT_REQUEST) {
                String nombre = data.getStringExtra("nom");
                String apellidos = data.getStringExtra("apell");
                String telefono = data.getStringExtra("tlf");
                Contacto contacto = new Contacto(nombre, apellidos, telefono);
                contactos.add(contacto);
            } else if (requestCode == EDIT_CONTACT_REQUEST) {
                String nombre = data.getStringExtra("nom");
                String apellidos = data.getStringExtra("apell");
                String telefono = data.getStringExtra("tlf");
                Contacto contacto = (Contacto) data.getSerializableExtra("contact");
                contacto.setNombre(nombre);
                contacto.setApellidos(apellidos);
                contacto.setTelefono(Integer.parseInt(telefono));
            }

            // Ordenar la lista por nombre y actualizar la vista
            Collections.sort(contactos, new Comparator<Contacto>() {
                @Override
                public int compare(Contacto c1, Contacto c2) {
                    return c1.getFullName().compareToIgnoreCase(c2.getFullName());
                }
            });
            adapter.notifyDataSetChanged();
        }
    }
}
