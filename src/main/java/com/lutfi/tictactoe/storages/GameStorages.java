package com.lutfi.tictactoe.storages;

import com.lutfi.tictactoe.models.Game;

import java.util.HashMap;
import java.util.Map;

public class GameStorages {
    private static Map<String, Game> games;
    private static GameStorages instance;
    private GameStorages(){
        games = new HashMap<>();

    }

    public static synchronized GameStorages getInstance(){
        if(instance==null){
            instance = new GameStorages();
        }
        return instance;
    }

    public Map<String, Game> getGames(){
        return games;
    }

    public void setGames(Game game){
        games.put(game.getGameId(), game);
    }
}
