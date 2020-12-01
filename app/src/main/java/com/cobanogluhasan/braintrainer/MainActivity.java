package com.cobanogluhasan.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button startButton;
    TextView sumTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfTheCorrectAnswer;
    int score = 0;
    int numberOfQuestion=0;
    TextView resultTextView;
    TextView pointsTextView;
    TextView timerTextview;
    Button playAgainButton;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    int timeFinished;
    RelativeLayout relativeLayout;
    CountDownTimer countDownTimer;

    public void playAgain(View view) {
        timeFinished=1;
        playAgainButton.setVisibility(View.INVISIBLE);
        score=0;
        numberOfQuestion=0;

        timerTextview.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");

        generateQuestion();

       countDownTimer= new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextview.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                timeFinished=0;
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextview.setText("0s");
                resultTextView.setText("Your Score: " + Integer.toString(score) + "/" +  Integer.toString(numberOfQuestion));
            }
        }.start();





    }


    public void generateQuestion() {
        Random random = new Random();

        int x = random.nextInt(51);
        int y = random.nextInt(51);

        int incorrectAnswer;

        sumTextView.setText(Integer.toString(x) +" + " + Integer.toString(y));

        locationOfTheCorrectAnswer=random.nextInt(4);

        for(int i =0; i<4;i++) {

            if(i==locationOfTheCorrectAnswer) {

                answers.add(x+y);
            }

            else {

                incorrectAnswer = random.nextInt(100)+1;

                while(incorrectAnswer == x + y) {
                    incorrectAnswer = random.nextInt(100)+1;
                }
                answers.add(incorrectAnswer);
            }

        }


        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

        answers.clear();

    }

    public void chooseAnswer(View view) {

        if (timeFinished !=0) {

            if (view.getTag().toString().equals(Integer.toString(locationOfTheCorrectAnswer))) {
                score++;
                resultTextView.setText("Correct!");
            } else {

                resultTextView.setText("Wrong");
            }


            numberOfQuestion++;
            pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));

            generateQuestion();
        }
    }

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);


        startButton = (Button) findViewById(R.id.startbutton);
       sumTextView = (TextView) findViewById(R.id.sumTextView);
       resultTextView = (TextView) findViewById(R.id.resultTextView);
       pointsTextView = (TextView) findViewById(R.id.pointsTextView);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        timerTextview = (TextView) findViewById(R.id.timerTextView);

        playAgainButton = (Button) findViewById(R.id.playAgainButton);





    }
}
