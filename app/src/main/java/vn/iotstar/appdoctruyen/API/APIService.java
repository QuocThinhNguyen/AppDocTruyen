package vn.iotstar.appdoctruyen.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import vn.iotstar.appdoctruyen.model.PhanLoaiTruyen;
import vn.iotstar.appdoctruyen.model.TruyenVotes;
import vn.iotstar.appdoctruyen.model.truyen;

public interface APIService {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    APIService apiService = new Retrofit.Builder()

            .baseUrl("http://192.168.1.110:8090/")

            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);
    @GET("truyen")
    Call<List<truyen>> getTruyenAll();


    @GET("theloai")
    Call<List<String>> getTheLoai();

    @GET("truyen/truyennewest")
    Call<List<PhanLoaiTruyen>> getNewestComics();

    @GET("truyen/truyennewest/{theloai}")
    Call<List<PhanLoaiTruyen>> getNewestComicsByTheLoai(@Path("theloai") String theloai);

    @GET("truyen/truyenvotes")
    Call<List<TruyenVotes>> getVoteComics();

    @GET("truyen/truyenvotes/{theloai}")
    Call<List<TruyenVotes>> getVoteComicsByTheLoai(@Path("theloai") String theloai);

    @GET("truyen/truyenview")
    Call<List<TruyenVotes>> getViewComics();

    @GET("truyen/truyenview/{theloai}")
    Call<List<TruyenVotes>> getViewComicsByTheLoai(@Path("theloai") String theloai);



    @GET("truyen/toptruyenmoi")
    Call<List<truyen>> getTruyenMoi();
    @GET("truyen/toptruyen")
    Call<List<truyen>> getTopTruyen();

}
