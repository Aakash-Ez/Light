package com.example.Balakrishna.light;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.Call;
import java.util.List;
public interface API {
    @GET("crimes-at-location")
    Call<List<Crime>> listCrime(@Query("date") String Year,@Query("lat") String Lat,@Query("lng") String Lng);
    @GET("forces")
    Call<List<Force>> listForce();
    @GET("forces/{id}")
    Call<ForceDetails> listForces(@Path("id") String ID);
}
