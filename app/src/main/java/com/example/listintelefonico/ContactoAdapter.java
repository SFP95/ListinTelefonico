package com.example.listintelefonico;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactoAdapter extends ArrayAdapter<Contacto> {
    public ContactoAdapter(@NonNull Context context, List<Contacto> contactos) {
        super(context, 0,contactos);
    }
    public ContactoAdapter(@NonNull Context context, Contacto[] contactos) {
        super(context, 0,contactos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contacto con = getItem(position);
        View v;
        v = LayoutInflater.from(getContext()).inflate(R.layout.elemento,parent,false);

        TextView tvNom = (TextView) v.findViewById(R.id.tvNombre);
        TextView tvapell = (TextView) v.findViewById(R.id.tvApellido);
        TextView tvtlf = (TextView) v.findViewById(R.id.tvTelefono);

        tvNom.setText(con.getNombre());
        tvapell.setText(con.getApellidos());
        tvtlf.setText(con.getTelefono());

        return v;
    }

}
