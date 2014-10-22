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
    private final BattleRender bRender;
    
	//public static boolean win;
	public static boolean attacked = false;
	public static boolean enemiesCreated = false;
	//public static boolean autobattle = false;
        
	
	private final static Random randomGen = new Random();
        private static int randomBattles = randomGen.nextInt(100);
	private int random = randomGen.nextInt(10);
	private static int creatures = 0;
	protected int eTargets = randomGen.nextInt(Player.getActualPlayers().length);
	
	public String Pdamager = "";
	public int Pdamage = 0;
	public String ptarget = "";
	public int misses = 0;
	//public int playerNum = 0;
	private int Damage;
        //private static int minDamage = randomGen.nextInt(2);
       private int randomDamage = 0;
	
	protected int tempEDamageHolder;
	
	protected int limit = 500;
	private static long eTimer = System.currentTimeMillis();
	protected Spells spell = null;
	
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
		
//		for(Player player : players) {
//			if(player.TA >= limit) {
//				player.pause();
//			} else {
//				player.play();
//			}
//		}
		
		PlayerBattle.turn();
		
		for(Enemy enemy : entities) {
			if(enemy.TA >= limit * 2) {
                            if(Time.entityTimer(20))
				enemy.pause();
			} else {
				enemy.play();
			}
		}
	}
	
	public static Enemy[] SetEnemies() {
		creatures = randomGen.nextInt(5);
		Enemy[] e = new Enemy[creatures + 1];
                
		
		//int enemy;
		int i = 0;
		int x = 32;
		int y = 24;
		//int j = 16;
                int j;
		
		//for(int i = 0; i < e.length; i++) {
            //for(Enemy enemies : Enemy.getEntities())
			//enemy = randomGen.nextInt(Enemy.getEntities().length);
			//System.out.println("Battles.SetEnemies: Enemy " + Enemy.enemies.get(FileLoader.EKeys().get(enemy)).getName() + " is attempting to be added");
                
                Enemy enemy;// = Enemy.getRandomEntity();
                boolean X;
                boolean X1;
                boolean Y;
                boolean Y1;
            while(e[creatures] == null) {
            	enemy = Enemy.getRandomEntity();
            	X = enemy.X <= Player.X;
            	X1 = enemy.X1 >= Player.X;
            	Y = enemy.Y <= Player.Y;
            	Y1 = enemy.Y1 >= Player.Y;
//            	if(enemy.name.equals(Enemy.names.get(0))) {
            	System.out.println(enemy.getName());
//            	System.out.println(X + ", " + X1 + ", " + Y + ", " + Y1);
  //          	System.out.println(creatures);
           	System.out.println(enemy.X);
           	System.out.println(enemy.X1);
         	System.out.println(enemy.Y);
            	System.out.println(enemy.Y1);
//            	Destinyor.wait(60);
            	//}
			if(X && X1 && Y && Y1) {
				
				e[i] = enemy;
				e[i].setXandY(x, y);
				i++;
				
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
			//} else {
				//i--;
			//}
		}
            }
		return e;
	}
	

	
	
	
	public static void enterCombat() {
		
            int random1 = randomGen.nextInt(100);
		
		if(Destinyor.Override) {
			Player.Attackable = false;
			random1 = randomGen.nextInt(100);
		}
		
		if(Player.Attackable) {
				if(random1 == randomBattles && Move.steps >= 10) {
					//for(Enemy enemy : Enemy.enemies.values()) {
						//if(Player.X >= enemy.X1 && Player.X <= enemy.X) {
							//if(Player.Y >= enemy.Y1 && Player.Y <= enemy.Y) {
								randomBattles = randomGen.nextInt(100);
								//random1 = randomGen.nextInt(100);
								UI.menu = UI.Fight;
//								UI.FightOn = true;
//								UI.MapOn = false;
								Destinyor.Refresh = true;
			                    Move.steps = 0;
			                    Battles.enemiesCreated = false;
							//}
						//}
					//}
				}
				
		}
		if(Move.steps > 100) {
			Move.steps = 0;
		}
		
	}
		
	
	
	public void Dieing(Player[] players, Enemy[] entities) {
		for(Player player : players) {
		for(Enemy enemy : entities)
			player.Exp += enemy.getExp();
			player.levelUp();
                        player.TA = 0;
                        player.pause();
		}
                Destinyor.Refresh = true;
                UI.menu = UI.Map;
//                        UI.FightOn = false;
//                        UI.MapOn = true;
                        enemiesCreated = false;
                        endBattle = true;
                        BattleRender.DrawSpells = false;
                        point.reset();
                        point.p = false;
	}
	
	public void calculateDamage(InputHandler input, Enemy[] entities, Move move) {
		//bInput.clicker(mouse);
		
		
		if(spell != null && spell.inUse) {
			this.spellHandler(spell.player, input, entities);
		} else {
           bInput.Combat(Player.getActualPlayers(), entities, input);
		turn(Player.getActualPlayers(), entities);
		}
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
        
        public void Attack(Player player, Enemy[] entities, InputHandler input) {
            //for(Player player : players) {
            	if(player.TA >= limit) {
                    for(Enemy enemy : entities) {
                    	if(enemy.Focused && enemy.alive() && Time.playerTimer(20) && player.TA >= 500) {
                    		PlayerAttack(player, player.getSpells(0), entities);
                    		//playerNum = p;
                    	}
                    }
                }
            //}
        }
        
        public void spellHandler(Player player, InputHandler input, Enemy[] entities) {
        	boolean[] anyFocused = new boolean[entities.length + 1];
            
        	Spell: {
            //for(Player player : players) {
            //if(input.enter.down) {
                //if(Time.getKeyTimer(6, true)) {
                        //if(player.TA >= limit) {
                            if(player.getSpells(point.spell) != null) {
                                for(Enemy enemy : entities) {
                                    if(enemy.Focused && !enemy.alive()) {
                                    	if(player.getSpells(point.spell).effect == Spells.Requires_Combo)
                                    		this.spell =  player.getSpells(point.spell);
                                        player.getSpells(point.spell).attack(player, enemy);
                                        player.TA = 0;
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
                                    		} //else {
                                    			
                                    		//}
                                    	}
                                    }
                            }
                        }
                   // }
                //}
            }
        	//return;
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
			if(!entities[i].alive()) {
                dead[i] = true;
			}
                        if(!entities[i].alive()) {
				//continue;
			//} //else {
                            if(areAllDead(dead))
                                Dieing(players, entities);
                            //else
                              //  continue;
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
			for(Enemy enemy : entities) {
                            enemy.attacking = false;
				if(enemy.TA >= limit * 2 && enemy.alive()) {
					if(System.currentTimeMillis() - eTimer >= 1000) {
                                            enemy.attacking = true;
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
                            
				for(Enemy enemy : entities) {
					if(enemy.Focused) {
//						if(misses >= 3) {
//							entities[j].HP -= player.Str;
//						}
						//if(random - entities[j].Def < 0 || random < 0 || randomDamage < 0 || randomDamage - entities[0].Def < 0) {
							player.attack(enemy);
							//entities[j].HP -= minDamage;
							//if(minDamage <= 0) {
								//misses++;
							//}
							//minDamage = randomGen.nextInt(2);
						//} else {
							//entities[j].HP -= (randomDamage - entities[0].Def);
							//Pdamage = randomDamage - entities[0].Def;
                           // Pdamager = player.Name;
                            ptarget = enemy.getName();
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
		for(Enemy enemy : entities) {
			if(spells.name.equals("Attack") && enemy.attacking) {
				Damage = randomGen.nextInt(enemy.getStr());
				try {
					Player player = AIBattle.getPlayer(enemy);
				} catch (EndBattleException e) {
					//throw new EndBattleException();
					e.EndBattle();
					return;
				}
//				switch(Destinyor.difficulty) {
//				case Destinyor.EASY: break;
//				case Destinyor.NORMAL: int[] p = new int[players.length]; for(int i = 0; i < players.length; i++) {
//					p[i] = players[i].Hp;
//				} break;
//				}
					
					if(enemy.getStr() - players[eTargets].Def < 0) {
						random = randomGen.nextInt(2);
						players[eTargets].Hp -= random;
						tempEDamageHolder = random;
						random = randomGen.nextInt(2);
						enemy.TA = 0;
					} else {
						players[eTargets].Hp -= (Damage - players[eTargets].Def);
						enemy.TA = 0;
					}	
				}
		}
			eTargets = randomGen.nextInt(Player.getActualPlayers().length);
	}
}
