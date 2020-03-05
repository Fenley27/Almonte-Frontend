package com.example.hellojbhbjbjj;

public class Plans {
    String _id;
    String name;
    String cuotas;
    String percentaje;
    String fecha;

    public Plans(String _id, String name, String cuotas, String percentaje, String fecha) {
        this._id = _id;
        this.name = name;
        this.cuotas = cuotas;
        this.percentaje = percentaje;
        this.fecha = fecha;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getCuotas() {
        return cuotas;
    }

    public String getPercentaje() {
        return percentaje;
    }

    public String getFecha() {
        return fecha;
    }
}
