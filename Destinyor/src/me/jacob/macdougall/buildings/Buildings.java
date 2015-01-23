package me.jacob.macdougall.buildings;

import java.util.ArrayList;
import java.util.List;
public abstract class Buildings {
	public static int amount = 0;
    public static List<Buildings> buildings = new ArrayList<>();
    
    public int x, y;
    public int width, height;
    
    public int[] doorLocationX;
    public int[] doorLocationY;
    
    public int[] windowLocationX;
    public int[] windowLocationY;
    
    public abstract void render();
    public abstract void update();
}
