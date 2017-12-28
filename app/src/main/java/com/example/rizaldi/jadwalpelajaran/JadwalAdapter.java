package com.example.rizaldi.jadwalpelajaran;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cipowela on 28/12/17.
 */

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.Holder> {

    private Context c;
    private List<Jadwal> jadwalList;

    public JadwalAdapter(Context c, List<Jadwal> jadwalList) {
        this.c = c;
        this.jadwalList = jadwalList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jadwal_layout, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Jadwal now = jadwalList.get(position);
        holder.makul.setText(now.makul);
        holder.hari_jam.setText(now.hari+", "+now.jam);
        holder.ruangan.setText(now.ruangan);
    }

    @Override
    public int getItemCount() {
        return jadwalList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView makul, hari_jam, ruangan;
        public Holder(View itemView) {
            super(itemView);
            makul = itemView.findViewById(R.id.makul);
            hari_jam = itemView.findViewById(R.id.hari_jam);
            ruangan = itemView.findViewById(R.id.ruangan);
        }
    }
}
