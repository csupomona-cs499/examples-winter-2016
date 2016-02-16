package cs499.cpp.edu.l09_data_storage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivityWithSharedPreferences extends AppCompatActivity {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Bind(R.id.nameEditText)
    EditText nameEditText;
    @Bind(R.id.ageEditText)
    EditText ageEditText;
    @Bind(R.id.majorEditText)
    EditText majorEditText;

    @Bind(R.id.listView)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        refreshListView();
    }

    @OnClick(R.id.addButton)
    void addButtonOnClick() {

        String name = nameEditText.getText().toString();
        int age = Integer.parseInt(ageEditText.getText().toString());
        String major = majorEditText.getText().toString();

        Student student = new Student();
        student.setName(name);
        student.setMajor(major);
        student.setAge(age);

        List<Student> studentList = readStudentListFromDb();
        studentList.add(student);

        SharedPreferences sharedPreferences = this.getSharedPreferences("student-sp", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            String studentListStr = objectMapper.writeValueAsString(studentList);
            editor.putString("student-list", studentListStr);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("TEST", "Inserted the student record!");
        refreshListView();
    }

    private void refreshListView() {
        List<Student> studentList = readStudentListFromDb();
        listView.setAdapter(new ArrayAdapter<Student>(
                this, android.R.layout.simple_list_item_1, studentList
        ));
    }

    private List<Student> readStudentListFromDb() {
        List<Student> studentList = new ArrayList<Student>();

        SharedPreferences sharedPreferences = getSharedPreferences("student-sp", MODE_PRIVATE);

        String studentListStr = sharedPreferences.getString("student-list", null);
        if (studentListStr != null) {
            try {
                studentList = objectMapper.readValue(studentListStr,
                        new TypeReference<List<Student>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return studentList;
    }
}
