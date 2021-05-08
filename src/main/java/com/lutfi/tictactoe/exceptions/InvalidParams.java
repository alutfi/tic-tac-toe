package com.lutfi.tictactoe.exceptions;

public class InvalidParams extends Exception{
    private String message;
    public InvalidParams(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
