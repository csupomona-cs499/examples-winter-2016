package cs499.cpp.edu.l04_adapterviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by yusun on 1/27/16.
 */
public class FriendAdapter extends ArrayAdapter<Friend> {

    private Context context;
    private List<Friend> friendList;

    public FriendAdapter(Context context, int resource, List<Friend> objects) {
        super(context, resource, objects);
        this.context = context;
        this.friendList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.i("Test", "getView() called at position " + position);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.friend_list_item, parent, false);

        final Friend friend = friendList.get(position);

        TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        nameTextView.setText(friend.getName());

        TextView numFriends = (TextView) view.findViewById(R.id.numFriendsTextView);
        numFriends.setText(friend.getNumFriends() + " mutual friends");

        ImageView imageView = (ImageView) view.findViewById(R.id.profileImageView);
        imageView.setImageResource(friend.getProfilePhotoId());

        Button addButton = (Button) view.findViewById(R.id.addFriendButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendList.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
