package me.jacob.macdougall.battles;

import java.util.Random;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.npcs.Enemy;
import me.jacob.macdougall.player.Player;

public class AIBattle {
	
	private static Random random = new Random();
	
	public static void turn(Enemy[] enemies) {
		//Enemy.getEntities();
//		for(Enemy enemies : Enemy..getActualPlayers()) {
//			if(player.TA >= player.TALimit) {
//				player.pause();
//			} else {
//				player.play();
//			}
//		}
		for(Enemy enemy : enemies) {
			if(enemy.TA >= enemy.limit) {
				enemy.pause();
			} else {
				enemy.play();
			}
		}
	}
	
	public static Player getPlayer(Enemy enemy) throws EndBattleException {
		//int i = 0;
		//do {
		int[] threat;// = new int[Player.getActualPlayers().length];
		int t = 0;
		boolean[] pBoolean = new boolean[Player.getActualPlayers().length];
		int p = 0;
		for(Player player : Player.getActualPlayers()) {
			if(player.alive()) {
				//threat[t] = player.getThreat();
				pBoolean[p] = true;
				t++;
				
				//if(player.Hp - enemy.attack(player) > 0)
				//return player;
			}
			//i++;
		}
		threat = new int[t];
		t = 0;
		p = 0;
		for(Player player : Player.getActualPlayers()) {
			if(pBoolean[p]) {
				threat[t] = player.getThreat();
				t++;
			}
			p++;
		}
		
		int rand = 0;
		if(Destinyor.difficulty <= Destinyor.NORMAL) {
			rand = getPlayerNormal(threat);
		} else if(Destinyor.difficulty == Destinyor.HARD) {
			rand = getLargestValue(threat);
		}
		//} while(i <= Player.getActualPlayers().length);
		//for(Player player : Player.getActualPlayers())
		
		//if(player.Hp > 0)
			//return player;
		try {
			return Player.getActualPlayers()[rand];
		} catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		throw new EndBattleException();
	}
	
	public static int getLargestValue(int[] values) {
		int largestInt = 0;
		
		for(int i = 0; i < values.length; i++) {
			if(values[i] >= largestInt) {
				largestInt = values[i];
			}
		}
		
		
		return largestInt;
	}
	
	public static int getPlayerNormal(int[] threat) {
		int rand = 0;
		for(int i = 0; i < threat.length; i++) {
			rand += threat[i];
		}
		if(rand < 0 ) {
			rand = random.nextInt(threat.length);
		}
		if(rand == 0) {
			rand = threat.length;
		}
		rand = random.nextInt(rand);
		for(int i = 0; i < threat.length; i++) {
			switch(i) {
				case 0: if(rand <= threat[i]) {
					return 0;
				}
				break;
				
				case 1: if(rand <= threat[i-1] + threat[i] && rand > threat[i]) {
					return 1;
				}
				break;
				case 2: if(rand <= threat[i-2] + threat[i-1] + threat[i] && rand > threat[i] + threat[i-1]) {
					return 2;
				}
				break;
				
				case 3: if(rand <= threat[i-3] + threat[i-2] + threat[i-1] + threat[i] && rand > threat[i] + threat[i-1] + threat[i-2]) {
					return 3;
				}
				break;
				
				default: return 0;
			}
		}
		return 0;
	}
	
	public static int eAttack(Enemy enemy, Player player) {
		if(enemy.hasEquipment()) {
			if(player.hasEquipment()) {
				return Calculations.attackWithBoth(enemy, player);
			} else {
				return Calculations.attackWithWeapons(enemy, player);
			}
		} else {
			if(player.hasEquipment()) {
				Calculations.attackWithArmour(enemy, player);
			} else {
				Calculations.attackWithoutBoth(enemy, player);
			}
		}
		return 0;
	}
	
//	if(enemy.hasEquipment()) {
//		return Calculations.attackWithBoth(player, enemy); // Attack with weapons, and armour being taken into account
//	} else {
//		return Calculations.attackWithWeapons(player, enemy); // Attack with only weapons being taken into account
//	}
//} else {
//	if(enemy.hasEquipment()) {
//		return Calculations.attackWithArmour(player, enemy); // Attack with only armour being taken into account
//	} else {
//		return Calculations.attackWithoutBoth(player, enemy);
//	}
	
}
