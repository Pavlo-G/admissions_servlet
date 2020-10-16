package web.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GradeValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String GRADE_PATTERN =
            "[2-9]|1[0-2]?";

    public GradeValidator() {
        pattern = Pattern.compile(GRADE_PATTERN);
    }

    /**
     * Validate hex with regular expression
     *
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
}
