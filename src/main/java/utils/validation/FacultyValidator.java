package utils.validation;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacultyValidator {


    private String lang;

    public FacultyValidator(String lang) {
        this.lang = lang;
    }


    FacultyCapacityValidator facultyCapacityValidator = new FacultyCapacityValidator();
    FieldValidator fieldValidator = new FieldValidator();
//    (String nameEn, String nameUk, String descriptionEn, String descriptionUk,
//    String budgetCapacity, String totalCapacity, String requiredSubject1En,
//    String requiredSubject1Uk, String requiredSubject2En, String requiredSubject2Uk,
//    String requiredSubject3En, String requiredSubject3Uk


    public Map<String, String> validateFaculty(Map<String,String> parameters) {

        Map<String, String> errors = new HashMap<>();


        if (fieldValidator.validate(parameters.get("totalCapacity"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.merge("totalCapacityError", " Місця факультету не можуть буди прожніми", (s, s2) -> s+", " + s2);

            } else {
                errors.merge("totalCapacityError", " Faculty capacity can not be empty", (s, s2) -> s +", " +s2);


            }

        }
        if (fieldValidator.validate(parameters.get("budgetCapacity"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.merge("budgetCapacityError","Бюджетні місця факультету не можуть буди прожніми", (s, s2) -> s +", " +s2);


            } else {
                errors.merge("budgetCapacityError","Faculty budget capacity can not be empty", (s, s2) -> s +", " +s2);

            }

        }
        if (!facultyCapacityValidator.isNumber(parameters.get("totalCapacity"))) {

            if (lang != null
                    && lang.equals("uk")) {
                errors.merge("totalCapacityError","Місця факультету повинні бути целим числом 0-999", (s, s2) -> s +", " +s2);
            } else {
                errors.merge("totalCapacityError", "Faculty capacity must be a number 0-999",(s, s2) -> s +", " +s2);
            }

        }
        if (!facultyCapacityValidator.isNumber(parameters.get("budgetCapacity"))) {

            if (lang != null
                    && lang.equals("uk")) {
                errors.merge("budgetCapacityError","Бюджетні місця факультету повинні бути целим числом 0-999", (s, s2) -> s +", " +s2);
            } else {
                errors.merge("budgetCapacityError","Faculty budget capacity must be a number 0-999", (s, s2) -> s +", " +s2);
            }

        }

        if (!facultyCapacityValidator.validate(parameters.get("budgetCapacity"), parameters.get("totalCapacity"))) {

            if (lang != null
                    && lang.equals("uk")) {

                errors.merge("totalCapacityError", "Бюджених місць не може бути більш ніж загальна кількість місць",(s, s2) -> s +", " +s2);
                errors.merge("budgetCapacityError","Бюджених місць не може бути більш ніж загальна кількість місць", (s, s2) -> s +", " +s2);

            } else {
                errors.merge("totalCapacityError", "Budget capacity can not overcome total capacity",(s, s2) -> s +", " +s2);
                errors.merge("budgetCapacityError","Budget capacity can not overcome total capacity", (s, s2) -> s +", " +s2);
            }

        }


        if (fieldValidator.validate(parameters.get("nameEn"))) {
            if (lang != null
                    && lang.equals("uk")) {

                errors.put("nameEnError", "Поле Назва Факультету Англійською не може буди пустим");
            } else {
                errors.put("nameEnError", "Field Faculty Name English cannot be empty");
            }


        }

        if (fieldValidator.validate(parameters.get("nameUk"))) {

            if (lang != null
                    && lang.equals("uk")) {
                errors.put("nameUkError", "Поле Назва Факультету Українською не може буди пустим");
            } else {
                errors.put("nameUkError", "Field Faculty Name Ukrainian cannot be empty");
            }


        }

        if (fieldValidator.validate(parameters.get("descriptionEn"))) {
            if (lang != null
                    && lang.equals("uk")) {

                errors.put("descriptionEnError", "Поле Опис Факультету Англійською не може буди пустим");
            } else {
                errors.put("descriptionEnError", "Field Faculty Description English cannot be empty");
            }
        }

        if (fieldValidator.validate(parameters.get("descriptionUk"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("descriptionUkError", "Поле Опис Факультету Українською не може буди пустим");
            } else {
                errors.put("descriptionUkError", "Field Faculty Description Ukrainian cannot be empty");
            }

        }

        if (fieldValidator.validate(parameters.get("requiredSubject1En"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("requiredSubject1EnError", "Поле Необхідний Предмет 1 Англійською не може буди пустим");
            } else {
                errors.put("requiredSubject1EnError", "Field Required Subject 1 English cannot be empty");
            }

        }

        if (fieldValidator.validate(parameters.get("requiredSubject1Uk"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("requiredSubject1UkError", "Поле Необхідний Предмет 1 Українською не може буди пустим");
            } else {
                errors.put("requiredSubject1UkError", "Field Required Subject 1 Ukrainian cannot be empty");
            }

        }

        if (fieldValidator.validate(parameters.get("requiredSubject2En"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("requiredSubject2EnError", "Поле Необхідний Предмет 2 Англійською не може буди пустим");
            } else {
                errors.put("requiredSubject2EnError", "Field Required Subject 2 English cannot be empty");
            }

        }

        if (fieldValidator.validate(parameters.get("requiredSubject2Uk"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("requiredSubject2UkError", "Поле Необхідний Предмет 2 Українською не може буди пустим");
            } else {
                errors.put("requiredSubject2UkError", "Field Required Subject 2 Ukrainian cannot be empty");
            }
        }

        if (fieldValidator.validate(parameters.get("requiredSubject3En"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("requiredSubject3EnError", "Поле Необхідний Предмет 3 Англійською не може буди пустим");
            } else {
                errors.put("requiredSubject3EnError", "Field Required Subject 3 English cannot be empty");
            }
        }

        if (fieldValidator.validate(parameters.get("requiredSubject3Uk"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("requiredSubject3UkError", "Поле Необхідний Предмет 3 Українською не може буди пустим");
            } else {
                errors.put("requiredSubject3UkError", "Field Required Subject 3 Ukrainian cannot be empty");
            }
        }


        return errors;
    }
}
