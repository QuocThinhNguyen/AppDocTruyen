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

import java.util.ArrayList;
import java.util.List;

import vn.iotstar.appdoctruyen.AdminController.ShowThongTinChapter;
import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.Chapter;
import vn.iotstar.appdoctruyen.model.ChapterAdmin;

public class QuanLyChapterAdapter extends RecyclerView.Adapter<QuanLyChapterAdapter.QuanLyChapterViewHolder> {
    private Context context;
    private List<ChapterAdmin> list;

    public QuanLyChapterAdapter(Context context, List<ChapterAdmin> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuanLyChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_qlthongke, parent, false);
        return new QuanLyChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanLyChapterViewHolder holder, int position) {
        ChapterAdmin chapter = list.get(position);
        if (chapter == null) {
            return;
        }
        holder.tv_idqlthongke.setText("" + chapter.getId());
        holder.tv_qltktentruyen.setText(chapter.getTenchapter());
        holder.ll_rcv_qlthongke.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(), ShowThongTinChapter.class);
            intent.putExtra("id_chapter",chapter.getId());
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class QuanLyChapterViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_idqlthongke, tv_qltktentruyen;
        private LinearLayout ll_rcv_qlthongke;

        public QuanLyChapterViewHolder(@NonNull View itemView) {

            super(itemView);
            tv_idqlthongke = itemView.findViewById(R.id.tv_idqlthongke);
            tv_qltktentruyen = itemView.findViewById(R.id.tv_qltktentruyen);
            ll_rcv_qlthongke = itemView.findViewById(R.id.ll_rcv_qlthongke);
        }
    }
}
