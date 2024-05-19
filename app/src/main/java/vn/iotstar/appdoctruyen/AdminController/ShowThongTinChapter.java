package vn.iotstar.appdoctruyen.AdminController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.AdminAdapter.QuanLyDNChapterAdapter;
import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.Chapter;
import vn.iotstar.appdoctruyen.model.ChapterAdmin;
import vn.iotstar.appdoctruyen.model.Noidungchapter;
import vn.iotstar.appdoctruyen.model.truyen;

public class ShowThongTinChapter extends AppCompatActivity implements View.OnClickListener {
    ImageView img_truyen, img_new;
    TextView tv_id, tv_danhgia, tv_luotxem, tv_ngaydang, tv_tentruyen;
    EditText edt_tenchapter, edt_linkanh;
    Button bt_chinhsua, bt_them, bt_huy, bt_xacnhanchaper, bt_huychinhsuachapter;
    truyen truyen1;
    Chapter chapter;
    int id;
    CardView cv_themndchapter;
    private RecyclerView rcv;
    private QuanLyDNChapterAdapter adapter;
    private List<ChapterAdmin> list;
    private List<truyen> truyenList;
    private List<Noidungchapter> noidungchapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_thong_tin_chapter);

        AnhXa();

        Intent intent = getIntent();
        id = intent.getIntExtra("id_chapter", 1);

        //Toast.makeText(this, "id: " + id, Toast.LENGTH_SHORT).show();
//        chapter=db.getOneChapter(id);
//        truyen=db.getTruyenById(chapter.getIdtruyen());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowThongTinChapter.this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(ShowThongTinChapter.this, DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(itemDecoration);
        list = new ArrayList<>();
        noidungchapterList = new ArrayList<>();

        setEnable(0);
        setData();
        showThongTinChapter();

        setOnClickListener();
    }

    private void showThongTinChapter() {
        APIService.apiService.getLinkChapterById(id).enqueue(new Callback<List<Noidungchapter>>() {
            @Override
            public void onResponse(Call<List<Noidungchapter>> call, Response<List<Noidungchapter>> response) {
                noidungchapterList = response.body();
                QuanLyDNChapterAdapter adapter = new QuanLyDNChapterAdapter(ShowThongTinChapter.this, noidungchapterList);
                rcv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Noidungchapter>> call, Throwable throwable) {
                //Toast.makeText(ShowThongTinChapter.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Lỗi kết nối. Lỗi: " + throwable.getMessage(), throwable);
                Toast.makeText(ShowThongTinChapter.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setOnClickListener() {
        img_new.setOnClickListener(this);
        bt_them.setOnClickListener(this);
        bt_huy.setOnClickListener(this);
        bt_chinhsua.setOnClickListener(this);
        bt_huychinhsuachapter.setOnClickListener(this);
        bt_xacnhanchaper.setOnClickListener(this);
    }

    private void setData() {
        APIService.apiService.getChapterContentById(id).enqueue(new Callback<List<ChapterAdmin>>() {
            @Override
            public void onResponse(Call<List<ChapterAdmin>> call, Response<List<ChapterAdmin>> response) {
                list = response.body();
                if (list != null) {
                    //Glide.with(ShowThongTinChapter.this).load(truyen.getLinkhanh()).into(img_truyen);
                    tv_id.setText(""+list.get(0).getId());
                    //tv_tentruyen.setText(truyen.getTentruyen());
                    edt_tenchapter.setText(list.get(0).getTenchapter());
                    tv_danhgia.setText(""+list.get(0).getDanhgia());
                    tv_luotxem.setText(""+list.get(0).getSoluotxem());
                    tv_ngaydang.setText(list.get(0).getNgaydang());
                }
            }

            @Override
            public void onFailure(Call<List<ChapterAdmin>> call, Throwable throwable) {

            }
        });

        APIService.apiService.getTruyenById(id).enqueue(new Callback<List<truyen>>() {
            @Override
            public void onResponse(Call<List<truyen>> call, Response<List<truyen>> response) {
                truyenList = response.body();
                if (truyenList != null) {
                    Glide.with(ShowThongTinChapter.this).load(truyenList.get(0).getLinkanh()).into(img_truyen);
                    tv_tentruyen.setText(truyenList.get(0).getTentruyen());
                }
            }

            @Override
            public void onFailure(Call<List<truyen>> call, Throwable throwable) {
                Toast.makeText(ShowThongTinChapter.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setEnable(int i) {
        if (i == 1) {
            edt_tenchapter.setEnabled(true);
            bt_xacnhanchaper.setVisibility(View.VISIBLE);
            bt_huychinhsuachapter.setVisibility(View.VISIBLE);
            bt_chinhsua.setVisibility(View.GONE);
        } else {
            edt_tenchapter.setEnabled(false);
            bt_xacnhanchaper.setVisibility(View.GONE);
            bt_huychinhsuachapter.setVisibility(View.GONE);
            bt_chinhsua.setVisibility(View.VISIBLE);
        }
    }

    private void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    private void AnhXa() {
        img_new = findViewById(R.id.img_newnoidungchapter);
        img_truyen = findViewById(R.id.img_qlc);
        bt_chinhsua = findViewById(R.id.bt_chinhsuachapter);
        bt_huy = findViewById(R.id.bt_huy_newndchapter);
        bt_them = findViewById(R.id.bt_them_newndchapter);
        tv_id = findViewById(R.id.tv_qlc_id);
        edt_tenchapter = findViewById(R.id.edt_qlc_tenchapter);
        edt_linkanh = findViewById(R.id.edt_linkanh_newndchapter);
        cv_themndchapter = findViewById(R.id.cv_themndchapter);
        bt_xacnhanchaper = findViewById(R.id.bt_xacnhanchapter);
        bt_huychinhsuachapter = findViewById(R.id.bt_huychinhsuachapter);
        rcv = findViewById(R.id.rcv_quanlynoidungchapter);
        tv_danhgia = findViewById(R.id.tv_qlc_danhgia);
        tv_luotxem = findViewById(R.id.tv_qlc_luotxem);
        tv_ngaydang = findViewById(R.id.tv_qlc_ngaydang);
        tv_tentruyen = findViewById(R.id.tv_qlc_tentruyen);
        rcv = findViewById(R.id.rcv_quanlynoidungchapter);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.img_newnoidungchapter){
            cv_themndchapter.setVisibility(View.VISIBLE);
        }
        if (v.getId() == R.id.bt_huy_newndchapter){
            cv_themndchapter.setVisibility(View.GONE);
        }
        if (v.getId() == R.id.bt_them_newndchapter){
            String linkanh=edt_linkanh.getText().toString().trim();
            if (linkanh.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập link ảnh", Toast.LENGTH_SHORT).show();
            }else {
                Noidungchapter noidungchapter = new Noidungchapter(linkanh);
                APIService.apiService.addLinkChapter(id, noidungchapter).enqueue(new Callback<Noidungchapter>() {
                    @Override
                    public void onResponse(Call<Noidungchapter> call, Response<Noidungchapter> response) {
                        Noidungchapter result = response.body();
                        if (result != null) {
                            Toast.makeText(ShowThongTinChapter.this, "Thêm nội dung chapter thành công", Toast.LENGTH_SHORT).show();
                            showThongTinChapter();
                            cv_themndchapter.setVisibility(View.GONE);
                        }
                        else {
                            Toast.makeText(ShowThongTinChapter.this, "Thêm nội dung chapter thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Noidungchapter> call, Throwable throwable) {
                        //Toast.makeText(ShowThongTinChapter.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                        Log.e("API Error", "Lỗi kết nối. Lỗi: " + throwable.getMessage(), throwable);
                        Toast.makeText(ShowThongTinChapter.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                    }
                });
                reload();
            }
        }
    }
}