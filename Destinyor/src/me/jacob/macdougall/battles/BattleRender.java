package me.jacob.macdougall.battles;

import java.awt.Color;
import java.awt.Graphics;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.magic.Combo_Spell;
import me.jacob.macdougall.magic.Spells;
import me.jacob.macdougall.npcs.Enemy;
import me.jacob.macdougall.player.Player;
import me.jacob.macdougall.world.Tile;
import graphic.engine.screen.Art;
import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;

public class BattleRender {
	
	public static boolean DrawSpells = false;
	
	private final Battles battle;
	private final Pointer point;
	
	public BattleRender(Battles battle, Pointer point) {
		this.battle = battle;
		this.point = point;
	}
	
	
	private static void BorderY(Screen screen, int w, int h){
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				screen.render(Art.getSpritesheet()[18][3], 8 * Tile.SIZE, (j + 9) * Tile.SIZE);
			}
		}
	}
	
	public void render(Player[] players, Screen screen, Enemy[] entities, BattleInput battles) {
		int count = 0;
		int X = 10;
		int Y = 256 + 16 + 10;
		// Player
		for(int p = 0; p < players.length; p++) {
			players[p].renderUI(screen, p * 64, p * 10);
		
		
		
			if(players[p].TA >= battle.limit) {
				battles.commands.get(0).render(screen);
				battles.commands.get(1).render(screen);
				//GameFont.render(, screen, 190, 305);
				//GameFont.render("Spells", screen, 190, 325);
				BorderY(screen, 2, 3);
                                GameFont.render(GameFont.pointer, screen, point.PointerX, point.PointerY);
                                if(DrawSpells && players[p].spells != null) {
                                    for(int s = 1; s < players[p].spells.size(); s++)
                                    GameFont.render(players[p].spells.get(s).name, screen, 240, 305);
                                }
			}
			//if(!Player.combatName.equals("")) {
			
			//}
		
			if(Destinyor.Debug) {
				GameFont.render(players[p].Hp + "", screen, 450, 16 + (p * 64));
			}
		}
		//battles.commands.get(2).y = 32;
//		battles.commands.get(2).render(screen);
//		//battles.commands.get(3).y = 88;
//		battles.commands.get(3).render(screen);
//		//battles.commands.get(4).y = 140;
//		battles.commands.get(4).render(screen);
//		//battles.commands.get(5).y = 192;
//		battles.commands.get(5).render(screen);
//		//battles.commands.get(6).y = 192 + 52;
//		battles.commands.get(6).render(screen);
		GameFont.render(Player.combatName + " did " + Player.temp + " to " + battle.ptarget, screen, X + 64 + 32, 256);
		
		if(battle.spell != null && battle.spell.inUse && battle.spell.effect == Spells.Requires_Combo) {
			switch(battle.spell.getCombo()) {
			case Combo_Spell.LEFT: GameFont.render("Left", screen, 256, 768 / 2); break;
			case Combo_Spell.RIGHT: GameFont.render("Right", screen, 256, 768 / 2); break;
			case Combo_Spell.UP: GameFont.render("Up", screen, 256, 768 / 2); break;
			case Combo_Spell.DOWN: GameFont.render("Down", screen, 256, 768 / 2); break;
			}
		}
		
		
		// Render Enemies
		if(entities != null)
		//for(int i = 0; i < entities.length; i++)
                for(Enemy enemy : entities)
		if(enemy != null) {
			if(!enemy.dead) {
				count++;
				enemy.render(screen);
				if(count >= 6) {
					Y = 256 + 52 - 10;
					X += 64 + 32;
					count = 0;
				}
				GameFont.render(enemy.getName(), screen, X, (count * 16) + Y);
				if(Destinyor.Debug)
					GameFont.render(String.valueOf(enemy.HP), screen, X + 108, (count * 16) + Y );
			}
			
			if(enemy.attacking) {
				GameFont.render(enemy.getName() + " Used " + "Attack: " + enemy.getName() + " did " + battle.tempEDamageHolder + " to " + players[battle.eTargets].Name, screen, X, Y);
			}
                }
                
                
	}
	
	public void renderTime(Graphics g, Player[] players) {
		g.setColor(Color.BLACK);
		for(int p = 0; p < players.length; p++) {
			players[p].renderTime(g, p);
		}
	}
	
	
}
