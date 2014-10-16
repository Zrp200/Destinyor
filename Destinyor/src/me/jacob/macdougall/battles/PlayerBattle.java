package me.jacob.macdougall.battles;

import me.jacob.macdougall.player.Player;

public class PlayerBattle {
	
	public static void turn() {
		
		for(Player player : Player.getActualPlayers()) {
			if(player.TA >= player.TALimit) {
				player.pause();
			} else {
				player.play();
			}
		}
		
	}
	
	public static boolean canAttack(Player player) {
		if(player.TA >= player.TALimit) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
}
