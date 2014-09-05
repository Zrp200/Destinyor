package me.jacob.macdougall.battles;

import input.engine.keyboard.InputHandler;
import input.engine.mouse.Mouse;

import java.awt.Graphics;
import java.util.Random;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.Time;
import me.jacob.macdougall.files.FileLoader;
import me.jacob.macdougall.graphics.UI;
import me.jacob.macdougall.items.*;
import me.jacob.macdougall.magic.Spells;
import me.jacob.macdougall.npcs.Enemy;
import me.jacob.macdougall.player.Move;
import me.jacob.macdougall.player.Player;
import graphic.engine.screen.Screen;

public class Battles {
    
    public static boolean endBattle = false;
    
    public BattleInput bInput;
    private Pointer point;
    private BattleRender bRender;
    
	//public static boolean win;
	public static boolean attacked = false;
	public static boolean enemiesCreated = false;
	//public static boolean autobattle = false;
        
	
	private final static Random randomGen = new Random();
        private static int randomBattles = randomGen.nextInt(100);
	private int random = randomGen.nextInt(10);
	private static int creatures = randomGen.nextInt(5);
	protected int eTargets = randomGen.nextInt(4);
	
	public String Pdamager = "";
	public int Pdamage = 0;
	public String ptarget = "";
	public int misses = 0;
	public int playerNum = 0;
	private int Damage;
        private static int minDamage = randomGen.nextInt(2);
        private int randomDamage;
	
	protected int tempEDamageHolder;
	
	protected int limit = 500;
	private static long eTimer = System.currentTimeMillis();
	
	public Battles(InputHandler input) {
		bInput = new BattleInput(input, point, this);
		point = new Pointer(bInput, this);
		bInput.point = point;
		bRender = new BattleRender(this, point);
	}
	
	public void renderTime(Player[] players, Graphics g) {
		bRender.renderTime(g, players);
	}
	
	public void render(Player[] players, Screen screen, Enemy[] entities) {
		bRender.render(players, screen, entities, bInput);
	}
	
	public void assignTarget(Mouse mouse, Enemy[] entities) {
		bInput.assingTarget(mouse, entities);
	}
	
	public void turn(Player[] players, Enemy[] entities) {
		
		for(int i = 0; i < players.length; i++) {
			if(players[i].TA >= limit) {
				players[i].pause();
			} else {
				players[i].play();
			}
		}
		
		for(int i = 0; i < entities.length; i++) {
			if(entities[i].TA >= limit * 2) {
                            if(Time.entityTimer(20))
				entities[i].pause();
			} else {
				entities[i].play();
			}
		}
	}
	
	public static Enemy[] SetEnemies() {
		Enemy[] e = new Enemy[creatures + 1];
                creatures = randomGen.nextInt(5);
		
		int enemy;
		
		int x = 32;
		int y = 24;
		int j = 16;
		
		for(int i = 0; i < e.length; i++) {
			enemy = randomGen.nextInt(FileLoader.EKeys().size());
			//System.out.println("Battles.SetEnemies: Enemy " + Enemy.enemies.get(FileLoader.EKeys().get(enemy)).getName() + " is attempting to be added");
			if(Enemy.enemies.get(FileLoader.EKeys().get(enemy)).X <= Player.X && Enemy.enemies.get(FileLoader.EKeys().get(enemy)).X1 >= Player.X 
                                && Enemy.enemies.get(FileLoader.EKeys().get(enemy)).Y <= Player.Y && Enemy.enemies.get(FileLoader.EKeys().get(enemy)).Y1 >= Player.Y) {
				e[i] = Enemy.newInstance(FileLoader.EKeys().get(enemy));
				e[i].SetPos(x, y);
			
				if(y == 16 + (16 * 4)) {
					j = 16;
				} else {
					j = 32;
				}
				
				if(y == 16 + (16 * 3) || y == 16 + (16 * 2)) {
					x += 32;
					y = j;
				} else {
					y += 48 + 4;
				}
			} else {
				i--;
			}
		}
		
		return e;
	}
	

	
	
	
	public static void enterCombat() {
            int random1 = randomGen.nextInt(100);
		
		if(Destinyor.Override) {
			Player.Attackable = false;
		}
		
		if(Player.Attackable) {
				if(random1 == randomBattles && Move.steps >= 10) {
					randomBattles = randomGen.nextInt(100);
					UI.menu = UI.Fight;
//					UI.FightOn = true;
//					UI.MapOn = false;
					Destinyor.Refresh = true;
                                        Move.steps = 0;
                                        Battles.enemiesCreated = false;
			}
				if(Move.steps > 100) {
					Move.steps = 0;
				}
		}
	}
	
	
	public void Dieing(Player[] players, Enemy[] entities) {
		for(int p = 0; p < players.length; p++) {
		for(int e = 0; e < entities.length; e++) 
			players[p].Exp += entities[e].Exp;
			players[p].levelUp();
                        players[p].TA = 0;
                        players[p].pause();
		}
                Destinyor.Refresh = true;
                UI.menu = UI.Map;
//                        UI.FightOn = false;
//                        UI.MapOn = true;
                        enemiesCreated = false;
                        endBattle = true;
                        BattleRender.DrawSpells = false;
	}
	
	public void calculateDamage(InputHandler input, Enemy[] entities, Move move) {
		//bInput.clicker(mouse);
           bInput.Combat(Player.getPlayers(), entities, input);
		turn(Player.getPlayers(), entities);
			//if(Time.playersTimer(10) || Destinyor.Debug) {
				//if(input.a.down && input.enter.down) {
				//}
//				if(input.s.down) {
//					DrawSpells = true;
//				}
			//}
		//}
		//}
        }
        
        public void Attack(Player[] players, Enemy[] entities, InputHandler input) {
            for(int p = 0; p < players.length; p++) {
            	if(players[p].TA >= limit) {
                    for(int i = 0; i < entities.length; i++) {
                    	if(entities[i].Focused && entities[i].HP > 0 && Time.playerTimer(20) && players[p].TA >= 500) {
                    		PlayerAttack(players[p], players[p].getSpells(0), entities);
                    		playerNum = p;
                    	}
                    }
                }
            }
        }
        
        public void spellHandler(Player[] players, InputHandler input, Enemy[] entities) {
        	boolean[] anyFocused = new boolean[entities.length + 1];
            
        	Spell: {
            for(int i = 0; i < players.length; i++) {
            //if(input.enter.down) {
                //if(Time.getKeyTimer(6, true)) {
                        if(players[i].TA >= limit) {
                            if(players[i].getSpells(point.spell) != null) {
                                for(int j = 0; j < entities.length; j++) {
                                    if(entities[j].Focused && (entities[j].dead || entities[j].HP < 0)) {
                                        players[i].getSpells(point.spell).attack(players[i], entities[j]);
                                        players[i].TA = 0;
                                        point.spell = 1;
                                        BattleRender.DrawSpells = false;
                                        //return;
                                        break Spell;
                                    } else {                                                                                                
                                    	for(int e = 0; e < entities.length; e++) {
                                    		if(entities[e].Focused) {
                                    		anyFocused[e] = true;
                                    		} else {
                                    			anyFocused[e] = false;
                                    		}
                                    		if(anyFocused(anyFocused)) {
                                    			break;
                                    		} else {
                                    			
                                    		}
                                    	}
                                    }
                            }
                        }
                    }
                }
            }
        	return;
        }
        
        public boolean anyFocused(boolean[] array)
        {
        for(boolean b : array) if(b) return true;
        return false;
        }
        
        public void EnemyAI(Player[] players, Enemy[] entities) {
            EnemyAttacks(players, entities);
		
                boolean[] dead = new boolean[entities.length];
                
		for(int i = 0; i < entities.length; i++) {
			if(entities[i].HP <= 0) {
				entities[i].dead = true;
                                dead[i] = true;
			}
                        if(!entities[i].dead) {
				continue;
			} else {
                            if(areAllDead(dead))
                                Dieing(players, entities);
                            else
                                continue;
                        }
		}
        }
        
        public boolean areAllDead(boolean[] array)
    {
    for(boolean b : array) if(!b) return false;
    return true;
    }
	
	public void EnemyAttacks(Player[] players, Enemy[] entities) {
		if(System.currentTimeMillis() - eTimer >= 2000) {
			for(int i = 0; i < entities.length; i++) {
                            entities[i].attacking = false;
				if(entities[i].TA >= limit * 2 && entities[i].HP > 0) {
					if(System.currentTimeMillis() - eTimer >= 1000) {
                                            entities[i].attacking = true;
					attack(players, Spells.Attack, entities);
					eTimer = System.currentTimeMillis();
					}
				}
			}
		}
	}
	
        public void PlayerAttack(Player player, Spells spells, Enemy[] entities) {
            Equipment[] items = new Equipment[player.equipped.size()];
            //System.out.println(player.Name);
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
                            	randomDamage = randomGen.nextInt(player.Str + items[0].damage);
                            } else {
                                randomDamage = randomGen.nextInt(player.Str);
                            }
                            
				for(int j = 0; j < entities.length; j++) {
					if(entities[j].Focused) {
//						if(misses >= 3) {
//							entities[j].HP -= player.Str;
//						}
						//if(random - entities[j].Def < 0 || random < 0 || randomDamage < 0 || randomDamage - entities[0].Def < 0) {
							player.attack(entities[j]);
							//entities[j].HP -= minDamage;
							//if(minDamage <= 0) {
								//misses++;
							//}
							//minDamage = randomGen.nextInt(2);
						//} else {
							//entities[j].HP -= (randomDamage - entities[0].Def);
							//Pdamage = randomDamage - entities[0].Def;
                           // Pdamager = player.Name;
                            ptarget = entities[j].getName();
						//}
                                                
                                                //for(int l = 0; l < items.length; l++) {
                                                  //  if(items[l] != null) {
                                               // if(items[l].spellEffect != null) {
                                               //     items[l].spellEffect.attack(player, entities[j]);
                                               // }
                                               // }
                                               // }
						player.TA = 0;
					}
				}
			}
        }
        
	public void attack(Player[] players, Spells spells, Enemy[] entities) {
		for(int i = 0; i < entities.length; i++) {
			if(spells.name.equals("Attack") && entities[i].attacking) {
				Damage = randomGen.nextInt(entities[i].Str);
					if(entities[i].Str - players[eTargets].Def < 0) {
						random = randomGen.nextInt(2);
						players[eTargets].Hp -= random;
						tempEDamageHolder = random;
						random = randomGen.nextInt(2);
						entities[i].TA = 0;
					} else {
						players[eTargets].Hp -= (Damage - players[eTargets].Def);
						entities[i].TA = 0;
					}	
				}
		}
			eTargets = randomGen.nextInt(4);
	}
}
