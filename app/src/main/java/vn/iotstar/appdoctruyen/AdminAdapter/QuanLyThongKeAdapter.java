package vn.iotstar.appdoctruyen.AdminAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.Thongke;

public class QuanLyThongKeAdapter extends RecyclerView.Adapter<QuanLyThongKeAdapter.QuanLyThongKeViewHolder>{
    private Context context;
    private List<Thongke> list;

    public QuanLyThongKeAdapter(Context context, List<Thongke> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuanLyThongKeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_qlthongke,parent,false);
        return new QuanLyThongKeAdapter.QuanLyThongKeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanLyThongKeViewHolder holder, int position) {

        Thongke thongKe=list.get(position);
        if(thongKe==null){
            return;
        }

        holder.tv_idqlthongke.setText(""+thongKe.getId());
        //Truyen truyen=db.getTruyenById(thongKe.getIdtruyen());

//        holder.tv_qltktentruyen.setText(truyen.getTentruyen());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class QuanLyThongKeViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_idqlthongke,tv_qltktentruyen;
        private LinearLayout ll_rcv_qlthongke;

        public QuanLyThongKeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_idqlthongke=itemView.findViewById(R.id.tv_idqlthongke);
            tv_qltktentruyen=itemView.findViewById(R.id.tv_qltktentruyen);
            ll_rcv_qlthongke=itemView.findViewById(R.id.ll_rcv_qlthongke);
        }
    }
}
