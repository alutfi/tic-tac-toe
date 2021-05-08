package com.lutfi.tictactoe.exceptions;

public class InvalidGames extends Exception{
    private String message;
    public InvalidGames(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
