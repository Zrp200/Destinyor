package me.jacob.macdougall.player;

import input.engine.keyboard.InputHandler;
import input.engine.keyboard.Key;
import me.jacob.macdougall.*;
import me.jacob.macdougall.battles.Battles;
import me.jacob.macdougall.graphics.UI;
import me.jacob.macdougall.npcs.NPC;
import me.jacob.macdougall.world.*;
import graphic.engine.screen.Bitmap;

public class Move {

	// Handles Input *yes I know the name is a lie it was originally suppose to be only movement*
	
	//InputHandler input;
	LevelMap map;
	Destinyor game;
	
	public static int steps = 0;
	public static int location = 0;
        
	public static final int DIR_UP = 0, DIR_DOWN = 1, DIR_LEFT = 2, DIR_RIGHT = 3;
	public static int dir = DIR_DOWN;
	
	public int x = Player.X * Tile.SIZE;

	public int y = Player.Y * Tile.SIZE;
	
	public Bitmap[][] frames = Player.frames;
	
	public static int frame = 0;
	
	public boolean canMove = true;
	public static boolean collision = false;
	public boolean run = true;
	public boolean hasMoved = false;
	
	public Move(Destinyor game, LevelMap lvlmap) {
		this.game = game;
		//input = new InputHandler(game);
		map = lvlmap;
	}
	
	public static int GetXDir() {
		if(dir == DIR_LEFT) {
			return -1;
		}
		if(dir == DIR_RIGHT) {
			return -1;
		}
		return 0;
	}
	
	public static int GetYDir() {
		if(dir == DIR_UP) {
			return -1;
		}
		if(dir == DIR_DOWN) {
			return 1;
		}
		return 0;
	}
	
	public void Movement() {
            
            if(Battles.endBattle)  {
                    Battles.endBattle = false;
                }
            
		if(UI.menu == UI.Map || UI.menu == UI.Minimap) {
                    //if(!UI.InventoryOn && !UI.EquipmentOn) {
			tick();
                   //}
		}
                
                if(UI.menu != UI.Fight) {
                    if(Keys.Inventory() && Time.inventoryTimer(10)) {
                        if(UI.menu != UI.Inventory) {
                        	UI.menu = UI.Inventory;
//                            UI.InventoryOn = true;
//                            UI.MapOn = false;
                            Destinyor.Refresh = true;
                        } else {
                        	UI.menu = UI.Map;
//                            UI.InventoryOn = false;
//                            UI.MapOn = true;
                            Destinyor.Refresh = true;
                        }
                }
                    if(Keys.Equipment()) {
                    	if(Time.inventoryTimer(10)) {
                    		if(UI.menu != UI.Equipment) {
                    			UI.menu = UI.Equipment;
                    			Destinyor.Refresh = true;
                    		} else {
                    			UI.menu = UI.Map;
                    			Destinyor.Refresh = true;
                    		}
                    	}
                    }
                }
                if(UI.menu == UI.Equipment || UI.menu == UI.Inventory || (UI.menu >= UI.Player1 && UI.menu <= UI.Player4)) {
                	if(Keys.MoveRight() && Time.getKeyTimer(10, true)) {
                		if(UI.menu >= UI.Player1 && UI.menu <= UI.Player4) {
                		UI.menu++;;
                		}
                		if(UI.menu > UI.Player4) {
                			UI.menu = UI.Player1;
                		}
                		if(UI.menu <= UI.Player1) {
                			UI.menu = UI.Player1;
                		}
                		Time.resetKeyTimer();
                	}
                	if(Keys.MoveLeft() && Time.getKeyTimer(10, true)) {
                		if(UI.menu >= UI.Player1 && UI.menu <= UI.Player4) {
                    		UI.menu--;
                    		}
                		if(UI.menu < UI.Player1) {
                    			UI.menu = UI.Player4;
                    		}
                    		Time.resetKeyTimer();
                	}
                	System.out.println("Menu: " + UI.menu);
                }
                
                game.ChangeScreenToUI();
        	game.ChangeScreenToMap();
                
		if(UI.menu == 0 || UI.menu == 2)
		minimap();
	}
	
	public void minimap() {
		if(Keys.Minimap()) {
			if(Time.mapTimer(10)) {
				if(UI.menu != UI.Minimap) {
					UI.menu = UI.Minimap;
					//UI.MinimapOn = true;
				} else {
					UI.menu = UI.Map;
					Destinyor.Refresh = true;
					//UI.MinimapOn = false;
				}
			}
		}
	}
        
    public void tick() {
    	if(Time.playerTimer(10)) {
		if(Keys.MoveUp()) {
			if(!canMove) return;
			dir = DIR_UP;
			move(DIR_UP);
		} else if(Keys.MoveDown()) {
			if(!canMove) return;
			dir = DIR_DOWN;
			move(DIR_DOWN);
		} else if(Keys.MoveLeft()) {
			if(!canMove) return;
			dir = DIR_LEFT;
			move(DIR_LEFT);
		} else if(Keys.MoveRight()) {
			if(!canMove) return;
			dir = DIR_RIGHT;
			move(DIR_RIGHT);
		} else if(frame != 0) { // Checks the frame to reset it. Resets the frame if it is an odd number, eg 1,3,5, and only if their is no key down
			float fPause = (frame / 2);
			int iPause = (frame / 2);
			if(fPause == iPause) {
				frame = frame + 1;
			}
		}
		
		
    	}
		
			//if(Time.frameTimer(90))
                            //if(frame >= Player.frames[dir].length)
				//frame = 0;
	}
	
	public void move(int dir) {
		if(!canMove) return;
		
		if(!Time.moveTimer(20)) return;
		
		if(dir == DIR_LEFT) {
			if(canMove(dir)) {
				MoveLeft();
			}
		}
		
		if(dir == DIR_RIGHT) {
			if(canMove(dir)) {
				MoveRight();
			}
		}
		
		if(dir == DIR_UP) {
			if(canMove(dir)) {
				MoveUp();
			}
		}
		
		if(dir == DIR_DOWN) {
			if(canMove(dir)) {
				MoveDown();
			}
		}
	}
	
	public int invertDir(int dir) {
		if(dir == DIR_UP) return DIR_DOWN;
		if(dir == DIR_DOWN) return DIR_UP;
		if(dir == DIR_LEFT) return DIR_RIGHT;
		if(dir == DIR_RIGHT) return DIR_LEFT;
		return 0;
	}
	
	private boolean canMove(int dir) {
		
		for(NPC n : NPC.npcs.values()) {
			if(dir == DIR_LEFT)
				if(n.collision(Player.X - 1, Player.Y))
					return false;
			if(dir == DIR_RIGHT)
				if(n.collision(Player.X + 1, Player.Y))
					return false;
			if(dir == DIR_UP)
				if(n.collision(Player.X, Player.Y - 1))
					return false;
			if(dir == DIR_DOWN)
				if(n.collision(Player.X, Player.Y + 1))
					return false;
		}
		if(UI.menu == UI.Map || UI.menu == UI.Minimap) {
			if(dir == DIR_LEFT) {
				Tile t = map.getTileAt(Player.X - 1, Player.Y);
				if(t != null && !t.isSolid()) return true;
				else return false;
			}
			if(dir == DIR_RIGHT) {
				Tile t = map.getTileAt(Player.X + 1, Player.Y);
				if(t != null && !t.isSolid()) return true;
				else return false;
			}
			if(dir == DIR_UP) {
				Tile t = map.getTileAt(Player.X, Player.Y - 1);
				if(t != null && !t.isSolid()) return true;
				else return false;
			}
			if(dir == DIR_DOWN) {
				Tile t = map.getTileAt(Player.X, Player.Y + 1);
				if(t != null && !t.isSolid()) return true;
				else return false;
			}
		} 
		return false;
	}
	
	public void MoveLeft(){
		steps += 1;
		Camera.cX -= 1 * 32;
		Player.X -= 1;
		frame += 1;
		Battles.enterCombat();
		Destinyor.Refresh = true;
	}
	
	public void MoveRight(){
		steps += 1;
		Camera.cX += 1 * Tile.SIZE;
		Player.X += 1;
		frame += 1;
		Battles.enterCombat();
		Destinyor.Refresh = true;
	}
	
	public void MoveUp(){
		steps += 1;
		Camera.cY -= 1 * 32;
		Player.Y -= 1;
		frame += 1;
		Battles.enterCombat();
		Destinyor.Refresh = true;
	}
	
	public void MoveDown(){
		steps += 1;
		Camera.cY += 1 * 32;
		Player.Y += 1;
		frame += 1;
		Battles.enterCombat();
		Destinyor.Refresh = true;
	}
	
}
