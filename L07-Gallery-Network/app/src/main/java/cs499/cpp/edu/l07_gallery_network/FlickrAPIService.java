package cs499.cpp.edu.l07_gallery_network;

import cs499.cpp.edu.l07_gallery_network.data.PhotoData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yusun on 2/3/16.
 */
public interface FlickrAPIService {

    @GET("services/rest")
    Call<PhotoData> getRecentPhotos(
            @Query("method")String method,
            @Query("api_key")String api_key,
            @Query("format")String format,
            @Query("extras")String extras,
            @Query("nojsoncallback")String nojsoncallback
    );
}
