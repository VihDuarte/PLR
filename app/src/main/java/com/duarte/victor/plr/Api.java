package com.duarte.victor.plr;

import com.duarte.victor.plr.model.Plr;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("v1/Scrap")
    Observable<List<Plr>> getPlrs(@Query("page") int page);

    @POST("v1/Scrap")
    Observable<String> postPlr(@Body Plr plr);

    @DELETE("v1/Scrap/{id}")
    Observable<Integer> deletePlr(@Path("id") String idPlr);
}
