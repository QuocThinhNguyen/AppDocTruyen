package vn.iotstar.appdoctruyen.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import vn.iotstar.appdoctruyen.model.Chapter;
import vn.iotstar.appdoctruyen.model.ChapterAdmin;
import vn.iotstar.appdoctruyen.model.Noidungchapter;
import vn.iotstar.appdoctruyen.model.PhanLoaiTruyen;
import vn.iotstar.appdoctruyen.model.TruyenVotes;
import vn.iotstar.appdoctruyen.model.truyen;

public interface APIService {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    APIService apiService = new Retrofit.Builder()

            .baseUrl("http://192.168.1.8:8090/")

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

    @GET("truyen/tentruyen")
    Call<List<String>> getTenTruyen();

    @POST("truyen")
    Call<truyen> addTruyen(@Body truyen truyen1);

    @GET("truyen/{id}")
    Call<List<truyen>> getTruyenById(@Path("id") int id);

    @GET("truyen/chapter/{id}")
    Call<List<ChapterAdmin>> getChapterById(@Path("id") int id);

    //@PutMapping("/truyen/{id}")
    //    public Truyen updateTruyen(@RequestBody Truyen truyen, @PathVariable Integer id) {
    //        Truyen truyen1 = repo.findById(id).orElse(null);
    //        truyen1.setTentruyen(truyen.getTentruyen());
    //        truyen1.setTacgia(truyen.getTacgia());
    //        truyen1.setMota(truyen.getMota());
    //        truyen1.setTheloai(truyen.getTheloai());
    //        truyen1.setLinkanh(truyen.getLinkanh());
    //        truyen1.setKey_search(truyen.getKey_search());
    //        return repo.save(truyen1);
    //    }
    @PUT("truyen/{id}")
    Call<truyen> updateTruyen(@Body truyen truyen, @Path("id") int id);

    //@PostMapping("/truyen/{id}/chapterupdate")
    //    public Chapter addChapter(@PathVariable Integer id, @RequestBody Map<String, Object> payload) {
    //        Truyen truyen = repo.findById(id).orElse(null);
    //        if (truyen == null) {
    //            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Truyen not found");
    //        }
    //        Chapter chapter = new Chapter();
    //        chapter.setIdtruyen(truyen);
    //        chapter.setTenchapter((String) payload.get("tenchapter"));
    //        chapter.setNgaydang(LocalDate.now()); // giả sử ngày đăng là ngày hiện tại
    //        chapter.setSoluotxem(0); // giả sử số lượt xem ban đầu là 0
    //        chapter.setDanhgia(0.0); // giả sử đánh giá ban đầu là 0.0
    //        return chapterRepository.save(chapter);
    //    }
    @POST("truyen/{id}/chapterupdate")
    Call<Chapter> addChapter(@Path("id") int id, @Body Chapter chapter);

    @GET("truyen/chapter/noidung/{id}")
    Call<List<ChapterAdmin>> getChapterContentById(@Path("id") int id);

//@GetMapping("/truyen/chapter/noidung/linkanh/{id}")
//    public List<NoiDungChapter> getLinkChapterById(@PathVariable Integer id) {
//        //Truyen truyen = repo.findById(id);
//        List<NoiDungChapter> list = chapterRepository.getLinkChapterById(id);
//        return list;
//    }
    @GET("truyen/chapter/noidung/linkanh/{id}")
    Call<List<Noidungchapter>> getLinkChapterById(@Path("id") int id);

    @POST("truyen/chapter/{id}/noidung")
    Call<Noidungchapter> addLinkChapter(@Path("id") int id, @Body Noidungchapter noidungchapter);

}
