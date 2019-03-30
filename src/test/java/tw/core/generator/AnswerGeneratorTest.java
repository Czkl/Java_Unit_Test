package tw.core.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tw.core.Answer;
import tw.core.exception.OutOfRangeAnswerException;

import static org.mockito.Mockito.*;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {

    private AnswerGenerator answerGenerator;
    private RandomIntGenerator randomIntGenerator;
    private Answer answer;

    @Before
    public void setUp() {

        randomIntGenerator = mock(RandomIntGenerator.class);
//        answer = mock(Answer.class);
        answerGenerator = new AnswerGenerator(randomIntGenerator);

    }

    @Test
    public void testGenerate() throws OutOfRangeAnswerException {
        when(randomIntGenerator.generateNums(9, 4)).thenReturn("0 7 2 1");
        Answer answer =answerGenerator.generate();
        Assert.assertNotNull(answer);
    }

}

