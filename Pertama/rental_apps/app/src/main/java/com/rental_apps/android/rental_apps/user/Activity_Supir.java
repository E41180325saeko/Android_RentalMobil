package com.rental_apps.android.rental_apps.user;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rental_apps.android.rental_apps.R;
import com.rental_apps.android.rental_apps.adapter.AdapterData;
import com.rental_apps.android.rental_apps.api.client;
import com.rental_apps.android.rental_apps.api.request;
import com.rental_apps.android.rental_apps.model.model_supir.DataSupir;
import com.rental_apps.android.rental_apps.model.model_supir.ResponseSupir;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Supir extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataSupir> listData = new ArrayList<>();

     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_list_driver);

         rvData = findViewById(R.id.rDriverList);
         lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
         rvData.setLayoutManager(lmData);
         retrieveData();
     }

     public void retrieveData() {
         request ardData = client.konekRetrofit().create(request.class);
         Call<ResponseSupir> tampilData = ardData.dataSupir();

         tampilData.enqueue(new Callback<ResponseSupir>() {
             @Override
             public void onResponse(Call<ResponseSupir> call, Response<ResponseSupir> response) {
                    if (response.isSuccessful()) {
                        Boolean status = response.body().getStatus();
                        String message = response.body().getMessage();
                        if (status) {
                            listData = response.body().getData();
                            adData.notifyDataSetChanged();
                            adData = new AdapterData(Activity_Supir.this, listData);
                            rvData.setAdapter(adData);
                        } else {
                            Toast.makeText(Activity_Supir.this, "Status : "+status+" | Message : "+message, Toast.LENGTH_SHORT).show();
                        }
                    }


//                     Boolean status = response.body().getStatus();
//                     String message = response.body().getMessage();
//
//                         Toast.makeText(Activity_Supir.this, "Status : "+status+" | Message : "+message, Toast.LENGTH_SHORT).show();
//
//                         listData = response.body().getData();
//                         adData = new AdapterData(Activity_Supir.this, listData);
//                         rvData.setAdapter(adData);
//                         adData.notifyDataSetChanged();
                     }

             @Override
             public void onFailure(Call<ResponseSupir> call, Throwable t) {
                 Toast.makeText(Activity_Supir.this, "Gagal", Toast.LENGTH_SHORT).show();
             }
         });

     }

}
