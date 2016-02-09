package cs499.cpp.edu.l08_firebase_demo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yusun on 2/8/16.
 */
public class StudentAdapter extends BaseAdapter {

    private Context context;
    private List<String> idList;
    private Map<String, Student> studentMap;
    private Firebase firebase;

    public StudentAdapter(Context context, Firebase firebase, DataSnapshot dataSnapshot) {
        this.context = context;
        this.firebase = firebase;
        initData(dataSnapshot);
    }

    private void initData(DataSnapshot dataSnapshot) {
        Log.i("TEST", dataSnapshot.getKey() + " : " + dataSnapshot.getValue());

        idList = new ArrayList<String>();
        studentMap = new HashMap<String, Student>();

        for(DataSnapshot data : dataSnapshot.getChildren()) {
            Log.i("TEST", "data: " + data + " type: " + data.getClass().getName());
            String id = data.getKey();
            Student student = data.getValue(Student.class);
            Log.i("TEST", "stu: " + student);
            idList.add(id);
            studentMap.put(id, student);
        }
    }

    @Override
    public int getCount() {
        return idList.size();
    }

    @Override
    public Object getItem(int position) {
        String id = idList.get(position);
        return studentMap.get(id);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_view_item, parent, false);

        final EditText nameTextView = (EditText) view.findViewById(R.id.nameTextView);
        final EditText courseTextView = (EditText) view.findViewById(R.id.courseTextView);

        Student student = (Student) getItem(position);
        nameTextView.setText(student.getName());
        courseTextView.setText(student.getCourse());

        Button button = (Button) view.findViewById(R.id.updateButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameTextView.getText().toString();
                String course = courseTextView.getText().toString();
                Student student = new Student(name, 20, course);
                firebase.child("student/" + idList.get(position)).setValue(student);
            }
        });

        return view;
    }
}
