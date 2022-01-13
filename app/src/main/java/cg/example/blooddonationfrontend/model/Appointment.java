package cg.example.blooddonationfrontend.model;

import java.util.UUID;

public class Appointment {
    private UUID id;
    private UUID questionnaire;
    private UUID user;
    private String dayOfAppointment;
    private String hourOfAppointment;


    public Appointment() {}

    public Appointment(UUID id, String dayOfAppointment, String hourOfAppointment, UUID questionnaire, UUID user) {
        this.id = id;
        this.questionnaire = questionnaire;
        this.user = user;
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
        return questionnaire;
    }

    public void setQuestionnaire(UUID questionnaire) {
        this.questionnaire = questionnaire;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
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
