package com.controller;


import com.converter.mapper.PokemonRawMapper;
import com.converter.mapper.TrainerRowMapper;
import com.dao.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@RestController
@RequestMapping("/battle")
public class BattleController {


    private

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * The function takes from the db trainer's data and pokemons belonging to them.
     * After that the function adds the pokemons to the trainers' bags and runs the battle.
     * @param trainer1Name
     * @param trainer2Name
     * @return
     */
    @GetMapping("/{trainer1_name}/{trainer2_name}")
    public BattleResult battleBetweenTrainers( @PathVariable("trainer1_name")String trainer1Name,
                                               @PathVariable("trainer2_name")String trainer2Name){

        String sqlGetTrainer1Data = "SELECT * from TRAINER WHERE TRAINER_NAME = '" + trainer1Name + "'";
        List<Trainer> trainer1Row = jdbcTemplate.query(sqlGetTrainer1Data, new TrainerRowMapper());

        String sqlGetTrainer2Data = "SELECT * from TRAINER WHERE TRAINER_NAME = '" + trainer2Name + "'";
        List<Trainer> trainer2Row = jdbcTemplate.query(sqlGetTrainer2Data, new TrainerRowMapper());


        String sqlGetTrainer1Pokemons = "SELECT * from POKEMON WHERE TRAINER_NAME = '" + trainer1Name + "'";
        List<Pokemon> trainer1Pokemons = jdbcTemplate.query(sqlGetTrainer1Pokemons, new PokemonRawMapper());

        String sqlGetTrainer2Pokemons = "SELECT * from POKEMON WHERE TRAINER_NAME = '" + trainer2Name + "'";
        List<Pokemon> trainer2Pokemons = jdbcTemplate.query(sqlGetTrainer2Pokemons, new PokemonRawMapper());


        for(Pokemon pokemon : trainer1Pokemons){
           trainer1Row.get(0).getBag().add(pokemon.getName());
        }

        for(Pokemon pokemon : trainer2Pokemons){
            trainer2Row.get(0).getBag().add(pokemon.getName());
        }


        PokemonList allPokemons = getPokemons();
        Battle battle = new Battle(trainer1Row.get(0), trainer2Row.get(0), allPokemons);

        return battle.battle();
    }


    /**
     * The function returns list of pokemons that db contains (this list is used to create the Battle object)
     * @return
     */
    public PokemonList getPokemons() {
        List<Pokemon> rows = jdbcTemplate.query("SELECT * from POKEMON",new PokemonRawMapper());
        PokemonList pokemonList = new PokemonList();
        pokemonList.setPokemons(rows);
        return pokemonList;
    }

}
