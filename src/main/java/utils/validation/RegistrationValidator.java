package utils.validation;

import java.util.HashMap;
import java.util.Map;

public class RegistrationValidator {
    private final String lang;
    private final FieldValidator fieldValidator;
    private final EmailValidator emailValidator;
    private final PhoneValidator phoneValidator;


    public RegistrationValidator(String lang, FieldValidator fieldValidator, EmailValidator emailValidator, PhoneValidator phoneValidator) {
        this.lang = lang;
        this.fieldValidator = fieldValidator;
        this.emailValidator = emailValidator;
        this.phoneValidator = phoneValidator;
    }

    public Map<String, String> validateRegistration(Map<String, String> parameters) {

        Map<String, String> errors = new HashMap<>();


        if (fieldValidator.validate(parameters.get("username"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("usernameError", "Логін не може бути пустим");
            } else {
                errors.put("usernameError", "Login cannot be empty");
            }
        }
        if (fieldValidator.validate(parameters.get("password"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("passwordError", "Пароль не може бути пустим");
            } else {
                errors.put("passwordError", "Password cannot be empty");
            }
        }

        if (fieldValidator.validate(parameters.get("email"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.merge("emailError", "Поштова скринька не може буди пустою", (s, s2) -> s + ", " + s2);
            } else {
                errors.merge("emailError", "Email cannot be empty", (s, s2) -> s + ", " + s2);
            }
        }


        if (!emailValidator.validate(parameters.get("email"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.merge("emailError", "Поштова скринька не дійсна", (s, s2) -> s + ", " + s2);
            } else {
                errors.merge("emailError", "Email is not valid", (s, s2) -> s + ", " + s2);

            }
        }

        if (fieldValidator.validate(parameters.get("firstName"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("firstNameError", "Поле Ім'я не може буди пустим");
            } else {
                errors.put("firstNameError", "First Name cannot be empty");
            }
        }


        if (fieldValidator.validate(parameters.get("lastName"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("lastNameError", "Поле Призвіще не може буди пустим");
            } else {
                errors.put("lastNameError", "Last Name cannot be empty");
            }
        }


        if (fieldValidator.validate(parameters.get("address"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("addressError", "Поле Адреса не може буди пустим");
            } else {
                errors.put("addressError", "address cannot be empty");
            }
        }

        if (fieldValidator.validate(parameters.get("city"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("cityError", "Поле Місто не може буди пустим");

            } else {
                errors.put("cityError", "City cannot be empty");
            }

        }


        if (fieldValidator.validate(parameters.get("region"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("regionError", "Поле Область не може буди пустим");
            } else {
                errors.put("regionError", "Region cannot be empty");
            }

        }


        if (fieldValidator.validate(parameters.get("school"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("schoolError", "Поле Школа не може буди пустим");
            } else {
                errors.put("schoolError", "School cannot be empty");
            }
        }


        if (fieldValidator.validate(parameters.get("phoneNumber"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.put("phoneNumberError", "Поле Номер не може буди пустим");
            } else {
                errors.put("phoneNumberError", "Number cannot be empty");
            }
        }


        if (!phoneValidator.validate(parameters.get("phoneNumber"))) {
            if (lang != null
                    && lang.equals("uk")) {
                errors.merge("phoneNumberError", "Номер не відповідае формату ххх-ххх-хххх", (s, s2) -> s + ", " + s2);
            } else {
                errors.merge("phoneNumberError", "Wrong number format xxx-xxx-xxxx", (s, s2) -> s + ", " + s2);
            }
        }

        return errors;
    }

}
