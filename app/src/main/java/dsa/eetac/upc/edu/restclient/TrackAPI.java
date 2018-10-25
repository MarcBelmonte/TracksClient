package dsa.eetac.upc.edu.restclient;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.Call;
import retrofit2.http.Body;

public interface TrackAPI {
    String BASE_URL = "http://147.83.7.155:8080/dsaApp/";

    @GET("tracks")
    Call<List<Track>> getAllTracks();

    //@GET("#/tracks/")

    @POST("tracks")
    Call<Track> addTrack(@Body Track track);

    @DELETE("tracks/{id}")
    Call<Void> deleteTrack(@Path("id") int id);
}