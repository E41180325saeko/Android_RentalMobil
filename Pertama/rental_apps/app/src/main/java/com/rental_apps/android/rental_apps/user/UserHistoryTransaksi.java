package com.rental_apps.android.rental_apps.user;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rental_apps.android.rental_apps.R;
import com.rental_apps.android.rental_apps.myinterface.InitComponent;




public class UserHistoryTransaksi extends Fragment implements InitComponent  {
    //Declate Toolbar Tittle
    private static final String TEXT_FRAGMENT = "RENTCAR";

    //Declare Component View
    private FloatingActionButton buttonwa;
    private View rootView;


    public static UserHistoryTransaksi newInstance(String text){
        UserHistoryTransaksi mFragment = new UserHistoryTransaksi();
        Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // TODO Auto-generated method stub
        rootView = inflater.inflate(R.layout.list_history, container, false);
        startInit();
        return rootView;
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

    }

    @Override
    public void initEvent() {

    }


}

