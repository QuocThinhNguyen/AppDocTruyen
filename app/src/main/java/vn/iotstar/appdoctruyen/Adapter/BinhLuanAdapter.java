package vn.iotstar.appdoctruyen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.BinhLuanTruyenDto;
import vn.iotstar.appdoctruyen.model.Binhluan;
import vn.iotstar.appdoctruyen.model.Taikhoan;

public class BinhLuanAdapter extends RecyclerView.Adapter<BinhLuanAdapter.BinhLuanViewHolder>{
    private Context context;
    private List<BinhLuanTruyenDto> list;


    public BinhLuanAdapter(Context context, List<BinhLuanTruyenDto> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BinhLuanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_binhluan,parent,false);
        return new BinhLuanAdapter.BinhLuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BinhLuanViewHolder holder, int position) {
        BinhLuanTruyenDto binhLuan=list.get(position);
        if(binhLuan==null){
            return;
        }
        Glide.with(context).load(binhLuan.getLinkAnh()).into(holder.img_avatar);
        holder.tv_taikhoan_bl.setText(binhLuan.getEmail());
        holder.tv_nd_bl.setText(binhLuan.getNoidung());
        holder.tv_ngaybinhluan.setText(binhLuan.getNgaydang());
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class BinhLuanViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_taikhoan_bl,tv_nd_bl,tv_ngaybinhluan,tv_;
        private ImageView img_avatar;

        public BinhLuanViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nd_bl=itemView.findViewById(R.id.tv_nd_bl);
            tv_taikhoan_bl=itemView.findViewById(R.id.tv_taikhoan_bl);
            tv_ngaybinhluan=itemView.findViewById(R.id.tv_ngaybinhluan);
            img_avatar=itemView.findViewById(R.id.img_avatar);
        }
    }
}
