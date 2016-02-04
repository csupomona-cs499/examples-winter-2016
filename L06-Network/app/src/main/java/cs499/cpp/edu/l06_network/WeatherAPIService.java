package cs499.cpp.edu.l06_network;

import cs499.cpp.edu.l06_network.data.WeatherData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yusun on 2/3/16.
 */
public interface WeatherAPIService {

    @GET("weather")
    Call<WeatherData> getWeatherData(
            @Query("q")String cityName,
            @Query("appid")String appId);

}
