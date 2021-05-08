package com.lutfi.tictactoe.controllers;

import com.lutfi.tictactoe.controllers.dto.Connect;
import com.lutfi.tictactoe.exceptions.GameNotFound;
import com.lutfi.tictactoe.exceptions.InvalidGames;
import com.lutfi.tictactoe.exceptions.InvalidParams;
import com.lutfi.tictactoe.models.Game;
import com.lutfi.tictactoe.models.GamePlay;
import com.lutfi.tictactoe.models.Player;
import com.lutfi.tictactoe.services.GameServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {
    private final GameServices gameServices;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody Player player){

        return ResponseEntity.ok(gameServices.createGame(player)) ;
    }

    @PostMapping("/connect")
    public ResponseEntity<Game> connect(@RequestBody Connect req) throws InvalidParams, InvalidGames {
        return ResponseEntity.ok(gameServices.connectGame(req.getPlayer(), req.getGameId()));
    }

    @PostMapping("/connect/random")
    public ResponseEntity<Game> connectRandom(@RequestBody Player player) throws InvalidParams, InvalidGames, GameNotFound {
        return ResponseEntity.ok(gameServices.connectRandomGame(player));
    }

    @PostMapping("/gameplay")
    public ResponseEntity<Game> play(@RequestBody GamePlay gamePlay) throws InvalidParams, InvalidGames, GameNotFound {
        Game game = gameServices.gamePlay(gamePlay);
        simpMessagingTemplate.convertAndSend("/topic/game-progress"+game.getGameId(),game);
        return ResponseEntity.ok(game);
    }
}
