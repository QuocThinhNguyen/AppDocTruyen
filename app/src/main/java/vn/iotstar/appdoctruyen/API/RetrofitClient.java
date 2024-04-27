package vn.iotstar.appdoctruyen.API;

import static vn.iotstar.appdoctruyen.API.BaseClient.createService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    private static Retrofit retrofit;
    private static String BASE_URL = "http://172.172.22.189:8090/truyen/";
    public static Retrofit getRetrofit(){
        if (retrofit == null){
            Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
    public static APIService getApiService(){
        return createService(APIService.class, BASE_URL);
    }
}
