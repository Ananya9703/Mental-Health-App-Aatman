package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {

    private ArrayList<Question> questions;
    private int currentQuestionIndex;
    private int score;

    private TextView questionTextView;
    private RadioButton option1RadioButton;
    private RadioButton option2RadioButton;
    private RadioButton option3RadioButton;
    private RadioGroup optionsRadioGroup;
    private Button nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        initializeQuestions();

        // Initialize UI elements
        questionTextView = findViewById(R.id.question);
        option1RadioButton = findViewById(R.id.opt1);
        option2RadioButton = findViewById(R.id.opt2);
        option3RadioButton = findViewById(R.id.opt3);
        optionsRadioGroup = findViewById(R.id.optRadioGroup);
        nextbtn=findViewById(R.id.next);
        // Initialize score and current question index
        score = 0;
        currentQuestionIndex = 0;

        // Set the first question and answer options
        setQuestionAndOptions();

        // Set click listener for the "Next" button
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (optionsRadioGroup.getCheckedRadioButtonId() == -1) {
                    // Show a toast if no radio button is selected
                    Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution
                }
                // Check the selected answer and update the score
                int selectedOptionId = optionsRadioGroup.getCheckedRadioButtonId();
                int selectedOptionIndex = optionsRadioGroup.indexOfChild(findViewById(selectedOptionId));
                updateScore(selectedOptionIndex);

                // Move to the next question
                currentQuestionIndex++;

                // If all questions have been answered, calculate final score and start appropriate activity
                if (currentQuestionIndex == questions.size()) {
                    calculateFinalScoreAndStartActivity();
                } else {
                    // Set the next question and answer options
                    setQuestionAndOptions();
                }
            }
        });
    }

    private void initializeQuestions() {
        // Initialize the questions and answer options
        questions = new ArrayList<>();
        //string resources
        String qn1=getString(R.string.q1);
        String qn2=getString(R.string.q2);
        String qn3=getString(R.string.q3);
        String qn4=getString(R.string.q4);
        String qn5=getString(R.string.q5);
        String qn6=getString(R.string.q6);
        String qn7=getString(R.string.q7);
        String qn8=getString(R.string.q8);
        String qn9=getString(R.string.q9);
        String qn10=getString(R.string.q10);
        String opt1=getString(R.string.optA);
        String opt2=getString(R.string.optB);
        String opt3=getString(R.string.optC);
        // Add the questions and answer options to the list

        questions.add(new Question(qn1, opt1,opt2,opt3,10,0,-10));
        questions.add(new Question(qn2, opt1,opt2,opt3,10,0,-10));
        questions.add(new Question(qn3, opt1,opt2,opt3,10,0,-10));
        questions.add(new Question(qn4, opt1,opt2,opt3,10,0,-10));
        questions.add(new Question(qn5, opt1,opt2,opt3,10,0,-10));
        questions.add(new Question(qn6, opt1,opt2,opt3,10,0,-10));
        questions.add(new Question(qn7, opt1,opt2,opt3,10,0,-10));
        questions.add(new Question(qn8, opt1,opt2,opt3,10,0,-10));
        questions.add(new Question(qn9, opt1,opt2,opt3,10,0,-10));
        questions.add(new Question(qn10, opt1,opt2,opt3,10,0,-10));
        // Add more questions as needed
    }

    private void setQuestionAndOptions() {
        // Get the current question
        Question currentQuestion = questions.get(currentQuestionIndex);

        // Set the question and answer options in the UI
        questionTextView.setText(currentQuestion.getQuestion());
        option1RadioButton.setText(currentQuestion.getOption1());
        option2RadioButton.setText(currentQuestion.getOption2());
        option3RadioButton.setText(currentQuestion.getOption3());

        // Clear the selection in the RadioGroup
        optionsRadioGroup.clearCheck();
    }

    private void updateScore(int selectedOptionIndex) {
        // Get the current question
        Question currentQuestion = questions.get(currentQuestionIndex);

        // Update the score based on the selected option
        if (selectedOptionIndex == 0) {
            score += currentQuestion.getScoreOption1();
        } else if (selectedOptionIndex == 1) {
            score += currentQuestion.getScoreOption2();
        } else if (selectedOptionIndex == 2) {
            score += currentQuestion.getScoreOption3();
        }
    }

    private void calculateFinalScoreAndStartActivity() {
        // Determine the activity to start based on the final score
        Class<?> activityClass;

        if (score >= 50 && score <= 100) {
            // Start the success activity
            activityClass = quizsuc.class;
        } else if (score >= -50 && score <= 50) {
            // Start the neutral activity
            activityClass = quizneut.class;
        } else {
            // Start the loss activity
            activityClass = quizlos.class;
        }

        // Start the appropriate activity
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);

        // Finish the quiz activity
        finish();
    }
}

class Question {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private int scoreOption1;
    private int scoreOption2;
    private int scoreOption3;

    public Question(String question, String option1, String option2, String option3, int scoreOption1, int scoreOption2, int scoreOption3) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.scoreOption1 = scoreOption1;
        this.scoreOption2 = scoreOption2;
        this.scoreOption3 = scoreOption3;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public int getScoreOption1() {
        return scoreOption1;
    }

    public int getScoreOption2() {
        return scoreOption2;
    }

    public int getScoreOption3() {
        return scoreOption3;
    }
}
