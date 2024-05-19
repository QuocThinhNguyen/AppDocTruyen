package vn.iotstar.appdoctruyen.AdminAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.AdminController.QuanLyTaiKhoan;
import vn.iotstar.appdoctruyen.AdminController.ShowThongTinTaiKhoan;
import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.Taikhoan;

public class QuanLyTaiKhoanAdapter extends RecyclerView.Adapter<QuanLyTaiKhoanAdapter.QuanLyTaiKhoanViewHolder> {
    private Context context;
    private List<Taikhoan> list;
    private Integer trangthai;


    public QuanLyTaiKhoanAdapter(Context context, List<Taikhoan> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuanLyTaiKhoanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_qltaikhoan,parent,false);
        return new QuanLyTaiKhoanAdapter.QuanLyTaiKhoanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanLyTaiKhoanViewHolder holder, int position) {
        Taikhoan taiKhoan=list.get(position);
        if(taiKhoan==null){
            return;
        }

        holder.tv_id.setText(""+taiKhoan.getId());
        holder.tv_email.setText(taiKhoan.getEmail());
        int trangthai=taiKhoan.getLoaitk();
        if(trangthai!=2){
            holder.bt_hien.setVisibility(View.GONE);
            holder.bt_an.setVisibility(View.VISIBLE);
            holder.tv_trangthai.setText("Hoạt động");
        }else {
            holder.bt_hien.setVisibility(View.VISIBLE);
            holder.bt_an.setVisibility(View.GONE);
            holder.tv_trangthai.setText("Bị khóa");
        }

        holder.ll_rcv_qltaikhoan.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(), ShowThongTinTaiKhoan.class);
            intent.putExtra("email",taiKhoan.getEmail());
            holder.itemView.getContext().startActivity(intent);
        });

        holder.bt_an.setOnClickListener(view -> {
            //db.updateTrangThai(taiKhoan.getId(),2);
            // @PUT("taikhoan/{id}/{loaitk}")
            //    Call<Taikhoan> updateLoaiTk(@Path("id") int id, @Path("loaitk") int loaitk);
            APIService.apiService.updateLoaiTk(taiKhoan.getId(),2).enqueue(new Callback<Taikhoan>() {
                @Override
                public void onResponse(Call<Taikhoan> call, Response<Taikhoan> response) {
                    Toast.makeText(context, "Khóa tài khoản thành công", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Taikhoan> call, Throwable t) {
                    Toast.makeText(context, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                }
            });
            holder.bt_hien.setVisibility(View.VISIBLE);
            holder.bt_an.setVisibility(View.GONE);
            holder.tv_trangthai.setText("Bị khóa");
        });
        holder.bt_hien.setOnClickListener(view -> {
            //db.updateTrangThai(taiKhoan.getId(),0);
            //@PUT("taikhoan/{id}/{loaitk}")
            //    Call<Taikhoan> updateLoaiTk(@Path("id") int id, @Path("loaitk") int loaitk);
            APIService.apiService.updateLoaiTk(taiKhoan.getId(),0).enqueue(new Callback<Taikhoan>() {
                @Override
                public void onResponse(Call<Taikhoan> call, Response<Taikhoan> response) {
                    Toast.makeText(context, "Mở khóa thành công", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Taikhoan> call, Throwable t) {
                    Toast.makeText(context, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                }
            });
            holder.bt_hien.setVisibility(View.GONE);
            holder.bt_an.setVisibility(View.VISIBLE);
            holder.tv_trangthai.setText("Hoạt động");
        });


//        APIService.apiService.getTaiKhoan().enqueue(new Callback<List<Taikhoan>>() {
//            @Override
//            public void onResponse(Call<List<Taikhoan>> call, Response<List<Taikhoan>> response) {
//                list = response.body();
//                if (list != null) {
//                    if (list.get(0).getLoaitk()!= 2){
//                        holder.bt_hien.setVisibility(View.GONE);
//                        holder.bt_an.setVisibility(View.VISIBLE);
//                        holder.tv_trangthai.setText("Hoạt động");
//                    }else{
//                        holder.bt_hien.setVisibility(View.VISIBLE);
//                        holder.bt_an.setVisibility(View.GONE);
//                        holder.tv_trangthai.setText("Bị khóa");
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Taikhoan>> call, Throwable throwable) {
//                Toast.makeText(context, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
//            }
//        });
//        if(trangthai!=2){
//            holder.bt_hien.setVisibility(View.GONE);
//            holder.bt_an.setVisibility(View.VISIBLE);
//            holder.tv_trangthai.setText("Hoạt động");
//        }else {
//            holder.bt_hien.setVisibility(View.VISIBLE);
//            holder.bt_an.setVisibility(View.GONE);
//            holder.tv_trangthai.setText("Bị khóa");
//        }
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class QuanLyTaiKhoanViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_id,tv_email,tv_trangthai;
        private Button bt_an,bt_hien;
        private LinearLayout ll_rcv_qltaikhoan;
        public QuanLyTaiKhoanViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id=itemView.findViewById(R.id.tv_idqltaikhoan);
            tv_email=itemView.findViewById(R.id.tv_emailqltaikhoan);
            tv_trangthai=itemView.findViewById(R.id.tv_trangthaiqltaikhoan);
            bt_an=itemView.findViewById(R.id.bt_anqltaikhoan);
            bt_hien=itemView.findViewById(R.id.bt_hienqltaikhoan);
            ll_rcv_qltaikhoan=itemView.findViewById(R.id.ll_rcv_qltaikhoan);
        }
    }
}
