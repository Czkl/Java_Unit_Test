package tw.controllers;

import org.junit.*;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.model.GuessResult;
import tw.views.GameView;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.mockito.Mockito.mock;


/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
    private GameController gameController;
    private Game game;
    private InputCommand inputCommand;
    private GameView gameView;
    private GuessResult guessResult;
    ByteArrayInputStream in;
    ByteArrayOutputStream out;

    @Before
    public void setup() throws Exception
    {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        gameController=mock(GameController.class);
        game=mock(Game.class);
        inputCommand=mock(InputCommand.class);
        gameView = mock(GameView.class);
        guessResult = mock(GuessResult.class);
    }

    public String sysOut() {
        return out.toString();
    }

    public void setIn(String input) {
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    public void testWhenCheckContinueReturnTrue() throws Exception {
        when(game.checkCoutinue()).thenReturn(true);
        gameController.play(inputCommand);
        verify(gameController,times(1)).play(inputCommand);
    }

    @Test
    public void testWhenCheckContinueReturnFalse() throws IOException {
        when(game.checkStatus()).thenReturn("FAIL");
//        when(game.checkCoutinue()).thenReturn(false);
        gameController.play(inputCommand);
        verify(gameController,times(1)).play(inputCommand);
        verify(gameView, times(1)).showGameStatus(game.checkStatus());
        /*sysOut();
        Assert.assertEquals(sysOut(),"Game Status: FAIL");*/
//        verify(gameView,times(1)).showBegin();
    }
}