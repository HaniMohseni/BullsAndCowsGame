package com.sg.bullcowapi.data;

import com.sg.bullcowapi.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
@Profile("database")
public class GameDatabaseDaoImpl implements GameDao {

    @Autowired
    JdbcTemplate jdbc;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDatabaseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game add(Game game) {
        final String sql = "INSERT INTO game(answer, gameFinished) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, String.valueOf(game.getAnswer()));
            statement.setBoolean(2, (game.getGameFinished()));
            return statement;

        }, keyHolder);

        game.setGameId(keyHolder.getKey().intValue());

        return game;
    }



    @Override
    public List<Game> getAll() {
        final String sql = "SELECT gameId, answer, gameFinished FROM game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game findById(int id) {

        final String sql = "SELECT gameId, answer, gameFinished "
                + "FROM game WHERE gameId = ?;";

        return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
    }

    @Override
    @Transactional
    public void deleteGameById(int id){
        final String DELETE_Game = "DELETE FROM game WHERE gameId = ?";
        jdbc.update(DELETE_Game, id);

        final String DELETE_GameFromRoun = "DELETE FROM round WHERE gameId = ?";
        jdbc.update(DELETE_GameFromRoun, id);


    }


    //Mapper

    private static final class GameMapper implements RowMapper<Game> {
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameId"));
            game.setAnswer( rs.getInt("answer"));
            game.setGameFinished(rs.getBoolean("gameFinished"));
            return game;
        }
    }


}
