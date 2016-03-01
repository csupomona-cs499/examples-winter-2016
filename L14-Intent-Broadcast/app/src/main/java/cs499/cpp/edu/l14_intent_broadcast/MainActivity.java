package cs499.cpp.edu.l14_intent_broadcast;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText) findViewById(R.id.editText);
        final Button button = (Button) findViewById(R.id.shareButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToShare = editText.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, textToShare);
                intent.setType("plain/text");
                startActivity(intent);
            }
        });

        if (getIntent() != null && getIntent().getAction().equals(Intent.ACTION_SEND)) {
            final ImageView imageView = (ImageView) findViewById(R.id.imageView);
            Uri imageUri = (Uri) getIntent().getParcelableExtra(Intent.EXTRA_STREAM);
            Picasso.with(this).load(imageUri).into(imageView);
        }

    }
}
