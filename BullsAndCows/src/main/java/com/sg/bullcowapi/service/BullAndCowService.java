package com.sg.bullcowapi.service;

import com.sg.bullcowapi.models.Game;
import com.sg.bullcowapi.models.Round;

import java.util.List;

public interface BullAndCowService {

    Game addGame(Game game);
    Round addRound(Round round);
    Game findById(int id);
    List<Game> getAllGames() ;
    List<Round> getAllByGameId(int gameId);

}
