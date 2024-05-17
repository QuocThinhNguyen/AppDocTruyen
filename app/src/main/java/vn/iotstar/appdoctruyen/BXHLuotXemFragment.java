package vn.iotstar.appdoctruyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.Adapter.LuotXemApdapter;
import vn.iotstar.appdoctruyen.Adapter.TheLoaiVotesAdapter;
import vn.iotstar.appdoctruyen.model.PhanLoaiTruyen;
import vn.iotstar.appdoctruyen.model.TruyenVotes;


public class BXHLuotXemFragment extends Fragment {

    View view;
    private RecyclerView rcv;
    private LuotXemApdapter rcv_adapter;
    String email;
    private List<TruyenVotes> mListVotes;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BXHLuotXemFragment() {

    }

    public static BXHLuotXemFragment newInstance(String param1, String param2) {
        BXHLuotXemFragment fragment = new BXHLuotXemFragment();
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
        view= inflater.inflate(R.layout.fragment_b_x_h_luot_xem, container, false);

        Anhxa();

        Intent intent=getActivity().getIntent();
        email=intent.getStringExtra("email");

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(itemDecoration);

        mListVotes = new ArrayList<>();

        callApiGetTruyenView();

        return view;
    }

    private void callApiGetTruyenView() {
        APIService.apiService.getViewComics().enqueue(new Callback<List<TruyenVotes>>() {
            @Override
            public void onResponse(Call<List<TruyenVotes>> call, Response<List<TruyenVotes>> response) {
                if (response.isSuccessful()) {
                    mListVotes = response.body();
                    LuotXemApdapter theLoaiAdapter = new LuotXemApdapter(getActivity(),mListVotes,email);
                    rcv.setAdapter(theLoaiAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<TruyenVotes>> call, Throwable t) {

            }
        });
    }
    private void Anhxa(){
        rcv=view.findViewById(R.id.rcv_xh_view);
    }
}