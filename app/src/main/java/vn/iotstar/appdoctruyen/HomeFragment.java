package vn.iotstar.appdoctruyen;

import android.content.Context;
import android.content.Intent;
import android.hardware.lights.LightState;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import  vn.iotstar.appdoctruyen.R;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.API.RetrofitClient;
import vn.iotstar.appdoctruyen.Adapter.truyenAdapter;
import vn.iotstar.appdoctruyen.model.truyen;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    ImageSlider imageSlider;
    View view, headerLayout;
    RecyclerView rc1;
    RecyclerView rc2;
    RecyclerView rc3;
    truyenAdapter truyenAdapter;
    truyenAdapter truyenMoiAdapter;
    truyenAdapter truyenTopAdapter;
    List<truyen> truyenList;

    List<truyen> truyenMoi;
    List<truyen> truyenTop;

    TextView tv_theloai,tv_xephang,tv_emailhome;

    String email;
    NavigationView navi;
    FirebaseUser user;

    Menu menu;
    MenuItem menuquantri;

    Button btn_login, btn_logout;
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

        navi.setNavigationItemSelectedListener(this);
        navi.bringToFront();
        //Xét quyền hiển thị chức năng
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user!= null) {
            email = user.getEmail();

            if (user.getEmail() == "admin@gmail.com") {
                menuquantri.setVisible(true);
            } else menuquantri.setVisible(false);
            tv_emailhome.setText(user.getEmail());
            tv_emailhome.setVisibility(view.VISIBLE);
            btn_logout.setVisibility(view.VISIBLE);
            btn_login.setVisibility(view.GONE);
        }
        else{
            menuquantri.setVisible(false);
            tv_emailhome.setVisibility(view.GONE);
            btn_logout.setVisibility(view.GONE);
            btn_login.setVisibility(view.VISIBLE);
        }

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
        btn_login.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.bt_dnhome) {
            Intent dialog_box = new Intent(getActivity(), Login.class);
            startActivity(dialog_box);
            getActivity().finish();
        }

        //switch (v.getId()){
        if (v.getId() == R.id.tv_theloai ) {
            Intent dialog_box3 = new Intent(getActivity(), TheLoaiFragment.class);
            dialog_box3.putExtra("email",email );
            startActivity(dialog_box3);
        }
        if (v.getId() == R.id.tv_xephang ) {
            Intent dialog_box4 = new Intent(getActivity(), XepHangFragment.class);
            dialog_box4.putExtra("email", email);
            startActivity(dialog_box4);
        }
        if (v.getId() == R.id.bt_dxhome)
        {
            FirebaseAuth m = FirebaseAuth.getInstance();
            m.signOut();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            Toast.makeText(getActivity().getApplicationContext(),"Đăng xuất thành công",Toast.LENGTH_SHORT).show();
            startActivity(intent);
            getActivity().finish();
        }
    }

    private void AnhXa(){
        rc1 = (RecyclerView) view.findViewById(R.id.rv3);

        rc2 = (RecyclerView) view.findViewById(R.id.rv);
        rc3 = (RecyclerView) view.findViewById(R.id.rv2);

        tv_theloai = (TextView) view.findViewById(R.id.tv_theloai);
        tv_xephang = (TextView) view.findViewById(R.id.tv_xephang);

        navi = (NavigationView) view.findViewById(R.id.menu);
        menu = navi.getMenu();
        menuquantri = menu.findItem(R.id.it_chucnangquantri);

        headerLayout= navi.inflateHeaderView(R.layout.menuheader);
        btn_login= (Button) headerLayout.findViewById(R.id.bt_dnhome);
        btn_logout=headerLayout.findViewById(R.id.bt_dxhome);
        tv_emailhome = headerLayout.findViewById(R.id.tv_emailhome);


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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

//            case R.id.it_quanlytaikhoan:
//                Intent dialog_box = new Intent(getActivity(), QuanLyTaiKhoan.class);
//                startActivity(dialog_box);
//                break;
//            case R.id.it_quanlytruyen:
//                Intent dialog_box1 = new Intent(getActivity(), QuanLyTruyen.class);
//                startActivity(dialog_box1);
//                break;
//            case R.id.it_quanlybinhluan:
//                Intent dialog_box2 = new Intent(getActivity(), QuanLyBinhLuan.class);
//                startActivity(dialog_box2);
//                break;
//            case R.id.it_quanlythongke:
//                Intent dialog_box3 = new Intent(getActivity(), QuanLyThongKe.class);
//                startActivity(dialog_box3);
//                break;
        if (menuItem.getItemId() == R.id.it_xephang)
        {
            Intent dialog_box4 = new Intent(getActivity(), XepHangFragment.class);
            startActivity(dialog_box4);
        }
        if (menuItem.getItemId() == R.id.it_theloai) {
            Intent dialog_box5 = new Intent(getActivity(), TheLoaiFragment.class);
            startActivity(dialog_box5);
        }

        return true;
    }
}