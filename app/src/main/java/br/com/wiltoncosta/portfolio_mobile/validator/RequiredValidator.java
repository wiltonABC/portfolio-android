package br.com.wiltoncosta.portfolio_mobile.validator;

import android.widget.EditText;

public class RequiredValidator extends Validator {

    public RequiredValidator(EditText fieldToValidate, String errorMessage) {
        super(fieldToValidate, errorMessage);
    }

    @Override
    public void validate(String value) {
        clearValidationError();

        if (value.length() == 0) {
            showValidationError();
            valid = false;
        } else {
            valid = true;
        }

    }

}
