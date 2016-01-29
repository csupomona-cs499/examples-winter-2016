package cs499.cpp.edu.l03_android_widgets;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by yusun on 1/25/16.
 */
public class ImageViewActivity extends Activity {

    private ImageView imageView;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.imageview_activity);

        imageView = (ImageView) findViewById(R.id.imageView);

        button1 = (Button) findViewById(R.id.scaleButtonCenter);
        button2 = (Button) findViewById(R.id.scaleButtonFitXY);
        button3 = (Button) findViewById(R.id.scaleCenterCrop);
        button4 = (Button) findViewById(R.id.scaleButtonFitCenter);
        button5 = (Button) findViewById(R.id.scaleButtonCenterInside);
        button6 = (Button) findViewById(R.id.dialog);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setScaleType(ImageView.ScaleType.CENTER);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                Intent intent = new Intent(ImageViewActivity.this, MainActivity.class);
                intent.putExtra("name", "CS499");
                intent.putExtra("int", 500);
                startActivity(intent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use the Builder class for convenient dialog construction
                AlertDialog.Builder builder = new AlertDialog.Builder(ImageViewActivity.this);
                builder.setMessage("Message for the dialog")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // FIRE ZE MISSILES!
                            }
                        })
                        .setNegativeButton("Cencel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
