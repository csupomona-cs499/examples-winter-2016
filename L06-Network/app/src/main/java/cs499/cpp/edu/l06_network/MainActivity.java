package cs499.cpp.edu.l06_network;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cs499.cpp.edu.l06_network.data.WeatherData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.JacksonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.cityEditText)
    EditText cityEditText;

    @Bind(R.id.resTextView)
    TextView resTextView;

    @Bind(R.id.humTextView)
    TextView humTextView;

    WeatherAPIService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        service = retrofit.create(WeatherAPIService.class);
    }

    @OnClick(R.id.getButton)
    void getButtonOnClick() {
        final String cityName = cityEditText.getText().toString();

        AsyncTask<Void, Void, WeatherData> getWeatherTask = new AsyncTask<Void, Void, WeatherData>() {
            @Override
            protected WeatherData doInBackground(Void... params) {
                WeatherData weatherData = null;
                try {
                    URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="
                            + cityName + "&appid=44db6a862fba0b067b1930da0d769e98");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.connect();
                    int code = urlConnection.getResponseCode();
                    Log.i("Test", "Response code: " + code);
                    String responseStr = IOUtils.toString(urlConnection.getInputStream());
                    Log.i("Test", "Response content: " + responseStr);

                    ObjectMapper objectMapper = new ObjectMapper();
                    weatherData = objectMapper.readValue(responseStr, WeatherData.class);
                    Log.i("TEST", weatherData.getMain().getTemp() + " " + weatherData.getMain().getHumidity());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return weatherData;
            }

            @Override
            protected void onPostExecute(WeatherData result) {
                resTextView.setText("Temp: " + result.getMain().getTemp());
                humTextView.setText("Humidity: " + result.getMain().getHumidity());
            }
        };

        getWeatherTask.execute();
    }

    @OnClick(R.id.getButtonRetrofit)
    void onClickGetRetrofit() {
        final String cityName = cityEditText.getText().toString();
        Call<WeatherData> call = service.getWeatherData(cityName, "44db6a862fba0b067b1930da0d769e98");
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Response<WeatherData> response) {
                WeatherData data = response.body();
                resTextView.setText("Temp: " + data.getMain().getTemp());
                humTextView.setText("Humidity: " + data.getMain().getHumidity());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
