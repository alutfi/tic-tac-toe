package com.lutfi.tictactoe.controllers.dto;

import com.lutfi.tictactoe.models.Player;
import lombok.Data;

@Data
public class Connect {
    private Player player;
    private String gameId;
}
