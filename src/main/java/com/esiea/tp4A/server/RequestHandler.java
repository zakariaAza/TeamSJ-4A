package com.esiea.tp4A.server;

import com.esiea.tp4A.game.Mars;
import com.esiea.tp4A.game.MyRover;
import com.esiea.tp4A.game.PlayerOuput;
import com.esiea.tp4A.game.TheGame;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class RequestHandler {
    // private HashMap<String, TheGame> games; // Si pas de sauvegarde local, sauvegarder dans une variable le temps d'une session
    @RequestMapping("/")
    public String index() {
        return "Welcome to Mars Rover : let's start your game !\n" +
            " ";
    }

    @PostMapping("/api/session/{gameName}")
    public ResponseEntity createGame(@PathVariable String gameName) throws IOException, ClassNotFoundException, APIAlreadyExistsException {
        //return ResponseEntity.ok(new GameManagement().createGame(gameName));
        try {
            TheGame theGame = new GameManagement().createGame(gameName);
            return ResponseEntity.ok(theGame);
        } catch (APIAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error.");
        }
    }

    @PostMapping("/api/session/{gameName}/player/{playerName}")
    public ResponseEntity createPlayer(@PathVariable String gameName, @PathVariable String playerName) {
        try {
            PlayerOuput player = new GameManagement().createRover(gameName, playerName);
            return ResponseEntity.ok(player);
        } catch (APIAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (APINotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error.");
        }
    }

    @RequestMapping("/api/session/{gameName}/player/{playerName}")
    public ResponseEntity getPlayer(@PathVariable String gameName, @PathVariable String playerName) {
        try {
            TheGame game = new GameManagement().checkGame(gameName);
            MyRover rover = game.retrieveRoverByPlayer(playerName);
            return ResponseEntity.ok(rover);
        } catch (APINotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error.");
        }
    }

    @PatchMapping("/api/session/{gameName}/player/{playerName}/{command}")
    public ResponseEntity command(@PathVariable String gameName, @PathVariable String playerName, @PathVariable String command) {
        if (!command.matches("[frbls]")) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Illegal command.");
        try {
            PlayerOuput player = new GameManagement().moveRover(gameName, playerName, command);
            return ResponseEntity.ok(player);
        } catch (APINotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error.");
        }
    }
}
