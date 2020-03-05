package com.example.hellojbhbjbjj;

public class Interest {
    String _id;
    String fecha;
    String monto;

    public Interest(String idLoan,String fecha, String monto) {
        this._id = idLoan;
        this.fecha = fecha;
        this.monto = monto;
    }

    public String getIdLoan() {
        return _id;
    }

    public void setIdLoan(String idLoan) {
        this._id = idLoan;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
