package vn.iotstar.appdoctruyen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.TruyenVotes;

public class VotesAdapter extends RecyclerView.Adapter<VotesAdapter.VotesViewHolder> {
    private Context context;
    private List<TruyenVotes> list;
    private String email;

    public VotesAdapter(Context context, List<TruyenVotes> list, String email) {
        this.context = context;
        this.list = list;
        this.email = email;
    }

    @NonNull
    @Override
    public VotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_theloainew,parent,false);
        return new VotesAdapter.VotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VotesViewHolder holder, int position) {
        TruyenVotes truyenVotes = list.get(position);
        if (truyenVotes == null){
            return;
        }
        holder.tv_tentruyen.setText(truyenVotes.getTentruyen());
        holder.tv_pl.setText("Đánh giá: "+truyenVotes.getSosaotb());
        Glide.with(this.context).load(truyenVotes.getLinkanh()).into(holder.img_theloai);
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }


    public class VotesViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_tentruyen,tv_pl;
        private LinearLayout ll_rcv_theloai;
        private ImageView img_theloai;

        public VotesViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tentruyen=itemView.findViewById(R.id.tv_theloai_tentruyen);
            tv_pl=itemView.findViewById(R.id.tv_theloai_ngaydang);
            ll_rcv_theloai=itemView.findViewById(R.id.ll_rcv_theloai);
            img_theloai=itemView.findViewById(R.id.img_theloai);
        }
    }

}
