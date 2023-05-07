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
        // Obtener referencias a los EditText
        nombre = findViewById(R.id.edNombre);
        apellidos = findViewById(R.id.edApellidos);
        telefono = findViewById(R.id.edTelefono);

        // Obtener el contacto seleccionado de la actividad anterior (si existe)
        Intent intent = getIntent();
        if (intent.hasExtra("contact")) {
            contacto = (Contacto) intent.getSerializableExtra("contact");
            nombre.setText(contacto.getNombre());
            apellidos.setText(contacto.getApellidos());
            //telefono.setText(contacto.getTelefono());
        }
    }
    

    public void onBorrar(View view) {
        Button deleteButton = findViewById(R.id.bBorrar);
        deleteButton.setVisibility(View.VISIBLE);
        if (contacto!=null) {
            Intent intent = new Intent();
            intent.putExtra("contact", contacto);
            intent.putExtra("delete", true);
            setResult(RESULT_OK, intent);
            finish();
        }else {
            deleteButton.setVisibility(View.GONE);
        }
    }

    public void onAceptar(View view) {
        String nom = nombre.getText().toString();
        String apell = apellidos.getText().toString();
        String tlf = telefono.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("nom", nom);
        intent.putExtra("apell", apell);
        intent.putExtra("tlf", tlf);

        if (contacto != null) {
            intent.putExtra("contact", contacto);
        }

        setResult(RESULT_OK, intent);
        finish();
    }

    public void onCancelar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}