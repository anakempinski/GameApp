package com.dao;


import java.util.List;

public class TrainersList {

    List<Trainer> trainers;

    public TrainersList(List<Trainer> trainers){
        this.trainers = trainers;
    }

    public List<Trainer> getTrainers(){
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers){
        this.trainers = trainers;
    }


    public String toString(){

        StringBuilder trainersStr = new StringBuilder();
        trainersStr.append("[");
        for(Trainer trainer : trainers){
            trainersStr.append( trainer.toString2() + ",");
        }

        trainersStr.replace(trainersStr.length()-1, trainersStr.length(), "]");
        return trainersStr.toString();
    }
}
