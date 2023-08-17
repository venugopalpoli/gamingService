package com.fsbtech.gamingservice.data;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class GamingServiceDataCache extends HashMap<String, Game> {

    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = reentrantReadWriteLock.readLock();
    private Lock writeLock = reentrantReadWriteLock.writeLock();
    @Override
    public Game get(Object key) {
        Game game = null;
        readLock.lock();
        try {
            game = super.get(key);
        } finally {
            readLock.unlock();
        }
        return game;
    }
    @Override
    public Game put(String key, Game game) {
        Game updatedGame = null;
        writeLock.lock();
        try {
            updatedGame=super.put(key, game);
        } finally {
            writeLock.unlock();
        }
        return updatedGame;
    }

    @Override
    public Game remove(Object key) {
        Game updatedGame = null;
        writeLock.lock();
        try {
            updatedGame=super.remove(key);
        } finally {
            writeLock.unlock();
        }
        return updatedGame;
    }

}
