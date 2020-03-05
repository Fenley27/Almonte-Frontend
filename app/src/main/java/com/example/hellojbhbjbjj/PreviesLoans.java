package com.example.hellojbhbjbjj;

public class PreviesLoans {
    String _id;
    String idPreviewLoan;

    public PreviesLoans(String idLoan, String idPreviewLoan) {
        this._id = idLoan;
        this.idPreviewLoan = idPreviewLoan;
    }

    public String getIdLoan() {
        return _id;
    }

    public String getIdPreviewLoan() {
        return idPreviewLoan;
    }
}
