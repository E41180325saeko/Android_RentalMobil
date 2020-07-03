package com.rental_apps.android.rental_apps.model.model_supir;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSupir {

    private Boolean status;
    private String message;
    private List<DataSupir> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataSupir> getData() {
        return data;
    }

    public void setData(List<DataSupir> data) {
        this.data = data;
    }
}
