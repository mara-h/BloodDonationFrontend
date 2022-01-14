package cg.example.blooddonationfrontend.view;

import static cg.example.blooddonationfrontend.model.Enums.AnswerType.bool;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.List;

import cg.example.blooddonationfrontend.R;
import cg.example.blooddonationfrontend.model.Enums;
import cg.example.blooddonationfrontend.model.Globals;
import cg.example.blooddonationfrontend.model.Question;

public class DonorFormActivity extends AppCompatActivity {
    int allQuestionsCount = 31;
    int count = 1;
    Boolean isQuestionnaireValid = true;
    Boolean isGoodAnswerNo;
    Boolean boolQuestion;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_form);

        ImageButton backButton = findViewById(R.id.back_button);
        ImageButton nextButton = findViewById(R.id.next_button);
        Button cupButtonOne = findViewById(R.id.CupButtonONE);
        Button cupButtonTwo = findViewById(R.id.CupButtonTWO);
        Button cupButtonThree = findViewById(R.id.CupButtonTHREE);
        Button yesButton = findViewById(R.id.YesButton);
        Button noButton = findViewById(R.id.noButton);
        TextView questionBody = findViewById(R.id.questionBody);
        TextView choiceText = findViewById(R.id.choiceText);

        if (Globals.allQuestions == null) {
            Toast.makeText(DonorFormActivity.this, "No questions available.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(DonorFormActivity.this, HomeActivity.class));
        }

        this.setNextQuestion();

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceText.setText("YES");
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceText.setText("NO");
            }
        });

        cupButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceText.setText("1-2 cups");
            }
        });

        cupButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceText.setText("3-5 cups");
            }
        });

        cupButtonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceText.setText(">5 cups");
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DonorFormActivity.this, HomeActivity.class));
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == allQuestionsCount) {
                    //TODO: generate and save questionnaire
                } else {
                    count++;

                    int response = verifyAnswer();
                    switch (response) {
                        case 0:
                            Log.e("Answer", "Invalid questionnaire");
                            choiceText.setText("");
                            startActivity(new Intent(DonorFormActivity.this, InvalidQuestionnaireActivity.class));
                            break;
                        case 1:
                            Log.e("Answer", "Valid questionnaire");
                            if(count == 32){
                                startActivity(new Intent(DonorFormActivity.this, HomeActivity.class));
                            }
                            choiceText.setText("");
                            setNextQuestion();
                            break;
                        default:
                            Log.e("Answer", "Missing answer");
                            break;
                    }
                }
            }
        });
    }

    //0 = raspunsul dat este incorect
    //1 = raspuns corect
    //2 = nu a dat raspuns inca
    private int verifyAnswer() {
        ImageButton backButton = findViewById(R.id.back_button);
        ImageButton nextButton = findViewById(R.id.next_button);
        Button cupButtonOne = findViewById(R.id.CupButtonONE);
        Button cupButtonTwo = findViewById(R.id.CupButtonTWO);
        Button cupButtonThree = findViewById(R.id.CupButtonTHREE);
        Button yesButton = findViewById(R.id.YesButton);
        Button noButton = findViewById(R.id.noButton);
        TextView questionBody = findViewById(R.id.questionBody);
        TextView choiceText = findViewById(R.id.choiceText);

        String gender = Globals.currentUser.getSex();

        if(choiceText.getText().equals(""))
            return 2;

        if (isGoodAnswerNo) {
            if(choiceText.getText().equals("YES"))
                return 0;
        } else {
            if(boolQuestion) {
                if(choiceText.getText().equals("NO"))
                    return 0;
            } else {
                if(choiceText.getText().equals(">5 cups"))
                    return 0;
                if(gender.equals("female")) {
                    if(choiceText.getText().equals("3-5 cups"))
                        return 0;
                }

            }

        }
        return 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setNextQuestion() {
        ImageButton backButton = findViewById(R.id.back_button);
        ImageButton nextButton = findViewById(R.id.next_button);
        Button cupButtonOne = findViewById(R.id.CupButtonONE);
        Button cupButtonTwo = findViewById(R.id.CupButtonTWO);
        Button cupButtonThree = findViewById(R.id.CupButtonTHREE);
        Button yesButton = findViewById(R.id.YesButton);
        Button noButton = findViewById(R.id.noButton);
        TextView questionBody = findViewById(R.id.questionBody);
        TextView choiceText = findViewById(R.id.choiceText);


        Question question = Globals.allQuestions.stream().filter(q -> count == q.getQuestionOrder()).findAny().orElse(null);
        if (question == null) {
            Toast.makeText(DonorFormActivity.this, "No questions available.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(DonorFormActivity.this, HomeActivity.class));
        }

        assert question != null;
        Enums.AnswerType answerType = question.getAnswerType();

        isGoodAnswerNo = question.isGoodAnswerNo();
        Log.e("isgoodAnswerno", ""+ question.isGoodAnswerNo());
        if (question.getAnswerType() == bool) {
            boolQuestion = true;
        } else {
            boolQuestion = false;
        }

        questionBody.setText(question.getQuestionBody());

        switch (answerType) {
            case bool:
                yesButton.setVisibility(View.VISIBLE);
                noButton.setVisibility(View.VISIBLE);
                cupButtonOne.setVisibility(View.GONE);
                cupButtonTwo.setVisibility(View.GONE);
                cupButtonThree.setVisibility(View.GONE);
                break;

            case userInputAlcoholQuantity:
                yesButton.setVisibility(View.GONE);
                noButton.setVisibility(View.GONE);
                cupButtonOne.setVisibility(View.VISIBLE);
                cupButtonTwo.setVisibility(View.VISIBLE);
                cupButtonThree.setVisibility(View.VISIBLE);
                break;
        }


    }
}

