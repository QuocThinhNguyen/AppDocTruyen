package vn.iotstar.appdoctruyen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import vn.iotstar.appdoctruyen.model.Taikhoan;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaiKhoanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaiKhoanFragment extends Fragment {

    TextView tv_tk_email,tv_tk_lv,tv_tongngaydiemdanh,tv_tk_diem,tv_tk_sotruyen,tv_tk_sobinhluan,tv_tk_sodanhgia,tv_binhluancuatoi,tv_danhgiacuatoi;
    TextView tv_doimatkhau,tv_dangxuat,tv_tttk;
    ImageView img_tk_avatar;
    String email;
    Taikhoan taiKhoan;
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM3 = "email";

    // TODO: Rename and change types of parameters


    public TaiKhoanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param email Parameter 1.

     * @return A new instance of fragment TaiKhoanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaiKhoanFragment newInstance(String email) {
        TaiKhoanFragment fragment = new TaiKhoanFragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM3, email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            email = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getContext().getApplicationContext(), email,Toast.LENGTH_SHORT).show();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tai_khoan, container, false);
    }
}