package cs499.cpp.edu.l03_android_widgets;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yusun on 1/25/16.
 */
public class ImageViewActivity2 extends Activity {

    @Bind(R.id.scaleButtonCenter)
    Button button1;
    @Bind(R.id.scaleButtonCenterInside)
    Button button2;
    @Bind(R.id.scaleButtonFitCenter)
    Button button3;
    @Bind(R.id.scaleButtonFitXY)
    Button button4;


    @Bind(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.imageview_activity);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.scaleButtonFitXY)
    void onClickButton1() {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @OnClick(R.id.scaleButtonFitCenter)
    void onClickButton2() {
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }
}
