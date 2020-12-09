package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.andtoif.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.JadwalViewHolder> {
    private Context mContext;


    private List<User> dataList;

    public UserAdapter(Context mContext, List<User> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;

    }


    @Override
    public JadwalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.mainlist, parent, false);
        return new JadwalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JadwalViewHolder holder, int position) {
        final User user = dataList.get(position);
        holder.username.setText(user.getUsername());
//        holder.email.setText(user.getEmail());
//        holder.ktp.setText(user.getNoKTP());
//        holder.hp.setText(user.getNoHP());


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

        private TextView username, email, ktp, hp;

        public JadwalViewHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.list);
//            email = (TextView) itemView.findViewById(R.id.tv_Leagueaway);
//            ktp = (TextView) itemView.findViewById(R.id.date);
//            hp = (TextView) itemView.findViewById(R.id.atime);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i = new Intent(mContext, Main2Activity.class);
//                    i.putExtra("events", dataList.get(getAdapterPosition()).getStrEvent());
//                    i.putExtra("events2", dataList.get(getAdapterPosition()).getStrThumb());
//                    i.putExtra("events3", dataList.get(getAdapterPosition()).getStrHomeforward());
//                    i.putExtra("events4", dataList.get(getAdapterPosition()).getStrAwayLineupDefense());
//                    i.putExtra("events5", dataList.get(getAdapterPosition()).getStrAwayforward());
//                    i.putExtra("events6", dataList.get(getAdapterPosition()).getStrHomeLineupMidfield());
//                    i.putExtra("events7", dataList.get(getAdapterPosition()).getStrAwayLineupGoalkeeper());
//                    i.putExtra("events8", dataList.get(getAdapterPosition()).getStrAwayLineupMidfield());
//                    i.putExtra("events9", dataList.get(getAdapterPosition()).getStrHomeLineupGoalkeeper());
//                    i.putExtra("events11", dataList.get(getAdapterPosition()).getStrHomeLineupDefense());
//                    i.putExtra("events12", dataList.get(getAdapterPosition()).getStrHomeTeam());
//                    i.putExtra("events13", dataList.get(getAdapterPosition()).getStrAwayTeam());
//                    i.putExtra("events14", dataList.get(getAdapterPosition()).getStrDate());
//                    i.putExtra("events15", dataList.get(getAdapterPosition()).getStrTime());
//                    i.putExtra("events16", dataList.get(getAdapterPosition()).getIntHomeScore());
//                    i.putExtra("events17", dataList.get(getAdapterPosition()).getIntAwayScore());
//
//                    mContext.startActivity(i);
//                }
//            });
        }

    }
}
