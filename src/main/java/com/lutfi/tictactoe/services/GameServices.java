package com.lutfi.tictactoe.services;

import com.lutfi.tictactoe.exceptions.GameNotFound;
import com.lutfi.tictactoe.exceptions.InvalidGames;
import com.lutfi.tictactoe.exceptions.InvalidParams;
import com.lutfi.tictactoe.models.Game;
import com.lutfi.tictactoe.models.GamePlay;
import com.lutfi.tictactoe.models.Player;
import com.lutfi.tictactoe.models.TicTacToe;
import com.lutfi.tictactoe.storages.GameStorages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.lutfi.tictactoe.models.Status.*;

@Service
@AllArgsConstructor
public class GameServices {
    public Game createGame(Player player){
        Game game = new Game();
        game.setBoard(new int[5][6]);
        game.setGameId(UUID.randomUUID().toString());
        game.setPlayerOne(player);
        game.setStatus(NEW);
        GameStorages.getInstance().setGames(game);
        return game;
    }

    public Game connectGame(Player player2, String gameId) throws InvalidParams, InvalidGames {
        if(!GameStorages.getInstance().getGames().containsKey(gameId)){
            throw new InvalidParams("Game doesn't exist");
        }
        Game game = GameStorages.getInstance().getGames().get(gameId);
        if(game.getPlayerTwo() != null){
            throw new InvalidGames("Game is not valid");
        }
        game.setPlayerTwo(player2);
        game.setStatus(IN_PROGRESS);
        GameStorages.getInstance().setGames(game);
        return game;
    }

    public Game connectRandomGame(Player player2) throws GameNotFound {
        Game game =  GameStorages.getInstance().getGames().values().stream().
                filter(it->it.getStatus().equals(NEW)).
                findFirst().orElseThrow(()-> new GameNotFound("Game Not Found"));

        game.setPlayerTwo(player2);
        game.setStatus(IN_PROGRESS);
        GameStorages.getInstance().setGames(game);
        return game;
    }

    public Game gamePlay(GamePlay gamePlay) throws GameNotFound, InvalidGames {
        if(!GameStorages.getInstance().getGames().containsKey(gamePlay.getGameId())){
            throw new GameNotFound("Game Not Found");
        }
        Game game = GameStorages.getInstance().getGames().get(gamePlay.getGameId());
        if(game.getStatus().equals(FINISHED)){
            throw new InvalidGames("Game is already finished ");
        }

        int [][] board= game.getBoard();
        board[gamePlay.getX()][gamePlay.getY()] = gamePlay.getTicTacToe().getValue();

        checkWinner(game.getBoard(), TicTacToe.X);
        checkWinner(game.getBoard(), TicTacToe.O);

        return game;
    }

    private Boolean checkWinner(int[][] board, TicTacToe ticTacToe) {
        int [] boards = new int[25];
        int counter = 0;
        for(int i=0; i<board.length ; i++){
            for(int j=0 ; j< board[i].length ; j++){
                boards[counter] = board[i][j];
                counter++;
            }
        }

        int [][] winCombinations5 = {{0,1,2,3,4},{5,6,7,8,9},{10,11,12,13,14},{15,16,17,18,19},{20,21,22,23,24},
                {0,5,10,15,20},{1,6,11,16,21},{2,7,12,17,22},{3,8,13,18,23},{4,9,14,19,24},{0,6,12,18,24}};

        for(int i=0 ; i<winCombinations5.length ; i++){
            int counters = 0;
            for(int j=0 ; j<winCombinations5[i].length ; j++){
                if(boards[winCombinations5[i][j]] == ticTacToe.getValue()){
                    counters++;
                    if(counters == 5){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
