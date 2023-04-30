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
    private static final int ADD_contacto_REQUEST = 1;
    private static final int EDIT_contacto_REQUEST = 2;
    private ListView listcontactoos;
    private Button bagregar;
    private listacontactoos contactoList = new listacontactoos();
    private ArrayAdapter<Contacto> adapter;
    private ArrayList<Contacto> contactos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listcontactoos = findViewById(R.id.lvListacontactoos);
        bagregar = findViewById(R.id.bAgregar);

        contactos = new ArrayList<Contacto>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactoList.getcontactosSortedByName());
        listcontactoos.setAdapter(adapter);

        listcontactoos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                intent.putExtra("contactoo", (CharSequence) contactos.get(position));
                startActivityForResult(intent, EDIT_contacto_REQUEST);
            }
        });
    }

    public void onAgregar(View view) {
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivityForResult(intent, ADD_contacto_REQUEST);
    }
    public void onCancelar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_contacto_REQUEST) {
                String nombre = data.getStringExtra("nombre");
                String apellidos = data.getStringExtra("apellidos");
                String telefono = data.getStringExtra("telefono");
                Contacto contacto = new Contacto(nombre, apellidos, telefono);
                contactos.add(contacto);
            } else if (requestCode == EDIT_contacto_REQUEST) {
                String nombre = data.getStringExtra("nombre");
                String apellidos = data.getStringExtra("apellidos");
                String telefono = data.getStringExtra("telefono");
                Contacto contacto = (Contacto) data.getSerializableExtra("contacto");
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