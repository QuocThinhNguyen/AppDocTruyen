package vn.iotstar.appdoctruyen;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import vn.iotstar.appdoctruyen.model.Taikhoan;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaiKhoanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaiKhoanFragment extends Fragment implements View.OnClickListener {

    TextView tv_tongngaydiemdanh,tv_tk_diem,tv_binhluancuatoi,tv_danhgiacuatoi;
    TextView tv_doimatkhau,tv_dangxuat,tv_tttk,tv_username;
    ImageView img_tk_avatar;
    String email;
    Taikhoan taiKhoan;
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "email";

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

        args.putString(ARG_PARAM1, email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            email = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tai_khoan, container, false);

        Anhxa();

        ThongTinTaiKhoan tki = new ThongTinTaiKhoan();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        tki.email = user.getEmail();
        tki.gettaikhoan(user.getEmail());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
             tv_username.setText(tki.tk.getHoten().toString());
            }
        }, 500);
        setOnClickListener();
        return  view;
    }

    private void Anhxa(){
        tv_tongngaydiemdanh=view.findViewById(R.id.tv_tongngaydiemdanh);
        img_tk_avatar=view.findViewById(R.id.img_tk_avatar);
        tv_binhluancuatoi=view.findViewById(R.id.tv_binhluancuatoi);
        tv_danhgiacuatoi=view.findViewById(R.id.tv_danhgiacuatoi);
        tv_doimatkhau=view.findViewById(R.id.tv_doimatkhau);
        tv_dangxuat=view.findViewById(R.id.tv_dangxuat);
        tv_tttk=view.findViewById(R.id.tv_tttk);
        tv_username=view.findViewById(R.id.tv_username);
    }

    private void setOnClickListener(){
        tv_danhgiacuatoi.setOnClickListener(this);
        tv_binhluancuatoi.setOnClickListener(this);
        tv_doimatkhau.setOnClickListener(this);
        tv_dangxuat.setOnClickListener(this);
        tv_tttk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId()== R.id.tv_binhluancuatoi) {
            Intent dialog_box = new Intent(getActivity(), BinhLuanCuaToi.class);
            startActivity(dialog_box);
        }
        if (view.getId()== R.id.tv_danhgiacuatoi) {
            Intent dialog_box1 = new Intent(getActivity(), DanhGiaCuaToi.class);
            startActivity(dialog_box1);
        }
        if (view.getId()== R.id.tv_doimatkhau) {
            Intent dialog_box2 = new Intent(getActivity(), DoiMatKhau.class);
            startActivity(dialog_box2);
        }
        else if (view.getId()== R.id.tv_dangxuat) {
            FirebaseAuth m = FirebaseAuth.getInstance();
            m.signOut();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            Toast.makeText(getActivity().getApplicationContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            getActivity().finish();
        }
        else if (view.getId()==R.id.tv_tttk){
            Intent dialog_box3 = new Intent(getActivity(), ThongTinTaiKhoan.class);
            startActivity(dialog_box3);
        }
    }
}