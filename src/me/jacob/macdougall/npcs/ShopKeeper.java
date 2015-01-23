package me.jacob.macdougall.npcs;

import java.util.ArrayList;
import java.util.List;

import me.jacob.macdougall.items.Items;
import me.jacob.macdougall.world.Houses;

public class ShopKeeper extends NPC {
    
    public int x,y;
    public int gold;
    public List<Items> inventory = new ArrayList<>();
    
    public Houses store;
    
    public ShopKeeper() {
        super("", "",  1, 1, "Hello Welcome to my shop! END What can I get you?", false);
    }
    
    public void browsing() {
        if(this.dL > 1) {
            // Open the browsing dialouge
        }
    }
    
    public void inventory() {
        
    }
    
    public void render() {
        
    }
    
    public void update() {
        
    }
}