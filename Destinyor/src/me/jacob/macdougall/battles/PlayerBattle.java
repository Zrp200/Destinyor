package me.jacob.macdougall.battles;

import me.jacob.macdougall.enemies.Enemy;
import me.jacob.macdougall.magic.Spells;
import me.jacob.macdougall.player.Player;


public class PlayerBattle {
	
	public static Spells spell = null;
	public static Pointer point = new Pointer();
	
	public static void turn() {
		for(Player player : Player.getActualPlayers()) {
			if(player.canAttack()) {
				player.pause();
			} else {
				player.play();
			}
		}
		
	}
    
    public static int pAttack(Player player, Enemy enemy) {
		if(player.hasEquipment()) {
			if(enemy.hasEquipment()) {
				return Calculations.attackWithBoth(player, enemy); // Attack with weapons, and armour being taken into account
			} else {
				return Calculations.attackWithWeapons(player, enemy); // Attack with only weapons being taken into account
			}
		} else {
			if(enemy.hasEquipment()) {
				return Calculations.attackWithArmour(player, enemy); // Attack with only armour being taken into account
			} else {
				return Calculations.attackWithoutBoth(player, enemy);
			}
		}
	}
    
    public static void update() {
    	turn();
    	if(Player.getPlayerAttack() != null && AIBattle.enemies != null)
    	point.point(Player.getPlayerAttack(), AIBattle.enemies);
    }
	
	
	
}
