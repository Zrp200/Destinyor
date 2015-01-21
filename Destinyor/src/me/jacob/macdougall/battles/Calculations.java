package me.jacob.macdougall.battles;

import me.jacob.macdougall.enemies.Enemies;
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
	public static int attackWithBoth(Player player, Enemies enemy) {
		int damage = 0;

		for(Equipment weapon : player.returnWeapons())
			damage += weapon.damage;

		damage += armedDamage(player.getStat(Player.STRENGTH), player.getStat(Player.SKILL), player.getStat(Player.SPEED), player.getStat(Player.WISDOM));

		for(Equipment armour : enemy.returnArmour())
			damage -= armour.damage;

		damage -= equippedDamage(enemy.getStat(Enemies.DEFENCE), enemy.getStat(Enemies.SKILL), enemy.getStat(Enemies.SPEED), enemy.getStat(Enemies.WISDOM));

		return (canDamage(damage)) ? damage : 1;
	}

	/**
	 * Attack with both weapons and armour against the player
	 * @param enemy Enemy, gets the weapons from the enemy
	 * @param player Player, gets the armour from the player
	 * @return damage if it's greater than 0, and returtns 1 if the calculation is 0, or less than 0
	 */
	public static int attackWithBoth(Enemies enemy, Player player) {
		int damage = 0;

		for(Equipment weapon : enemy.returnWeapons())
			damage += weapon.damage;

		damage += armedDamage(enemy.getStat(Enemies.STRENGTH), enemy.getStat(Enemies.SKILL), enemy.getStat(Enemies.SPEED), enemy.getStat(Enemies.WISDOM));

		for(Equipment armour : player.returnArmour())
			damage -= armour.damage;

		damage -= equippedDamage(player.getStat(Player.DEFENCE), player.getStat(Player.SKILL), player.getStat(Player.SPEED), player.getStat(Player.WISDOM));

		return (canDamage(damage)) ? damage : 1;
	}

	public static int attackWithWeapons(Player player, Enemies enemy) {
		int damage = 0;

		for(Equipment weapon : player.returnWeapons())
			damage += weapon.damage;

		damage += armedDamage(player.getStat(Player.STRENGTH), player.getStat(Player.SKILL), player.getStat(Player.SPEED), player.getStat(Player.WISDOM));
		damage -= unequippedDamage(enemy.getStat(Enemies.DEFENCE), enemy.getStat(Enemies.SKILL), enemy.getStat(Enemies.SPEED), enemy.getStat(Enemies.WISDOM));

		return (canDamage(damage)) ? damage : 1;
	}

	public static int attackWithWeapons(Enemies enemy, Player player) {
		int damage = 0;

		for(Equipment weapon : enemy.returnWeapons())
			damage += weapon.damage;

		damage += armedDamage(enemy.getStat(Enemies.STRENGTH), enemy.getStat(Enemies.SKILL), enemy.getStat(Enemies.SPEED), enemy.getStat(Enemies.WISDOM));
		damage -= unequippedDamage(player.getStat(Player.DEFENCE), player.getStat(Player.SKILL), player.getStat(Player.SPEED), player.getStat(Player.WISDOM));

		return (canDamage(damage)) ? damage : 1;
	}

	public static int attackWithArmour(Player player, Enemies enemy) {
		int damage = 0;

		damage += unarmedDamage(player.getStat(Player.STRENGTH), player.getStat(Player.SKILL), player.getStat(Player.SPEED), player.getStat(Player.WISDOM));

		for(Equipment armour : enemy.returnArmour())
			damage -= armour.damage;

		damage -= equippedDamage(enemy.getStat(Enemies.DEFENCE), enemy.getStat(Enemies.SKILL), enemy.getStat(Enemies.SPEED), enemy.getStat(Enemies.WISDOM));

		return (canDamage(damage)) ? damage : 1;
	}

	public static int attackWithArmour(Enemies enemy, Player player) {
		int damage = 0;

		damage += unarmedDamage(enemy.getStat(Enemies.STRENGTH), enemy.getStat(Enemies.SKILL), enemy.getStat(Enemies.SPEED), enemy.getStat(Enemies.WISDOM));

		for(Equipment armour : player.returnArmour())
			damage -= armour.damage;

		damage -= equippedDamage(player.getStat(Player.DEFENCE), player.getStat(Player.SKILL), player.getStat(Player.SPEED), player.getStat(Player.WISDOM));
		return (canDamage(damage)) ? damage : 1;
	}

	public static int attackWithoutBoth(Player player, Enemies enemy) {
		int damage = 0;

		damage += unarmedDamage(player.getStat(Player.STRENGTH), player.getStat(Player.SKILL), player.getStat(Player.SPEED), player.getStat(Player.WISDOM));
		damage -= unequippedDamage(enemy.getStat(Enemies.DEFENCE), enemy.getStat(Enemies.SKILL), enemy.getStat(Enemies.SPEED), enemy.getStat(Enemies.WISDOM));
		return (canDamage(damage)) ? damage : 1;
	}

	public static int attackWithoutBoth(Enemies enemy, Player player) {
		int damage = 0;

		damage += unarmedDamage(enemy.getStat(Enemies.STRENGTH), enemy.getStat(Enemies.SKILL), enemy.getStat(Enemies.SPEED), enemy.getStat(Enemies.WISDOM));
		damage -= unequippedDamage(player.getStat(Player.DEFENCE), player.getStat(Player.SKILL), player.getStat(Player.SPEED), player.getStat(Player.WISDOM));
		
		return (canDamage(damage)) ? damage : 1;
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
