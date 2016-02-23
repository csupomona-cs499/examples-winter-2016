package cs499.cpp.edu.l10_data_sync_hybrid;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private StudentServiceAPI studentServiceAPI;
    private ListView listView;
    private ArrayAdapter<Student> studentArrayAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://0cc64225.ngrok.io/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        studentServiceAPI = retrofit.create(StudentServiceAPI.class);

        loadStudentLocally();
        loadStudentRemotely();
    }

    private void loadStudentLocally() {
        SharedPreferences sharedPreferences = getSharedPreferences("stu", MODE_PRIVATE);
        String listStr = sharedPreferences.getString("stu-list", null);
        if (listStr != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                List<Student> studentList = objectMapper.readValue(listStr, new TypeReference<List<Student>>(){});
                studentArrayAdapter = new ArrayAdapter<Student>(
                        MainActivity.this, android.R.layout.simple_list_item_1, studentList);
                listView.setAdapter(studentArrayAdapter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadStudentRemotely() {
//        progressDialog = ProgressDialog.show(this, "Please Wait",
//                "Getting the list of students ...", true);
//        progressDialog.show();

        Call<List<Student>> call = studentServiceAPI.getStudentList();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> studentsList = response.body();
                studentArrayAdapter = new ArrayAdapter<Student>(
                        MainActivity.this, android.R.layout.simple_list_item_1, studentsList);
                listView.setAdapter(studentArrayAdapter);
                persistStudentList(studentsList);
                //progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.e("TEST", "Failed to get the list.", t);
                //progressDialog.dismiss();
            }
        });
    }

    private void persistStudentList(List<Student> studentList) {
        SharedPreferences sharedPreferences = getSharedPreferences("stu", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            editor.putString("stu-list", objectMapper.writeValueAsString(studentList));
            editor.commit();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
