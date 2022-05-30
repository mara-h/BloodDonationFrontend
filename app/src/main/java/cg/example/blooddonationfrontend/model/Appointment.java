package cg.example.blooddonationfrontend.model;

import java.util.UUID;

public class Appointment {
    private UUID id;
    private UUID questionnaireId;
    private UUID userId;
    private String dayOfAppointment;
    private String hourOfAppointment;


    public Appointment() {}

    public Appointment(UUID id, String dayOfAppointment, String hourOfAppointment, UUID questionnaire, UUID user) {
        this.id = id;
        this.questionnaireId = questionnaire;
        this.userId = user;
        this.dayOfAppointment = dayOfAppointment;
        this.hourOfAppointment = hourOfAppointment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getQuestionnaire() {
        return questionnaireId;
    }

    public void setQuestionnaire(UUID questionnaire) {
        this.questionnaireId = questionnaire;
    }

    public UUID getUser() {
        return userId;
    }

    public void setUser(UUID user) {
        this.userId = user;
    }

    public String getDayOfAppointment() {
        return dayOfAppointment;
    }

    public void setDayOfAppointment(String dayOfAppointment) {
        this.dayOfAppointment = dayOfAppointment;
    }

    public String getHourOfAppointment() {
        return hourOfAppointment;
    }

    public void setHourOfAppointment(String hourOfAppointment) {
        this.hourOfAppointment = hourOfAppointment;
    }
}
