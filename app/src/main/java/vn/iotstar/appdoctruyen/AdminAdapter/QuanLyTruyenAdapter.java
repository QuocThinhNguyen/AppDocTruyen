package vn.iotstar.appdoctruyen.AdminAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import vn.iotstar.appdoctruyen.AdminController.ShowThongTinTruyen;
import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.truyen;

public class QuanLyTruyenAdapter extends RecyclerView.Adapter<QuanLyTruyenAdapter.QuanLyTruyenViewHolder> {
    private Context context;
    private List<truyen>mList;

    public QuanLyTruyenAdapter(Context context, List<truyen> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public QuanLyTruyenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_qlthongke,parent,false);
        return new QuanLyTruyenAdapter.QuanLyTruyenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanLyTruyenViewHolder holder, int position) {
        truyen Truyen = mList.get(position);
        if (Truyen == null){
            return;
        }
        holder.tv_idtruyen.setText(""+Truyen.getId());
        holder.tv_tentruyen.setText(Truyen.getTentruyen());
        holder.ll_rcv_qltruyen.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(), ShowThongTinTruyen.class);
            intent.putExtra("id_truyen",Truyen.getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(mList!=null){
            return mList.size();
        }
        return 0;
    }

    public static class QuanLyTruyenViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_idtruyen,tv_tentruyen;
        private LinearLayout ll_rcv_qltruyen;
        public QuanLyTruyenViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_idtruyen=itemView.findViewById(R.id.tv_idqlthongke);
            tv_tentruyen=itemView.findViewById(R.id.tv_qltktentruyen);
            ll_rcv_qltruyen=itemView.findViewById(R.id.ll_rcv_qlthongke);
        }
    }
}
