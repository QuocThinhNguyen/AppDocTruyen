package vn.iotstar.appdoctruyen;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.Adapter.TheLoaiAdapter;
import vn.iotstar.appdoctruyen.model.PhanLoaiTruyen;
import vn.iotstar.appdoctruyen.model.Truyen1;

public class TheLoaiNewFragment extends Fragment {
    View view;
    TheLoaiFragment theLoai;
    Truyen1 truyen;
    public RecyclerView rcv;
    public TheLoaiAdapter rcv_adapter;
    String email;
    public String _theloai;

    private List<PhanLoaiTruyen> mListPL;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public TheLoaiNewFragment() {
        // Required empty public constructor
    }

    public static TheLoaiNewFragment newInstance(String param1, String param2) {
        TheLoaiNewFragment fragment = new TheLoaiNewFragment();
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

        view= inflater.inflate(R.layout.fragment_theloai_new, container, false);

        Anhxa();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(itemDecoration);

        mListPL = new ArrayList<>();

        Intent intent=getActivity().getIntent();
        email=intent.getStringExtra("email");

        callApiGetTruyenMoiNhat();

        TheLoaiFragment theLoaiFragment = (TheLoaiFragment) getActivity();
        if (theLoaiFragment != null) {
            theLoaiFragment.setOnTheLoaiSelectedListener(new TheLoaiFragment.OnTheLoaiSelectedListener() {
                @Override
                public void onTheLoaiSelected(String theLoai) {
                    _theloai = theLoai;
                    if (_theloai != null && !_theloai.isEmpty()) {
                        // Call the API to get books by the selected category
                        callApiGetTruyenMoiNhatTheoTheLoai();
                    }
                }
            });
        }

        return view;
    }

    private void callApiGetTruyenMoiNhatTheoTheLoai() {
        APIService.apiService.getNewestComicsByTheLoai(_theloai).enqueue(new Callback<List<PhanLoaiTruyen>>() {
            @Override
            public void onResponse(Call<List<PhanLoaiTruyen>> call, Response<List<PhanLoaiTruyen>> response) {
                mListPL = response.body();
                TheLoaiAdapter theLoaiAdapter = new TheLoaiAdapter(getActivity(), mListPL, email);
                rcv.setAdapter(theLoaiAdapter);
            }

            @Override
            public void onFailure(Call<List<PhanLoaiTruyen>> call, Throwable t) {
                //Toast.makeText(TheLoaiNewFragment.this,"Loi",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callApiGetTruyenMoiNhat(){
        APIService.apiService.getNewestComics().enqueue(new Callback<List<PhanLoaiTruyen>>() {
            @Override
            public void onResponse(Call<List<PhanLoaiTruyen>> call, Response<List<PhanLoaiTruyen>> response) {
                mListPL = response.body();
                TheLoaiAdapter theLoaiAdapter = new TheLoaiAdapter(getActivity(),mListPL,email);
                rcv.setAdapter(theLoaiAdapter);
            }

            @Override
            public void onFailure(Call<List<PhanLoaiTruyen>> call, Throwable t) {
                //Toast.makeText(TheLoaiNewFragment.this,"Loi",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Anhxa(){
        rcv=view.findViewById(R.id.rcv_theloai_new);
    }

}