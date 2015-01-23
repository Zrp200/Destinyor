package me.jacob.macdougall.battles;

import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;
import me.jacob.macdougall.enemies.Enemies;
import me.jacob.macdougall.magic.Spells;
import me.jacob.macdougall.player.Player;


public class PlayerBattle {
	
	public static Spells spell = null;
	public static Pointer point = new Pointer();
	
	private static Player attackingPlayer = null;
	private static Enemies recentlyAttacked = null;
	
	private static int temp = -1;
	
	public static void setAttacking(Player player, Enemies enemy, int tempDamage) {
		attackingPlayer = player;
		recentlyAttacked = enemy;
		temp = tempDamage;
	}
	
	public static Player getAttackingPlayer() {
		return attackingPlayer;
	}
	
	public static Enemies getRecentlyAttackedEnemy() {
		return recentlyAttacked;
	}
	
	public static int getDamage() {
		return temp;
	}
	
	public static void getAttackingText(Screen screen) {
		// If getDamage() > 0 than the player hit, otherwise it was a miss
		if(getDamage() > 0) {
			GameFont.render(attackingPlayer.getName() + " did " + getDamage() + " damage", screen, 232 + 4, 336 - 64);
			GameFont.render("to " + recentlyAttacked.getName(), screen, 232 + 4, 336 - 64 + 16);
		} else {
			GameFont.render(attackingPlayer.getName() + " missed " + recentlyAttacked.getName(), screen, 232, 336 - 64);
		}
	}
	
	public static void turn() {
		for(Player player : Player.getActualPlayers()) {
			if(player.canAttack()) {
				player.pause();
			} else {
				player.play();
			}
		}
		
	}
    
    public static int pAttack(Player player, Enemies enemies) {
		if(player.hasEquipment()) {
			if(enemies.hasEquipment()) {
				return Calculations.attackWithBoth(player, enemies); // Attack with weapons, and armour being taken into account
			} else {
				return Calculations.attackWithWeapons(player, enemies); // Attack with only weapons being taken into account
			}
		} else {
			if(enemies.hasEquipment()) {
				return Calculations.attackWithArmour(player, enemies); // Attack with only armour being taken into account
			} else {
				return Calculations.attackWithoutBoth(player, enemies);
			}
		}
	}
    
    public static void update() {
    	turn();
    	if(Player.getPlayerAttack() != null && AIBattle.enemies != null)
    	point.point(Player.getPlayerAttack(), AIBattle.enemies);
    }
	
	
	
}
