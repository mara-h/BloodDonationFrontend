package cg.example.blooddonationfrontend.model;

import java.util.List;

public class Globals {

    public static void setCurrentUser(User currentUser) {
        if(currentUser.getFirstName() == null) {
            currentUser.setFirstName("");
        }
        if(currentUser.getLastName() == null) {
            currentUser.setLastName("");
        }
        if(currentUser.getEmail() == null) {
            currentUser.setEmail("");
        }
        if(currentUser.getPassword() == null) {
            currentUser.setPassword("");
        }
        if(currentUser.getAge() == null) {
            currentUser.setAge("");
        }
        if(currentUser.getCnp() == null) {
            currentUser.setCnp("");
        }
        if(currentUser.getSex() == null) {
            currentUser.setSex("");
        }
        if(currentUser.getBloodGroup() == null) {
            currentUser.setBloodGroup("");
        }

        Globals.currentUser = currentUser;
    }

    public static User currentUser;

    public static List<Question> allQuestions;

    public static Questionnaire currentQuestionnaire;

    public static User questionnaireUser;

    public static Boolean canGenerate = false;

    public static Appointment currentAppointment;

    public static void setQuestions(List<Question> allQuestions) {
        Globals.allQuestions = allQuestions;
    }

    public static void setCurrentQuestionnaire(Questionnaire currentQuestionnaire) {
        Globals.currentQuestionnaire = currentQuestionnaire;
    }

    public static void setQuestionnaireUser(User questionnaireUser) {
        Globals.questionnaireUser = questionnaireUser;
    }

    public static void setCanGenerate(Boolean canGenerate) {
        Globals.canGenerate = canGenerate;
    }

    //adaugata de mine, nu stiu daca e ok
    public static void setCurrentAppointment(Appointment currentAppointment) {
        Globals.currentAppointment = currentAppointment;
    }
}
