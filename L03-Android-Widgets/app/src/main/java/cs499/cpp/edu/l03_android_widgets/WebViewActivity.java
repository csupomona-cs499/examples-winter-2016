package cs499.cpp.edu.l03_android_widgets;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by yusun on 1/25/16.
 */
public class WebViewActivity extends Activity {

    private Button goButton;
    private EditText urlEditText;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.webview_activity);

        goButton = (Button) findViewById(R.id.goButton);
        urlEditText = (EditText) findViewById(R.id.urlEditText);
        webView = (WebView) findViewById(R.id.webView);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlEditText.getText().toString();
                webView.loadUrl(url);
            }
        });
    }
}
