package me.jacob.macdougall.buildings;

import java.util.Map;
import java.util.HashMap;

public abstract class Buildings {
	public static int amount = 0;
    public static Map<Integer, Buildings> buildings = new HashMap<>();
    
    public int x, y;
    public int width, height;
    
    public int[] doorLocationX;
    public int[] doorLocationY;
    
    public int[] windowLocationX;
    public int[] windowLocationY;
    
    public abstract void render();
    public abstract void update();
}
