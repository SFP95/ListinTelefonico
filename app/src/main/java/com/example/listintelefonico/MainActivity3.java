package com.example.listintelefonico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity {
    private Contacto contacto;
    private EditText nombre, apellidos, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        nombre = findViewById(R.id.edNombre);
        apellidos = findViewById(R.id.edApellidos);
        telefono = findViewById(R.id.edTelefono);
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
}
