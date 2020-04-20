package com.rental_apps.android.rental_apps.admin;
        import android.content.Context;
        import android.os.Bundle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.DefaultItemAnimator;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Toast;

        import com.google.gson.Gson;
        import com.mikepenz.itemanimators.SlideLeftAlphaAnimator;
        import com.rental_apps.android.rental_apps.R;
        import com.rental_apps.android.rental_apps.SPreferenced.SPref;
        import com.rental_apps.android.rental_apps.adapter.Carts;
        import com.rental_apps.android.rental_apps.adapter.DetailTransaksiAdapter;
        import com.rental_apps.android.rental_apps.api.client;
        import com.rental_apps.android.rental_apps.model.model_detail_transaksi.DataDetailTransaksi;
        import com.rental_apps.android.rental_apps.model.model_detail_transaksi.ResponseDetailTransaksi;
        import com.rental_apps.android.rental_apps.model.model_transaksi.DataTransaksi;
        import com.rental_apps.android.rental_apps.myinterface.InitComponent;

        import java.util.ArrayList;
        import java.util.List;

        import cn.pedant.SweetAlert.SweetAlertDialog;
        import customfonts.MyTextView;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

/**
 * Created by Ujang Wahyu on 04/01/2018.
 */

public class ActivityDetailListTransaksi extends AppCompatActivity implements InitComponent, View.OnClickListener {
    private MyTextView confirm;
    private Context mContext;
    private RecyclerView recyclerCart;
    //Declare Adapter
    private DetailTransaksiAdapter mAdapter;
    private SweetAlertDialog pDialog;

    //Declare Object Users
    ResponseDetailTransaksi dataTransaksi;
    List<DataDetailTransaksi> listDetailTransaksi=new ArrayList<>();

    DataTransaksi transaksi;

    @Override
    protected void onCreate(Bundle SavedInstance) {
        super.onCreate(SavedInstance);
        setContentView(R.layout.activity_list_cart);

        Gson gson = new Gson();
        transaksi= gson.fromJson(getIntent().getStringExtra("transaksi"), DataTransaksi.class);

        mContext=this;
        startInit();
    }

    @Override
    public void startInit() {
        initToolbar();
        initUI();
        initValue();
        initEvent();
    }

    @Override
    public void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(transaksi.getKODETRANSAKSI());
    }

    @Override
    public void initUI() {
        recyclerCart = (RecyclerView)findViewById(R.id.rCartList);
        confirm = (MyTextView)findViewById(R.id.checkout);
    }

    @Override
    public void initValue() {
        prepareCart();
        getTransaksi();
        mAdapter.notifyDataSetChanged();
//        setCheckout();
    }

    public void setCheckout(){
        confirm.setText("(Rp. "+String.format("%,.2f", Double.parseDouble(Carts.totalAmount(SPref.getCARTS()).toString()))+") Checkout");
    }
    @Override
    public void initEvent() {
        confirm.setOnClickListener(this);
    }

    private void prepareCart(){
        mAdapter = new DetailTransaksiAdapter(mContext,listDetailTransaksi);
        recyclerCart.setHasFixedSize(true);
        recyclerCart.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerCart.setItemAnimator(new DefaultItemAnimator());
        recyclerCart.setAdapter(mAdapter);
        recyclerCart.setItemAnimator(new SlideLeftAlphaAnimator());
        recyclerCart.getItemAnimator().setAddDuration(500);
        recyclerCart.getItemAnimator().setRemoveDuration(500);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getTransaksi(){
        final Call<ResponseDetailTransaksi> users= client.getApi().dataDetailTransaksi(transaksi.getKODETRANSAKSI());
        users.enqueue(new Callback<ResponseDetailTransaksi>() {
            @Override
            public void onResponse(Call<ResponseDetailTransaksi> call, Response<ResponseDetailTransaksi> response) {
                dataTransaksi=response.body();
                if (response.isSuccessful()) {
                    if (dataTransaksi.getStatus()) {
                        listDetailTransaksi.clear();
                        listDetailTransaksi.addAll(dataTransaksi.getData());
                        mAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(mContext, "Tidak Ada Data Ditemukan", Toast.LENGTH_LONG).show();

                    }
                }else {
                    Toast.makeText(mContext, "Tidak Ada Data Ditemukan", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailTransaksi> call, Throwable t) {
                Toast.makeText(mContext,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.checkout:
                Toast.makeText(mContext,"Proses",Toast.LENGTH_LONG).show();
                break;
        }
    }
}

