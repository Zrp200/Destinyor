package me.jacob.macdougall.world;

import java.util.Map;
import java.util.HashMap;

public class Staircase {
    
    private static Map<Integer, Staircase> staircases = new HashMap<>();
    private static int amount;
    
    private LevelMap GoToLevel;
    private LevelMap StairsLocation;
    
    public int x, y;
    
    public LevelMap GetLocation() {
        return StairsLocation;
    }
    
    public LevelMap SendPlayerTo() {
        return GoToLevel;
    }
    
    // First one is the stairs location, second one is the sender of the player
    public Staircase(LevelMap location, LevelMap Level, int x, int y) {
        this.StairsLocation = location;
        this.GoToLevel = Level;
        this.x = x;
        this.y = y;
        staircases.put(amount, this);
        amount++;
    }
}
