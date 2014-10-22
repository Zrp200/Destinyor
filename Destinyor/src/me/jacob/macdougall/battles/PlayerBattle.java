package me.jacob.macdougall.battles;

import input.engine.keyboard.InputHandler;
import me.jacob.macdougall.items.Equipment;
import me.jacob.macdougall.magic.Spells;
import me.jacob.macdougall.npcs.Enemy;
import me.jacob.macdougall.player.Move;
import me.jacob.macdougall.player.Player;


public class PlayerBattle {
	
	public Spells spell = null;
	public Pointer point = null;
	
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
	
	public void calculateDamage(InputHandler input, Enemy[] entities, Move move) {
		if(spell != null && spell.inUse) {
			this.spellHandler(spell.player, input, entities);
		} else {
          // bInput.Combat(Player.getActualPlayers(), entities, input);
			turn();
		}
	}
	
    public void spellHandler(Player player, InputHandler input, Enemy[] entities) {
    	boolean[] anyFocused = new boolean[entities.length + 1];
        
    	Spell: {
    		if(player.getSpells(point.spell) != null) {
    			for(Enemy enemy : entities) {
    				if(enemy.Focused && enemy.alive()) {
    					if(player.getSpells(point.spell).effect == Spells.Requires_Combo) {
    						this.spell =  player.getSpells(point.spell);
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
    
    public boolean anyFocused(boolean[] array)
    {
    for(boolean b : array) if(b) return true;
    return false;
    }
    
    public void PlayerAttack(Player player, Spells spells, Enemy[] entities) {
        Equipment[] items = new Equipment[player.equipped.size()];
        int i = 0;
        if(player.equipped != null)
        for(Equipment equipment : player.equipped.values()) {
            if(equipment.isWeapon()) {
                items[i] = equipment;
                i++;
            }
        }
		if(spells.name.contains("Attack") && player.canAttack) {
                        if(items.length > 0) {
                        	//randomDamage = randomGen.nextInt(player.Str + items[0].damage);
                        } else {
                            //randomDamage = randomGen.nextInt(player.Str);
                        }
                        
			for(Enemy enemy : entities) {
				if(enemy.Focused) {
					player.attack(enemy);
                    //ptarget = enemy.getName();
					player.TA = 0;
				}
			}
		}
    }
	
	
	
}
