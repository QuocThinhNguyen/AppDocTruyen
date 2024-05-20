package vn.iotstar.appdoctruyen.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
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


import vn.iotstar.appdoctruyen.DanhGiaCuaToi;
import vn.iotstar.appdoctruyen.model.BinhLuanCuaToiDto;
import vn.iotstar.appdoctruyen.model.Chapter;
import vn.iotstar.appdoctruyen.model.ChapterAdmin;
import vn.iotstar.appdoctruyen.model.DanhGiaCuaToiDto;
import retrofit2.http.Query;
import vn.iotstar.appdoctruyen.model.BinhLuanCuaToiDto;
import vn.iotstar.appdoctruyen.model.Chapter;
import vn.iotstar.appdoctruyen.model.ChapterAdmin;
import vn.iotstar.appdoctruyen.model.LichSuDocTruyenModel;
import vn.iotstar.appdoctruyen.model.Noidungchapter;


import vn.iotstar.appdoctruyen.model.Chapter;
import vn.iotstar.appdoctruyen.model.Lichsudoctruyen;
import vn.iotstar.appdoctruyen.model.Model_TimKiem;

import vn.iotstar.appdoctruyen.model.BinhLuanDto;
import vn.iotstar.appdoctruyen.model.BinhLuanTruyenDto;
import vn.iotstar.appdoctruyen.model.ChapterDto;
import vn.iotstar.appdoctruyen.model.NoiDungChapterDto;
import vn.iotstar.appdoctruyen.model.Noidungchapter;


import vn.iotstar.appdoctruyen.model.PhanLoaiTruyen;

import vn.iotstar.appdoctruyen.model.TaiKhoanDto;


import vn.iotstar.appdoctruyen.model.Taikhoan;

import vn.iotstar.appdoctruyen.model.TaiKhoanDto;


import vn.iotstar.appdoctruyen.model.Truyen1;
import vn.iotstar.appdoctruyen.model.TruyenVotes;
import vn.iotstar.appdoctruyen.model.truyen;

public interface APIService {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    APIService apiService = new Retrofit.Builder()


            .baseUrl("http://192.168.165.85:8090/")


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

//    @GET("truyen/{id}")
//    Call<List<truyen>> getTruyenById(@Path("id") int id);

    @GET("truyen/chapter/{id}")
    Call<List<ChapterAdmin>> getChapterByIdAdmin(@Path("id") int id);

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
    Call<List<LichSuDocTruyenModel>> getListTruyenDaDoc(@Path("idtaikhoan") int idtaikhoan);

    @GET("/truyen/getone/{idchapter}")
    Call<Truyen1> getOneTruyen(@Path("idchapter") Integer idchapter);

    @GET("/truyen/chapter/getone/{idchapter}")
    Call<ChapterDto> getOneChapter(@Path("idchapter") int id);

    @GET("/truyen/chapter/tenchapter/{idtruyen}")
    Call<String> getTenChapterNew(@Path("idtruyen") int idtruyen);

    @GET("/search")
    Call<ArrayList<Model_TimKiem>> getListTimKiem(@Query("textsearch") String textsearch);


    @GET("/taikhoan")
    Call<List<Taikhoan>> getTaiKhoan();

    @GET("timtaikhoan/{email}")
    Call<List<Taikhoan>> getTaiKhoanByEmail(@Path("email") String email);

    @PUT("taikhoan/{id}/{loaitk}")
    Call<Taikhoan> updateLoaiTk(@Path("id") int id, @Path("loaitk") int loaitk);

    @POST("taikhoan")
    Call<Taikhoan> addTaiKhoan(@Body Taikhoan taikhoan);

    @GET("taikhoan/{email}")
    Call<TaiKhoanDto> findByEmail(@Path("email") String email);

    @GET("/binhluancuatoi/{id}")
    Call<List<BinhLuanCuaToiDto>> findByIdn(@Path("id") Integer id);

    @GET("/danhgiacuatoi/{id}")
    Call<List<DanhGiaCuaToiDto>> findDanhGiaByIdn(@Path("id") Integer id);

    @GET("/timtaikhoan/{email}")
    Call<List<TaiKhoanDto>> findByEmail1(@Path("email") String email);
    @GET("/findidtaikhoan/{email}")
    Call<Integer> findIdTaiKhoan(@Path("email") String email);

    @GET("/getidbychapterandtk/{idchapter}/{idtaikhoan}")
    Call<List<Integer>> getIDByChapterAndTK(@Path("idchapter") int idchapter, @Path("idtaikhoan") int idtaikhoan);
    @PUT("/danhgia/{idchapter}/{idtaikhoan}/{sosao}")
    Call<Void> updateDanhGia(@Path("idchapter") int idchapter, @Path("idtaikhoan") int idtaikhoan, @Path("sosao") double sosao);
    @GET("/truyen/gettbdanhgiatheochapter/{id}")
    Call<Double> getAverageRatingByIdChapter(@Path("id")int id);

    //@PostMapping("/taikhoan")
    //    public Taikhoan addTaiKhoan(@RequestBody Taikhoan taikhoan) {
    //        return taiKhoanRepository.save(taikhoan);
    //    }

    //@PutMapping("/taikhoan/{id}")
    //    public Taikhoan updateTaiKhoan(@RequestBody Taikhoan taikhoan, @PathVariable Integer id) {
    //        Taikhoan taikhoan1 = taiKhoanRepository.findById(id).orElse(null);
    //        if (taikhoan1 == null) {
    //            return null;
    //        }
    //        taikhoan1.setHoten(taikhoan.getHoten());
    //        taikhoan1.setDienthoai(taikhoan.getDienthoai());
    //        //taikhoan1.setEmail(taikhoan.getEmail());
    //        //taikhoan1.setMatkhau(taikhoan.getMatkhau());
    //        //taikhoan1.setLoaitk(taikhoan.getLoaitk());
    //        return taiKhoanRepository.save(taikhoan1);
    //    }
    @PUT("taikhoan/{id}")
    Call<Taikhoan> updateTaiKhoan(@Body Taikhoan taikhoan, @Path("id") int id);


}
