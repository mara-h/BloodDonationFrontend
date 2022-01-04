package cg.example.blooddonationfrontend.controller;

import android.widget.EditText;

public class InputValidator {
    public boolean doStringsMatch(String firstString, String seconString) {
        if(firstString == seconString)
            return true;
        return false;
    }

    public void setFieldError(EditText field, String errorMessage) {
        field.setError(errorMessage);
        field.requestFocus();
        return; ///?
    }
}
