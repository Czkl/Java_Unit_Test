package tw.core;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.model.Record;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testCreateAnswer() {
        Answer answer = Answer.createAnswer("4 5 6 7");
        Assert.assertNotNull(answer);
    }


    @Test
    public void testValidateException() throws OutOfRangeAnswerException {
        Answer answer = new Answer();

        exception.expect(OutOfRangeAnswerException.class);
        exception.expectMessage("Answer format is incorrect");
        List<String>  numList = Arrays.asList(new String[]{"2", "2", "4", "5"});
        answer.setNumList(numList);
        answer.validate();

        numList = Arrays.asList(new String[]{"2", "555", "4", "5"});
        answer.setNumList(numList);
        answer.validate();
    }

    @Test
    public void testValidate() throws OutOfRangeAnswerException {

        Answer answer = new Answer();
        List<String> numList = Arrays.asList(new String[]{"2", "3", "4", "5"});
        answer.setNumList(numList);
        answer.validate();
    }

    @Test
    public void testCheck() {
        Answer answer = Answer.createAnswer("1 2 3 4");
        Answer inputAnswer = Answer.createAnswer("3 4 7 8");
        Record record= answer.check(inputAnswer);

        Assert.assertEquals("0A2B", record.getValue());

        List<String> numList = Arrays.asList(new String[]{"3", "1", "4", "2"});
        inputAnswer.setNumList(numList);

        record = answer.check(inputAnswer);
        Assert.assertEquals("0A4B", record.getValue());

        numList = Arrays.asList(new String[]{"1", "2", "3", "4"});
        inputAnswer.setNumList(numList);

        record = answer.check(inputAnswer);
        Assert.assertEquals("4A0B", record.getValue());

        numList = Arrays.asList(new String[]{"7", "8", "9", "5"});
        inputAnswer.setNumList(numList);

        record = answer.check(inputAnswer);
        Assert.assertEquals("0A0B", record.getValue());
    }
}