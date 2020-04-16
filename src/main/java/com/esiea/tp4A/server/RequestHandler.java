package com.esiea.tp4A.server;

import com.esiea.tp4A.game.PlayerOuput;
import com.esiea.tp4A.game.TheGame;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class RequestHandler {
    private GameManagement gameManagement = new GameManagement(new HashMap<String, TheGame>());
    //private HashMap<String, TheGame> games = new HashMap<>(); // Si pas de sauvegarde local, sauvegarder dans une variable le temps d'une session
    @RequestMapping("/")
    public String index() {
        return "Welcome to Mars Rover : let's start your game !\n" +
            "Commands :\n" +
            "Create Game : post > /api/session/{gameName}\n" +
            "Create Player : post > /api/session/{gameName}/player/{playerName}\n" +
            "Get Player : get > /api/session/{gameName}/player/{playerName}\n" +
            "Command Player : patch > /api/session/{gameName}/player/{playerName}/{command}";
    }

    @PostMapping("/api/session/{gameName}")
    public ResponseEntity createGame(@PathVariable String gameName) {
        try {
            TheGame theGame = gameManagement.createGame(gameName);
            return ResponseEntity.ok(theGame);
        } catch (DataAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/api/session/{gameName}/player/{playerName}")
    public ResponseEntity createPlayer(@PathVariable String gameName, @PathVariable String playerName) {
        try {
            PlayerOuput player = gameManagement.createRover(gameName, playerName);
            return ResponseEntity.ok(player);
        } catch (DataAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @RequestMapping("/api/session/{gameName}/player/{playerName}")
    public ResponseEntity getPlayer(@PathVariable String gameName, @PathVariable String playerName) {
        try {
            TheGame game = gameManagement.checkGame(gameName);
            PlayerOuput player = gameManagement.getRover(game, playerName);
            return ResponseEntity.ok(player);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/api/session/{gameName}/player/{playerName}/{command}")
    public ResponseEntity command(@PathVariable String gameName, @PathVariable String playerName, @PathVariable String command) {
        if (!command.matches("[frbls]")) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Illegal command.");
        try {
            PlayerOuput player = gameManagement.moveRover(gameName, playerName, command);
            return ResponseEntity.ok(player);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
