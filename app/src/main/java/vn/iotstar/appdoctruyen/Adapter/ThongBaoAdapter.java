package vn.iotstar.appdoctruyen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.Thongbao;

public class ThongBaoAdapter extends RecyclerView.Adapter<ThongBaoAdapter.ThongBaoViewHolder>{
    private Context context;
    private ArrayList<Thongbao> list;

    public ThongBaoAdapter(Context context, ArrayList<Thongbao> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ThongBaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_thongbao,parent,false);
        return new ThongBaoAdapter.ThongBaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongBaoViewHolder holder, int position) {
        Thongbao thongBao=list.get(position);
        if(thongBao==null){
            return;
        }

        holder.tv_ndtb.setText(thongBao.getNoidung());
        holder.tv_tieude.setText(thongBao.getTieude());
        holder.tv_ngaydangtb.setText(thongBao.getNgaydang());
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class ThongBaoViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_tieude,tv_ndtb,tv_ngaydangtb;

        public ThongBaoViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ndtb=itemView.findViewById(R.id.tv_ndtb);
            tv_ngaydangtb=itemView.findViewById(R.id.tv_ngaydangtb);
            tv_tieude=itemView.findViewById(R.id.tv_tieudetb);
        }
    }
}
