package cs499.cpp.edu.l07_gallery_network;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import cs499.cpp.edu.l07_gallery_network.data.PhotoItem;

/**
 * Created by yusun on 2/3/16.
 */
public class ImageAdapter extends BaseAdapter {

    private List<PhotoItem> photoItemList;
    private Context context;

    public ImageAdapter(Context context, List<PhotoItem> photoItems) {
        this.context = context;
        this.photoItemList = photoItems;
    }

    @Override
    public int getCount() {
        return photoItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return photoItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(context).load(photoItemList.get(position).getUrl_s()).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, photoItemList.get(position).getTitle(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return imageView;
    }
}
