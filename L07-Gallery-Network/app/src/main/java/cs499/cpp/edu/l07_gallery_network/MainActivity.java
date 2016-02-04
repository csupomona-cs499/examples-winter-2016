package cs499.cpp.edu.l07_gallery_network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import java.io.IOException;

import cs499.cpp.edu.l07_gallery_network.data.PhotoData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.JacksonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private FlickrAPIService service;

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridview);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        service = retrofit.create(FlickrAPIService.class);
        Call<PhotoData> photoDataCall = service.getRecentPhotos(
                "flickr.photos.getRecent",
                "2cdc2886adad927a7757ba48fbe311c8",
                "json",
                "url_s",
                "1");

        photoDataCall.enqueue(new Callback<PhotoData>() {
            @Override
            public void onResponse(Response<PhotoData> response) {
                Log.i("TEST", "Photo size: " + response.body().getPhotos().getPhoto().size());
                ImageAdapter imageAdapter = new ImageAdapter(MainActivity.this, response.body().getPhotos().getPhoto());
                gridView.setAdapter(imageAdapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("TEST", "Failed to get the response. ", t);
            }
        });
    }
}
