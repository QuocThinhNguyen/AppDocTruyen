package vn.iotstar.appdoctruyen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.Adapter.truyenAdapter;
import vn.iotstar.appdoctruyen.model.LichSuDocTruyenModel;
import vn.iotstar.appdoctruyen.model.Lichsudoctruyen;
import vn.iotstar.appdoctruyen.model.TaiKhoanDto;
import vn.iotstar.appdoctruyen.model.Taikhoan;
import vn.iotstar.appdoctruyen.model.Truyen1;
import vn.iotstar.appdoctruyen.Adapter.TruyenDaDocAdapter;
import vn.iotstar.appdoctruyen.model.truyen;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LichSuDocFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LichSuDocFragment extends Fragment {

    ProgressDialog dlog;
    View view;
    String email;
    TaiKhoanDto taiKhoan;
    Truyen1 truyen;
    private List<LichSuDocTruyenModel> lichSuDocTruyenList;
    public RecyclerView rcv;
    public TruyenDaDocAdapter rcv_adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LichSuDocFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LichSuDocFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LichSuDocFragment newInstance(String param1, String param2) {
        LichSuDocFragment fragment = new LichSuDocFragment();
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
        view=inflater.inflate(R.layout.fragment_lich_su_doc_truyen, container, false);
        Anhxa();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email= user.getEmail();
        ThongTinTaiKhoan thongTinTaiKhoan = new ThongTinTaiKhoan();
        thongTinTaiKhoan.email= email;
        thongTinTaiKhoan.gettaikhoan(email);
        // Sử dụng Handler để trì hoãn hành động trong luồng chính



        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email= user.getEmail();
        //ThongTinTaiKhoan thongTinTaiKhoan = new ThongTinTaiKhoan();
        thongTinTaiKhoan.email= email;
        thongTinTaiKhoan.gettaikhoan(email);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);

        DividerItemDecoration item = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(item);
        dlog = new ProgressDialog(getContext());
        dlog.setMessage("Đang xử lý!");
        dlog.setCancelable(false);
        dlog.show();

        // Sử dụng Handler để trì hoãn hành động trong luồng chính
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                taiKhoan = thongTinTaiKhoan.tk;
                recyclerViewTruyenDaDoc();
                dlog.dismiss();

            }
        }, 5000);

//        Intent intent=getActivity().getIntent();
//        email=intent.getStringExtra("email");
        /*taiKhoan=db.getTaiKhoan(email);*/



        return view;
    }



    public void recyclerViewTruyenDaDoc() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);

        lichSuDocTruyenList = new ArrayList<>();
        //GetTruyenDaDoc();

        rcv_adapter=new TruyenDaDocAdapter(getActivity(), lichSuDocTruyenList, taiKhoan);
        rcv.setAdapter(rcv_adapter);
    }
    private void GetTruyenDaDoc() {
        APIService.apiService.getListTruyenDaDoc(taiKhoan.getId()).enqueue(new Callback<List<LichSuDocTruyenModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<LichSuDocTruyenModel>> call, @NonNull Response<List<LichSuDocTruyenModel>> response) {
                lichSuDocTruyenList = response.body();

                rcv_adapter  = new TruyenDaDocAdapter(getContext(), lichSuDocTruyenList, taiKhoan);

                //rcv_adapter  = new TruyenDaDocAdapter(getActivity(), lichSuDocTruyenList, taiKhoan);

                rcv.setAdapter(rcv_adapter);
            }

            @Override

            public void onFailure(@NonNull Call<List<LichSuDocTruyenModel>> call, @NonNull Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(getContext(), "loihi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void Anhxa(){
        rcv=view.findViewById(R.id.rcv_truyendadoc);
    }
}