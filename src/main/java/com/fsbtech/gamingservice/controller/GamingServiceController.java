package com.fsbtech.gamingservice.controller;

import com.fsbtech.gamingservice.data.Game;
import com.fsbtech.gamingservice.exception.GamingServiceNotFoundException;
import com.fsbtech.gamingservice.service.GamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GamingServiceController {

    @Autowired
    private GamingService gamingService;

    @GetMapping("/get/all")
    public List<Game> getAllGames(){

        return gamingService.getAllGames();
    }

    @GetMapping("/get/{id}")
    public Game getGame(@PathVariable("id") String id){
        Game game = gamingService.getGame(id);
        if(game != null) {
            return game;
        }
        throw new GamingServiceNotFoundException(id + " is not exist");
    }

    @PostMapping("/add")
    public Game addGame(@RequestBody Game game){
        return  gamingService.addGame(game);
    }

    @PutMapping("/update/{id}")
    public Game updateGame(@PathVariable("id") String id,@RequestBody Game game){
        Game updatedGame = gamingService.updateGame(id, game);
        if(updatedGame != null) {
            return updatedGame;
        }
        throw new GamingServiceNotFoundException(id + " is not exist");
    }

    @DeleteMapping ("/delete/{id}")
    public Game deleteGame(@PathVariable("id") String id){
        Game game = gamingService.deleteGame(id);
        if(game != null) {
            return game;
        }
        throw new GamingServiceNotFoundException(id + " is not exist");
    }
}
