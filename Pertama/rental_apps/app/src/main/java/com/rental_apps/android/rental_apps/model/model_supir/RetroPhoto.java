package com.rental_apps.android.rental_apps.model.model_supir;

import com.google.gson.annotations.SerializedName;

public class RetroPhoto {

    @SerializedName("ID_SUPIR")
    private int ID_SUPIR;
    @SerializedName("NAMA")
    private String NAMA;
    @SerializedName("TELP")
    private String TELP;
    @SerializedName("ALAMAT")
    private String ALAMAT;
    @SerializedName("STATUS")
    private int STATUS;

    public RetroPhoto(Integer ID_SUPIR, String NAMA, String TELP, String ALAMAT, Integer STATUS) {
        this.ID_SUPIR = ID_SUPIR;
        this.NAMA = NAMA;
        this.TELP = TELP;
        this.ALAMAT = ALAMAT;
        this.STATUS = STATUS;
    }
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
