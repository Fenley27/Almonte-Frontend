package com.example.hellojbhbjbjj;

public class Loans {
    String _id;
    String client;
    String monto;
    String plan;
    boolean estadoPago;
    String status;
    String fecha;

    public Loans(String id, String client, String monto, String plan, boolean estadoPago, String status, String fecha) {
        this._id = id;
        this.client = client;
        this.monto = monto;
        this.plan = plan;
        this.estadoPago = estadoPago;
        this.status = status;
        this.fecha = fecha;
    }

    public String get_id() {
        return _id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public boolean isEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(boolean estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
