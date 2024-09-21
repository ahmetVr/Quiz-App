package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.quizapp.databinding.ActivityMainBinding;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private int questionNumber = 1;

    private int allQuestion = Questions.questions.length;

    private int currentIndex = 0;

    private int trueAnswerNumber = 0;

    private int wrongAnswerNumber = 0;

    private String selectedAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = binding.choiceA.getText().toString();
                performOperation(binding.buttonA,binding.choiceA);
            }
        });

        binding.buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = binding.choiceB.getText().toString();
                performOperation(binding.buttonB,binding.choiceB);
            }
        });

        binding.buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = binding.choiceC.getText().toString();
                performOperation(binding.buttonC,binding.choiceC);
            }
        });

        binding.buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = binding.choiceD.getText().toString();
                performOperation(binding.buttonD,binding.choiceD);
            }
        });

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionNumber++;
                highLight();
            }
        });
    }
    public void performOperation(Button button, TextView choice) {
        if(checkAnswers()) {
            trueAnswerNumber++;
            currentIndex++;
            button.setBackgroundColor(Color.GREEN);
            choice.setBackgroundColor(Color.GREEN);
        } else {
            button.setBackgroundColor(Color.RED);
            choice.setBackgroundColor(Color.RED);
            wrongAnswerNumber++;
            currentIndex++;
        }
    }

    private void loadNewQuestion() {
        if(currentIndex == allQuestion) {
            //finishQuiz();
        } else {
            binding.question.setText(Questions.questions[currentIndex]);
            binding.choiceA.setText(Questions.choices[currentIndex][0]);
            binding.choiceB.setText(Questions.choices[currentIndex][1]);
            binding.choiceC.setText(Questions.choices[currentIndex][2]);
            binding.choiceD.setText(Questions.choices[currentIndex][3]);
            binding.totalQuestion.setText(questionNumber + " / " + allQuestion);
        }
        binding.trueAns.setText(binding.trueAns.getText().toString().substring(0,binding.trueAns.getText().toString().length()-1) + trueAnswerNumber);
        binding.wronAns.setText(binding.wronAns.getText().toString().substring(0,binding.wronAns.getText().toString().length()-1) + wrongAnswerNumber);
    }

    public void highLight() {
        binding.buttonA.setBackgroundColor(Color.CYAN);
        binding.buttonB.setBackgroundColor(Color.CYAN);
        binding.buttonC.setBackgroundColor(Color.CYAN);
        binding.buttonD.setBackgroundColor(Color.CYAN);
        binding.choiceA.setBackgroundColor(Color.WHITE);
        binding.choiceB.setBackgroundColor(Color.WHITE);
        binding.choiceC.setBackgroundColor(Color.WHITE);
        binding.choiceD.setBackgroundColor(Color.WHITE);
    }

    public boolean checkAnswers() {
        if(selectedAnswer.equals(Questions.correctAnswers[currentIndex])) {
            return true;
        } else {
            return false;
        }
    }
}