package com.example.listintelefonico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class listaContactos {
    private ArrayList<Contacto> contacts = new ArrayList<>();

    public void addContact(Contacto contacto) {
        contacts.add(contacto);
    }

    public void removeContact(Contacto contacto) {
        contacts.remove(contacto);
    }

    public void updateContact(Contacto oldContact, Contacto newContact) {
        int index = contacts.indexOf(oldContact);
        contacts.set(index, newContact);
    }

    public ArrayList<Contacto> getContactsSortedByName() {
        Collections.sort(contacts, new Comparator<Contacto>() {
            @Override
            public int compare(Contacto contact1, Contacto contact2) {
                return contact1.getFirstName().compareTo(contact2.getFirstName());
            }
        });
        return contacts;
    }

    public ArrayList<Contacto> getContactsSortedByLastName() {
        Collections.sort(contacts, new Comparator<Contacto>() {
            @Override
            public int compare(Contacto contact1, Contacto contact2) {
                return contact1.getLastName().compareTo(contact2.getLastName());
            }
        });
        return contacts;
    }
}
