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
public class TrainerCatchController {

    private

    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * The function takes from the db a trainer's data and list of its pokemons.
     * If the trainer has 3 pokemons (which is the maximum number): the most former pokemon
     * from the trainer's bag is set free (using removeAndAdd function from the Bag class.
     * The free pokemon is also deleted from the Pokemon table.
     * The function also updates the trainer field of the pokemon that was caught.
     * @param trainerName
     * @param pokemonName
     * @return
     */
    @GetMapping("/{trainer_name}/catch/{pokemon_name}")
    public String trainerData(@PathVariable("trainer_name")String trainerName, @PathVariable("pokemon_name")String pokemonName){

        String sqlGetTrainerData = "SELECT * from TRAINER WHERE TRAINER_NAME = '" + trainerName + "'";
        List<Trainer> trainerRow = jdbcTemplate.query(sqlGetTrainerData, new TrainerRowMapper());

        String sqlGetTrainerPokemons = "SELECT * from POKEMON WHERE TRAINER_NAME = '" + trainerName + "'";
        List<Pokemon> trainerPokemons = jdbcTemplate.query(sqlGetTrainerPokemons, new PokemonRawMapper());


        Trainer trainer = new Trainer(trainerRow.get(0).getName(), trainerRow.get(0).getLevel());
        for(Pokemon pokemon : trainerPokemons){
            trainer.getBag().add(pokemon.getName());
        }


        if(trainer.getBag().getSize() == 3){

            String query = "DELETE FROM POKEMON WHERE NAME = \'" + trainer.getBag().getPokemonsList().get(0) + "\'";
            jdbcTemplate.update(query);

            trainer.getBag().removeAndAdd(pokemonName);
        }

        else {
            trainer.getBag().add(pokemonName);
        }

        jdbcTemplate.update("UPDATE POKEMON SET TRAINER_NAME = '" + trainerName + "' WHERE NAME = '" + pokemonName + "'");


        return trainer.getBag().toString();

    }


}
