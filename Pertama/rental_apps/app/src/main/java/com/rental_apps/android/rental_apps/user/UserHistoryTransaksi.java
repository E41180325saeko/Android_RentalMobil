package com.rental_apps.android.rental_apps.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rental_apps.android.rental_apps.R;
import com.rental_apps.android.rental_apps.myinterface.InitComponent;
import com.mikepenz.itemanimators.SlideLeftAlphaAnimator;
import com.pixplicity.easyprefs.library.Prefs;
import com.rental_apps.android.rental_apps.SPreferenced.SPref;
import com.rental_apps.android.rental_apps.adapter.HistoryAdapter;
import com.rental_apps.android.rental_apps.api.client;
import com.rental_apps.android.rental_apps.model.model_history.ResponseHistory;
import com.rental_apps.android.rental_apps.model.model_history.DataHistory;
import java.util.ArrayList;
import java.util.List;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHistoryTransaksi extends Fragment implements InitComponent  {
    //Declate Toolbar Tittle
    private static final String TEXT_FRAGMENT = "RENTCAR";

    //Declare Component View
    private FloatingActionButton buttonwa;
    private View rootView;
    private TextView mTxtTitle;
    private RecyclerView recyclerHistory;

    //Declate Activity Context
    Context mContext;

    //Declare Object History
    ResponseHistory dataHistory;
    List<DataHistory> listHistory=new ArrayList<com.rental_apps.android.rental_apps.model.model_history.DataHistory>();

    //Declare Adapter
    private HistoryAdapter mAdapter;

    public static UserHistoryTransaksi newInstance(String text){
        UserHistoryTransaksi mFragment = new UserHistoryTransaksi();
        Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mContext=getActivity();
        // TODO Auto-generated method stub
        rootView = inflater.inflate(R.layout.list_history, container, false);
        startInit();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_icon, menu);
        setItem(menu);
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        getHistory();
    }

    private void setItem(Menu menu){
        MenuItem menuAdd = menu.findItem(R.id.add);
        menuAdd.setVisible(false);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                getHistory();
                return true;
            case R.id.add:
                Toasty.success(mContext, "Tambah", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        getActivity().setTitle(getArguments().getString(TEXT_FRAGMENT));
    }

    @Override
    public void initUI() {
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        recyclerHistory= (RecyclerView)rootView.findViewById(R.id.rTransaksiList);
        buttonwa = (FloatingActionButton) rootView.findViewById(R.id.btn_wa);

        buttonwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:" + "081330726003");
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setPackage("com.whatsapp");
                startActivity(i);
            }
        });
    }

    @Override
    public void initValue() {
        prepareTransaksi();
        getHistory();
    }

    @Override
    public void initEvent() {

    }

    public void getHistory(){
        final Call<ResponseHistory>
                history= client.getApi().dataHistory(Prefs.getInt(SPref.getIdUser(),0));

        //register = client.getApi().userUpdate(""+Prefs.getInt(SPref.getIdUser(),0),name.getText().toString(),
//        Log.d("test","hekki");
        history.enqueue(new Callback<ResponseHistory>() {
            @Override
            public void onResponse(Call<ResponseHistory> call, Response<ResponseHistory> response) {
                if (response.isSuccessful()) {
                    dataHistory=response.body();
                    if (dataHistory.getStatus()) {
                        listHistory.clear();
                        listHistory.addAll(dataHistory.getData());
                        mAdapter.notifyDataSetChanged();
                    } else {
                        listHistory.clear();
                        mAdapter.notifyDataSetChanged();
                        Toasty.error(mContext, "Belum Ada Transaksi", Toast.LENGTH_LONG).show();
                    }
                }else {
                    listHistory.clear();
                    mAdapter.notifyDataSetChanged();
                    Toasty.error(mContext, "Belum Ada Transaksi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseHistory> call, Throwable t) {
                Toasty.error(mContext, "Belum Ada Transaksi", Toast.LENGTH_LONG).show();
//                Toasty.error(mContext,t.getMessage(),Toast.LENGTH_LONG).show();
            }

        });
    }

    private void prepareTransaksi(){
        mAdapter = new HistoryAdapter(listHistory);
        recyclerHistory.setHasFixedSize(true);
        recyclerHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerHistory.setItemAnimator(new DefaultItemAnimator());
        recyclerHistory.setAdapter(mAdapter);
        recyclerHistory.setItemAnimator(new SlideLeftAlphaAnimator());

    }

}

