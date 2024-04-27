package vn.iotstar.appdoctruyen.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.iotstar.appdoctruyen.model.truyen;

public interface APIService {
    @GET("truyen")
    Call<List<truyen>> getTruyenAll();
}
