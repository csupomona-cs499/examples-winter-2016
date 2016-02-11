package cs499.cpp.edu.l08_firebase_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cs499.cpp.edu.l08_firebase_demo.data.Question;
import cs499.cpp.edu.l08_firebase_demo.data.Submission;

/**
 * Created by yusun on 2/10/16.
 */
public class QuizActivity extends AppCompatActivity {

    @Bind(R.id.answerEditText)
    EditText answerEditText;
    @Bind(R.id.questionTextView)
    TextView questionTextView;
    @Bind(R.id.submitButton)
    Button submitButton;

    private Firebase firebase;
    private Question currentQuestion;
    private long currentTimeSpent;
    private Submission submission;
    private int prevId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);
        ButterKnife.bind(this);

        submission = new Submission();
        String email = getIntent().getStringExtra("email");
        String uid = getIntent().getStringExtra("uid");
        submission.setEmail(email);
        submission.setUid(uid);

        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://fiery-torch-1028.firebaseio.com/");

        firebase.child("question").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentQuestion = dataSnapshot.getValue(Question.class);

                if (currentQuestion.getId() == 0) {
                    reset();
                } else if (currentQuestion.getId() != prevId) {
                    prevId = currentQuestion.getId();
                    questionTextView.setText(currentQuestion.toString());
                    currentTimeSpent = System.currentTimeMillis();
                    submitButton.setEnabled(true);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void reset() {
        questionTextView.setText("Wait for the next question!");
        submitButton.setEnabled(false);
        submission.setNumCorrect(0);
        submission.setTimeSpend(0);
        firebase.child("submission/" + submission.getUid()).setValue(submission);
    }

    @OnClick(R.id.submitButton)
    void submitOnClikc() {
        currentTimeSpent = System.currentTimeMillis() - currentTimeSpent;
        int answer = Integer.parseInt(answerEditText.getText().toString());
        submitButton.setEnabled(false);
        if (currentQuestion.isCorrect(answer)) {
            submission.setNumCorrect(submission.getNumCorrect() + 1);
            submission.setTimeSpend(submission.getTimeSpend() + currentTimeSpent);
        } else {
            submission.setTimeSpend((submission.getTimeSpend() + currentTimeSpent) * 3);
        }
        firebase.child("submission/" + submission.getUid()).setValue(submission);
    }
}
