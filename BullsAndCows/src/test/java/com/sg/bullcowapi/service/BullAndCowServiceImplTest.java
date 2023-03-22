package com.sg.bullcowapi.service;

import com.sg.bullcowapi.data.GameDao;
import com.sg.bullcowapi.data.GameDaoStubImpl;
import com.sg.bullcowapi.data.RoundDao;
import com.sg.bullcowapi.data.RoundDaoStubImpl;
import com.sg.bullcowapi.models.Game;
import com.sg.bullcowapi.models.Round;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BullAndCowServiceImplTest {


    GameDao gameDao ;
    RoundDao roundDao ;
    Game onlyGame, myGame;
    List<Game> games = new ArrayList<>();

    private Round onlyRound;
    private List<Round> rounds = new ArrayList<>();

    private BullAndCowService service;
    public BullAndCowServiceImplTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = applicationContext.getBean(BullAndCowServiceImpl.class);
        gameDao = applicationContext.getBean(GameDaoStubImpl.class);
        roundDao = applicationContext.getBean(RoundDaoStubImpl.class);

    }

    @BeforeEach
    void setUp() {

        List<Game> games = gameDao.getAll();
        for (Game game:games) {
            List<Round> rounds = roundDao.getAllByGameId(game.getGameId());
            for (Round round : rounds) {
                roundDao.deleteRoundById(game.getGameId());

            }
        }
        for (Game game:games){
            gameDao.deleteGameById(game.getGameId());
        }

        onlyGame = new Game();
        onlyGame.setGameId(1);
        onlyGame.setGameFinished(true);
        onlyGame.setAnswer(1234);
        gameDao.add(onlyGame);

        Round onlyRound = new Round();
        onlyRound.setGuess("1234");
        onlyRound.setGameId(1);
        onlyRound.setGuessResult("e:4:p:0");
        onlyRound.setPlayTime(LocalDateTime.parse("2022-12-15T11:00:35"));
        roundDao.add(onlyRound);
    }


        @Test
        void addGame() {

            int sizeBeforeAdd = service.getAllGames().size();
            service.addGame(onlyGame);
            int sizeAfterAdd=service.getAllGames().size();
            assertEquals(sizeBeforeAdd,sizeBeforeAdd);

        }

    @Test
    void addRound() {


        onlyGame = new Game();
        onlyGame = service.addGame(onlyGame);
       // int gameId = service.getAllGames().get(1).getGameId();
        int sizeBeforeAdd = service.getAllByGameId(onlyGame.getGameId()).size();

        Round onlyRound = new Round();
        onlyRound.setGuess("1234");
        onlyRound.setGameId(onlyGame.getGameId());
        onlyRound.setGuessResult("e:4:p:0");
        onlyRound.setPlayTime(LocalDateTime.parse("2022-12-15T11:00:35"));

        service.addRound(onlyRound);
        int sizeAfterAdd=service.getAllByGameId(onlyGame.getGameId()).size();

        assertEquals(sizeBeforeAdd , sizeAfterAdd-1);
    }



    @Test
    void GuessResult() {

        service.addGame(onlyGame);
       onlyRound = service.addRound(onlyRound);
       assertTrue(onlyRound.getGuessResult().equals("e:4:p:0"));
    }

}