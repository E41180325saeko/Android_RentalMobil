package com.rental_apps.android.rental_apps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rental_apps.android.rental_apps.R;
import com.rental_apps.android.rental_apps.model.model_supir.DataSupir;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private Context ctx;
    private List<DataSupir> listData;

    public AdapterData(Context ctx, List<DataSupir> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_driver, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataSupir dm = listData.get(position);

        holder.tvId.setText(String.valueOf(dm.getID_SUPIR()));
        holder.tvNama.setText(dm.getNAMA());
        holder.tvTelp.setText(dm.getTELP());
        holder.tvAlamat.setText(dm.getALAMAT());
        holder.tvStatus.setText(String.valueOf(dm.getSTATUS()));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvId, tvNama, tvTelp, tvAlamat, tvStatus;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvTelp = itemView.findViewById(R.id.tv_telp);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvStatus = itemView.findViewById(R.id.tv_status);
        }
    }
}
