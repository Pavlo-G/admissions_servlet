package utils.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FacultyCapacityValidator {
    private Pattern pattern;
    private Matcher matcher;

    private static final String CAPACITY_PATTERN =
            "[1-9][0-9]{0,2}";

    public FacultyCapacityValidator() {
        pattern = Pattern.compile(CAPACITY_PATTERN);
    }


    public boolean validate(final String budgetCapacity, final String totalCapacity) {
        if (isNumber(budgetCapacity) && isNumber(totalCapacity)) {
            return totalMoreThanBudget(budgetCapacity, totalCapacity);
        }
        return false;
    }

    public boolean isNumber(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }


    public boolean totalMoreThanBudget(final String budgetCapacity, final String totalCapacity) {
        return Integer.parseInt(budgetCapacity) <= Integer.parseInt(totalCapacity);
    }
}