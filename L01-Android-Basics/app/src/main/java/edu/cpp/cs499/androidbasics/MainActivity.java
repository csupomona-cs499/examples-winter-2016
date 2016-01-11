package edu.cpp.cs499.androidbasics;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<QuizQuestion> QUESTIONS = new ArrayList<QuizQuestion>() {{
        add(new QuizQuestion("LA is the largest city in the US.", false));
        add(new QuizQuestion("NYC is the largest city in the US.", true));
        add(new QuizQuestion("5% of students in 499 will get A.", false));
        add(new QuizQuestion("Alabama is going to beat Clemson.", true));
    }};

    private int currentQuestionIndex = 0;

    private TextView questionTitleTextView;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("lifecycle", "onCreate");

        setContentView(R.layout.activity_main);

        questionTitleTextView = (TextView) findViewById(R.id.questionTitleTextView);
        updateTitle();

        trueButton = (Button) findViewById(R.id.trueButton);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QUESTIONS.get(currentQuestionIndex).isCorrectAnswer() == true) {
                    Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        falseButton = (Button) findViewById(R.id.falseButton);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QUESTIONS.get(currentQuestionIndex).isCorrectAnswer() == false) {
                    Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestionIndex = (currentQuestionIndex + 1) % QUESTIONS.size();
                updateTitle();
            }
        });
    }

    private void updateTitle() {
        questionTitleTextView.setText(QUESTIONS.get(currentQuestionIndex).getTitle());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle", "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lifecycle", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lifecycle", "onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lifecycle", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lifecycle", "onResume");
    }
}
