package com.sg.bullcowapi.controllers;

import com.sg.bullcowapi.data.GameDao;
import com.sg.bullcowapi.data.RoundDao;
import com.sg.bullcowapi.models.Game;
import com.sg.bullcowapi.models.Round;
import com.sg.bullcowapi.service.BullAndCowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BullAndCowController {

    @Autowired
    BullAndCowService service;


    //Starts a game endpoint
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game create(@RequestBody Game game) {
        return service.addGame(game);
    }

    //Makes a guess endpoint
    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public Round create(@RequestBody Round round) {
        return service.addRound(round);
    }


    //get all game endpoint
    @GetMapping("/game")
    public List<Game> allGame() {
        return service.getAllGames();
    }

    //find a game endpoint
    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> findById(@PathVariable int gameId) {
        Game result = service.findById(gameId);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    //get all rounds of a specific game endpoint
    @GetMapping("/rounds/{gameId}")
    public List<Round> allRoundsOfGame(@PathVariable int gameId) {
        return service.getAllByGameId(gameId);
    }

    //Error and Exception handling
    public class Error {

        private LocalDateTime timestamp = LocalDateTime.now();
        private String message;

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


    @ControllerAdvice
    @RestController
    public class ToDoControllerExceptionHandler extends ResponseEntityExceptionHandler {

        private static final String CONSTRAINT_MESSAGE = "Could not save your item. "
                + "Please ensure it is valid and try again.";

        @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
        public final ResponseEntity<Error> handleSqlException(
                SQLIntegrityConstraintViolationException ex,
                WebRequest request) {

            Error err = new Error();
            err.setMessage(CONSTRAINT_MESSAGE);
            return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
