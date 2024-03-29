package com.mapper;

import com.dao.Pokemon;
import com.dao.PokemonType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PokemonRawMapper implements RowMapper<Pokemon> {
    @Override
    public Pokemon mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(rs.getInt("ID"));
        pokemon.setName(rs.getString("NAME"));
        pokemon.setType(Enum.valueOf(PokemonType.class,rs.getString("TYPE")));

        return pokemon;

    }
}
