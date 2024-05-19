package vn.iotstar.appdoctruyen;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.Adapter.BinhLuanTruyenAdapter;
import vn.iotstar.appdoctruyen.Adapter.truyenAdapter;
import vn.iotstar.appdoctruyen.model.BinhLuanTruyenDto;
import vn.iotstar.appdoctruyen.model.Taikhoan;
import vn.iotstar.appdoctruyen.model.Thongke;
import vn.iotstar.appdoctruyen.model.Truyen1;
import vn.iotstar.appdoctruyen.model.truyen;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChiTietTruyenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChiTietTruyenFragment extends Fragment {

    Truyen1 truyen;
    View view;
    TextView tv_danhgia,tv_tongluotxem,tv_tongbinhluan,tv_mota;
    Thongke thongKe;
    Taikhoan taiKhoan;
    String email;
    private RecyclerView rcv_binhluan;
    private BinhLuanTruyenAdapter rcv_adapter;
    int id_truyen;
    double tbDanhGia;
    Long tongLuotXem, tongBinhLuan;
    private List<BinhLuanTruyenDto> listBinhLuan;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChiTietTruyenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChiTietTruyenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChiTietTruyenFragment newInstance(String param1, String param2) {
        ChiTietTruyenFragment fragment = new ChiTietTruyenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_chi_tiet_truyen, container, false);
        Anhxa();
        Intent intent=getActivity().getIntent();
        id_truyen=intent.getIntExtra("id_truyen",0);
        email=intent.getStringExtra("email");
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rcv_binhluan.setLayoutManager(linearLayoutManager);
        listBinhLuan = new ArrayList<>();
        rcv_adapter = new BinhLuanTruyenAdapter(getActivity(), listBinhLuan);
        rcv_binhluan.setAdapter(rcv_adapter);
        getChiTietTruyen();
        return view;
    }
    private void Anhxa(){

        tv_danhgia= view.findViewById(R.id.tv_danhgia);
        tv_tongluotxem=view.findViewById(R.id.tv_tongluotxem);
        tv_tongbinhluan=view.findViewById(R.id.tv_tongbinhluan);
        tv_mota=view.findViewById(R.id.tv_motatruyen);
        rcv_binhluan=view.findViewById(R.id.rcv_binhluan);

    }
    private void getChiTietTruyen() {
        APIService.apiService.getAverageRatingByTruyenId(id_truyen).enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                tbDanhGia = response.body();
                tv_danhgia.setText(""+tbDanhGia);
            }
            public void onFailure(@NonNull Call<Double> call, @NonNull Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
            }

        });
        APIService.apiService.countBinhLuanByTruyenId(id_truyen).enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                tongBinhLuan = response.body();
                tv_tongbinhluan.setText(""+tongBinhLuan);
            }

            @Override
            public void onFailure(Call<Long> call, Throwable  t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
            }
        });
        APIService.apiService.sumSoluotxemByTruyenId(id_truyen).enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                tongLuotXem = response.body();
                tv_tongluotxem.setText(""+tongLuotXem);
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
            }
        });
        APIService.apiService.getBinhLuan(id_truyen).enqueue(new Callback<List<BinhLuanTruyenDto>>() {
            @Override
            public void onResponse(Call<List<BinhLuanTruyenDto>> call, Response<List<BinhLuanTruyenDto>> response) {
                listBinhLuan = response.body();
                BinhLuanTruyenAdapter binhLuanTruyenAdapter = new BinhLuanTruyenAdapter(getContext(), listBinhLuan);
                rcv_binhluan.setAdapter(binhLuanTruyenAdapter);
            }

            @Override
            public void onFailure(Call<List<BinhLuanTruyenDto>> call, Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(getContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}