DROP database if exists gameTestDB;
create database gameTestDB;

use gameTestDB;
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
CONSTRAINT guessId PRIMARY KEY (guessId),
CONSTRAINT FOREIGN KEY FK_gameId (gameId)
REFERENCES game(gameId),
guess int not null
);