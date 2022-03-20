package com.controller;

import com.converter.mapper.PokemonRawMapper;
import com.converter.mapper.TrainerRowMapper;
import com.dao.Pokemon;
import com.dao.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/trainer")
public class TrainerController {

    private

    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * The function takes from the db the data of a certain trainer, creates the Trainer object,
     * and adds pokemons belonging to the trainer to his bag
     * @param trainerName
     * @return
     */
    @GetMapping("/{trainer_name}")
    public String trainerData(@PathVariable("trainer_name") String trainerName) {

        String sqlGetTrainerData = "SELECT * from TRAINER WHERE TRAINER_NAME = '" + trainerName + "'";
        List<Trainer> trainerRow = jdbcTemplate.query(sqlGetTrainerData, new TrainerRowMapper());

        String sqlGetTrainerPokemons = "SELECT * from POKEMON WHERE TRAINER_NAME = '" + trainerName + "'";
        List<Pokemon> trainerPokemons = jdbcTemplate.query(sqlGetTrainerPokemons, new PokemonRawMapper());


        Trainer trainer = new Trainer(trainerRow.get(0).getName(), trainerRow.get(0).getLevel());

        for (Pokemon pokemon : trainerPokemons) {
            trainer.getBag().add(pokemon.getName());
        }

        return trainer.toString2();

    }

}



