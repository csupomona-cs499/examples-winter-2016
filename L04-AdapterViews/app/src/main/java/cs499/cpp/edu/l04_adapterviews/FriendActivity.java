package cs499.cpp.edu.l04_adapterviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yusun on 1/27/16.
 */
public class FriendActivity extends Activity {

    private ListView listView;

    private List<Friend> friendList = new ArrayList<Friend>() {{
        add(new Friend("Allison", 23, R.drawable.allison));
        add(new Friend("Victor", 50, R.drawable.victor));
        add(new Friend("Brian", 10, R.drawable.brian));
        add(new Friend("Zach", 88, R.drawable.zach));
        add(new Friend("Ben", 3, R.drawable.allison));
        add(new Friend("Trump", 0, R.drawable.victor));
        add(new Friend("Cruz", 44, R.drawable.brian));
        add(new Friend("Bernie", 11, R.drawable.zach));
        add(new Friend("Ben22", 322, R.drawable.allison));
        add(new Friend("Trump22", 0, R.drawable.victor));
        add(new Friend("Cruz22", 442, R.drawable.brian));
        add(new Friend("Bernie22", 211, R.drawable.zach));
    }};

    private FriendAdapter friendAdapter;
    private Button addNewFriendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        
        listView = (ListView) findViewById(R.id.listView);
        friendAdapter = new FriendAdapter(this, R.layout.friend_list_item, friendList);
        listView.setAdapter(friendAdapter);

        addNewFriendButton = (Button) findViewById(R.id.addNewFriend);
        addNewFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friend friend = new Friend("New friend", 10, R.drawable.brian);
                friendAdapter.add(friend);
                friendAdapter.notifyDataSetChanged();
            }
        });
    }
}
