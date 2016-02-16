package cs499.cpp.edu.l09_data_storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private StudentDbHelper studentDbHelper;

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

        studentDbHelper = new StudentDbHelper(this);
        refreshListView();
    }

    @OnClick(R.id.addButton)
    void addButtonOnClick() {
        String name = nameEditText.getText().toString();
        int age = Integer.parseInt(ageEditText.getText().toString());
        String major = majorEditText.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentEntry.STUDENT_COLUMN_NAME_NAME, name);
        contentValues.put(StudentEntry.STUDENT_COLUMN_NAME_AGE, age);
        contentValues.put(StudentEntry.STUDENT_COLUMN_NAME_MAJOR, major);

        SQLiteDatabase db = studentDbHelper.getWritableDatabase();
        db.insert(StudentEntry.TABLE_NAME, "null", contentValues);

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

        String[] columns = {
                StudentEntry.STUDENT_COLUMN_NAME_ID,
                StudentEntry.STUDENT_COLUMN_NAME_NAME,
                StudentEntry.STUDENT_COLUMN_NAME_AGE,
                StudentEntry.STUDENT_COLUMN_NAME_MAJOR};

        SQLiteDatabase db = studentDbHelper.getReadableDatabase();
        Cursor cursor = db.query(StudentEntry.TABLE_NAME,
                                    columns,
                                    null,
                                    null,
                                    null,
                                    null,
                                    StudentEntry.STUDENT_COLUMN_NAME_NAME
                                    );
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(StudentEntry.STUDENT_COLUMN_NAME_ID));
            String name = cursor.getString(cursor.getColumnIndex(StudentEntry.STUDENT_COLUMN_NAME_NAME));
            int age = cursor.getInt(cursor.getColumnIndex(StudentEntry.STUDENT_COLUMN_NAME_AGE));
            String major = cursor.getString(cursor.getColumnIndex(StudentEntry.STUDENT_COLUMN_NAME_MAJOR));
            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setAge(age);
            student.setMajor(major);
            studentList.add(student);

            cursor.moveToNext();
        }

        return studentList;
    }
}
