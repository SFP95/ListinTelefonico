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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity2 extends AppCompatActivity {

    private ListView listcontactos;
    private Button bagregar;
    private ArrayList<Contacto> contactos;
    private ArrayAdapter<Contacto> adapter;
    private EditText nombre, apellidos,telefono;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();

       asignarEdts();

    }

    private void asignarEdts() {
        nombre=findViewById(R.id.edNombre);
        apellidos=findViewById(R.id.edApellidos);
        telefono=findViewById(R.id.edTelefono);
    }

    public void onAgregar(View view) {
        String nom= String.valueOf(nombre.getText());
        String apell= String.valueOf(apellidos.getText());
        String tlf= String.valueOf(telefono.getText());

        Contacto contacto = new Contacto(nom, apell,tlf);

        Intent i = new Intent();
        i.putExtra("contactoNuevo",contacto);
        setResult(RESULT_OK, i);
        finish();
    }

    public void onCancelar(View view) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

}
