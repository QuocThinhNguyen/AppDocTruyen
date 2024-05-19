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

import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.BinhLuanCuaToiDto;

public class BinhLuanCuaToiAdapter extends RecyclerView.Adapter<BinhLuanCuaToiAdapter.BinhLuanCuaToiViewHolder>{

    private Context context;
    private List<BinhLuanCuaToiDto> list;
    private Integer layout;

    public BinhLuanCuaToiAdapter(Context context, Integer layout, List<BinhLuanCuaToiDto> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }
    @NonNull
    @Override
    public BinhLuanCuaToiAdapter.BinhLuanCuaToiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_binhluancuatoi,parent,false);
        return new BinhLuanCuaToiAdapter.BinhLuanCuaToiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BinhLuanCuaToiViewHolder holder, int position) {
        BinhLuanCuaToiDto binhluan = list.get(position);
        if (binhluan  == null){
            return;
        }

        holder.tv_tentruyen.setText(binhluan.getIdchapter().toString());
        holder.tv_chapter.setText("Chapter: "+binhluan.getIdchapter());
        holder.tv_noidung.setText("Nội dung: "+binhluan.getNoidung());
        holder.tv_ngaydang.setText("Ngày đăng: "+binhluan.getNgaydang());
        //Glide.with(this.context).load(truyenVotes.getLinkanh()).into(holder.img_theloai);
    }


    @Override
    public int getItemCount() {
        if (list!=null)
            return list.size();
        return 0;
    }

    public class BinhLuanCuaToiViewHolder extends RecyclerView.ViewHolder{
        TextView tv_tentruyen, tv_chapter,tv_noidung, tv_ngaydang;
        ImageView img_truyen;

        public BinhLuanCuaToiViewHolder(@NonNull View itemView) {

            super(itemView);
            tv_tentruyen = itemView.findViewById(R.id.tv_tentruyen);
            tv_chapter = itemView.findViewById(R.id.tv_chapter);
            tv_noidung = itemView.findViewById(R.id.tv_binhluan);
            tv_ngaydang = itemView.findViewById(R.id.tv_ngaydang);
            img_truyen = itemView.findViewById(R.id.img_truyen);
        }
    }
}