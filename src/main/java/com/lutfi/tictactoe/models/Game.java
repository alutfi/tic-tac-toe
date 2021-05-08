package com.lutfi.tictactoe.models;

import lombok.Data;

@Data
public class Game {
    private String gameId;
    private Player playerOne;
    private Player playerTwo;
    private Status status;
    private int [][] board;
    private TicTacToe winner;
}
