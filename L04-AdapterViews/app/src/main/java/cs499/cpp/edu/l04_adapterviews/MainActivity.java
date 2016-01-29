package cs499.cpp.edu.l04_adapterviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        final String[] courses = {"CS499", "CS480", "CS240", "EE300", "CIS355",
                "CS499", "CS480", "CS240", "EE300", "CIS355",
                "CS499", "CS480", "CS240", "EE300", "CIS355",
                "CS499", "CS480", "CS240", "EE300", "CIS355"};
        ListAdapter adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, courses);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, courses[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
