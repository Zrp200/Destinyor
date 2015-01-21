package me.jacob.macdougall.battles;

import graphic.engine.screen.Screen;

import java.util.Random;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.GameVariables;
import me.jacob.macdougall.enemies.Enemies;
import me.jacob.macdougall.graphics.UI;
import me.jacob.macdougall.player.Player;

public class AIBattle {

	private static final Random random = new Random();
	public static Enemies[] enemies;
	
	private static int defendingPlayer = 0;

	public static void turn(Enemies[] enemies2) {
		for(Enemies enemy : enemies2) {
			if(enemy.canAttack()) {
				enemy.pause();
			} else {
				enemy.play();
			}
		}
	}

	public static Player getPlayer(Enemies enemy) {// throws EndBattleException {

		int[] threat;
		int t = 0;
		boolean[] pBoolean = new boolean[Player.getActualPlayers().length];
		int p = 0;
		for(Player player : Player.getActualPlayers()) {
			if(player.alive()) {
				pBoolean[p] = true;
				t++;
			}
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

		int rand;

		if(GameVariables.getDifficultly() == GameVariables.Difficultly.HARD) {
			rand = getLargestValue(threat);
		} else {
			rand = getPlayerNormal(threat);
		}
		defendingPlayer = rand;
		try {
			return Player.getActualPlayers()[rand];
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

		return null;
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
		if(rand < 0) {
			rand = random.nextInt(threat.length);
		}
		if(rand == 0) {
			rand = threat.length;
		}
		rand = random.nextInt(rand);
		for(int i = 0; i < threat.length; i++) {
			switch (i) {
				case 0:
					if(rand <= threat[i]) {
						return 0;
					}
					break;

				case 1:
					if(rand <= threat[i - 1] + threat[i] && rand > threat[i]) {
						return 1;
					}
					break;
				case 2:
					if(rand <= threat[i - 2] + threat[i - 1] + threat[i] && rand > threat[i] + threat[i - 1]) {
						return 2;
					}
					break;

				case 3:
					if(rand <= threat[i - 3] + threat[i - 2] + threat[i - 1] + threat[i] && rand > threat[i] + threat[i - 1] + threat[i - 2]) {
						return 3;
					}
					break;

				default:
					return 0;
			}
		}
		return 0;
	}

	public static int eAttack(Enemies enemy, Player player) {
		if(enemy.hasEquipment()) {
			if(player.hasEquipment()) {
				return Calculations.attackWithBoth(enemy, player);
			} else {
				return Calculations.attackWithWeapons(enemy, player);
			}
		} else {
			if(player.hasEquipment()) {
				return Calculations.attackWithArmour(enemy, player);
			} else {
				return Calculations.attackWithoutBoth(enemy, player);
			}
		}
	}

	public static void update() {
		turn(enemies);
		for(Enemies enemy : enemies) {
			if(enemy.canAttack()) {
				try {
					enemy.attack(Player.getActualPlayer(defendingPlayer), eAttack(enemy, getPlayer(enemy)));
					//Player.getActualPlayer(defendingPlayer).setStatRelative(Player.HEALTH_POINTS, -damage.nextInt(eAttack(enemy, getPlayer(enemy))));
				} catch(IndexOutOfBoundsException e) {
					enemy.attack(Player.getActualPlayer(0), eAttack(enemy, getPlayer(enemy)));
					//Player.getActualPlayer(0).setStatRelative(Player.HEALTH_POINTS, -damage.nextInt(eAttack(enemy, getPlayer(enemy))));
					
				}
				enemy.resetTurnTimer();
				break;
			}
		}
		win(enemies);
	}

	public static void win(Enemies[] enemies2) {
		boolean[] dead = new boolean[enemies2.length];

		for(int i = 0; i < enemies2.length; i++) {
			if(!enemies2[i].alive()) {
				dead[i] = true;
			}
			if(!enemies2[i].alive()) {
				if(areAllDead(dead))
					Dieing(Player.getActualPlayers(), enemies2);
			}
		}
	}

	public static boolean areAllDead(boolean[] array) {
		for(boolean b : array)
			if(!b)
				return false;
		return true;
	}

	public static void Dieing(Player[] players, Enemies[] enemies2) {
		for(Player player : players) {
			for(Enemies enemy : enemies2)
				player.setStatRelative(Player.EXPERIENCE, enemy.getStat(Enemies.EXPERIENCE));
			player.levelUp();
			player.resetTurnTimer();
			player.pause();
			player.setTarget(null);
		}
		//Destinyor.Refresh();
		UI.menu = UI.Map;
		Battles.enemiesCreated = false;
		Battles.endBattle = true;
		BattleRender.DrawSpells = false;
		Pointer.reset();
		Pointer.p = false;
		Pointer.ep = false;
	}

	public static void render(Screen screen) {

	}

}
