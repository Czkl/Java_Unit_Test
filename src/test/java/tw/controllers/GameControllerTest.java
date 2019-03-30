package tw.controllers;

import com.google.inject.Injector;
import org.junit.*;

import static com.google.inject.Guice.createInjector;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.Spy;
import tw.GuessNumberModule;
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


    private GameController gameController ;
    private Game game;
    private GameView gameView;
    private InputCommand inputCommand;
    private GuessResult guessResult;
    private Answer answer;

    ByteArrayOutputStream out;

    @Before
    public void setup() throws Exception
    {

        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        answer = mock(Answer.class);
        inputCommand=mock(InputCommand.class);

        game = mock(Game.class);
        gameView = mock(GameView.class);
        guessResult = mock(GuessResult.class);

        gameController = new GameController(game,gameView);
        gameController = spy(gameController);
    }

    public String sysOut() {
        return out.toString();
    }





    @Test
    public void testPlayWhenCheckCoutinueTrueThenFalse() throws IOException {
        when(game.checkCoutinue()).thenReturn(true,false);
        when(game.guess(inputCommand.input())).thenReturn(guessResult);
        gameController.play(inputCommand);

        verify(gameController, times(2)).play(inputCommand);
        verify(gameView, times(1)).showGuessResult(guessResult);
        verify(gameView, times(1)).showGuessHistory(game.guessHistory());
        verify(gameView, times(1)).showGameStatus(game.checkStatus());
    }


    @Test
    public void testWhenCheckContinueReturn6TrueThenFalse() throws Exception {
        when(game.checkCoutinue()).thenReturn(true,true,true,true,true,true,false);
        when(game.guess(inputCommand.input())).thenReturn(guessResult);

        gameController.play(inputCommand);

        verify(gameController,times(7)).play(inputCommand);
        verify(gameView, times(6)).showGuessResult(guessResult);
        verify(gameView, times(6)).showGuessHistory(game.guessHistory());
        verify(gameView, times(1)).showGameStatus(game.checkStatus());
    }


    @Test
    public void testWhenCheckContinueReturnFalse() throws IOException {
        when(game.checkCoutinue()).thenReturn(false);
        gameController.play(inputCommand);

        verify(gameController,times(1)).play(inputCommand);
        verify(gameView, times(1)).showGameStatus(game.checkStatus());
    }

}