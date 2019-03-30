package tw.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tw.validator.InputValidator;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {


    @Test
    public void testValidate() {
        InputValidator inputValidator = new InputValidator();

        Assert.assertTrue(inputValidator.validate("1 2 3 4"));
        Assert.assertFalse(inputValidator.validate("21 2 4 1"));
        Assert.assertFalse(inputValidator.validate("2 2 1 3"));
        Assert.assertFalse(inputValidator.validate("1 1 1 1"));
        Assert.assertFalse(inputValidator.validate(""));
        Assert.assertFalse(inputValidator.validate("2,2,1,3"));
        Assert.assertFalse(inputValidator.validate("1,2"));
        Assert.assertFalse(inputValidator.validate("1 3"));
        Assert.assertFalse(inputValidator.validate("5 3 2"));
    }
}
