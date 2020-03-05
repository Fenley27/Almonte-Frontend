package com.example.hellojbhbjbjj;

public class Payment {
    String _id;
    String fecha;
    String monto;

    public Payment(String idLoan,String fecha, String monto) {
        this._id = idLoan;
        this.fecha = fecha;
        this.monto = monto;
    }

    public String getIdLoan() {
        return _id;
    }
    public String getFecha() {
        return fecha;
    }
    public String getMonto() {
        return monto;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
