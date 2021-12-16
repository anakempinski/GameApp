package com.dao;

import java.util.ArrayList;
import java.util.List;

public class Bag {

    private List<String> pokemonsList;

    public Bag(){
        this.pokemonsList = new ArrayList<>();
    }

    public List<String> getPokemonsList(){
        return pokemonsList;
    }

    /**
     * The function is called if trainer's bag contains 3 pokemons and he caught a new one
     * @param pokemonName
     */
    public void removeAndAdd(String pokemonName){
        pokemonsList.set(0, pokemonsList.get(1));
        pokemonsList.set(1, pokemonsList.get(2));
        pokemonsList.set(2, pokemonName);
    }

    public void add(String pokemonName){   // is called if trainers bag contains less than three pokemon names
        pokemonsList.add(pokemonName);
    }

    public int getSize(){
        return pokemonsList.size();
    }


    /**
     * The function prints the contance of trainer's bag (is used for "catch Pokemon by Trainer" request)
     * @return
     */
    public String toString(){

        StringBuilder pokemonsNames = new StringBuilder();
        pokemonsNames.append("{\"bag\":[");

        for(String name : pokemonsList){
            pokemonsNames.append("\"" + name + "\",");
        }
        pokemonsNames.replace(pokemonsNames.length()-1, pokemonsNames.length(), "]}");

        return pokemonsNames.toString();
    }


    /**
     * The function prints the constance of trainer's bag (is used for "get all trainers data sorted" request)
     * @return
     */
    public String toString2(){

        String pokemonsNames = toString();
        pokemonsNames = pokemonsNames.replace("{", "");
        pokemonsNames = pokemonsNames.replace("}", "");

        return pokemonsNames.toString();

    }


}
