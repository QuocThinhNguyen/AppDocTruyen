package vn.iotstar.appdoctruyen.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.iotstar.appdoctruyen.DanhGiaCuaToi;
import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.BinhLuanCuaToiDto;
import vn.iotstar.appdoctruyen.model.DanhGiaCuaToiDto;
import vn.iotstar.appdoctruyen.model.Danhgia;

public class DanhGiaCuaToiAdapter extends RecyclerView.Adapter<DanhGiaCuaToiAdapter.DanhGiaCuaToiViewHolder>{

    private Context context;
    private List<DanhGiaCuaToiDto> list;
    private Integer layout;

    public DanhGiaCuaToiAdapter(Context context, Integer layout, List<DanhGiaCuaToiDto> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }
    @NonNull
    @Override
    public DanhGiaCuaToiAdapter.DanhGiaCuaToiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_danhgiacuatoi,parent,false);
        return new DanhGiaCuaToiAdapter.DanhGiaCuaToiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhGiaCuaToiViewHolder holder, int position) {
        DanhGiaCuaToiDto danhgia = list.get(position);
        if (danhgia  == null){
            return;
        }

        holder.tv_tentruyen.setText(danhgia.getIdchapter().toString());
        holder.tv_chapter.setText("Chapter: "+danhgia.getIdchapter());
        holder.tv_pl.setText("Đánh giá: "+danhgia.getSosao()+ "sao");
        holder.tv_ngaydang.setText("Ngày đăng: "+danhgia.getNgaydanhgia());
        //Glide.with(this.context).load(truyenVotes.getLinkanh()).into(holder.img_theloai);
    }


    @Override
    public int getItemCount() {
        if (list!=null)
            return list.size();
        return 0;
    }

    public class DanhGiaCuaToiViewHolder extends RecyclerView.ViewHolder{
        TextView tv_tentruyen, tv_chapter,tv_pl, tv_ngaydang;
        ImageView img_truyen;

        public DanhGiaCuaToiViewHolder(@NonNull View itemView) {

            super(itemView);
            tv_tentruyen = itemView.findViewById(R.id.tv_tong_tentruyen);
            tv_chapter = itemView.findViewById(R.id.tv_tong_tenchapter);
            tv_pl = itemView.findViewById(R.id.tv_tong_pl);
            tv_ngaydang = itemView.findViewById(R.id.tv_tong_ngaydang);
            img_truyen = itemView.findViewById(R.id.img_tong_truyen);
        }
    }
}