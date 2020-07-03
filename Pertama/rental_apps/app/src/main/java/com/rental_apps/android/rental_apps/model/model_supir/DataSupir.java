package com.rental_apps.android.rental_apps.model.model_supir;

public class DataSupir {
    private int ID_SUPIR, STATUS;
    private String NAMA, TELP, ALAMAT;

    public int getID_SUPIR() {
        return ID_SUPIR;
    }

    public void setID_SUPIR(int ID_SUPIR) {
        this.ID_SUPIR = ID_SUPIR;
    }

    public String getNAMA() {
        return NAMA;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }

    public String getTELP() {
        return TELP;
    }

    public void setTELP(String TELP) {
        this.TELP = TELP;
    }

    public String getALAMAT() {
        return ALAMAT;
    }

    public void setALAMAT(String ALAMAT) {
        this.ALAMAT = ALAMAT;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }
}
