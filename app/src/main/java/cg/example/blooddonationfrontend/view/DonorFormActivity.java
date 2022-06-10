package cg.example.blooddonationfrontend.view;

import static cg.example.blooddonationfrontend.model.Enums.AnswerType.bool;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

import cg.example.blooddonationfrontend.R;
import cg.example.blooddonationfrontend.api.RetrofitClient;
import cg.example.blooddonationfrontend.model.Enums;
import cg.example.blooddonationfrontend.model.Globals;
import cg.example.blooddonationfrontend.model.Question;
import cg.example.blooddonationfrontend.model.Questionnaire;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonorFormActivity extends AppCompatActivity {
    int allQuestionsCount = 30;
    int count = 1;
    Boolean isQuestionnaireValid = true;
    Boolean isGoodAnswerNo;
    Boolean boolQuestion;
    TextView choiceText;
    String gender = Globals.currentUser.getSex();


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_donor_form);

        ImageButton backButton = findViewById(R.id.back_button);
        ImageButton nextButton = findViewById(R.id.next_button);
        Button cupButtonOne = findViewById(R.id.CupButtonONE);
        Button cupButtonTwo = findViewById(R.id.CupButtonTWO);
        Button cupButtonThree = findViewById(R.id.CupButtonTHREE);
        Button yesButton = findViewById(R.id.YesButton);
        Button noButton = findViewById(R.id.noButton);
        TextView questionBody = findViewById(R.id.questionBody);
        choiceText = findViewById(R.id.choiceText);
        choiceText.setVisibility(View.INVISIBLE);

        if (Globals.allQuestions == null) {
            Toast.makeText(DonorFormActivity.this, "Întrebările nu sunt disponibile.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(DonorFormActivity.this, HomeActivity.class));
        }

        this.setNextQuestion();

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceText.setText("YES");
                yesButton.setBackgroundColor(yesButton.getContext().getResources().getColor(R.color.black));
                yesButton.setTextColor(yesButton.getContext().getResources().getColor(R.color.white));
                noButton.setBackgroundColor(noButton.getContext().getResources().getColor(R.color.white));
                noButton.setTextColor(noButton.getContext().getResources().getColor(R.color.black));
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceText.setText("NO");
                noButton.setBackgroundColor(noButton.getContext().getResources().getColor(R.color.black));
                noButton.setTextColor(noButton.getContext().getResources().getColor(R.color.white));
                yesButton.setBackgroundColor(yesButton.getContext().getResources().getColor(R.color.white));
                yesButton.setTextColor(yesButton.getContext().getResources().getColor(R.color.black));
            }
        });

        cupButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceText.setText("1-2 pahare");
                cupButtonOne.setBackgroundColor(cupButtonOne.getContext().getResources().getColor(R.color.black));
                cupButtonOne.setTextColor(cupButtonOne.getContext().getResources().getColor(R.color.white));
                cupButtonTwo.setBackgroundColor(cupButtonTwo.getContext().getResources().getColor(R.color.white));
                cupButtonTwo.setTextColor(cupButtonTwo.getContext().getResources().getColor(R.color.black));
                cupButtonThree.setBackgroundColor(cupButtonThree.getContext().getResources().getColor(R.color.white));
                cupButtonThree.setTextColor(cupButtonThree.getContext().getResources().getColor(R.color.black));
            }
        });

        cupButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceText.setText("3-5 pahare");
                cupButtonOne.setBackgroundColor(cupButtonOne.getContext().getResources().getColor(R.color.white));
                cupButtonOne.setTextColor(cupButtonOne.getContext().getResources().getColor(R.color.black));
                cupButtonTwo.setBackgroundColor(cupButtonTwo.getContext().getResources().getColor(R.color.black));
                cupButtonTwo.setTextColor(cupButtonTwo.getContext().getResources().getColor(R.color.white));
                cupButtonThree.setBackgroundColor(cupButtonThree.getContext().getResources().getColor(R.color.white));
                cupButtonThree.setTextColor(cupButtonThree.getContext().getResources().getColor(R.color.black));
            }
        });

        cupButtonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceText.setText(" > 5 pahare");
                cupButtonOne.setBackgroundColor(cupButtonOne.getContext().getResources().getColor(R.color.white));
                cupButtonOne.setTextColor(cupButtonOne.getContext().getResources().getColor(R.color.black));
                cupButtonTwo.setBackgroundColor(cupButtonTwo.getContext().getResources().getColor(R.color.white));
                cupButtonTwo.setTextColor(cupButtonTwo.getContext().getResources().getColor(R.color.black));
                cupButtonThree.setBackgroundColor(cupButtonThree.getContext().getResources().getColor(R.color.black));
                cupButtonThree.setTextColor(cupButtonThree.getContext().getResources().getColor(R.color.white));
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
                yesButton.setBackgroundColor(yesButton.getContext().getResources().getColor(R.color.white));
                yesButton.setTextColor(yesButton.getContext().getResources().getColor(R.color.black));
                noButton.setBackgroundColor(noButton.getContext().getResources().getColor(R.color.white));
                noButton.setTextColor(noButton.getContext().getResources().getColor(R.color.black));
                if (count == allQuestionsCount) {
                    int alcohol = verifyAlcohol();
                    switch (alcohol) {
                        case 0:
                            Globals.setCanGenerate(false);
                            choiceText.setText("");
                            startActivity(new Intent(DonorFormActivity.this, InvalidQuestionnaireActivity.class));
                            break;
                        case 3:
                            Questionnaire questionnaire = new Questionnaire();
                            questionnaire.setUserId(Globals.currentUser.getId());
                            questionnaire.setValid(true);
                            saveQuestionnaire(questionnaire);
                            break;
                        default:
                            break;
                    }
                } else {
                    count++;

                    if(gender.equals("male") && count==28)
                        count++;

                    int response = verifyAnswer();
                    switch (response) {
                        case 0:
                            Globals.setCanGenerate(false);
                            choiceText.setText("");
                            startActivity(new Intent(DonorFormActivity.this, InvalidQuestionnaireActivity.class));
                            break;
                        case 1:
                            choiceText.setText("");
                            setNextQuestion();
                            break;
                        case 3:
                            Questionnaire questionnaire = new Questionnaire();
                            questionnaire.setUserId(Globals.currentUser.getId());
                            questionnaire.setValid(true);
                            saveQuestionnaire(questionnaire);
                            break;
                        default:
                            break;
                    }

                }
            }
        });
    }


    //0 = raspunsul dat este incorect
    //1 = raspuns corect
    //2 = nu a dat raspuns inca
    // 3 = nu a consumat alcool
    private int verifyAnswer() {


        if (choiceText.getText().equals(""))
            return 2;

        if (count == allQuestionsCount) {
            if (choiceText.getText().equals("NO")) {
                return 3;
            }
            return 1;
        }

        if (isGoodAnswerNo) {
            if (choiceText.getText().equals("YES"))
                return 0;
        } else {
            if (boolQuestion) {
                if (choiceText.getText().equals("NO"))
                    return 0;
            }
        }
        return 1;
    }


    private int verifyAlcohol() {
        if (choiceText.getText().equals(" > 5 pahare")) {
            return 0;
        }
        if (gender.equals("female") || gender.equals("feminin")) {
            if (choiceText.getText().equals("3-5 pahare"))
                return 0;
        }
        return 3;
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
            Toast.makeText(DonorFormActivity.this, "întrebările nu sunt disponibile.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(DonorFormActivity.this, HomeActivity.class));
        }

        assert question != null;
        Enums.AnswerType answerType = question.getAnswerType();

        isGoodAnswerNo = question.isGoodAnswerNo();

        if (question.getAnswerType() == bool) {
            boolQuestion = true;
        } else {
            boolQuestion = false;
        }

        questionBody.setText(question.getQuestionBody());
        questionBody.setGravity(Gravity.CENTER);

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

    private void saveQuestionnaire(Questionnaire questionnaire) {
        Call<Questionnaire> call = RetrofitClient
                .getInstance()
                .getAPI()
                .saveQuestionnaire(questionnaire);

        call.enqueue(new Callback<Questionnaire>() {
            public void onResponse(Call<Questionnaire> call, Response<Questionnaire> response) {
                Boolean success;
                success = response.isSuccessful();
                int requestCode = response.code();

                if (success) {
                    Globals.setCanGenerate(false);
                    Questionnaire crtQuestionnaire = response.body();
                    UUID id = crtQuestionnaire.getId();
                    Globals.setCurrentQuestionnaire(crtQuestionnaire);
                    Intent intentQR = new Intent(DonorFormActivity.this, QRActivity.class);
                    intentQR.putExtra("id", id.toString());

                    startActivity(intentQR);
                } else {
                    Toast.makeText(DonorFormActivity.this, "Probleme întâmpinate.", Toast.LENGTH_LONG).show();
                }
            }

            public void onFailure(Call<Questionnaire> call, Throwable t) {
                Toast.makeText(DonorFormActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("failure: " + t.getMessage());
            }
        });
    }
}
