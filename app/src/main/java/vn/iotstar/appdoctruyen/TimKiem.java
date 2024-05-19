package vn.iotstar.appdoctruyen;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.appdoctruyen.API.APIService;
import vn.iotstar.appdoctruyen.Adapter.TheLoaiAdapter;
import vn.iotstar.appdoctruyen.Adapter.TimKiemAdapter;
import vn.iotstar.appdoctruyen.Adapter.TruyenDaDocAdapter;
import vn.iotstar.appdoctruyen.model.Lichsudoctruyen;
import vn.iotstar.appdoctruyen.model.Model_TimKiem;
import vn.iotstar.appdoctruyen.model.PhanLoaiTruyen;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public class TimKiem extends AppCompatActivity implements View.OnClickListener{

    EditText edt_search;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    ArrayList<String> listtheloai;

    ArrayList<Model_TimKiem> listtimkiem;
    TextView tv_trong;
    ImageView img_giongnoi;
    ScrollView srv_danhsach;
    String email;

    public String textTheLoai;
    public String textSearch="";
    private RecyclerView rcv_timkiem;
    private TimKiemAdapter rcv_adapter;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);

        Anhxa();

        Intent intent=getIntent();
        email=intent.getStringExtra("email");

        listtheloai = new ArrayList<>();
        getTheLoai();
        listtheloai.add(0,"Tất cả");

        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,listtheloai);
        autoCompleteTextView.setText(listtheloai.get(0));
        autoCompleteTextView.setAdapter(adapterItems);
        textTheLoai=listtheloai.get(0);

        setOnClickListener();
        Search();
    }

    private void getTheLoai() {
        APIService.apiService.getTheLoai().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listtheloai = (ArrayList<String>) response.body();
                } else {
                    Log.e("API_CALL", "Failed to fetch data from API");
                    Toast.makeText(getApplicationContext(), "Failed to fetch data from API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                Log.e("API_CALL", "Failed to fetch data from API", t);
                Toast.makeText(getApplicationContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }
    public void editTextSearch(String textSearch){
        if(textSearch.equals("")){
            Toast.makeText(this, "Không có sản phẩm nào", Toast.LENGTH_SHORT).show();
            tv_trong.setVisibility(View.VISIBLE);
            srv_danhsach.setVisibility(View.GONE);
        }else{
            tv_trong.setVisibility(View.GONE);
            srv_danhsach.setVisibility(View.VISIBLE);
            String txt = removeAccent(textSearch);
            //recyclerViewTruyen(txt);
        }

    }



//    private void recyclerViewTruyen(String textSearch) {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        rcv_timkiem.setLayoutManager(linearLayoutManager);
//
//        listtimkiem = new ArrayList<>();
//        getListTimKiem();
//        if(listtimkiem.size() == 0){
//            Toast.makeText(this, "Không có truyện cần tìm!!!", Toast.LENGTH_SHORT).show();
//            tv_trong.setVisibility(View.VISIBLE);
//            srv_danhsach.setVisibility(View.GONE);
//        }else {
//            getListTimKiem();
//            rcv_adapter = new TimKiemAdapter(this,listtimkiem,email);
//            rcv_timkiem.setAdapter(rcv_adapter);
//        }
//
//    }

//    private void getListTimKiem() {
//        APIService.apiService.getListTimKiem(textSearch).enqueue(new Callback<ArrayList<Model_TimKiem>>() {
//            @Override
//            public void onResponse(@NonNull Call<ArrayList<Model_TimKiem>> call, @NonNull Response<ArrayList<Model_TimKiem>> response) {
//                listtimkiem = response.body();
//                rcv_adapter  = new TimKiemAdapter(getContext(), listtimkiem, email);
//                rcv.setAdapter(rcv_adapter);
//            }
//
//            @Override
//
//            public void onFailure(@NonNull Call<ArrayList<Model_TimKiem>> call, @NonNull Throwable t) {
//                Log.e("API_CALL", "Failed to fetch data from API", t);
//                Toast.makeText(getContext(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//        });
//    }




    public static String removeAccent(String s){
        s=s.toLowerCase();
        s=s.replaceAll("đ", "d");
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public void Search(){
        edt_search.setOnClickListener(v -> {
            textSearch = edt_search.getText().toString();
            editTextSearch(textSearch);
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item=adapterView.getItemAtPosition(i).toString();
                textTheLoai=item;
                editTextSearch(textSearch);
                if(item==""){
                    Toast.makeText(getApplicationContext(),"Thể loại: Tất cả",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Thể loại: "+item,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Anhxa() {
        edt_search=findViewById(R.id.edt_search);
        autoCompleteTextView=findViewById(R.id.auto_complete_txt);
        tv_trong=findViewById(R.id.tv_trong);
        rcv_timkiem=findViewById(R.id.rcv_timkiem);
        img_giongnoi=findViewById(R.id.img_giongnoi);
        srv_danhsach=findViewById(R.id.srv_danhsach);

    }

    private void setOnClickListener(){
        img_giongnoi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_giongnoi)
        {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
            }
            catch (Exception e) {
                Toast.makeText(this, " " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                edt_search.setText(Objects.requireNonNull(result).get(0));
            }
        }
    }
}