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
        contactListView.setOnItemClickListener((parent,v,pos,id)->pulsado(parent,v,pos,id));

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

    private void pulsado(AdapterView<?> parent, View v, int pos, long id) {
        Contacto con = contactList.get(pos); // Obtener el objeto Contacto de la lista
        Intent i = new Intent(this, MainActivity3.class);
        i.putExtra("contact", con);
        startActivityForResult(i, REQUEST_CODE_EDIT_CONTACT);
    }



    public void onAgregar(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("añadir",2);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_AGREGAR && resultCode == RESULT_OK) {
            if (data != null) {
                Contacto con = (Contacto) data.getSerializableExtra("contactoNuevo");
                contactList.add(con);
                contactAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode == REQUEST_CODE_EDIT_CONTACT && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Elemento Borrado", Toast.LENGTH_SHORT).show();
            if (data != null) {
                int deletedPosition = data.getIntExtra("deletedPosition", -1);
                if (deletedPosition != -1) {
                    contactList.remove(deletedPosition); // Eliminar el elemento de la lista de contactos
                    contactAdapter.notifyDataSetChanged(); // Notificar al adaptador del cambio en la lista
                }
            }
        }
    }
}



