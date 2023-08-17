package com.fsbtech.gamingservice.service;

import com.fsbtech.gamingservice.data.Game;
import com.fsbtech.gamingservice.data.GamingServiceDataCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class GamingService {

    @Autowired
    private GamingServiceDataCache gamingServiceDataCache;

    public List<Game> getAllGames(){
        List<Game> gameList = new ArrayList<Game>();
        for(Map.Entry<String, Game> gameEntry: gamingServiceDataCache.entrySet()){
            gameList.add(gameEntry.getValue());
        }
        return gameList;
    }

    public Game getGame(String id){
        return gamingServiceDataCache.get(id);
    }

    public Game addGame(Game game){
        game.setId(UUID.randomUUID().toString());
        game.setCreationDate(LocalDate.now());
        gamingServiceDataCache.put(game.getId() ,game);
        return game;
    }

    public Game updateGame(String id, Game game){
        Game gameFromCache = gamingServiceDataCache.get(id);
        if(gameFromCache != null) {
            if (game.getName() != null) gameFromCache.setName(game.getName());
            if (game.getCreationDate() != null) gameFromCache.setCreationDate(game.getCreationDate());
            if (game.getActive() != null) gameFromCache.setActive(game.getActive());
        }
        return gameFromCache;
    }

    public Game deleteGame(String id){
        return gamingServiceDataCache.remove(id);
    }

}
