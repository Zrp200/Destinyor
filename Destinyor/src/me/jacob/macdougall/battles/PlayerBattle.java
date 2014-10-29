package me.jacob.macdougall.battles;

import input.engine.keyboard.InputHandler;
import me.jacob.macdougall.items.Equipment;
import me.jacob.macdougall.magic.Spells;
import me.jacob.macdougall.npcs.Enemy;
import me.jacob.macdougall.player.Move;
import me.jacob.macdougall.player.Player;


public class PlayerBattle {
	
	public static Spells spell = null;
	public static Pointer point = null;
	
	public static void turn() {
		
		for(Player player : Player.getActualPlayers()) {
			if(player.TA >= player.TALimit) {
				player.pause();
			} else {
				player.play();
			}
		}
		
	}
	
	public static boolean canAttack(Player player) {
		if(player.TA >= player.TALimit) {
			return true;
		} else {
			return false;
		}
	}
	
	public PlayerBattle() {
		//point = new Pointer();
	}
	
	public static void calculateDamage(InputHandler input, Enemy[] entities, Move move) {
		if(spell != null && spell.inUse) {
			spellHandler(spell.player, input, entities);
		} else {
          // bInput.Combat(Player.getActualPlayers(), entities, input);
			turn();
		}
	}
	
    public static void spellHandler(Player player, InputHandler input, Enemy[] entities) {
    	boolean[] anyFocused = new boolean[entities.length + 1];
        
    	Spell: {
    		if(player.getSpells(point.spell) != null) {
    			for(Enemy enemy : entities) {
    				if(enemy.Focused && enemy.alive()) {
    					if(player.getSpells(point.spell).effect == Spells.Requires_Combo) {
    						spell =  player.getSpells(point.spell);
                            player.getSpells(point.spell).attack(player, enemy);
                            player.TA = 0;
                            point.spell = 1;
                            BattleRender.DrawSpells = false;
                            break Spell;
    					}
    				} else {                                                                                                
                       	for(int e = 0; e < entities.length; e++) {
                       		if(entities[e].Focused) {
                       			anyFocused[e] = true;
                       		} else {
                       			anyFocused[e] = false;
                       		}
                       		if(anyFocused(anyFocused)) {
                       			break;
                       		}
                       	}
    				}
    			}
    		}
    	}
    }
    
    public static boolean anyFocused(boolean[] array)
    {
    for(boolean b : array) if(b) return true;
    return false;
    }
    
    public static void PlayerAttack(Player player, Spells spells, Enemy enemy) {
        player.attack(enemy);
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
		//return 1;
	}
	
	
	
}
