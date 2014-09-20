package me.jacob.macdougall.npcs;

import java.util.Map;
import java.util.HashMap;

import me.jacob.macdougall.items.Equipment;
import me.jacob.macdougall.world.Houses;

public class ShopKeeper extends NPC {
    
    public int x,y;
    public int gold;
    public Map<Integer, Equipment> inventory = new HashMap<>();
    
    public int n = -1;
    public int dialouge = 0;
    public Houses store;
    
    public ShopKeeper() {
        super("", "",  1, 1, "Hello Welcome to my shop! END What can I get you?", false);
    }
    
    public void browsing() {
        if(this.n > 1) {
            
        }
    }
    
    public void inventory() {
        
    }
    
    public void render() {
        
    }
    
    public void update() {
        
    }
}