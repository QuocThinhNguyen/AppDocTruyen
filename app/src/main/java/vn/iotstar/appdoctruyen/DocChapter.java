package vn.iotstar.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.Adapter.BinhLuanAdapter;
import vn.iotstar.appdoctruyen.Adapter.BinhLuanTruyenAdapter;
import vn.iotstar.appdoctruyen.Adapter.DocChapterAdapter;
import vn.iotstar.appdoctruyen.Adapter.truyenAdapter;
import vn.iotstar.appdoctruyen.model.BinhLuanDto;
import vn.iotstar.appdoctruyen.model.BinhLuanTruyenDto;
import vn.iotstar.appdoctruyen.model.ChapterDto;
import vn.iotstar.appdoctruyen.model.DanhGiaDto;
import vn.iotstar.appdoctruyen.model.NoiDungChapterDto;
import vn.iotstar.appdoctruyen.model.TaiKhoanDto;
import vn.iotstar.appdoctruyen.model.Taikhoan;
import vn.iotstar.appdoctruyen.model.truyen;

public class DocChapter extends AppCompatActivity implements View.OnClickListener  {
    private RecyclerView rcv,rcv_binhluan;
    private DocChapterAdapter rcv_adapter;
    private BinhLuanAdapter rcv_binhluanadapter;

    public int id_chapter,id_truyen;
    TextView tv_tenchapter,tv_sosaochapter;
    ImageView img_backdoctruyen, img_pre, img_next;
    Button bt_danhgia, bt_binhluan;
    EditText edt_binhluan;
    ChapterDto chapterDto;
    List<NoiDungChapterDto> truyenList;
    List<BinhLuanTruyenDto> binhLuanTruyen;
    List<TaiKhoanDto> listtaiKhoanTruyen;
    List<ChapterDto> listten;
    List<Integer> listid;
    int idtaikhoan;
    int kt = 0;
    double sosaochapter;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    RatingBar rtb;
    Integer minIdChapter, maxIdChapter;
    public interface MinMaxCallback {
        void onMinMaxReady(int minId, int maxId);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docchapter);
        Anhxa();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);

        Intent intent=getIntent();
        id_chapter=intent.getIntExtra("id_chapter",0);
        id_truyen=intent.getIntExtra("id_truyen",0);
        getMinMax(new MinMaxCallback() {
            @Override
            public void onMinMaxReady(int minId, int maxId) {
                minIdChapter = minId;
                maxIdChapter = maxId;
                // Thiết lập onClickListener sau khi minIdChapter và maxIdChapter đã được cập nhật
                setOnClickListener();
            }
        });

        if(id_chapter!=0){
            GetTenChapter();
        }
        truyenList = new ArrayList<>();
        rcv_adapter=new DocChapterAdapter(truyenList,DocChapter.this);
        rcv.setAdapter(rcv_adapter);
        GetNoiDungChapter();
        recyclerViewBinhLuan();

    }
    private void Anhxa(){
        rcv=findViewById(R.id.rcv_docchapter);
        rcv_binhluan=findViewById(R.id.rcv_binhluan);
        tv_tenchapter=findViewById(R.id.tv_tenchapter);
        img_backdoctruyen=findViewById(R.id.img_backdoctruyen);
        img_next=findViewById(R.id.img_next);
        img_pre=findViewById(R.id.img_pre);
        edt_binhluan=findViewById(R.id.edt_binhluan);
        bt_binhluan=findViewById(R.id.bt_binhluan);
        bt_danhgia=findViewById(R.id.bt_danhgia);
        rtb=findViewById(R.id.rtb);
        tv_sosaochapter=findViewById(R.id.tv_sosaochapter);
    }
    private void GetTenChapter(){
        APIService.apiService.getTenById(id_chapter).enqueue(new Callback<List<ChapterDto>>() {
            @Override
            public void onResponse(Call<List<ChapterDto>> call, Response<List<ChapterDto>> response) {
                listten = response.body();
                chapterDto = listten.get(0);
                tv_tenchapter.setText(chapterDto.getTenchapter());
                Log.e("MinIdChapter", String.valueOf(minIdChapter));
            }
            @Override
            public void onFailure(Call<List<ChapterDto>> call, Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
            }

        });
        APIService.apiService.updateLuotXemChapter(id_chapter).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
            }
        });
    }
    private void GetNoiDungChapter(){
        APIService.apiService.getNoiDungChapterById(id_chapter).enqueue(new Callback<List<NoiDungChapterDto>>() {
            @Override
            public void onResponse(Call<List<NoiDungChapterDto>> call, Response<List<NoiDungChapterDto>> response) {
                truyenList = response.body();
                DocChapterAdapter categoryAdapter = new DocChapterAdapter(truyenList, DocChapter.this);
                rcv.setAdapter(categoryAdapter);
            }

            @Override
            public void onFailure(Call<List<NoiDungChapterDto>> call, Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
            }
        });
    }
    private void recyclerViewBinhLuan(){
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv_binhluan.setLayoutManager(linearLayoutManager2);
        binhLuanTruyen = new ArrayList<>();
        rcv_binhluanadapter=new BinhLuanAdapter(DocChapter.this,binhLuanTruyen);
        rcv_binhluan.setAdapter(rcv_binhluanadapter);
        GetBinhLuanTheoIdChapter();
    }
    /*private void GetBinhLuanTheoIdChapter(){
        APIService.apiService.getBinhLuanTheoIdChapter(id_truyen).enqueue(new Callback<List<BinhLuanTruyenDto>>() {
            @Override
            public void onResponse(Call<List<BinhLuanTruyenDto>> call, Response<List<BinhLuanTruyenDto>> response) {
                binhLuanTruyen = response.body();
                BinhLuanTruyenAdapter binhLuanTruyenAdapter = new BinhLuanTruyenAdapter(DocChapter.this, binhLuanTruyen);
                rcv_binhluan.setAdapter(binhLuanTruyenAdapter);
            }

            @Override
            public void onFailure(Call<List<BinhLuanTruyenDto>> call, Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(DocChapter.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }*/
    private void GetBinhLuanTheoIdChapter() {
        APIService.apiService.getBinhLuanTheoIdChapter(id_chapter).enqueue(new Callback<List<BinhLuanTruyenDto>>() {
            @Override
            public void onResponse(Call<List<BinhLuanTruyenDto>> call, Response<List<BinhLuanTruyenDto>> response) {
                if (response.isSuccessful()) {
                    List<BinhLuanTruyenDto> responseData = response.body();
                    if (responseData != null && !responseData.isEmpty()) {
                        binhLuanTruyen.clear();
                        binhLuanTruyen.addAll(responseData);
                        rcv_binhluanadapter.notifyDataSetChanged();
                    } else {
                        Log.e("API_CALL", "Response body is empty");
                        Toast.makeText(DocChapter.this, "No comments found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("API_CALL", "Response not successful: " + response.errorBody());
                    Toast.makeText(DocChapter.this, "Failed to load comments", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BinhLuanTruyenDto>> call, Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(DocChapter.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMinMax(final MinMaxCallback callback) {
        APIService.apiService.getMinIdChapter(id_truyen).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int minId = response.body();
                APIService.apiService.getMaxIdChapter(id_truyen).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        int maxId = response.body();
                        // Gọi callback khi cả minId và maxId đã được cập nhật
                        callback.onMinMaxReady(minId, maxId);
                    }
                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.e("API_CALL", "Failed to fetch maxId from API", t);
                    }
                });
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("API_CALL", "Failed to fetch minId from API", t);
            }
        });
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_pre) {
            if (id_chapter == minIdChapter) {
                Toast.makeText(this, "Bạn đang ở Chapter đầu tiên!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, DocChapter.class);
                intent.putExtra("email", user.getEmail());
                intent.putExtra("id_chapter", id_chapter - 1);
                intent.putExtra("id_truyen", id_truyen);
                startActivity(intent);
            }
        } else if (view.getId() == R.id.img_next) {
            if (id_chapter == maxIdChapter) {
                Toast.makeText(this, "Bạn đang ở Chapter cuối", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent1 = new Intent(this, DocChapter.class);
                intent1.putExtra("email", user.getEmail());
                intent1.putExtra("id_chapter", id_chapter + 1);
                intent1.putExtra("id_truyen", id_truyen);
                startActivity(intent1);
            }
        } else if (view.getId() == R.id.img_backdoctruyen) {
            Intent intent2 = new Intent(this, CTTruyen.class);
            intent2.putExtra("email", user.getEmail());
            intent2.putExtra("id_truyen", id_truyen);
            startActivity(intent2);
            finish();
        } else if (view.getId() == R.id.bt_binhluan) {
            if (edt_binhluan.getText().length() != 0) {

                if (edt_binhluan.getText().length() != 0) {
                    //Nhớ thay email
                    APIService.apiService.findByEmail1("dangtruong@gmail.com").enqueue(new Callback<List<TaiKhoanDto>>() {
                        @Override
                        public void onResponse(Call<List<TaiKhoanDto>> call, Response<List<TaiKhoanDto>> response) {
                            List<TaiKhoanDto> listtaiKhoanTruyen = response.body();
                            if (listtaiKhoanTruyen != null && !listtaiKhoanTruyen.isEmpty()) {
                                idtaikhoan = listtaiKhoanTruyen.get(0).getId();

                                BinhLuanDto binhLuanDto = new BinhLuanDto(id_chapter, idtaikhoan, edt_binhluan.getText() + "");
                                APIService.apiService.themBinhLuan(binhLuanDto).enqueue(new Callback<BinhLuanDto>() {
                                    @Override
                                    public void onResponse(Call<BinhLuanDto> call, Response<BinhLuanDto> response) {
                                        edt_binhluan.setText("");
                                        recyclerViewBinhLuan();
                                    }

                                    @Override
                                    public void onFailure(Call<BinhLuanDto> call, Throwable t) {
                                        Log.e("API_CALL", "Failed to fetch data from API", t);
                                        Toast.makeText(DocChapter.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                Toast.makeText(DocChapter.this, "Không tìm thấy tài khoản", Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onFailure(Call<List<TaiKhoanDto>> call, Throwable t) {
                            Log.e("API_CALL", "Failed to fetch data from API", t);
                            Toast.makeText(DocChapter.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(this, "Vui lòng nhập bình luận!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    else if (view.getId() == R.id.bt_danhgia) {
            APIService.apiService.findByEmail1("quangthien11@gmail.com").enqueue(new Callback<List<TaiKhoanDto>>() {
                @Override
                public void onResponse(Call<List<TaiKhoanDto>> call, Response<List<TaiKhoanDto>> response) {
                    List<TaiKhoanDto> listtaiKhoanTruyen = response.body();
                    if (listtaiKhoanTruyen != null && !listtaiKhoanTruyen.isEmpty()) {
                        idtaikhoan = listtaiKhoanTruyen.get(0).getId();
                        APIService.apiService.getIDByChapterAndTK(id_chapter, idtaikhoan).enqueue(new Callback<List<Integer>>() {
                            @Override
                            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                                listid = response.body();
                                if (listid != null && !listid.isEmpty()){
                                    kt = listid.get(0);


                                }
                                else {
                                    Toast.makeText(DocChapter.this, "Sai", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<List<Integer>> call, Throwable t) {
                                Log.e("API_CALL", "Failed to fetch data from API", t);
                                Toast.makeText(DocChapter.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(DocChapter.this, "Không tìm thấy tài khoản", Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onFailure(Call<List<TaiKhoanDto>> call, Throwable t) {
                    Log.e("API_CALL", "Failed to fetch data from API", t);
                    Toast.makeText(DocChapter.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


            double sosao = rtb.getRating();
            if (kt != 0) {
                APIService.apiService.updateDanhGia(id_chapter, idtaikhoan, sosao).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("API_CALL", "Failed to fetch data from API", t);
                        Toast.makeText(DocChapter.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });
                APIService.apiService.getAverageRatingByIdChapter(id_chapter).enqueue(new Callback<Double>() {

                    @Override
                    public void onResponse(Call<Double> call, Response<Double> response) {
                        double sosao = response.body();
                        tv_sosaochapter.setText(""+sosao);
                    }

                    @Override
                    public void onFailure(Call<Double> call, Throwable t) {
                        Log.e("API_CALL", "Failed to fetch data from API", t);
                        Toast.makeText(DocChapter.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } /*else {
                            DanhGiaDto binhLuanDto = new DanhGiaDto(id_chapter, idtaikhoan, sosao);
                            APIService.apiService.themDanhGia(binhLuanDto).enqueue(new Callback<DanhGiaDto>() {
                                @Override
                                public void onResponse(Call<DanhGiaDto> call, Response<DanhGiaDto> response) {

                                }

                                @Override
                                public void onFailure(Call<DanhGiaDto> call, Throwable t) {
                                    Log.e("API_CALL", "Failed to fetch data from API", t);
                                    Toast.makeText(DocChapter.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                setData();
            }*/

        }
    }

    private void setData(){



    }

    private void setOnClickListener(){
        img_backdoctruyen.setOnClickListener(this);
        img_pre.setOnClickListener(this);
        img_next.setOnClickListener(this);
        bt_binhluan.setOnClickListener(this);
        bt_danhgia.setOnClickListener(this);
    }
}