package com.example.listintelefonico;

public class contactoo {
    private String nombre;
    private String apellidos;
    private int telefono;


    public contactoo(String nombre, String apellidos, String telefono){
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.telefono= Integer.parseInt(telefono);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getFullName() {
        return nombre + " " + apellidos;
    }

    @Override
    public String toString() {
        return getFullName();
    }
    public <T> Comparable<T> getnombre() {
        return null;
    }

    public <T> Comparable<T> getapellidos() {
        return null;
    }
}
