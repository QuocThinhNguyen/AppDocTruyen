package vn.iotstar.appdoctruyen.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import vn.iotstar.appdoctruyen.model.PhanLoaiTruyen;
import vn.iotstar.appdoctruyen.model.truyen;

public interface APIService {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    APIService apiService = new Retrofit.Builder()

            .baseUrl("http://172.172.4.28:8090/")

            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);
    @GET("truyen")
    Call<List<truyen>> getTruyenAll();


    @GET("theloai")
    Call<List<String>> getTheLoai();

    @GET("/truyennewest")
    Call<List<PhanLoaiTruyen>> getNewestBooks();


    @GET("truyen/toptruyenmoi")
    Call<List<truyen>> getTruyenMoi();
    @GET("truyen/toptruyen")
    Call<List<truyen>> getTopTruyen();

}
