package vn.iotstar.appdoctruyen.AdminController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import vn.iotstar.appdoctruyen.AdminAdapter.QuanLyThongKeAdapter;
import vn.iotstar.appdoctruyen.R;

public class QuanLyThongKe extends AppCompatActivity {

    private RecyclerView rcv;
    private QuanLyThongKeAdapter adapter;
    TextView tv_qltk_sltruyen,tv_qltk_slchapter,tv_qltk_slview,tv_qltk_slvote,tv_qltk_slcomment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_thong_ke);
    }
}