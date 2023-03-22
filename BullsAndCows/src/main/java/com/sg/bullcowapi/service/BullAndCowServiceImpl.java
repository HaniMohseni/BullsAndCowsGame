package com.sg.bullcowapi.service;

import com.sg.bullcowapi.data.GameDao;
import com.sg.bullcowapi.data.RoundDao;
import com.sg.bullcowapi.models.Game;
import com.sg.bullcowapi.models.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BullAndCowServiceImpl implements BullAndCowService {
    private static String chosenAnswer = "";
    int currentGameId=0;

    private GameDao gameDao;

    private RoundDao roundDao;

    @Autowired

    public BullAndCowServiceImpl(GameDao gameDao,RoundDao roundDao ){
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }


    @Override
    public Game addGame(Game game) {

        //creating answer
        chosenAnswer =  produceRandomAnswer();
        game.setAnswer(Integer.parseInt(chosenAnswer));
        game.setGameFinished(false);
        return gameDao.add(game);
    }

    @Override
    public Round addRound(Round round) {
        // gameId and guess from controller
        //creating guess result
        currentGameId=round.getGameId();
      Game myGame =  gameDao.findById(currentGameId);
      int answer = myGame.getAnswer();
      String guess =  round.getGuess();
      int eCount = exactMatchCompute(Integer.toString(answer),(guess));
      int pCount = partialMatchCompute(Integer.toString(answer),(guess));
      String guessResult = "e:"+eCount + ":p:"+ pCount;
      round.setGuessResult(guessResult);
      round.setPlayTime(LocalDateTime.now());
      if(eCount==pCount){
          myGame.setGameFinished(true);
      }
      return roundDao.add(round);
    }

    @Override
    public Game findById(int id) {
        return gameDao.findById(id);
    }

    @Override
    public List<Game> getAllGames() {
        return gameDao.getAll();
    }

    @Override
    public List<Round> getAllByGameId(int gameId) {
        return roundDao.getAllByGameId(gameId);
    }


    //generating random 4 digit answer in string format
    private static String produceRandomAnswer() {
        int randomAnswer = 1000 + ((int) (Math.random() * 10000) % 9000);
        chosenAnswer = Integer.toString(randomAnswer);
        while (hasDuplicatedDigits(chosenAnswer)) {
            produceRandomAnswer();
        }
        return chosenAnswer;
    }

    // Checking duplicate digits for random answer
    private static boolean hasDuplicatedDigits(String answer) {
        for (int i = 0; i < answer.length() - 1; i++) {
            for (int j = i + 1; j < answer.length(); j++) {
                if (answer.charAt(i) == answer.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }


    // computing exactMatch
    private static int exactMatchCompute(String answer, String guess) {
        int exactMatch = 0;

        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == guess.charAt(i)) {
                exactMatch++;
            }
        }
        return exactMatch;
    }

    //computing partial matches
    private static int partialMatchCompute(String answer, String guess) {
        int partialMatch = 0;
        for (int i = 0; i < answer.length(); i++) {
            for (int j = 0; j < guess.length(); j++) {
                if (i != j) {
                    if (answer.charAt(i) == guess.charAt(j)) {
                        partialMatch++;
                    }
                }
            }
        }
        return partialMatch;
    }

}
