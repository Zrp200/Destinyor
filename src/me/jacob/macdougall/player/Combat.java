// Old Class should be deleted soon
package me.jacob.macdougall.player;

//import me.zabuzasword3.engine.magic.Spells;
//import java.util.List;
//import java.util.Random;
//
//import me.zabuzasword3.engine.Destinyor;
//import me.zabuzasword3.engine.InputHandler;
//import me.zabuzasword3.engine.entities.Entity;
//import me.zabuzasword3.engine.graphics.Art;
//import me.zabuzasword3.engine.graphics.GameFont;
//import me.zabuzasword3.engine.graphics.UI;
//import me.zabuzasword3.engine.graphics.Screen;
//import me.zabuzasword3.engine.world.Tile;

public class Combat {
	
//	public static final int limit = 500;
//	
//	public static boolean win;
//	public static boolean attacked = false;
//	public static boolean enemiesCreated = false;
//	public static boolean autobattle = false;
//	
//	private static long timer = System.currentTimeMillis();
//	private static long eTimer = System.currentTimeMillis();
//	
//	private static Random randomGen = new Random();
//	private static int random = randomGen.nextInt(10);
//	
//	private static Random randomETargets = new Random();
//	private static int eTargets = randomETargets.nextInt(2);
//	
//	private static int Damage;
//	
//	private static int tempEDamageHolder;
//	
//	public static Entity[] SetEnemies(List<String> keys, Player player, Entity... entities) {
//		
//		player.setSpells();
//		
//		Random enemies = new Random();
//		
//		int x = 32;
//		int y = 24;
//		int j = 16;
//		
//		for(int i = 0; i < entities.length; i++) {
//			int enemy = enemies.nextInt(keys.size());
//			entities[i] = Entity.newInstance(keys.get(enemy));
//			if(entities[i].Condition.equals("Boss") && player.Lvl < 20) {
//				i--;
//			} else {
//				entities[i].SetPos(x, y);
//				System.out.println(enemy);
//			
//				if(y == 16 + (16 * 4)) {
//					j = 16;
//				} else {
//					j = 32;
//				}
//				
//				if(y == 16 + (16 * 3) || y == 16 + (16 * 2)) {
//					x += 32;
//					y = j;
//				} else {
//					y += 48 + 4;
//				}
//			}
//		}
//
//		return entities;
//	}
//	
//	public static void turn(Player player, Player2 player2, Entity... entities) {
//		
//		if(player.TA >= limit) {
//			player.pause();
//		} else {
//			player.play();
//		}
//		
//		if(player2.TA >= limit) {
//			player2.pause();
//		} else {
//			player2.play();
//		}
//		
//		for(int i = 0; i < entities.length; i++) {
//			if(entities[i].TA >= limit * 2 && System.currentTimeMillis() - eTimer < 2000) {
//				entities[i].pause();
//			} else {
//				entities[i].play();
//			}
//		}
//	}
//	
//	private static void BorderY(Screen screen, int w, int h){
//		for(int i = 0; i < w; i++) {
//			for(int j = 0; j < h; j++) {
//				screen.render(Art.spritesheet[18][3], 8 * Tile.SIZE, (j + 9) * Tile.SIZE);
//			}
//		}
//	}
//	
//	public static void render(Player player, Player2 player2, Screen screen, int x, int y, Entity... entities) {
//		int count = 0;
//		int X = 10;
//		int Y = 256 + 16 + 10;
//		// Player
//		player.renderUI(screen, 0, 0);
//		player2.renderUI(screen);
//		player2.tick();
//		
//		
//		
//		if(player.TA >= limit || player2.TA >= limit) {
//		GameFont.render("Attack", screen, 190, 305);
//		GameFont.render("Spells", screen, 190, 325);
//		BorderY(screen, 2, 3);
//		}
//		
//		if(Destinyor.Debug) {
//		GameFont.render(player.Hp + "", screen, 450, 16);
//		
//		GameFont.render(player2.Hp + "", screen, 450, 80);
//		}
//        
//		// Render Enemies
//		for(int i = 0; i < entities.length; i++)
//		if(entities[i] != null) {
//			if(entities[i].HP > 0) {
//				count++;
//				entities[i].render(screen);
//				if(count >= 6) {
//					Y = 256 + 52 - 10;
//					X += 64 + 32;
//					count = 0;
//				}
//				GameFont.render(entities[i].getName(), screen, X, (count * 16) + Y );
//				if(Destinyor.Debug)
//					GameFont.render(String.valueOf(entities[i].HP), screen, X + 108, (count * 16) + Y );
//			} else {
//				if(entities[i].HP <= 0) {
//					entities[i].dead = true;
//					Dieing(player, entities[i]);
//				}
//			}
//			
//			if(entities[i].attacking) {
//				GameFont.render(entities[i].getName() + " Used " + "Attack: " + entities[i].getName() + " did " + tempEDamageHolder, screen, X, Y);
//			}
//		}
//	}
//	
//	public static void enterCombat() {
//		Random randomGen = new Random();
//		int random1 = randomGen.nextInt(10);
//		
//		if(Destinyor.Override == true) {
//			Player.Attackable = false;
//		}
//		
//		if(Player.Attackable) {
//				if(random1 == random) {
//					random = randomGen.nextInt(10);
//					UI.FightOn = true;
//					UI.MapOn = false;
//					Destinyor.Refresh = true;
//			}
//		}
//	}
//	
//	
//	public static void Dieing(Player player, Entity...entities) {
//		for(int i = 0; i < entities.length; i++)
//		if(entities[i].dead && !entities[i].appiedExp) {
//			player.Exp += entities[i].Exp;
//			player.levelUp();
//			entities[i].appiedExp = true;
//		}
//	}
//	
//	public static Spells[] getSpells(Spells... spells) {
//		return spells;
//	}
//	
//	public static void calculateDamage(Player player, Player2 player2, InputHandler input, Spells spells, Entity... entities) {
//		
//		if(entities == null) {
//			throw new RuntimeException("Entities == null");
//		}
//		
//		if(input.closesb.down && autobattle) {
//			autobattle = false;
//		}
//		
//		if(input.opensb.down && !autobattle) {
//			autobattle = true;
//		}
//		
//		turn(player, player2, entities);
//		
//		if(System.currentTimeMillis() - timer >= 500 || Destinyor.Debug) {
//			if(player.TA >= limit || Destinyor.Debug) {
//				if(input.a.down) {
//					for(int i = 0; i < entities.length; i++) {
//						if(entities[i].Focused && entities[i].HP > 0) {
//							//attack(player, player2, input, player.spells.get(player.Name + "Attack"), entities[i]);
//							timer = System.currentTimeMillis();
//						}
//					}
//				}
//				
//				if(input.s.down) {
//					// Use Spell
//				}
//				
//			}
//		}
//		
//		if(System.currentTimeMillis() - timer >= 500 || Destinyor.Debug) {
//			if(player2.TA >= limit || Destinyor.Debug) {
//				if(input.a.down) {
//					for(int i = 0; i < entities.length; i++) {
//						if(entities[i].Focused && entities[i].HP > 0) {
//							attack(player, player2, input, player2.spells.get(player2.Name + "Attack"), entities[i]);
//							timer = System.currentTimeMillis();
//						}
//					}
//				}
//				
//				if(input.s.down) {
//				// Use Spell
//				}
//			}
//		}
//		
//		// Enemies
//		EnemyAttacks(player, player2, entities);
//		
//		for(int i = 0; i < entities.length; i++) {
//			if(entities[i].HP <= 0) {
//				entities[i].dead = true;
//			}
//			if(entities[0].dead && entities[0] != null) {
//				if(entities[1].dead && entities[1] != null) {
//					if(entities[2].dead && entities[2] != null) {
//						if(entities[3].dead && entities[3] != null) {
//							if(entities[4].dead && entities[4] != null) {
//								enemiesCreated = false;
//								UI.FightOn = false;
//								UI.MapOn = true;
//								Destinyor.Refresh = true;
//							} else {
//								if(entities[4] == null) {
//									UI.FightOn = false;
//									UI.MapOn = true;
//									Destinyor.Refresh = true;
//									enemiesCreated = false;
//									
//								}
//							}
//						} else {
//							if(entities[3] == null) {
//								UI.FightOn = false;
//								UI.MapOn = true;
//								Destinyor.Refresh = true;
//								enemiesCreated = false;
//							}
//						}
//					} else {
//						if(entities[2] == null) {
//							UI.FightOn = false;
//							UI.MapOn = true;
//							Destinyor.Refresh = true;
//							enemiesCreated = false;
//						}
//					}
//				} else {
//					if(entities[1] == null) {
//						UI.FightOn = false;
//						UI.MapOn = true;
//						Destinyor.Refresh = true;
//						enemiesCreated = false;
//					}
//				}
//			} else {
//				if(entities[0] == null) {
//					UI.FightOn = false;
//					UI.MapOn = true;
//					Destinyor.Refresh = true;
//					enemiesCreated = false;
//				}
//			}
//		}
//		
//		Dieing(player, entities);
//		
//		
//	}
//	
//	public static void EnemyAttacks(Player player, Player2 player2, Entity... entities) {
//		if(System.currentTimeMillis() - eTimer >= 2000) {
//			for(int i = 0; i < entities.length; i++) {
//				entities[i].attacking = false;
//				if(entities[i].TA >= limit * 2 && entities[i].HP > 0) {
//					if(System.currentTimeMillis() - eTimer >= 1000) {
//						attack(player, player2, null, entities[i].spells.get(entities[i].getName() + "Attack"), entities[i]);
//						eTimer = System.currentTimeMillis();
//					}
//				}
//			}
//		}
//	}
//	
//	public static void attack(Player player, Player2 player2, InputHandler input, Spells spells, Entity... entities) {
//		
//		if(spells.name.contains(player.Name + "Attack")) {
//			random = randomGen.nextInt(player.Str);
//			if(random - entities[0].Def < 0) {
//				entities[0].HP -= random;
//				random = randomGen.nextInt(2);
//			} else {
//				entities[0].HP -= (random - entities[0].Def);
//			}
//			player.TA = 0;
//		}
//		
//		if(spells.name.contains(player2.Name + "Attack")) {
//			random = randomGen.nextInt(player2.Str);
//			if(random - entities[0].Def < 0) {
//				entities[0].HP -= random;
//				random = randomGen.nextInt(2);
//			} else {
//				entities[0].HP -= (random - entities[0].Def);
//			}
//			player2.TA = 0;
//		}
//		
//		
//		for(int i = 0; i < entities.length; i++) {
//			if(spells.name.contains(entities[i].getName() + "Attack")) {
//				Damage = randomGen.nextInt(entities[i].Str);
//			
//				if(eTargets == 0) {
//					if(entities[i].Str - player.Def < 0) {
//						random = randomGen.nextInt(2);
//						player.Hp -= random;
//						entities[i].attacking = true;
//						tempEDamageHolder = random;
//						random = randomGen.nextInt(2);
//						entities[i].TA = 0;
//					} else {
//						player.Hp -= (Damage - player.Def);
//						entities[i].TA = 0;
//					}	
//				}
//			
//				if(eTargets ==  1) {
//					if(Damage - player2.Def < 0) {
//						random = randomGen.nextInt(2);
//						player2.Hp -= random;
//						entities[i].attacking = true;
//						tempEDamageHolder = random;
//						random = randomGen.nextInt(2);
//						entities[i].TA = 0;
//					} else {
//						player2.Hp -= (Damage - player2.Def);
//						entities[i].TA = 0;
//					}
//				}
//				
////				if(eTargets ==  2) {
////					if(Damage - player3.Def < 0) {
////						player3.Hp -= randomD;
////						randomD = randomNoDamage.nextInt(2);
////						entities[i].TA = 0;
////					} else {
////						player3.Hp -= (Damage - player3.Def);
////						entities[i].TA = 0;
////					}
////				}
//				
////				if(eTargets ==  3) {
////					if(Damage - player4.Def < 0) {
////						player4.Hp -= randomD;
////						randomD = randomNoDamage.nextInt(2);
////						entities[i].TA = 0;
////					} else {
////						player4.Hp -= (Damage - player4.Def);
////						entities[i].TA = 0;
////					}
////				}
//				
//				eTargets = randomETargets.nextInt(2);
//			}
//		}
//		
//		
////		if(spells.name.contains("Attack")) {
////			if(spells.name.contains(player.Name)) {
////				if(entities[0].Resistance.split(",").equals(spells.type)) {
////					entities[0].HP -= spells.damage - (entities[0].Def + entities[0].Def);
////				}
////				entities[0].HP -= spells.damage - (entities[0].Def);
////			}
////			
////			if(spells.name.equals(entities[0].getName() + "Attack")) {
////				if(player.Resistance[0].equals(spells.type)) {
////					player.Hp -= spells.damage - (player.Def + player.Def);
////				}
////				player.Hp -= spells.damage - (player.Def);
////			}
////		}
//		//player.TA = 0;
//	}
//	
////	@SuppressWarnings("static-access")
////	public static void attackE1(Player player, InputHandler input, Entity entity, Spells spells) {
////		if(entity.Focused && input.a.down && entity.HP > 0 || autobattle && entity.Focused && entity.HP > 0) {
////			entity.HP = entity.HP - (player.Str / entity.Def);
////			player.Hp = player.Hp - (entity.Str / player.Def);
////			//System.out.println("e = " + entity.HP);
////			//System.out.println("p = " + player.Hp);
////			player.TA = 0;
////			attacked = true;
////		}
////		
////		if(entity.Focused && input.down.down && entity.HP > 0) {
////			System.out.println(spells.damage);
////			entity.HP = entity.HP - (spells.damage + (player.Skl / 2) * player.Lvl) / entity.Def;
////		}
////	}
////	
////	@SuppressWarnings("static-access")
////	public static void attackE2(Player player, InputHandler input, Entity entity) {
////		if(entity.Focused && input.a.down && entity.HP > 0 || autobattle && entity.Focused && entity.HP > 0) {
////			entity.HP = entity.HP - (player.Str / entity.Def);
////			player.Hp = player.Hp - (entity.Str / player.Def);
////			System.out.println("e = " + entity.HP);
////			System.out.println("p = " + player.Hp);
////			player.TA = 0;
////			attacked = true;
////		}
////	}
////	
////	@SuppressWarnings("static-access")
////	public static void attackE3(Player player, InputHandler input, Entity entity) {
////		if(entity.Focused && input.a.down && entity.HP > 0 || autobattle && entity.Focused && entity.HP > 0) {
////			entity.HP = entity.HP - (player.Str / entity.Def);
////			player.Hp = player.Hp - (entity.Str / player.Def);
////			System.out.println("e = " + entity.HP);
////			System.out.println("p = " + player.Hp);
////			player.TA = 0;
////			attacked = true;
////		}
////	}
////	
////	@SuppressWarnings("static-access")
////	public static void attackE4(Player player, InputHandler input, Entity entity) {
////		if(entity.Focused && input.a.down && entity.HP > 0 || autobattle && entity.Focused && entity.HP > 0) {
////			entity.HP = entity.HP - (player.Str / entity.Def);
////			player.Hp = player.Hp - (entity.Str / player.Def);
////			System.out.println("e = " + entity.HP);
////			System.out.println("p = " + player.Hp);
////			player.TA = 0;
////			attacked = true;
////		}
////	}
////	
////	@SuppressWarnings("static-access")
////	public static void attackE5(Player player, InputHandler input, Entity entity) {
////		if(entity.Focused && input.a.down && entity.HP > 0 || autobattle && entity.Focused && entity.HP > 0) {
////			entity.HP = entity.HP - (player.Str / entity.Def);
////			player.Hp = player.Hp - (entity.Str / player.Def);
////			System.out.println("e = " + entity.HP);
////			System.out.println("p = " + player.Hp);
////			player.TA = 0;
////			attacked = true;
////		}
////	}
////	
////	@SuppressWarnings("static-access")
////	public static void attackE6(Player player, InputHandler input, Entity entity) {
////		if(entity.Focused && input.a.down && entity.HP > 0 || autobattle && entity.Focused && entity.HP > 0) {
////			entity.HP = entity.HP - (player.Str / entity.Def);
////			player.Hp = player.Hp - (entity.Str / player.Def);
////			System.out.println("e = " + entity.HP);
////			System.out.println("p = " + player.Hp);
////			player.TA = 0;
////			attacked = true;
////		}
////	}
////	
////	@SuppressWarnings("static-access")
////	public static void attackE7(Player player, InputHandler input, Entity entity) {
////		if(entity.Focused && input.a.down && entity.HP > 0 || autobattle && entity.Focused && entity.HP > 0) {
////			entity.HP = entity.HP - (player.Str / entity.Def);
////			player.Hp = player.Hp - (entity.Str / player.Def);
////			System.out.println("e = " + entity.HP);
////			System.out.println("p = " + player.Hp);
////			player.TA = 0;
////			attacked = true;
////		}
////	}
////	
////	@SuppressWarnings("static-access")
////	public static void attackE8(Player player, InputHandler input, Entity entity) {
////		if(entity.Focused && input.a.down && entity.HP > 0 || autobattle && entity.Focused && entity.HP > 0) {
////			entity.HP = entity.HP - (player.Str / entity.Def);
////			player.Hp = player.Hp - (entity.Str / player.Def);
////			System.out.println("e = " + entity.HP);
////			System.out.println("p = " + player.Hp);
////			player.TA = 0;
////			attacked = true;
////		}
////	}
////	
////	@SuppressWarnings("static-access")
////	public static void attackE9(Player player, InputHandler input, Entity entity) {
////		if(entity.Focused && input.a.down && entity.HP > 0 || autobattle && entity.Focused && entity.HP > 0) {
////			entity.HP = entity.HP - (player.Str / entity.Def);
////			player.Hp = player.Hp - (entity.Str / player.Def);
////			System.out.println("e = " + entity.HP);
////			System.out.println("p = " + player.Hp);
////			player.TA = 0;
////			attacked = true;
////		}
////	}
//	
}
