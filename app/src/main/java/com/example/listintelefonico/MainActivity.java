package com.example.listintelefonico;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_AGREGAR = 1;
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
        /*contactList.add(new Contacto("Sonia", "Fernandez", "999999999"));
        contactList.add(new Contacto("Alba", "Brox", "999999999"));*/

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
        intent.putExtra("añadir",2);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_AGREGAR && resultCode == RESULT_OK && data != null) {
            String nombre = data.getStringExtra("nombre");
            String apellidos = data.getStringExtra("apellidos");
            String telefono = data.getStringExtra("telefono");

            // Crear un nuevo objeto Contacto con los datos recibidos
            Contacto nuevoContacto = new Contacto(nombre, apellidos, telefono);

            // Agregar el nuevo contacto a la lista de contactos
            contactList.add(nuevoContacto);

            // Notificar al adapter que los datos han cambiado
            contactAdapter.notifyDataSetChanged();

            // Mostrar la lista y ocultar el mensaje de lista vacía
            TextView textView = findViewById(R.id.tvListVacia);
            textView.setVisibility(View.GONE);
            contactListView.setVisibility(View.VISIBLE);
        }
    }

}
