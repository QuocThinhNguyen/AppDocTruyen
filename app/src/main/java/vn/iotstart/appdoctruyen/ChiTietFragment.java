package vn.iotstart.appdoctruyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChiTietFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChiTietFragment extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChiTietFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChiTietFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChiTietFragment newInstance(String param1, String param2) {
        ChiTietFragment fragment = new ChiTietFragment();
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


        return inflater.inflate(R.layout.fragment_chi_tiet, container, false);
    }


//    private void recyclerViewBinhLuan(){
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
//        rcv_binhluan.setLayoutManager(linearLayoutManager);
//        ArrayList<BinhLuan> listBinhLuan=db.getBinhLuanTruyen(id_truyen);
//        rcv_adapter=new BinhLuanTruyenAdapter(getActivity(),listBinhLuan,db);
//        rcv_binhluan.setAdapter(rcv_adapter);
//    }
}