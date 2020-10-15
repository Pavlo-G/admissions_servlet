package web.validation;

import jakarta.el.EvaluationListener;

public class FieldValidator {

    public boolean validate(final String hex) {

        return hex == null || hex.isEmpty();
    }
}
