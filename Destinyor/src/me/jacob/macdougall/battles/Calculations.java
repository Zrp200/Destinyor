package me.jacob.macdougall.battles;

import me.jacob.macdougall.enemies.Enemy;
import me.jacob.macdougall.items.Equipment;
import me.jacob.macdougall.player.Player;

public class Calculations {

	public static boolean canDamage(int damage) {
		return (damage > 0);
	}

	/**
	 * Attack with both weapons and armour being accounted for
	 * @param player Player, gets the weapons from the player
	 * @param enemy Enemy, gets the armour from the enemy
	 * @return damage if it is greater than 0, and returns 1 if the calculation is 0, or less than 0
	 */
	public static int attackWithBoth(Player player, Enemy enemy) {
		int damage = 0;

		for(Equipment weapon : player.returnWeapons())
			damage += weapon.damage;

		damage += armedDamage(player.getStr(), player.getSkl(), player.getSpd(), player.getWis());

		for(Equipment armour : enemy.returnArmour())
			damage -= armour.damage;

		damage -= equippedDamage(enemy.getDef(), enemy.getSkl(), enemy.getSpd(), enemy.getWis());

		if(canDamage(damage))
			return damage;
		else
			return 1;
	}

	/**
	 * Attack with both weapons and armour against the player
	 * @param enemy Enemy, gets the weapons from the enemy
	 * @param player Player, gets the armour from the player
	 * @return damage if it's greater than 0, and returtns 1 if the calculation is 0, or less than 0
	 */
	public static int attackWithBoth(Enemy enemy, Player player) {
		int damage = 0;

		for(Equipment weapon : enemy.returnWeapons())
			damage += weapon.damage;

		damage += armedDamage(enemy.getStr(), enemy.getSkl(), enemy.getSpd(), enemy.getWis());

		for(Equipment armour : player.returnArmour())
			damage -= armour.damage;

		damage -= equippedDamage(player.getDef(), player.getSkl(), player.getSpd(), player.getWis());

		if(canDamage(damage))
			return damage;
		else
			return 1;
	}

	public static int attackWithWeapons(Player player, Enemy enemy) {
		int damage = 0;

		for(Equipment weapon : player.returnWeapons())
			damage += weapon.damage;

		damage += armedDamage(player.getStr(), player.getSkl(), player.getSpd(), player.getWis());
		damage -= unequippedDamage(enemy.getDef(), enemy.getSkl(), enemy.getSpd(), enemy.getWis());

		if(canDamage(damage))
			return damage;
		else
			return 1;
	}

	public static int attackWithWeapons(Enemy enemy, Player player) {
		int damage = 0;

		for(Equipment weapon : enemy.returnWeapons())
			damage += weapon.damage;

		damage += armedDamage(enemy.getStr(), enemy.getSkl(), enemy.getSpd(), enemy.getWis());
		damage -= unequippedDamage(player.getDef(), player.getSkl(), player.getSpd(), player.getWis());

		if(canDamage(damage))
			return damage;
		else
			return 1;
	}

	public static int attackWithArmour(Player player, Enemy enemy) {
		int damage = 0;

		damage += unarmedDamage(player.getStr(), player.getSkl(), player.getSpd(), player.getWis());

		for(Equipment armour : enemy.returnArmour())
			damage -= armour.damage;

		damage -= equippedDamage(enemy.getDef(), enemy.getSkl(), enemy.getSpd(), enemy.getWis());

		if(canDamage(damage))
			return damage;
		else
			return 1;
	}

	public static int attackWithArmour(Enemy enemy, Player player) {
		int damage = 0;

		damage += unarmedDamage(enemy.getStr(), enemy.getSkl(), enemy.getSpd(), enemy.getWis());

		for(Equipment armour : player.returnArmour())
			damage -= armour.damage;

		damage -= equippedDamage(player.getDef(), player.getSkl(), player.getSpd(), player.getWis());

		if(canDamage(damage))
			return damage;
		else
			return 1;
	}

	public static int attackWithoutBoth(Player player, Enemy enemy) {
		int damage = 0;

		damage += unarmedDamage(player.getStr(), player.getSkl(), player.getSpd(), player.getWis());
		damage -= unequippedDamage(enemy.getDef(), enemy.getSkl(), enemy.getSpd(), enemy.getWis());

		if(canDamage(damage))
			return damage;
		else
			return 1;
	}

	public static int attackWithoutBoth(Enemy enemy, Player player) {
		int damage = 0;

		damage += unarmedDamage(enemy.getStr(), enemy.getSkl(), enemy.getSpd(), enemy.getWis());
		damage -= unequippedDamage(player.getDef(), player.getSkl(), player.getSpd(), player.getWis());

		if(canDamage(damage))
			return damage;
		else
			return 1;
	}

	public static int strBonus(int Str) {
		return Str;
	}

	/**
	 * The smarter you are the more you will manuvere yourself to reduce, or increase the damage
	 * @param Wis Wis of the caller, aka defender, or attacker, depending on when it's called
	 * @return Wis * 0.01 aka 1% of Wis stat
	 */
	public static float wisBonus(int Wis) {
		return (Wis * 0.01f);
	}

	public static float sklBonus(int Skl) {
		return (Skl * 0.50f);
	}

	public static int defBonus(int Def) {
		return Def;
	}

	public static float spdBonus(int Spd) {
		return (Spd * 0.25f);
	}

	public static float armedSpdBonus(int Spd) {
		return (Spd * 0.05f);
	}

	/**
	 * 
	 * @param Str
	 * @param Skl
	 * @param Spd
	 * @param Wis
	 * @return
	 */
	public static int unarmedDamage(int Str, int Skl, int Spd, int Wis) {
		float d = strBonus(Str) + sklBonus(Skl) + spdBonus(Spd);
		return Math.round(d);
	}

	/**
	 * 
	 * @param Def
	 * @param Skl
	 * @param Spd
	 * @param Wis
	 * @return
	 */
	public static int unequippedDamage(int Def, int Skl, int Spd, int Wis) {
		float d = defBonus(Def) + sklBonus(Skl) + spdBonus(Spd);
		return Math.round(d);
	}

	/**
	 * 
	 * @param Str
	 * @param Skl
	 * @param Spd
	 * @param Wis
	 * @return
	 */
	public static int armedDamage(int Str, int Skl, int Spd, int Wis) {
		float d = strBonus(Str) + sklBonus(Skl) + armedSpdBonus(Spd) + wisBonus(Wis);
		return Math.round(d);
	}

	/**
	 * 
	 * @param Def
	 * @param Skl
	 * @param Spd
	 * @param Wis
	 * @return
	 */
	public static int equippedDamage(int Def, int Skl, int Spd, int Wis) {
		float d = defBonus(Def) + sklBonus(Skl) + armedSpdBonus(Spd) + wisBonus(Wis);
		return Math.round(d);
	}

}
