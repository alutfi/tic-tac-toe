package com.lutfi.tictactoe.exceptions;

public class GameNotFound extends Exception{
    private String message;
    public GameNotFound(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
