package vn.iotstar.appdoctruyen.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.iotstar.appdoctruyen.BinhLuanCuaToi;
import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.BinhLuanCuaToiDto;
import vn.iotstar.appdoctruyen.model.TruyenVotes;

public class BinhLuanCuaToiAdapter extends RecyclerView.Adapter<BinhLuanCuaToiAdapter.BinhLuanCuaToiViewHolder>{

    private Context context;
    private List<BinhLuanCuaToiDto> list;
    private String email;

    public BinhLuanCuaToiAdapter(Context context, List<BinhLuanCuaToiDto> list, String email) {
        this.context = context;
        this.list = list;
        this.email = email;
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

        holder.tv_tentruyen.setText(binhluan.getIdchapter());
        holder.tv_chapter.setText("Chapter: "+binhluan.getIdchapter().toString());
        holder.tv_noidung.setText("Nội dung: "+binhluan.getNoidung().toString());
        holder.tv_ngaydang.setText(("Ngày đăng: "+binhluan.getNgaydang().toString()));
        //Glide.with(this.context).load(truyenVotes.getLinkanh()).into(holder.img_theloai);
    }


    @Override
    public int getItemCount() {
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
