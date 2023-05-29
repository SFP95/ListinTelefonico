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

        if (contacto != null) {
            nombre.setText(contacto.getNombre());
            apellidos.setText(contacto.getApellidos());
            telefono.setText(contacto.getTelefono());
            guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nom = nombre.getText().toString();
                    String apell = apellidos.getText().toString();
                    String tlf = telefono.getText().toString();

                    Intent intent = new Intent();
                    intent.putExtra("nom", nom);
                    intent.putExtra("apell", apell);
                    intent.putExtra("tlf", tlf);
                    intent.putExtra("contact", contacto);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

            borrar.setOnClickListener(v -> {
                Intent intent1 = new Intent();
                intent1.putExtra("delete", true);
                intent1.putExtra("contact", contacto);
                setResult(RESULT_OK, intent1);
                finish();
            });
        } else {
            guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nom = nombre.getText().toString();
                    String apell = apellidos.getText().toString();
                    String tlf = telefono.getText().toString();

                    Intent intent = new Intent();
                    intent.putExtra("nom", nom);
                    intent.putExtra("apell", apell);
                    intent.putExtra("tlf", tlf);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

            borrar.setVisibility(View.GONE);
        }
    }
}
