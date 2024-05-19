package vn.iotstar.appdoctruyen;

import android.content.Intent;
import android.hardware.lights.LightState;
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

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.API.RetrofitClient;
import vn.iotstar.appdoctruyen.Adapter.truyenAdapter;
import vn.iotstar.appdoctruyen.model.truyen;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements View.OnClickListener {
    ImageSlider imageSlider;
    View view;
    RecyclerView rc1;
    RecyclerView rc2;
    RecyclerView rc3;
    truyenAdapter truyenAdapter;
    truyenAdapter truyenMoiAdapter;
    truyenAdapter truyenTopAdapter;
    List<truyen> truyenList;

    List<truyen> truyenMoi;
    List<truyen> truyenTop;

    TextView tv_theloai,tv_xephang;

    String email;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private static final String ARG_PARAM3 = "email";

    public HomeFragment() {
    }


    public static HomeFragment newInstance(String email) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM3, email);
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
        view = inflater.inflate(R.layout.fragment_home, container, false);
        imageSlider = (ImageSlider) view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.image1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image4, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        AnhXa();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager3=new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);

        rc1.setLayoutManager(linearLayoutManager);
        rc2.setLayoutManager(linearLayoutManager2);
        rc3.setLayoutManager(linearLayoutManager3);

        truyenList = new ArrayList<>();
        truyenAdapter = new truyenAdapter(getActivity(), truyenList, email);
        rc1.setAdapter(truyenAdapter);
        truyenMoi = new ArrayList<>();
        truyenAdapter = new truyenAdapter(getActivity(), truyenMoi,email);
        rc2.setAdapter(truyenMoiAdapter);
        truyenTop = new ArrayList<>();
        truyenAdapter = new truyenAdapter(getActivity(), truyenTop,email);
        rc3.setAdapter(truyenTopAdapter);

        GetTruyen();
        setOnClickListener();
        return view;
    }

    private void setOnClickListener() {
        tv_theloai.setOnClickListener(this);
        tv_xephang.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

        //switch (v.getId()){
        if (v.getId() == R.id.tv_theloai) {
            Intent dialog_box3 = new Intent(getActivity(), TheLoaiFragment.class);
            dialog_box3.putExtra("email", email);
            startActivity(dialog_box3);
        }
        if (v.getId() == R.id.tv_xephang) {
            Intent dialog_box4 = new Intent(getActivity(), XepHangFragment.class);
            dialog_box4.putExtra("email", email);
            startActivity(dialog_box4);
        }
    }

    private void AnhXa(){
        rc1 = (RecyclerView) view.findViewById(R.id.rv3);

        rc2 = (RecyclerView) view.findViewById(R.id.rv);
        rc3 = (RecyclerView) view.findViewById(R.id.rv2);

        tv_theloai = (TextView) view.findViewById(R.id.tv_theloai);
        tv_xephang = (TextView) view.findViewById(R.id.tv_xephang);

    }
    private void GetTruyen() {
        APIService.apiService.getTruyenAll().enqueue(new Callback<List<truyen>>() {
            @Override
            public void onResponse(@NonNull Call<List<truyen>> call, @NonNull Response<List<truyen>> response) {
                truyenList = response.body();
                truyenAdapter categoryAdapter = new truyenAdapter(getContext(), truyenList,email);
                rc1.setAdapter(categoryAdapter);
            }

            @Override

            public void onFailure(@NonNull Call<List<truyen>> call, @NonNull Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(getContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
        APIService.apiService.getTruyenMoi().enqueue(new Callback<List<truyen>>() {
            @Override
            public void onResponse(@NonNull Call<List<truyen>> call, @NonNull Response<List<truyen>> response) {
                truyenMoi = response.body();
                truyenAdapter truyenAdapter1 = new truyenAdapter(getContext(), truyenMoi, email);
                rc2.setAdapter(truyenAdapter1);
            }

            @Override

            public void onFailure(@NonNull Call<List<truyen>> call, @NonNull Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(getContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
        APIService.apiService.getTopTruyen().enqueue(new Callback<List<truyen>>() {
            @Override
            public void onResponse(@NonNull Call<List<truyen>> call, @NonNull Response<List<truyen>> response) {
                truyenTop = response.body();
                truyenAdapter truyenAdapter2 = new truyenAdapter(getContext(), truyenTop,email);
                rc3.setAdapter(truyenAdapter2);
            }

            @Override

            public void onFailure(@NonNull Call<List<truyen>> call, @NonNull Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(getContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

}