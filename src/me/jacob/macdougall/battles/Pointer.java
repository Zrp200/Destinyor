package me.jacob.macdougall.battles;

import graphic.engine.screen.Art;
import graphic.engine.screen.Screen;
import me.jacob.macdougall.Time;
import me.jacob.macdougall.enemies.Enemies;
import me.jacob.macdougall.input.Keys;
import me.jacob.macdougall.player.Player;

public class Pointer {

	public static int spell = 1;
	// Main = 0, Spells = 1, Items = 2
	public int menu = 0;
	public int commands = 0;
	public int enemy = 0;

	public static final int commandsMax = 3;
	public static final int menuMax = 1;
	public static final int spellMax = 1; // Assing Somewhere

	public static int PointerX = 140;
	public static int PointerY = 275;

	public static int ePointerX = 20;
	public static int ePointerY = 38;

	public static boolean ep = false;
	public static boolean p = false;
	public int e = 0;

	public Pointer() {
	}

	public static void reset() {
		PointerX = 140;
		PointerY = 265;
		ePointerX = 20;
		ePointerY = 38;
	}

	public void render(Screen screen) {
		if(ep)
			screen.render(Art.getFont()[35][1], ePointerX, ePointerY);
		if(p)
			screen.render(Art.getFont()[35][1], PointerX, PointerY);
	}

	public void point(Player player, Enemies[] enemies) {
		switch (menu) {
			case 0:
				if(Time.getKeyTimer(10, false)) {
					menuZero(player, enemies);
				}
				break;

			case 1:
				if(Time.getKeyTimer(10, false)) {
					if(Keys.MoveUp()) {
						if(spell >= 1 && spell <= player.spells.size()) {
							spell--;
						} else {
							spell = 0;
						}
						Time.resetKeyTimer();
					}
					if(Keys.MoveDown()) {
						if(spell >= 1 && spell <= player.spells.size()) {
							spell++;
						} else {
							spell = 0;
						}
						Time.resetKeyTimer();
					}
					if(Keys.MoveLeft()) {
						BattleRender.DrawSpells = false;
						menu = 0;
						Time.resetKeyTimer();
					}
					if(Keys.MoveRight()) {
						Battles.spellHandler(player, enemies);
						menu = 0;
						spell = 0;
						commands = 0;
					}
				}
				break;
		}
	}
	
	

	public void commands(Player player, Enemies[] enemies) {
		switch (commands) {
			case 0:
				if(Keys.Enter() && Time.getKeyTimer(10, false)) {
					if(checkEnemy(enemies)) {
						int damage = player.attack(player.getTarget(), PlayerBattle.pAttack(player, player.getTarget()));
						PlayerBattle.setAttacking(player, player.getTarget(), damage);
						Time.resetKeyTimer();
					} else {
						pointEnemy();
					}
				}
				break;
			case 1:
				if(Keys.Enter() && Time.getKeyTimer(10, false)) {
					menu = 1;
					BattleRender.DrawSpells = true;
					Time.resetKeyTimer();
				}
				break;
		}
	}

	public void menuZero(Player player, Enemies[] enemies) {
		if(!ep && p) {
			if(Keys.MoveUp()) {
				if(commands > 0 && commands <= commandsMax) {
					commands--;
					PointerY -= 20;
				} else {
					commands = 3;
					PointerX = 140;
					PointerY = 335;
				}
				Time.resetKeyTimer();
			}
			if(Keys.MoveDown()) {
				if(commands >= 0 && commands < commandsMax) {
					commands++;
					PointerY += 20;
				} else {
					commands = 0;
					PointerX = 140;
					PointerY = 275;
				}
				Time.resetKeyTimer();
			}
			if(Keys.MoveRight()) {
				if(commands == 1) {
					BattleRender.DrawSpells = true;
					menu = 1;
					Time.resetKeyTimer();
				}
			}
			commands(player, enemies);
		}
	}

	public void pointEnemy() {
		ep = true;
		p = false;
	}

	public int getEnemy(Enemies[] enemies) {
		if(ep && !BattleInput.targetSelected) {
			if(Time.getKeyTimer(10, false)) {
				if(Keys.MoveDown()) {
					enemy--;
					Time.resetKeyTimer();
				}
				if(Keys.MoveUp())
					enemy++;
				Time.resetKeyTimer();
			}
			if(enemy > enemies.length)
				enemy = 0;
			if(enemy < 0)
				enemy = enemies.length;
			ePointerY = (52 - 8) * (enemy + 1);
			if(Keys.Enter() && Time.getKeyTimer(10, false)) {
				ep = false;
				reset();
				return enemy;

			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

	public boolean checkEnemy(Enemies[] enemies) {
		return (Player.getPlayerAttack().getTarget() != null);
	}
}
