package me.jacob.macdougall.magic;

import java.util.Random;

import me.jacob.macdougall.enemies.Enemies;
import me.jacob.macdougall.input.Keys;
import me.jacob.macdougall.player.Player;

public class Combo_Spell extends Spells {
	
	public static final int LEFT = 1, RIGHT = 2, UP = 3, DOWN = 4;
	
	Random rand = new Random();
	int comboLength = -1;
	int[] combo;
	int[] userCombo;
	int answer;
	int currentCombo = -1;
	
	
	public Combo_Spell(String name, Element element, int damage, int targets, int cost, String Condition) {
		super(name, element, damage, targets, cost, Condition);
		this.effect = Spells.Requires_Combo;
	}
	
	@Override
	public void attack(Player player, Enemies enemy) {
		if(combo == null) {
		comboLength = rand.nextInt(9) + 1;
		combo = new int[comboLength];
		userCombo = new int[comboLength];
		this.inUse = true;
		
		for(int i = 0; i < comboLength; i++) {
			combo[i] = rand.nextInt(3) + 1;
		}
		}
		
		for(int i = 0; i < userCombo.length; i++) {
			if(Keys.MoveLeft()) {
				if(userCombo[i] == 0) {
					userCombo[i] = LEFT;
					currentCombo = LEFT;
				}
			}
			
			if(Keys.MoveRight()) {
				if(userCombo[i] == 0) {
					userCombo[i] = RIGHT;
					currentCombo = RIGHT;
				}
			}
			
			if(Keys.MoveUp()) {
				if(userCombo[i] == 0) {
					userCombo[i] = UP;
					currentCombo = UP;
				}
			}
			
			if(Keys.MoveDown()) {
				if(userCombo[i] == 0) {
					userCombo[i] = DOWN;
					currentCombo = DOWN;
				}
			}
		}
		
		
		if(userCombo[comboLength - 1] != 0) {
		for(int i = 0; i < combo.length; i++) {
			if(combo[i] == userCombo[i]) {
				answer++;
			}
		}
		this.damage = damage * answer;
		enemy.setStatRelative(Enemies.HEALTH_POINTS, -(this.damage + player.getStat(Player.WISDOM)));
		this.inUse = false;
		}
	}
	@Override
	public int getCombo() {
		return (currentCombo > 0) ? currentCombo : 0;
	}
}
