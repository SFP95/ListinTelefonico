package com.example.listintelefonico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView listContactos;
    private Button bagregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listContactos = findViewById(R.id.lvListaContactos);
        bagregar = findViewById(R.id.bAgregar);
    }

    public void onAgregar(View view) {
    }
}