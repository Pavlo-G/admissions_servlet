package utils.validation;


import java.util.HashMap;
import java.util.Map;

public class FacultyValidator {


    private String lang;

    public FacultyValidator(String lang) {
        this.lang = lang;
    }


    FacultyCapacityValidator facultyCapacityValidator = new FacultyCapacityValidator();
    FieldValidator fieldValidator = new FieldValidator();


    public Map<String, String> validateFaculty(String nameEn, String nameUk, String descriptionEn, String descriptionUk,
                                               String budgetCapacity, String totalCapacity, String requiredSubject1En,
                                               String requiredSubject1Uk, String requiredSubject2En, String requiredSubject2Uk,
                                               String requiredSubject3En, String requiredSubject3Uk) {

        Map<String, String> errors = new HashMap<>();


        if (fieldValidator.validate(totalCapacity)) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.merge("totalCapacityError", " Місця факультету не можуть буди прожніми", (s, s2) -> s+", " + s2);

            } else {
                errors.merge("totalCapacityError", " Faculty capacity can not be empty", (s, s2) -> s +", " +s2);


            }

        }
        if (fieldValidator.validate(budgetCapacity)) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.merge("budgetCapacityError","Бюджетні місця факультету не можуть буди прожніми", (s, s2) -> s +", " +s2);


            } else {
                errors.merge("budgetCapacityError","Faculty budget capacity can not be empty", (s, s2) -> s +", " +s2);

            }

        }
        if (!facultyCapacityValidator.isNumber(totalCapacity)) {

            if (lang != null
                    && lang.equals("uk")) {
                errors.merge("totalCapacityError","Місця факультету повинні бути целим числом 0-999", (s, s2) -> s +", " +s2);
            } else {
                errors.merge("totalCapacityError", "Faculty capacity must be a number 0-999",(s, s2) -> s +", " +s2);
            }

        }
        if (!facultyCapacityValidator.isNumber(budgetCapacity)) {

            if (lang != null
                    && lang.equals("uk")) {
                errors.merge("budgetCapacityError","Бюджетні місця факультету повинні бути целим числом 0-999", (s, s2) -> s +", " +s2);
            } else {
                errors.merge("budgetCapacityError","Faculty budget capacity must be a number 0-999", (s, s2) -> s +", " +s2);
            }

        }

        if (!facultyCapacityValidator.validate(budgetCapacity, totalCapacity)) {

            if (lang != null
                    && lang.equals("uk")) {

                errors.merge("totalCapacityError", "Бюджених місць не може бути більш ніж загальна кількість місць",(s, s2) -> s +", " +s2);
                errors.merge("budgetCapacityError","Бюджених місць не може бути більш ніж загальна кількість місць", (s, s2) -> s +", " +s2);

            } else {
                errors.merge("totalCapacityError", "Budget capacity can not overcome total capacity",(s, s2) -> s +", " +s2);
                errors.merge("budgetCapacityError","Budget capacity can not overcome total capacity", (s, s2) -> s +", " +s2);
            }

        }


        if (fieldValidator.validate(nameEn)) {
            if (lang != null
                    && lang.equals("uk")) {

                errors.put("nameEnError", "Поле Назва Факультету Англійською не може буди пустим");
            } else {
                errors.put("nameEnError", "Field Faculty Name English cannot be empty");
            }


        }

        if (fieldValidator.validate(nameUk)) {

            if (lang != null
                    && lang.equals("uk")) {
                errors.put("nameUkError", "Поле Назва Факультету Українською не може буди пустим");
            } else {
                errors.put("nameUkError", "Field Faculty Name Ukrainian cannot be empty");
            }


        }

        if (fieldValidator.validate(descriptionEn)) {
            if (lang != null
                    && lang.equals("uk")) {

                errors.put("descriptionEnError", "Поле Опис Факультету Англійською не може буди пустим");
            } else {
                errors.put("descriptionEnError", "Field Faculty Description English cannot be empty");
            }
        }

        if (fieldValidator.validate(descriptionUk)) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("descriptionUkError", "Поле Опис Факультету Українською не може буди пустим");
            } else {
                errors.put("descriptionUkError", "Field Faculty Description Ukrainian cannot be empty");
            }

        }

        if (fieldValidator.validate(requiredSubject1En)) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("requiredSubject1EnError", "Поле Необхідний Предмет 1 Англійською не може буди пустим");
            } else {
                errors.put("requiredSubject1EnError", "Field Required Subject 1 English cannot be empty");
            }

        }

        if (fieldValidator.validate(requiredSubject1Uk)) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("requiredSubject1UkError", "Поле Необхідний Предмет 1 Українською не може буди пустим");
            } else {
                errors.put("requiredSubject1UkError", "Field Required Subject 1 Ukrainian cannot be empty");
            }

        }

        if (fieldValidator.validate(requiredSubject2En)) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("requiredSubject2EnError", "Поле Необхідний Предмет 2 Англійською не може буди пустим");
            } else {
                errors.put("requiredSubject2EnError", "Field Required Subject 2 English cannot be empty");
            }

        }

        if (fieldValidator.validate(requiredSubject2Uk)) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("requiredSubject2UkError", "Поле Необхідний Предмет 2 Українською не може буди пустим");
            } else {
                errors.put("requiredSubject2UkError", "Field Required Subject 2 Ukrainian cannot be empty");
            }
        }

        if (fieldValidator.validate(requiredSubject3En)) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("requiredSubject3EnError", "Поле Необхідний Предмет 3 Англійською не може буди пустим");
            } else {
                errors.put("requiredSubject3EnError", "Field Required Subject 3 English cannot be empty");
            }
        }

        if (fieldValidator.validate(requiredSubject3Uk)) {
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
