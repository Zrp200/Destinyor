package me.jacob.macdougall.magic;

import me.jacob.macdougall.enemies.Enemies;
import me.jacob.macdougall.player.Player;

public class Drain extends Spells {

	public Drain(String name, Element element, int damage, int targets, int cost, String Condition) {
		super(name, element, damage, targets, cost, Condition);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void attack(Player player, Enemies enemy2) {
		enemy2.setStatRelative(Enemies.HEALTH_POINTS, damage); // Damages the enemy by the damage done
		player.setStatRelative(Enemies.HEALTH_POINTS, -damage); // Heals the player by the damage done
	}
	
	
}
