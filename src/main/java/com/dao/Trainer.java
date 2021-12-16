package com.dao;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Trainer {

    private String name;
    private int level;
    private Bag bag;

    public Trainer(String name, int level){
        this.name = name;
        this.level = level;
        this.bag = new Bag();
    }

    public String getName(){
        return name;
    }

    public int getLevel(){
        return level;
    }

    public Bag getBag(){
        return bag;
    }


    /**
     * The function is used for "get trainer data" request
     * @return
     */
    public String toString(){
        return "{\"name\":\"" + name + "\", \"level\":\"" + level + "\", " + bag.toString() + "}";
    }

    /**
     * The function is used for "get all the trainers' data" request
     * Is called inside toString)_ function of TrainersList class
     * @return
     */
    public String toString2(){
        return "{\"name\":\"" + name + "\", \"level\":\"" + level + "\", " + bag.toString2() + "}";
    }



}
