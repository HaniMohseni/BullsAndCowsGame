package com.sg.bullcowapi.models;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class Round {
    int roundId;
    int gameId;
    String guessResult;
    String guess;
    LocalDateTime playTime;



    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setRoundId(int guessId) {
        this.roundId = guessId;
    }

    public int getRoundId() {

        return roundId;
    }


    public String getGuessResult() {
        return guessResult;
    }

    public void setGuessResult(String guessResult) {
        this.guessResult = guessResult;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }




    public LocalDateTime getPlayTime() {
        return playTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return roundId == round.roundId && gameId == round.gameId && Objects.equals(guessResult, round.guessResult) && Objects.equals(guess, round.guess) && Objects.equals(playTime, round.playTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundId, gameId, guessResult, guess, playTime);
    }

    @Override
    public String toString() {
        return "Round{" +
                "roundId=" + roundId +
                ", gameId=" + gameId +
                ", guessResult='" + guessResult + '\'' +
                ", guess=" + guess +
                ", playTime=" + playTime +
                '}';
    }

    public void setPlayTime(LocalDateTime playTime) {
        this.playTime = playTime;
    }
}


