package com.sg.bullcowapi.models;

import java.util.Objects;

public class Game {

    private int gameId;
    private int answer;
    private boolean gameFinished;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int id) {
        this.gameId = id;
    }

    public int getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return gameId == game.gameId && answer == game.answer && gameFinished == game.gameFinished;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, answer, gameFinished);
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public boolean getGameFinished() {
        return gameFinished;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", answer=" + answer +
                ", gameFinished=" + gameFinished +
                '}';
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

}