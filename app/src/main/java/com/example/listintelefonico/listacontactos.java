package com.example.listintelefonico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class listacontactos {
    private ArrayList<Contacto> contactos = new ArrayList<>();

    public void addcontacto(Contacto contacto) {
        contactos.add(contacto);
    }

    public void removecontacto(Contacto contacto) {
        contactos.remove(contacto);
    }

    public void updatecontacto(Contacto oldcontacto, Contacto newcontacto) {
        int index = contactos.indexOf(oldcontacto);
        contactos.set(index, newcontacto);
    }

    public ArrayList<Contacto> getcontactosSortedByName() {
        Collections.sort(contactos, new Comparator<Contacto>() {
            @Override
            public int compare(Contacto contacto1, Contacto contacto2) {
                return contacto1.getnombre().compareTo(contacto2.getnombre());
            }
        });
        return contactos;
    }

    public ArrayList<Contacto> getcontactosSortedByapellidos() {
        Collections.sort(contactos, new Comparator<Contacto>() {
            @Override
            public int compare(Contacto contacto1, Contacto contacto2) {
                return contacto1.getapellidos().compareTo(contacto2.getapellidos());
            }
        });
        return contactos;
    }
}
