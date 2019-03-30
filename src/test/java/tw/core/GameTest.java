package tw.core;

import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;
import tw.core.model.GuessResult;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(Game.class)
public class GameTest {


    @Test
    public void testGuess() throws OutOfRangeAnswerException, IllegalAccessException {
        AnswerGenerator answerGenerator = new AnswerGenerator(new RandomIntGenerator());
        Answer answer = mock(Answer.class);
        Game game = new Game(answerGenerator);

        MemberModifier.field(Game.class,"actualAnswer").set(game,answerGenerator.generate());
        GuessResult guessResult = game.guess(answer);
        Assert.assertNotNull(guessResult);

    }

    @Test
    public void testCheckStatus() throws Exception {
        AnswerGenerator answerGenerator = mock(AnswerGenerator.class);
        List<GuessResult> guessResults = mock(List.class);
        String result = "";

        Game game = PowerMockito.spy(new Game(answerGenerator));

        PowerMockito.when(game, "checkCorrectGuessResult").thenReturn(true,false);
        MemberModifier.field(Game.class, "guessResults").set(game, guessResults);
        // 反射取得修改guessResults
//        Field field = game.getClass().getDeclaredField("guessResults");
//        field.setAccessible(true);
//        field.set(game,guessResults);
        when(guessResults.size()).thenReturn(8,2,3,9);

        result = game.checkStatus();
        Assert.assertEquals("fail",result);

        result = game.checkStatus();
        Assert.assertEquals("success",result);

        result = game.checkStatus();
        Assert.assertEquals("continue",result);

        result = game.checkStatus();
        Assert.assertEquals("fail",result);

    }

}
