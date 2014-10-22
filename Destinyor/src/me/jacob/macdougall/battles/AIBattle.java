package me.jacob.macdougall.battles;

import me.jacob.macdougall.npcs.Enemy;
import me.jacob.macdougall.player.Player;

public class AIBattle {
	
	public static void turn() {
		//Enemy.getEntities();
//		for(Enemy enemies : Enemy..getActualPlayers()) {
//			if(player.TA >= player.TALimit) {
//				player.pause();
//			} else {
//				player.play();
//			}
//		}
	}
	
	public static Player getPlayer(Enemy enemy) throws EndBattleException {
		//int i = 0;
		//do {
		for(Player player : Player.getActualPlayers()) {
			if(player.Hp > 0) {
				if(player.Hp - enemy.attack(player) > 0)
				return player;
			}
			//i++;
		}
		//} while(i <= Player.getActualPlayers().length);
		for(Player player : Player.getActualPlayers())
		
		if(player.Hp > 0)
			return player;
		
		throw new EndBattleException();
	}
	
}
