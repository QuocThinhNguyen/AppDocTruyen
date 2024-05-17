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
import vn.iotstar.appdoctruyen.Adapter.TheLoaiVotesAdapter;
import vn.iotstar.appdoctruyen.model.PhanLoaiTruyen;
import vn.iotstar.appdoctruyen.model.Truyen1;
import vn.iotstar.appdoctruyen.model.TruyenVotes;

public class TheLoaiVoteFragment extends Fragment {

    View view;
    TheLoaiFragment theLoai;
    Truyen1 truyen;
    public RecyclerView rcv;
    public TheLoaiAdapter rcv_adapter;
    String email;
    public String _theloai;

    private List<TruyenVotes> mListVotes;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public TheLoaiVoteFragment() {

    }

    public static TheLoaiVoteFragment newInstance(String param1, String param2) {
        TheLoaiVoteFragment fragment = new TheLoaiVoteFragment();
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

        view= inflater.inflate(R.layout.fragment_theloai_vote, container, false);

        Anhxa();

        Intent intent=getActivity().getIntent();
        email=intent.getStringExtra("email");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(itemDecoration);

        mListVotes = new ArrayList<>();

        callApiGetTruyenVotes();

        TheLoaiFragment theLoaiFragment = (TheLoaiFragment) getActivity();
        if (theLoaiFragment != null) {
            theLoaiFragment.setOnTheLoaiSelectedListener(new TheLoaiFragment.OnTheLoaiSelectedListener() {
                @Override
                public void onTheLoaiSelected(String theLoai) {
                    _theloai = theLoai;
                    if (_theloai != null && !_theloai.isEmpty()) {
                        // Call the API to get books by the selected category
                        allApiGetTruyenVotesTheoTheLoai();
                    }
                }
            });
        }

        return view;
    }

    private void allApiGetTruyenVotesTheoTheLoai() {
        APIService.apiService.getVoteComicsByTheLoai(_theloai).enqueue(new Callback<List<TruyenVotes>>() {
            @Override
            public void onResponse(Call<List<TruyenVotes>> call, Response<List<TruyenVotes>> response) {
                mListVotes = response.body();
                TheLoaiVotesAdapter theLoaiAdapter = new TheLoaiVotesAdapter(getActivity(), mListVotes, email);
                rcv.setAdapter(theLoaiAdapter);
            }

            @Override
            public void onFailure(Call<List<TruyenVotes>> call, Throwable t) {
                //Toast.makeText(TheLoaiNewFragment.this,"Loi",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callApiGetTruyenVotes() {
        APIService.apiService.getVoteComics().enqueue(new Callback<List<TruyenVotes>>() {
            @Override
            public void onResponse(Call<List<TruyenVotes>> call, Response<List<TruyenVotes>> response) {
                if (response.isSuccessful()) {
                    mListVotes = response.body();
                    TheLoaiVotesAdapter theLoaiAdapter = new TheLoaiVotesAdapter(getActivity(),mListVotes,email);
                    rcv.setAdapter(theLoaiAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<TruyenVotes>> call, Throwable t) {

            }
        });
    }


    public void Anhxa(){
        rcv=view.findViewById(R.id.rcv_theloai_vote);
    }

}