package com.dao;


import java.util.ArrayList;
import java.util.List;

public class Battle {

    private Trainer trainer1;
    private Trainer trainer2;
    PokemonList allPocemons;


    public Battle(Trainer trainer1, Trainer trainer2, PokemonList allPocemons){
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        this.allPocemons = allPocemons;
    }


    /**
     * The function checks if there is a reason to cancel a battle
     * @return
     */
    private boolean ifCancelBattle(){
        if(trainer1.getBag().getSize() < 3 || trainer2.getBag().getSize() < 3){
            return true;
        }
        return false;
    }


    /**
     * Based on the list which contains only pokemon's names, the function creates list of Pokemon objects
     * @param trainer
     * @return
     */
    public List<Pokemon> getTrainerPokemons(Trainer trainer){
        List<Pokemon> trainerPokemons = new ArrayList<>();
        List<String> bag = trainer.getBag().getPokemonsList();

        for(Pokemon pokemon : allPocemons.getPokemons()){
            if(bag.contains(pokemon.getName())){
                trainerPokemons.add(pokemon);
            }
        }

        return trainerPokemons;
    }


    /**
     * The function runs battle between two trainers, and returns the result according to number of pointers
     * that each trainer received
     * @return
     */
    public BattleResult battle(){
        if(ifCancelBattle()) {
            return new BattleResult("Error", "canceled");
        }

        List<Pokemon> trainer1Pokemons = getTrainerPokemons(trainer1);
        List<Pokemon> trainer2Pokemons = getTrainerPokemons(trainer2);


        int trainer1Points = 0;
        int trainer2Points = 0;

        int ind = 0;
        while (ind < 3) {
            Pokemon pokemon1 = trainer1Pokemons.get(ind);
            Pokemon pokemon2 = trainer2Pokemons.get(ind);


            if(pokemon1.getType() == pokemon2.getType()){
                trainer1Points += 1;
                trainer2Points += 1;
            }

            else if((pokemon1.getType() == PokemonType.Water && pokemon2.getType() == PokemonType.Fire) ||
                    (pokemon1.getType() == PokemonType.Fire && pokemon2.getType() == PokemonType.Grass) ||
                    (pokemon1.getType() == PokemonType.Grass && pokemon2.getType() == PokemonType.Water))
            {
                trainer1Points += 2;
            }

            else {
                trainer2Points += 2;
            }

            ind++;
        }


        if(trainer1Points == trainer2Points){
            return new BattleResult("Draw", "draw");
        }

        else if(trainer1Points > trainer2Points){
            return new BattleResult("Success", trainer1.getName() + " wins");
        }

        else{
            return new BattleResult("Success", trainer2.getName() + " wins");
        }

    }




}
