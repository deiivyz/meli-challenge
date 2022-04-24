package com.meli.challenge.annotations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DnaValidator implements ConstraintValidator<DnaValidation, String[]> {

    public boolean isValid(String[] dna, ConstraintValidatorContext cxt) {
        if (dna == null || dna.length == 0) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[ACGT]{" + dna.length * dna.length + "}$");
        Matcher matcher = pattern.matcher(String.join("", dna));
        return matcher.find();
    }

}
