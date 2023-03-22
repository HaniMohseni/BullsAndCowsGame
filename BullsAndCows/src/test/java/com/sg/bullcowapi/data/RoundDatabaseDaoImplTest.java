package com.sg.bullcowapi.data;

import com.sg.bullcowapi.TestApplicationConfiguration;
import com.sg.bullcowapi.models.Game;
import com.sg.bullcowapi.models.Round;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class RoundDatabaseDaoImplTest {
   Game game;
    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;



    @BeforeEach
    void setUp() {

//        List<Round> rounds = roundDao.getAll();
//        for(Round round : rounds) {
//            roundDao.deleteRoundById(round.getRoundId());
//        }
//
//        List<Game> games = gameDao.getAll();
//        for(Game game : games) {
//            gameDao.deleteGameById(game.getGameId());
//        }


        game = new Game();
        game.setAnswer(1234);
        game.setGameFinished(true);
        game = gameDao.add(game);


    }

    @Test
    void add() {
        Round round = new Round();
        //round.setRoundId(1);
        round.setGuess("1234");
        round.setGameId(game.getGameId());
        round.setGuessResult("e:0:p:3");
        round.setPlayTime(LocalDateTime.parse("2022-12-15T11:00:35"));
        round =   roundDao.add(round);
        //List<Round> fromDao = roundDao.getAllByGameId(round.getGameId());
        List<Round> fromDao = roundDao.getAllByGameId(game.getGameId());

      //assertEquals(round, fromDao);
        System.out.println(fromDao.get(0));
        assertTrue(fromDao.contains(round));

    }

    @Test
    void getAllByGameId() {

        Round round = new Round();
       // round.setRoundId(1);
        round.setGuess("1234");
        round.setGameId(game.getGameId());
        round.setGuessResult("0");
        round.setPlayTime(LocalDateTime.parse("2022-12-15T11:00:35"));
        round = roundDao.add(round);

        //Game game= gameDao.findById(round.getGameId());
        List<Round>rounds =roundDao.getAll();

        List<Round> fromDao = roundDao.getAllByGameId(round.getGameId());
        assertNotNull(round);
        assertTrue(rounds.contains(round));


        //rounds = roundDao.add(rounds);
        //assertTrue(rounds.contains(round));
        //assertTrue(rounds.contains(round));



    }
}