package utils.validation;

import java.util.HashMap;
import java.util.Map;

public class AdmissionRequestValidator {
    private String lang;
    FieldValidator fieldValidator = new FieldValidator();
    GradeValidator gradeValidator = new GradeValidator();

    public AdmissionRequestValidator(String lang) {
        this.lang = lang;
    }

    public Map<String, String> validateAdmissionRequest(Map<String, String> parameters) {

        Map<String, String> errors = new HashMap<>();

        for (Map.Entry<String, String> entity : parameters.entrySet()
        ) {
            if (fieldValidator.validate(entity.getValue())) {
                if (lang != null
                        && lang.equals("uk")) {
                    errors.put(entity.getKey() + "Error", " Місця факультету не можуть буди прожніми");

                } else {
                    errors.put(entity.getKey() + "Error", " Faculty capacity can not be empty");

                }

            }
            if (!gradeValidator.validate(entity.getValue())) {
                if (lang != null
                        && lang.equals("uk")) {

                    errors.put(entity.getKey() + "Error", "Оцінка може бути від 1 до 12");
                } else {
                    errors.put(entity.getKey() + "Error", "Grade should be from 1 to 12");
                }

            }
        }
        return errors;
    }

}

