package vn.iotstar.appdoctruyen.Adapter;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.API.RetrofitClient;
import vn.iotstar.appdoctruyen.R;


import vn.iotstar.appdoctruyen.TheLoaiNewFragment;
import vn.iotstar.appdoctruyen.model.Chapter;
import vn.iotstar.appdoctruyen.model.ChapterDto;
import vn.iotstar.appdoctruyen.model.LichSuDocTruyenModel;
import vn.iotstar.appdoctruyen.model.Lichsudoctruyen;
import vn.iotstar.appdoctruyen.model.TaiKhoanDto;
import vn.iotstar.appdoctruyen.model.Taikhoan;
import vn.iotstar.appdoctruyen.model.Truyen1;
import vn.iotstar.appdoctruyen.model.TruyenVotes;

import java.io.StringReader;
import java.util.List;

public class TruyenDaDocAdapter extends RecyclerView.Adapter<TruyenDaDocAdapter.TruyenDaDocViewHolder>{
    private Context context;
    //private List<Lichsudoctruyen> list;

    private List<LichSuDocTruyenModel> list;
    private TaiKhoanDto taiKhoan;


    private ChapterDto chapter;

    private Truyen1 truyen;

    String tenchaptermoinhat;

    private int id;





//     public TruyenDaDocAdapter(Context context, List<Lichsudoctruyen> list, TaiKhoanDto taikhoan) {
//         this.context = context;
//         this.list = list;
//         this.taikhoan = taikhoan;

    public TruyenDaDocAdapter(Context context, List<LichSuDocTruyenModel> list, TaiKhoanDto taiKhoan) {
        this.context = context;
        this.list = list;
        this.taiKhoan = taiKhoan;

    }

    @NonNull
    @Override
    public TruyenDaDocAdapter.TruyenDaDocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_truyendadoc,parent,false);
        return new TruyenDaDocAdapter.TruyenDaDocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TruyenDaDocAdapter.TruyenDaDocViewHolder holder, int position) {
        LichSuDocTruyenModel truyendadoc=list.get(position);
        if(truyendadoc==null){
            return;
        }

        id=truyendadoc.getIdchapter();
        getOneChapter(id);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getOneTruyen(chapter.getId());

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        Glide.with(context).load(truyen.getLinkanh()).into(holder.img_truyendadoc);
                        holder.tv_tentruyen.setText(truyen.getTentruyen());
                        holder.tv_chapterdangxem.setText("Chapter đang xem: "+truyendadoc.getIdchapter());
                        id=truyendadoc.getIdchapter();
                        getOneChapter(id);
                        getOneTruyen(chapter.getId());




                    }
                }, 500);


            }
        }, 500);

    }

    private void getOneChapter(int id) {
        APIService.apiService.getOneChapter(id).enqueue(new Callback<ChapterDto>() {
            @Override
            public void onResponse(Call<ChapterDto> call, Response<ChapterDto> response) {
                chapter = response.body();
            }

            @Override
            public void onFailure(Call<ChapterDto> call, Throwable t) {

                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(context.getApplicationContext(), "loine: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getOneTruyen(Integer chapter) {
        APIService.apiService.getOneTruyen(chapter).enqueue(new Callback<Truyen1>() {
            @Override
            public void onResponse(@NonNull Call<Truyen1> call, @NonNull Response<Truyen1> response) {
                truyen = response.body();
            }

            @Override
            public void onFailure(@NonNull Call<Truyen1> call, @NonNull Throwable t) {

                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(context.getApplicationContext(), "loi2: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getTenChapterNew(int id) {
        APIService.apiService.getTenChapterNew(id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                String jsonResponse = response.body(); // JSON từ API


                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(jsonResponse));
                reader.setLenient(true); // Đặt chế độ lenient

                // Giải mã JSON
                tenchaptermoinhat = gson.fromJson(String.valueOf(reader), String.class);

            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(context.getApplicationContext(), "loi: " +id+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class TruyenDaDocViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_truyendadoc;
        private TextView tv_tentruyen,tv_chapterdangxem;

        public TruyenDaDocViewHolder(@NonNull View itemView) {
            super(itemView);
            img_truyendadoc=itemView.findViewById(R.id.img_truyendadoc);
            tv_tentruyen=itemView.findViewById(R.id.tv_tentruyen);
            tv_chapterdangxem=itemView.findViewById(R.id.tv_chapterdangxem);
        }
    }
}
