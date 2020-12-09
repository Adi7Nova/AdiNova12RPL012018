package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andtoif.R;

import java.util.List;

public class TokoAdapter extends RecyclerView.Adapter<TokoAdapter.JadwalViewHolder> {
    private Context mContext;


    private List<Toko> dataList;

    public TokoAdapter(Context mContext, List<Toko> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;

    }


    @Override
    public JadwalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.list_toko, parent, false);
        return new JadwalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JadwalViewHolder holder, int position) {
        final Toko user = dataList.get(position);
        holder.merk.setText(user.getMerk());
        holder.jenis.setText(user.getJenis());
        holder.harga.setText(user.getHarga());


    }

    @Override
    public int getItemCount() {
        return dataList.size();

    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class JadwalViewHolder extends RecyclerView.ViewHolder {

        private TextView merk, jenis, harga;


        public JadwalViewHolder(View itemView) {
            super(itemView);
            merk = (TextView) itemView.findViewById(R.id.merk);
            jenis = (TextView) itemView.findViewById(R.id.jenis);
            harga = (TextView) itemView.findViewById(R.id.harga);
        }

    }
}