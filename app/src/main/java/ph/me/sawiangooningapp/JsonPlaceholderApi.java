package ph.me.sawiangooningapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderApi {
    @GET("Sawian")
    public Call<List<Sawian>>_getSawian();
}