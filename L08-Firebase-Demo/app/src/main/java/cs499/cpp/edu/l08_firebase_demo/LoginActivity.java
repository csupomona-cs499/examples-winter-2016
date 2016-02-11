package cs499.cpp.edu.l08_firebase_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yusun on 2/10/16.
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.emailEditText)
    EditText emailEditText;
    @Bind(R.id.passwordEditText)
    EditText passwordEditText;

    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://fiery-torch-1028.firebaseio.com/");
    }

    @OnClick(R.id.loginButton)
    void onClickLogin() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        firebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Log.i("TEST", "Success: " + authData);
                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                String email = (String) authData.getProviderData().get("email");
                String uid = authData.getUid();

                Intent intent = new Intent(LoginActivity.this, QuizActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.e("TEST", "Failed: " + firebaseError);
            }
        });
    }

    @OnClick(R.id.signupButton)
    void onClickSignup() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        firebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                Log.i("TEST", "New user: " + stringObjectMap);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Log.e("TEST", "Failed to create the user. " + firebaseError);
            }
        });
    }


}
