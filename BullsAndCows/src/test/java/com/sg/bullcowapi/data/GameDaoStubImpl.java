package com.sg.bullcowapi.data;

import com.sg.bullcowapi.models.Game;
import com.sg.bullcowapi.service.BullAndCowServiceImpl;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class GameDaoStubImpl implements GameDao{

    private Game onlyGame;
    private List<Game> games = new ArrayList<>();

   public GameDaoStubImpl(){


    }

    @Override
    public Game add(Game game) {
        onlyGame = new Game();
        onlyGame.setGameId(1);
        onlyGame.setGameFinished(true);
        onlyGame.setAnswer(1234);
        games.add(onlyGame);

//        games.add(game);
        return onlyGame;
    }

    @Override
    public List<Game> getAll() {
        return games;
    }

    @Override
    public Game findById(int id) {
       if(onlyGame.getGameId()==id)
        return onlyGame;
       else
           return null;
    }

    @Override
    public void deleteGameById(int id) {
       if(id == onlyGame.getGameId()){
           games.remove(onlyGame);

       }


    }
}
