package cs499.cpp.edu.l08_firebase_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.nameEditText)
    EditText nameEditText;
    @Bind(R.id.courseEditText)
    EditText courseEditText;
    @Bind(R.id.listView)
    ListView listView;

    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://fiery-torch-1028.firebaseio.com/");

        firebase.child("student").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StudentAdapter studentAdapter = new StudentAdapter(
                        MainActivity.this, firebase, dataSnapshot);
                listView.setAdapter(studentAdapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @OnClick(R.id.addButton)
    void addButtonOnClick() {
        String name = nameEditText.getText().toString();
        String course = courseEditText.getText().toString();
        Student student = new Student(name, 20, course);
        firebase.child("student").push().setValue(student);
    }
}
