package com.sg.bullcowapi.data;

import com.sg.bullcowapi.models.Round;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoundDaoStubImpl implements RoundDao {


    private Round onlyRound;
    private List<Round> rounds = new ArrayList<>();


    public RoundDaoStubImpl() {
//        Round onlyRound = new Round();
//        onlyRound.setRoundId(1);
//        onlyRound.setGuess("1234");
//        onlyRound.setGameId(1);
//        onlyRound.setGuessResult("");
//        onlyRound.setPlayTime(LocalDateTime.parse("2022-12-15T11:00:35"));
//        rounds.add(onlyRound);
    }


    @Override
    public Round add(Round round) {
        rounds.add(round);
        return round;
    }


    @Override
      public List<Round> getAllByGameId(int id) {
        List<Round> list = rounds.stream().filter(rounds -> rounds.getGameId() == id)
                .collect(Collectors.toList());
        return list;
    }


    @Override
    public void deleteRoundById(int id) {
        if (id == onlyRound.getRoundId()) {
            rounds.remove(onlyRound);

        }
    }

        @Override
        public List<Round> getAll () {
            return rounds;
        }


}
