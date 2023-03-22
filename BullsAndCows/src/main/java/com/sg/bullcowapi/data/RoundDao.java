package com.sg.bullcowapi.data;

import com.sg.bullcowapi.models.Game;
import com.sg.bullcowapi.models.Round;

import java.util.List;

public interface RoundDao {


    // add a new round
    Round add(Round round);

    //Returns a list of round for a specific game
    List<Round> getAllByGameId(int gameId);

    void deleteRoundById(int id);

    List<Round> getAll();



}
