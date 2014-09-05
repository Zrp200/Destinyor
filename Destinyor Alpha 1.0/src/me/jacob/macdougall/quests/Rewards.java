package me.jacob.macdougall.quests;

import java.util.Map;

import me.jacob.macdougall.items.Equipment;
import me.jacob.macdougall.magic.Effect;
import me.jacob.macdougall.player.Player;

public class Rewards {
    
    public static Map<String, Rewards> rewards;
    public String name;
    
    public static Map<String, Rewards> itemReward;
    public static Map<String, Rewards> effectReward;
    public static Map<String, Rewards> playerReward;
    
	public Equipment[] item;
	public Effect[] effect;
	public Player[] player;
        
        public Rewards(Equipment[] item, Effect[] effect, Player[] player) {
            this.item = item;
            this.effect = effect;
            this.player = player;
        }
        
        public Object getReward() {
            Object[] rewards = new Object[itemReward.size() + effectReward.size() + playerReward.size()];
            for(int i = 0; i < rewards.length; i++) {
            if(item != null)
                if(item[i] != null)
                    rewards[i] = item[i];
            
            if(effect != null)
                if(effect[i] != null)
                rewards[i] = effect[i];
            
            if(player != null)
                if(player[i] != null)
                    rewards[i] = player[i];
        }
            return rewards;
        }
	
}
