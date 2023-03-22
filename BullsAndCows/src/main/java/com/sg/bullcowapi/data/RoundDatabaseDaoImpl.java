package com.sg.bullcowapi.data;

import com.sg.bullcowapi.models.Game;
import com.sg.bullcowapi.models.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@Repository
@Profile("database")
public class RoundDatabaseDaoImpl implements RoundDao {

    @Autowired
    JdbcTemplate jdbc;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoundDatabaseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Round add(Round round) {
        final String sql = "INSERT INTO round(gameId, guessResult, guess, playTime) VALUES(?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, (round.getGameId()));
            statement.setString(2, String.valueOf(round.getGuessResult()));
            statement.setString(3, (round.getGuess()));
            statement.setTimestamp(4, Timestamp.valueOf(round.getPlayTime()));

            return statement;

        }, keyHolder);

        round.setRoundId(keyHolder.getKey().intValue());
       System.out.println(round);
        return round;
    }



    @Override
    public List<Round> getAllByGameId(int gameId) {

        final String sql = "SELECT gameId, guessId, guessResult, guess, playTime " +
                "FROM round WHERE gameId = ?;";
        return jdbcTemplate.query(sql, new RoundDatabaseDaoImpl.RoundMapper(), gameId);

    }

    @Override
    public List<Round> getAll() {
        final String sql = "SELECT gameId, guessId, guessResult, playTime, guess FROM round;";
        return jdbcTemplate.query(sql, new RoundDatabaseDaoImpl.RoundMapper());
    }

    @Override
    @Transactional
    public void deleteRoundById(int id){
               final String DELETE_Roun = "DELETE FROM round WHERE guessId = ?";
        jdbc.update(DELETE_Roun, id);


    }


    //Mapper
    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            //guessId, guessResult, guess, playTime
            Round round = new Round();
            round.setGameId(rs.getInt("gameId"));
            round.setGuess(rs.getString("guess"));
            round.setRoundId(rs.getInt("guessId"));
            round.setGuessResult(rs.getString("guessResult"));
            round.setPlayTime( rs.getTimestamp("playTime").toLocalDateTime());
            return round;
        }
    }
}



