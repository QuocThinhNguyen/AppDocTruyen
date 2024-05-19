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

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.Adapter.ChapterAdapter;
import vn.iotstar.appdoctruyen.Adapter.truyenAdapter;
import vn.iotstar.appdoctruyen.model.Chapter;
import vn.iotstar.appdoctruyen.model.ChapterDto;
import vn.iotstar.appdoctruyen.model.truyen;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChapterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChapterFragment extends Fragment {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    ChapterDto chapter;
    String email;
    View view;
    TextView tv_chapter,tv_ngaydang,tv_luotxem;
    private RecyclerView rcv;
    private ChapterAdapter rcv_adapter;
    int id_truyen;
    private List<ChapterDto> list;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChapterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChapterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChapterFragment newInstance(String param1, String param2) {
        ChapterFragment fragment = new ChapterFragment();
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
        view= inflater.inflate(R.layout.fragment_chapter, container, false);
        Anhxa();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);
        Intent intent=getActivity().getIntent();
        /*email=intent.getStringExtra("email");*/
        id_truyen=intent.getIntExtra("id_truyen",0);
        list = new ArrayList<>();
        rcv_adapter = new ChapterAdapter(getActivity(), list, user.getEmail());
        rcv.setAdapter(rcv_adapter);
        GetChapter();
        return view;
    }
    private void Anhxa(){

        tv_chapter=view.findViewById(R.id.tv_chapter);
        tv_ngaydang=view.findViewById(R.id.tv_ngaydang);
        tv_luotxem=view.findViewById(R.id.tv_luotxem);
        rcv=view.findViewById(R.id.rcv_chapter);

    }
    private void GetChapter() {
        APIService.apiService.getChapterById(id_truyen).enqueue(new Callback<List<ChapterDto>>() {
            @Override
            public void onResponse(Call<List<ChapterDto>> call, Response<List<ChapterDto>> response) {
                list = response.body();
                ChapterAdapter chapterAdapter = new ChapterAdapter(getContext(), list, user.getEmail());
                rcv.setAdapter(chapterAdapter);
            }

            @Override
            public void onFailure(Call<List<ChapterDto>> call, Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(getContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}