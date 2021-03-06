package me.jacob.macdougall.npcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.player.Player;
import me.jacob.macdougall.world.LevelMap;

public class RandomNPCs extends NPC {
	
	public static List<RandomNPCs> randomNpcs = new ArrayList<>();
	
	public static final String[][] names = {
        {"Bob", "Jim", "Scott", "Kevin", "Oscar", "Stanely", "Mark", "Dylan", "Ray", "Rei", "Ryan", "Sam", "Nick", "Steve", "Stanely",
            "Gary", "Neal", "Micheal", "Leo", "Triston"},
        {"Pam", "Angela", "Molly", "Amy", "Kelly", "Alessa", "Sam", "Sue", "Joyce", "Elizibeth"},
        {"Michael", "Geoff", "Jack", "Gavin", "Lewis", "Simon"}
    };
	
	private static final String[] randomDialouge = {
		"Hello p1.name, you can open the minimap with 'M' by default", "Hello p1.name, you can target enemies by hitting the '1'-'5' keys"
	};
	
	public static void RandomNpcs(int name, int gender, int x, int y, LevelMap map) {
		Random random = new Random();
		boolean made = false;
            if(map != null && map.getTileAt(x, y) != null && !map.getTileAt(x, y).isSolid() && npcs.size() < (512 * 512) / 32 && x != Player.X && y != Player.Y) {
            	for(RandomNPCs n : randomNpcs) {
            		if(n.getName().equals(names[gender][name])) {
            			made = true;
            			break;
            		}
            	}
            if(!made) {
            	
            	RandomNPCs rn = new RandomNPCs(names[gender][name], "0,12", x, y, randomDialouge[random.nextInt(randomDialouge.length)], false);
            	//rn.init(map);
            	randomNpcs.add(rn);
            	npcs.add(rn);
            }
            }
	}
	
	public RandomNPCs(String name, String frame, int x, int y, String dialouge, boolean moving) {
		super(name, frame, x, y, dialouge, moving, Destinyor.getCurrentLevel());
	}
	
	public static void changeAllLevels(LevelMap level) {
		for(RandomNPCs rn : randomNpcs) {
			rn.changeLevel(level);
		}
	}
}
