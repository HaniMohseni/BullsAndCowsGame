package com.sg.bullcowapi.data;

import com.sg.bullcowapi.models.Game;

import java.util.List;

public interface GameDao {

    // begin(add) a new game
    Game add(Game game);

    //Returns a list of all games
    List<Game> getAll();

    //Returns a specific game based on ID
    Game findById(int id);

    void deleteGameById(int id);





}
