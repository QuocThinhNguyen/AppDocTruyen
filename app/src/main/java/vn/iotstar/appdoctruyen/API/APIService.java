package vn.iotstar.appdoctruyen.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.http.Field;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import vn.iotstar.appdoctruyen.model.Chapter;
import vn.iotstar.appdoctruyen.model.Lichsudoctruyen;
import vn.iotstar.appdoctruyen.model.Model_TimKiem;

import vn.iotstar.appdoctruyen.model.BinhLuanDto;
import vn.iotstar.appdoctruyen.model.BinhLuanTruyenDto;
import vn.iotstar.appdoctruyen.model.ChapterDto;
import vn.iotstar.appdoctruyen.model.NoiDungChapterDto;
import vn.iotstar.appdoctruyen.model.Noidungchapter;

import vn.iotstar.appdoctruyen.model.PhanLoaiTruyen;
import vn.iotstar.appdoctruyen.model.Truyen1;
import vn.iotstar.appdoctruyen.model.TruyenVotes;
import vn.iotstar.appdoctruyen.model.truyen;

public interface APIService {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    APIService apiService = new Retrofit.Builder()


            .baseUrl("http://192.168.1.14:8090/")

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
    @GET("truyen/gettruyen/{id}")
    Call<List<truyen>> getTruyenById(@Path("id") int id);
    @GET("truyen/getbinhluan/{id}")
    Call<List<BinhLuanTruyenDto>> getBinhLuan(@Path("id") int id);

    @GET("truyen/gettbdanhgia/{id}")
    Call<Double> getAverageRatingByTruyenId(@Path("id")int id);
    @GET("truyen/gettongbinhluan/{id}")
    Call<Long> countBinhLuanByTruyenId(@Path("id")int id);
    @GET("truyen/gettongluotxem/{id}")
    Call<Long> sumSoluotxemByTruyenId(@Path("id")int id);
    @GET("truyen/getchapterbyidtruyen/{id}")
    Call<List<ChapterDto>> getChapterById(@Path("id") int id);
    @GET("truyen/gettenchapter/{id}")
    Call<List<ChapterDto>> getTenById(@Path("id") int id);
    @GET("truyen/getnoidungchapter/{id}")
    Call<List<NoiDungChapterDto>> getNoiDungChapterById(@Path("id") int id);

    @PUT("/updateLuotXem/{id}")
    Call<Void> updateLuotXemChapter(@Path("id") int id);

    @GET("truyen/getbinhluantheoIdChapter/{id}")
    Call<List<BinhLuanTruyenDto>> getBinhLuanTheoIdChapter(@Path("id") int id);
    @GET("truyen/getminidchapter/{id}")
    Call<Integer> getMinIdChapter(@Path("id")int id);
    @GET("truyen/getmaxidchapter/{id}")
    Call<Integer> getMaxIdChapter(@Path("id")int id);
    @POST("addBinhLuan")
    Call<BinhLuanDto> themBinhLuan(@Body BinhLuanDto binhLuanDto);



    @GET("tusach/lichsu/{idtaikhoan}")
    Call<List<Lichsudoctruyen>> getListTruyenDaDoc(@Path("idtaikhoan") int idtaikhoan);

    @GET("/truyen/getone/{idchapter}")
    Call<Truyen1> getOneTruyen(@Path("idchapter") int idchapter);

    @GET("/truyen/chapter/getone/{idchapter}")
    Call<ChapterDto> getOneChapter(@Path("idchapter") int id);

    @GET("/truyen/chapter/tenchapter/{idtruyen}")
    Call<String> getTenChapterNew(@Path("idtruyen") int idtruyen);

    @GET("/search")
    Call<List<Model_TimKiem>> getListTimKiem(@Field("textsearch") String textsearch);
}
