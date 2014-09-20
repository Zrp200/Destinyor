package me.jacob.macdougall.quests;

import java.util.HashMap;
import java.util.Map;

import me.jacob.macdougall.npcs.*;

public class Quest {
    
    public static Map<Integer, Quest> quests = new HashMap<>();
    
    public Rewards[] rewards;
    public NPC[] npcs;
    public Boss[] bosses;
    
    public boolean Completed = false;
    
    public Quest(Rewards[] rewards, NPC[] npcs, Boss[] bosses, boolean completed) {
            this.bosses = bosses;
            this.npcs = npcs;
            this.rewards = rewards;
            this.Completed = completed;
    }
    
    public Boss[] getBosses() {
        Boss[] boss;
        if(bosses == null) {
            boss = new Boss[1];
            boss[0] = Boss.noBoss;
        } else {
            boss = bosses;
        }
            return boss;
    }
    
    public boolean bossCheck() {
    	boolean bosschecker[] = new boolean[bosses.length];
    	if(bosses == null) {
    		return true;
    	}
    	for(int i = 0; i < bosses.length; i++)
    	if(bosses[i].HP <= 0 || bosses[i].Defeated) {
    		bosschecker[i] = true;
    		bosses[i].Defeated = true;
    	}
    	for(int i = 0; i < bosses.length; i++) {
    		if(!bosschecker[i]) {
    			return false;
    		} else {
    			if(i >= bosses.length) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    public Rewards[] getRewards() {
    	if(Completed)
            return rewards;
    	return null;
    }
}