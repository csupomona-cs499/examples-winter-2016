package cs499.cpp.edu.l03_android_widgets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView demoTextView;
    private EditText passwordEditText;
    private EditText demoEditText;

    private Switch demoSwitch;
    private TextView wifiTextView;

    private SeekBar demoSeekBar;
    private TextView currentValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        demoTextView = (TextView) findViewById(R.id.textViewDemo);
        demoTextView.setText(demoTextView.getText() + " (running)");

        demoEditText = (EditText) findViewById(R.id.demoEditText);
        demoEditText.setText("Please Write st.");

        passwordEditText = (EditText) findViewById(R.id.demoEditText2);
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                demoTextView.setText(passwordEditText.getText());
            }
        });

        Button okButton = (Button) findViewById(R.id.okButton);
        //MyButtonOnClickListner listner = new MyButtonOnClickListner(demoEditText, demoTextView);
        //okButton.setOnClickListener(listner);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demoTextView.setText(demoEditText.getText());
            }
        });

        demoSwitch = (Switch) findViewById(R.id.demoSwitch);
        wifiTextView = (TextView) findViewById(R.id.wifiTextView);

        demoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                wifiTextView.setText("WIFI Status: " + isChecked);
            }
        });

        demoSeekBar = (SeekBar) findViewById(R.id.demoSeekBar);
        currentValueTextView = (TextView) findViewById(R.id.seekBarValueTextView);
        demoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentValueTextView.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

//class MyButtonOnClickListner implements View.OnClickListener {
//
//    private TextView myTextView;
//    private EditText myEditText;
//
//    public MyButtonOnClickListner(EditText myEditText, TextView myTextView) {
//        this.myEditText = myEditText;
//        this.myTextView = myTextView;
//    }
//
//    @Override
//    public void onClick(View v) {
//        myTextView.setText(myEditText.getText());
//    }
//}
