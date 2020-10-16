package web.validation;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneValidatorTest {

    @Test
    public void validateNumberTrue() {
        PhoneValidator phoneValidator= new PhoneValidator();

        Assert.assertTrue(phoneValidator.validate("123-123-1234"));

    }
    @Test
    public void validateNumberFalse() {
        PhoneValidator phoneValidator= new PhoneValidator();

        Assert.assertFalse(phoneValidator.validate("123123-1234"));

    }
    @Test
    public void validateNumberGivingNotNumbers() {
        PhoneValidator phoneValidator= new PhoneValidator();

        Assert.assertFalse(phoneValidator.validate("abc-def-1234"));

    }



}