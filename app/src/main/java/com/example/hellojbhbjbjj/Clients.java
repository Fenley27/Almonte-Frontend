package com.example.hellojbhbjbjj;

public class Clients {

    String _id;
    String name;
    String apellido;
    String cedula;
    String telefono;
    String dirreccion;
    String ciudad;;
    String DirReferencia;
    String puntos;

    public Clients(String id,String name, String apellido, String cedula, String telefono, String direeccion, String ciudad, String dirReferencia, String puntos) {
        this._id = id;
        this.name = name;
        this.apellido = apellido;
        this.cedula = cedula;
        this.telefono = telefono;
        this.dirreccion = direeccion;
        this.ciudad = ciudad;
        this.DirReferencia = dirReferencia;
        this.puntos = puntos;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireeccion() {
        return dirreccion;
    }

    public void setDireeccion(String direeccion) {
        this.dirreccion = direeccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDirReferencia() {
        return DirReferencia;
    }

    public void setDirReferencia(String dirReferencia) {
        this.DirReferencia = dirReferencia;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }
}
