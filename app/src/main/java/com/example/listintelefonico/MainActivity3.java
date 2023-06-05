package com.example.listintelefonico;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    private Contacto contacto;
    private EditText nombre, apellidos, telefono;
    private ListView contactListView;
    private ArrayAdapter<Contacto> contactAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        nombre = findViewById(R.id.edNombre);
        apellidos = findViewById(R.id.edApellidos);
        telefono = findViewById(R.id.edTelefono);
        contactListView = findViewById(R.id.lvListacontactos);
        Button guardar = findViewById(R.id.bAceptar);
        Button borrar = findViewById(R.id.bBorrar);

        Intent intent = getIntent();
        contacto = (Contacto) intent.getSerializableExtra("contact");
        String nom = contacto.getNombre();
        String ape = contacto.getApellidos();
        String tlf = String.valueOf(contacto.getTelefono());

        nombre.setText(nom);
        apellidos.setText(ape);
        telefono.setText(tlf);

        }

    public void onCancelar(View view) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    public void onBorrar(View view) {
        Intent intent = new Intent();
        intent.putExtra("borrarContacto", contacto);
        setResult(RESULT_OK, intent);

        contactAdapter.remove(contacto);
        contactAdapter.notifyDataSetChanged();

        finish();
    }

    public void onAceptar(View view) {
    }
}
