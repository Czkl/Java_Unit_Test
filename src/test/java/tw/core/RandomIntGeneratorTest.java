package tw.core;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.generator.RandomIntGenerator;

import java.util.Arrays;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {

    public ExpectedException exception = ExpectedException.none();

    private RandomIntGenerator randomIntGenerator = new RandomIntGenerator();


    @Test(expected = Exception.class)
    public void testGenerateNumsException() {
        String result = randomIntGenerator.generateNums(1, 4);
    }

    @Test
    public void test_GenerateNums_Is_Less_then_DigitMax() {
        int digitMax = 10;
        String result = randomIntGenerator.generateNums(digitMax, 4);
        String[] test = result.split(" ");
        long count =  Arrays.asList(test).stream()
                .map(n -> Integer.parseInt(n))
                .filter(n -> n < digitMax)
                .count();
        Assert.assertEquals(4,count);
    }

    @Test
    public void test_GenerateNums_Is_meet_numbersOfNeed() {
        int digitMax = 10;
        int numbersOfNeed = 6;
        String result = randomIntGenerator.generateNums(digitMax, numbersOfNeed);
        String[] test = result.split(" ");
        long count =  Arrays.asList(test).stream()
                .map(n -> Integer.parseInt(n))
                .filter(n -> n < digitMax)
                .count();
        Assert.assertEquals(numbersOfNeed,count);
    }
}