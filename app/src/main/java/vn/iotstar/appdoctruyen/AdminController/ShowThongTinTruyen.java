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

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.AdminAdapter.QuanLyChapterAdapter;
import vn.iotstar.appdoctruyen.R;
import vn.iotstar.appdoctruyen.model.Chapter;
import vn.iotstar.appdoctruyen.model.ChapterAdmin;
import vn.iotstar.appdoctruyen.model.TruyenVotes;
import vn.iotstar.appdoctruyen.model.truyen;

public class ShowThongTinTruyen extends AppCompatActivity implements View.OnClickListener {

    ImageView img_truyen, img_new;
    TextView tv_id;
    EditText edt_tacgia, edt_mota, edt_theloai, edt_linkanh, edt_trangthai, edt_tentruyen, edt_tenchapter_newchapter;
    Button bt_chinhsua, bt_them, bt_huy, bt_xacnhantruyen, bt_huychinhsuatruyen;

    truyen truyen1;
    int id;
    CardView cv_themchapter;
    private RecyclerView rcv;
    private QuanLyChapterAdapter adapter;
    private List<truyen> truyenList;
    private List<ChapterAdmin> chapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_thong_tin_truyen);

        AnhXa();
        Intent intent = getIntent();
        id = intent.getIntExtra("id_truyen", 1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowThongTinTruyen.this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(ShowThongTinTruyen.this, DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(itemDecoration);
        chapterList = new ArrayList<>();

        setEnable(0);
        setData();
        showChapter();
        setOnClickListener();
    }

    private void setOnClickListener() {
        img_new.setOnClickListener(this);
        bt_them.setOnClickListener(this);
        bt_huy.setOnClickListener(this);
        bt_chinhsua.setOnClickListener(this);
        bt_huychinhsuatruyen.setOnClickListener(this);
        bt_xacnhantruyen.setOnClickListener(this);
    }

    private void showChapter() {
        APIService.apiService.getChapterById(id).enqueue(new Callback<List<ChapterAdmin>>() {
            @Override
            public void onResponse(Call<List<ChapterAdmin>> call, Response<List<ChapterAdmin>> response) {
                chapterList = response.body();
                QuanLyChapterAdapter adapter = new QuanLyChapterAdapter(ShowThongTinTruyen.this, chapterList);
                rcv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ChapterAdmin>> call, Throwable throwable) {
                //Toast.makeText(ShowThongTinTruyen.this, "Không láy dc chapter", Toast.LENGTH_SHORT).show();
                Log.e("API Error", "Không láy dc chapter. Lỗi: " + throwable.getMessage(), throwable);
                Toast.makeText(ShowThongTinTruyen.this, "Không láy dc chapter. Lỗi: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData() {
        APIService.apiService.getTruyenById(id).enqueue(new Callback<List<truyen>>() {
            @Override
            public void onResponse(Call<List<truyen>> call, Response<List<truyen>> response) {
                truyenList = response.body();
                if (truyenList != null) {
                    edt_tentruyen.setText(truyenList.get(0).getTentruyen());
                    Glide.with(ShowThongTinTruyen.this).load(truyenList.get(0).getLinkanh()).into(img_truyen);
                    edt_tacgia.setText(truyenList.get(0).getTacgia());
                    edt_mota.setText(truyenList.get(0).getMota());
                    edt_theloai.setText(truyenList.get(0).getTheloai());
                    edt_trangthai.setText("" + truyenList.get(0).getTrangthai());
                    tv_id.setText("" + truyenList.get(0).getId());
                    edt_linkanh.setText(truyenList.get(0).getLinkanh());
                }
            }

            @Override
            public void onFailure(Call<List<truyen>> call, Throwable throwable) {
                Toast.makeText(ShowThongTinTruyen.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setEnable(int i) {
        if (i == 1) {
            edt_linkanh.setEnabled(true);
            edt_trangthai.setEnabled(true);
            edt_tentruyen.setEnabled(true);
            edt_mota.setEnabled(true);
            edt_theloai.setEnabled(true);
            edt_tacgia.setEnabled(true);
            bt_chinhsua.setVisibility(View.GONE);
            bt_huychinhsuatruyen.setVisibility(View.VISIBLE);
            bt_xacnhantruyen.setVisibility(View.VISIBLE);
        } else {
            edt_linkanh.setEnabled(false);
            edt_trangthai.setEnabled(false);
            edt_tentruyen.setEnabled(false);
            edt_mota.setEnabled(false);
            edt_theloai.setEnabled(false);
            edt_tacgia.setEnabled(false);
            bt_chinhsua.setVisibility(View.VISIBLE);
            bt_huychinhsuatruyen.setVisibility(View.GONE);
            bt_xacnhantruyen.setVisibility(View.GONE);
        }
    }

    private void AnhXa() {
        img_new = findViewById(R.id.img_newchapter);
        img_truyen = findViewById(R.id.img_qlt);
        bt_chinhsua = findViewById(R.id.bt_chinhsuatruyen);
        bt_huy = findViewById(R.id.bt_huy_newchapter);
        bt_them = findViewById(R.id.bt_them_newchapter);
        tv_id = findViewById(R.id.tv_qlt_id);
        edt_tacgia = findViewById(R.id.edt_qlt_tacgia);
        edt_mota = findViewById(R.id.edt_qlt_mota);
        edt_theloai = findViewById(R.id.edt_qlt_theloai);
        edt_linkanh = findViewById(R.id.edt_qlt_linkanh);
        edt_trangthai = findViewById(R.id.edt_qlt_trangthai);
        edt_tentruyen = findViewById(R.id.edt_qlt_tentruyen);
        cv_themchapter = findViewById(R.id.cv_themchapter);
        bt_xacnhantruyen = findViewById(R.id.bt_xacnhantruyen);
        bt_huychinhsuatruyen = findViewById(R.id.bt_huychinhsuatruyen);
        edt_tenchapter_newchapter = findViewById(R.id.edt_tenchapter_newchapter);
        rcv = findViewById(R.id.rcv_quanlychapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_newchapter) {
            cv_themchapter.setVisibility(View.VISIBLE);
        }
        if (v.getId() == R.id.bt_chinhsuatruyen) {
            setEnable(1);
        }
        if (v.getId() == R.id.bt_huychinhsuatruyen) {
            setEnable(0);
        }
        if (v.getId() == R.id.bt_xacnhantruyen) {
            int id = Integer.parseInt(tv_id.getText().toString());
            String tentruyen = edt_tentruyen.getText().toString();
            String tacgia = edt_tacgia.getText().toString();
            String mota = edt_mota.getText().toString();
            String theloai = edt_theloai.getText().toString();
            String linkanh = edt_linkanh.getText().toString();
            String trangthai = edt_trangthai.getText().toString();
            String key_search = removeAccent(tentruyen).trim();
            if (!tentruyen.isEmpty() && !tacgia.isEmpty() && !theloai.isEmpty() && !mota.isEmpty() && !linkanh.isEmpty()) {
                truyen1 = new truyen(tentruyen, tacgia, mota, theloai, linkanh, Integer.parseInt(trangthai), key_search);
                APIService.apiService.updateTruyen(truyen1, id).enqueue(new Callback<truyen>() {
                    @Override
                    public void onResponse(Call<truyen> call, Response<truyen> response) {
                        truyen result = response.body();
                        if (result != null) {
                            Toast.makeText(ShowThongTinTruyen.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<truyen> call, Throwable throwable) {
                        Toast.makeText(ShowThongTinTruyen.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
                cv_themchapter.setVisibility(View.GONE);
                reload();
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        }
        if(v.getId() == R.id.bt_them_newchapter){
            //String tentruyen = edt_tentruyen.getText().toString();
            truyen idtruyen = new truyen();
            idtruyen.setId(Integer.parseInt(tv_id.getText().toString()));
            String tenchapter = edt_tenchapter_newchapter.getText().toString();

            if(tenchapter.isEmpty()){
                Toast.makeText(this, "Vui lòng nhập tên chapter", Toast.LENGTH_SHORT).show();
            }else {
                Chapter chapter = new Chapter(tenchapter,null ,0,0.0);
                APIService.apiService.addChapter(id,chapter).enqueue(new Callback<Chapter>() {
                    @Override
                    public void onResponse(Call<Chapter> call, Response<Chapter> response) {
                        Chapter result = response.body();
                        if(result != null){
                            Toast.makeText(ShowThongTinTruyen.this, "Thêm chapter thành công", Toast.LENGTH_SHORT).show();
                            showChapter();
                        }
                    }

                    @Override
                    public void onFailure(Call<Chapter> call, Throwable throwable) {
                        //Toast.makeText(ShowThongTinTruyen.this, "Thêm chapter thất bại", Toast.LENGTH_SHORT).show();
                        //Log.e("API Error", "Thêm chapter thất bại. Lỗi: " + throwable.getMessage(), throwable);
                        //Toast.makeText(ShowThongTinTruyen.this, "Thêm chapter thất bại", Toast.LENGTH_SHORT).show();
                    }

                });
                reload();
            }
        }
        if (v.getId() == R.id.bt_huy_newchapter) {
            cv_themchapter.setVisibility(View.GONE);
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

    public static String removeAccent(String s) {
        s = s.toLowerCase();
        s = s.replaceAll("đ", "d");
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

}