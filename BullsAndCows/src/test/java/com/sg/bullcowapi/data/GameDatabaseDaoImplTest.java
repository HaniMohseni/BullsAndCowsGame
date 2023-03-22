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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class GameDatabaseDaoImplTest {

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;



    @BeforeEach
    void setUp() {

        List<Game> games = gameDao.getAll();
        for(Game game : games) {
            gameDao.deleteGameById(game.getGameId());
        }

        List<Round> rounds = roundDao.getAll();
        for(Round roun : rounds) {
            roundDao.deleteRoundById(roun.getRoundId());
        }
    }

    @Test
    void add() {

        Game game = new Game();
        game.setGameId(1);
        game.setGameFinished(true);
        game.setAnswer(1234);
        gameDao.add(game);

        Game fromDao =gameDao.findById(game.getGameId());
        assertEquals(game, fromDao);


    }

    @Test
    void getAll() {

        Game game = new Game();
        game.setGameId(1);
        game.setGameFinished(true);
        game.setAnswer(1234);
        gameDao.add(game);

        Game game2 = new Game();
        game2.setGameId(2);
        game2.setGameFinished(true);
        game2.setAnswer(4678);
        gameDao.add(game2);

        List <Game> games = gameDao.getAll();

        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
    }

    @Test
    void findById() {
        Game game = new Game();
        game.setGameId(1);
        game.setGameFinished(true);
        game.setAnswer(1234);
        game =  gameDao.add(game);

        Game fromDao = gameDao.findById(game.getGameId());

        assertEquals(game, fromDao);


    }
}