package utils.validation;

import org.junit.Assert;
import org.junit.Test;



public class FacultyCapacityValidatorTest {

    FacultyCapacityValidator facultyCapacityValidator= new FacultyCapacityValidator();
    @Test
    public void isNumberAssertTrue() {
     Assert.assertTrue(facultyCapacityValidator.isNumber("2"));
    }
    @Test
    public void isNumberAssertFalse() {
        Assert.assertFalse(facultyCapacityValidator.isNumber("c"));
    }

    @Test
    public void totalMoreThanBudgetAssertTrue() {
        Assert.assertTrue(facultyCapacityValidator.totalMoreThanBudget("10","15"));
    }
    @Test
    public void totalMoreThanBudgetAssertFalse() {
        Assert.assertFalse(facultyCapacityValidator.totalMoreThanBudget("15","10"));
    }
}