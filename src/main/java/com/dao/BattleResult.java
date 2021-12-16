package com.dao;


public class BattleResult {

    private String status;
    private String message;

    public BattleResult(String status, String message){
        this.status = status;
        this.message = message;
    }

    public BattleResult(){

    }

    public String getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }

    public String toString(){
        return "{status : " + status + ", message: " + message + "}";
    }
}
