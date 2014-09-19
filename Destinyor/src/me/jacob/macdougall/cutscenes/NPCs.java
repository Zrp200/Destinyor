package me.jacob.macdougall.cutscenes;

import me.jacob.macdougall.DebugWriter;
import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.npcs.NPC;

import java.util.Map;
import java.util.HashMap;

public class NPCs extends NPC {
    // Temporay cloning class used for cutscenes, then gets deleted
    public static Map<String, NPCs> cNpcs = new HashMap<>();
    
    
    public int[][] direction;
    public int[] dir;
    public boolean Moving = false;
    public String absoluteName;
    public String trueName;
    
    public int id;
    
    
    public NPCs(String name, String frame, int x, int y, String Dialouge, int id) {
        super(name + "c", frame, x, y, Dialouge, false);
        absoluteName = name + "c";
        DebugWriter.println("Adding NPC: " + absoluteName);
        //System.out.println(absoluteName);
        trueName = name.replace("" + id, "");
        //System.out.println(trueName);
        this.cutscene = true;
        cNpcs.put(name, this);
        this.id = id;
        init();
    }
    
    public void init() {
    	for(int i = 0; i < dialouge.length; i++) {
        	dialouge[i] = dialouge[i].replaceAll(absoluteName, trueName);
        	//dialouge[i].replaceAll(absoluteName, trueName);
        	//System.out.println(dialouge[i].replaceAll(absoluteName, trueName));
        }
    }
    
    public void stopDrawing() {
    	String temp = this.name.replace(id + "c", "");
    	NPC.npcs.get(temp).render = false;
    }
    
    
    
	
    
    public void Speaking(int i) {
//		if(System.currentTimeMillis() >= t1 + 100) {
//			t1 = System.currentTimeMillis();
//			if(input.enter.down) {
    	this.setDialougeWithCharacter();
				Destinyor.Refresh = true;
//				this.n++;
				this.speaking = true;
				this.dialougeLocation = i;
                                this.cutscene = true;
//				if(dialougeLocation == dialouge.length) {
//					this.dialougeLocation = 0;
//					this.speaking = false;
//				}
			}
		//}
	//}
    
    public void delete() {
        NPCs.npcs.remove(this.name);
        NPCs.cNpcs.remove(this.name);
        String temp = this.name.replace(id + "c", "");
        NPC.npcs.get(temp).render = true;;
    }
    
    	public void CTick() {
    	if(System.currentTimeMillis() - t > 400) { 
            frame++;
            t = System.currentTimeMillis();
        }
        if(frame >= frames.length) frame = 0;
    	}
//    	
//    	
//		time++;
//		if(time % (ran + 60) == 0) {
//			ran = random.nextInt(300);
//			int XorY = random.nextInt(2);
//			
//			if(X >= XHolder + 2 && !collision(Player.X - 1, Player.Y)) {
//				if(CanMove(X - 1, Y))
//				X += -1; canMove = false; 
//			} else if(X <= XHolder - 2 && !collision(Player.X + 1, Player.Y)) {
//				if(CanMove(X + 1, Y))
//				X += 1; canMove = false;
//			}
//			
//			if(Y >= YHolder + 2 && !collision(Player.X, Player.Y + 1)) {
//				if(CanMove(X, Y + 1))
//				Y += -1; canMove = false; 
//			} else if(Y <= YHolder - 2 && !collision(Player.X, Player.Y - 1)) {
//				if(CanMove(X, Y - 1))
//				Y += 1; canMove = false;
//			}
//			
//			int move = random.nextInt(3) - 1;
//			
//			if(XorY == 0) { 
//				if(X < XHolder + 2 && X > XHolder - 2 && canMove && !collision(Player.X - move, Player.Y)) {
//					if(CanMove(X + move, Y)) {
//						X += move;
//					}
//				}
//			}
//			if(XorY == 1) {
//				if(Y < YHolder + 2 && Y > YHolder - 2 && canMove && !collision(Player.X, Player.Y - move)) {
//					move = random.nextInt(3) - 1;
//					if(CanMove(X, Y + move)) {
//						Y += move;
//					}
//				} 
//			}
//		}
//                if(System.currentTimeMillis() - t > 400) { 
//                    frame++;
//                    t = System.currentTimeMillis();
//                }
//                if(frame >= frames.length) frame = 0;
	}
