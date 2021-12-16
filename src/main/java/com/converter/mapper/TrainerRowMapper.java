package com.mapper;


import com.plainid.assignment.dao.Trainer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;


import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerRowMapper implements RowMapper<Trainer> {

    @Nullable
    @Override
    public Trainer mapRow(ResultSet resultSet, int i) throws SQLException {
        String name = resultSet.getString("TRAINER_NAME");
        int level = resultSet.getInt("LEVEL");
        return new Trainer(name, level);
    }
}

