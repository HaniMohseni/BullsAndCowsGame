DROP database if exists gameDB;
create database gameDB;

use gameDB;
create table game (
gameId INT PRIMARY KEY AUTO_INCREMENT,
answer int NOT NULL,
gameFinished BOOL NOT NULL DEFAULT 0
);

create table round (
guessId INT  AUTO_INCREMENT,
gameId Int not null,
guessResult varchar(4) NOT NULL DEFAULT 0,
playTime timestamp,
guess int not null,
CONSTRAINT guessId PRIMARY KEY (guessId),
CONSTRAINT FOREIGN KEY FK_gameId (gameId)
REFERENCES game(gameId)

);

INSERT INTO game (gameId, answer, gameFinished) VALUES
 (1, 1234, true),
 (2, 4678, true),
 (3, 5124, true),
 (4, 2345, false);
 
 INSERT INTO round (guessId, gameId, guessResult, guess) VALUES
 (1, 1, 0,1234),
 (2, 2, 1, 4678),
 (3,3, 0, 9876),
 (4,4, 1,2345);
