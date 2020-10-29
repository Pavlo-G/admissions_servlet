package utils.validation;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidatorTest {

    @Test
    public void validateWrongEmailAssertFalse() {
        EmailValidator emailValidator= new EmailValidator();
        Assert.assertFalse(emailValidator.validate("xxx"));
    }


    @Test
    public void validateRightEmailAssertTrue() {
        EmailValidator emailValidator= new EmailValidator();
        Assert.assertTrue(emailValidator.validate("mail@gmail.com"));
    }
}