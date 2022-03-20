package com.controller;


import com.converter.mapper.PokemonRawMapper;
import com.converter.mapper.TrainerRowMapper;
import com.dao.Pokemon;
import com.dao.Trainer;
import com.dao.TrainersList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class TrainersController {

    private

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * The function sorts in increasing order trainers according to their level, and after that returns
     * trainrers' data
     * @return
     */
    @GetMapping("/trainers")
    public String sortTrainers() {

        List<Trainer> trainersRows = jdbcTemplate.query("SELECT * from TRAINER", new TrainerRowMapper());


        for(int i = 0; i < trainersRows.size(); i++){
            String sqlGetTrainerPokemons = "SELECT * from POKEMON WHERE TRAINER_NAME = '" + trainersRows.get(i).getName() + "'";
            List<Pokemon> trainerPokemons = jdbcTemplate.query(sqlGetTrainerPokemons, new PokemonRawMapper());
            for(Pokemon pokemon : trainerPokemons){
                trainersRows.get(i).getBag().add(pokemon.getName());
            }
        }

        Collections.sort(trainersRows, new Comparator<Trainer>()
        {
            @Override
            public int compare(Trainer t1, Trainer t2) {
                return Integer.valueOf(t1.getLevel()).compareTo(t2.getLevel());
            }
        });


        TrainersList trainerList = new TrainersList(trainersRows);

        return trainerList.toString();

    }
}


