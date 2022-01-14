package cg.example.blooddonationfrontend.model;

import java.util.UUID;

public class Questionnaire {

    private UUID id;
    public UUID userId;
    private Boolean valid;


    public Questionnaire(UUID id, UUID userId,Boolean valid) {
        this.id = id;
        this.userId = userId;
        this.valid = valid;
    }

    public Questionnaire() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Boolean isValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
