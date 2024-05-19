package vn.iotstar.appdoctruyen.AdminAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.Noidungchapter;

public class QuanLyDNChapterAdapter extends RecyclerView.Adapter<QuanLyDNChapterAdapter.QuanLyNDChapterViewHolder>{
    private Context context;
    private List<Noidungchapter> list;

    public QuanLyDNChapterAdapter(Context context, List<Noidungchapter> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuanLyNDChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_qlthongke,parent,false);
        return new QuanLyNDChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanLyNDChapterViewHolder holder, int position) {
        Noidungchapter noiDungChapter=list.get(position);
        if(noiDungChapter==null){
            return;
        }

        holder.tv_idqlthongke.setText(""+noiDungChapter.getId());
        holder.tv_qltktentruyen.setText(noiDungChapter.getLinkanh());
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class QuanLyNDChapterViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_idqlthongke,tv_qltktentruyen;
        public QuanLyNDChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_idqlthongke=itemView.findViewById(R.id.tv_idqlthongke);
            tv_qltktentruyen=itemView.findViewById(R.id.tv_qltktentruyen);
        }
    }
}
