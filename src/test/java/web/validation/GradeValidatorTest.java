package web.validation;

import org.junit.Assert;
import org.junit.Test;

import java.lang.management.GarbageCollectorMXBean;

import static org.junit.Assert.*;

public class GradeValidatorTest {

    @Test
    public void validateGradeFrom1To12AssertTrue() {
        GradeValidator gradeValidator = new GradeValidator();

        for (int i = 1; i <=12 ; i++) {
            Assert.assertTrue(gradeValidator.validate(String.valueOf(i)));

        }

    }


    @Test
    public void validateGradeMore12AssertFalse() {
        GradeValidator gradeValidator = new GradeValidator();

        Assert.assertFalse(gradeValidator.validate(String.valueOf(13)));
        Assert.assertFalse(gradeValidator.validate(String.valueOf(135)));
        Assert.assertFalse(gradeValidator.validate(String.valueOf(96)));

    }

    @Test
    public void validateGradeLessZeroAssertFalse() {

        GradeValidator gradeValidator = new GradeValidator();

        Assert.assertFalse(gradeValidator.validate(String.valueOf(0)));
        Assert.assertFalse(gradeValidator.validate(String.valueOf(-1)));
        Assert.assertFalse(gradeValidator.validate(String.valueOf(-96)));

    }

}