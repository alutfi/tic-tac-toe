package com.lutfi.tictactoe.models;

import lombok.Data;

@Data
public class GamePlay {
    private TicTacToe ticTacToe;
    private Integer x;
    private Integer y;
    private String gameId;
}
