package com.example.listintelefonico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class listacontactoos {
    private ArrayList<contactoo> contactos = new ArrayList<>();

    public void addcontacto(contactoo contactoo) {
        contactos.add(contactoo);
    }

    public void removecontacto(contactoo contactoo) {
        contactos.remove(contactoo);
    }

    public void updatecontacto(contactoo oldcontacto, contactoo newcontacto) {
        int index = contactos.indexOf(oldcontacto);
        contactos.set(index, newcontacto);
    }

    public ArrayList<contactoo> getcontactosSortedByName() {
        Collections.sort(contactos, new Comparator<contactoo>() {
            @Override
            public int compare(contactoo contacto1, contactoo contacto2) {
                return contacto1.getnombre().compareTo(contacto2.getnombre());
            }
        });
        return contactos;
    }

    public ArrayList<contactoo> getcontactosSortedByapellidos() {
        Collections.sort(contactos, new Comparator<contactoo>() {
            @Override
            public int compare(contactoo contacto1, contactoo contacto2) {
                return contacto1.getapellidos().compareTo(contacto2.getapellidos());
            }
        });
        return contactos;
    }
}
