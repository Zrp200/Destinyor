package me.jacob.macdougall.cutscenes;

import me.jacob.macdougall.DebugWriter;
import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.Keys;
import me.jacob.macdougall.Time;
import me.jacob.macdougall.files.FileLoader;
import me.jacob.macdougall.npcs.NPC;
import me.jacob.macdougall.player.Move;
import me.jacob.macdougall.player.Player;
import me.jacob.macdougall.threads.Thread_Controller;
import me.jacob.macdougall.world.LevelMap;

import input.engine.keyboard.InputHandler;

import java.util.Map;
import java.util.HashMap;

public class Cutscene {
    //Cutscene will take up the most temporaly memory and will probably take the most time to load.
    
    public static Map<String, Cutscene> cutscenes = new HashMap<>();
    private static final Map<Integer, Integer> directions = new HashMap<>();
//    private static Map<Integer, Integer> dirs = new HashMap<>();
    //private Map<String, String[]> npcs = new HashMap<>();
    public Map<String, NPCs> npc = new HashMap<>();
    //private Map<String, NPCs> cNPCs = new HashMap<>();
    
    public static String getName;
    
    public String name;
    public String[] Holder;
    public String[] names;
    public String[] commands;
    public String[][] stuff;
    public String text;
    private String textHolder;
    public int[][] direction;
    public int[] dir;
    private String[] info;
    public int x, y;
    public int level;
    public int npcs = 0;
    public boolean finished = false;
    public static boolean playing = false;
    private NPCs cNpc;
    public int cI = 0;
    private int cJ = 0;
    private int dx = 0;
    private int dy = 0;
    public int dd = 0;
    
    // Direction, location, founder
    private int dxf = 0;
    private int dyf = 0;
    private int ddf = 0;
//    private int[] ddirsf;
    //private int directionf;
    //public boolean moving = false;
    //boolean[] dup;
    //public NPC[] npcs;
    
    
    public Cutscene(String location, InputHandler input) {
    	
        name = location.replace((Destinyor.DestinyorCutsceneFolder + Destinyor.fileSplit), "");
        textHolder = FileLoader.readCutscenes(location).replaceAll(" ", "");
        Holder = textHolder.split(":");
        
        int holder = 0;
        int namesH = 0;
        
        names = new String[(int) (Math.ceil(Holder.length / 2))];
        
        //System.out.println(Math.ceil(Holder.length / 2));
        
            for(int i = 1; i < Holder.length; i += 2) {
                // 1, 3
                names[namesH] = Holder[i];
                namesH++;
                
            }
            
            stuff = new String[(Holder.length / 2)][];
            
            for(int i = 2; i < Holder.length; i += 2) {
                // 2, 4
            stuff[holder] = Holder[i].split(",");
            holder++;
            }
            
        info = Holder[0].split(",");
        
        //for(int i = 0; i < info.length; i++) {
        for(String infos : info) {
            if(infos.contains("X")) {
                x = Integer.parseInt(String.valueOf(infos.charAt(2)));
            }
            if(infos.contains("Y")) {
                y = Integer.parseInt(String.valueOf(infos.charAt(2)));
            }
            if(infos.contains("Level")) {
                level = Integer.parseInt(String.valueOf(infos.charAt(6)));
            }
        }
        
        int nX = 0, nY = 0;
        String nDialouge = "";
            
//        direction[0] = new int[stuff[i].length];
//        direction[1] = new int[stuff[i].length];
//        direction[2] = new int[stuff[i].length];
//        dir = new int[stuff[i].length];
            
        for(int i = 0; i < stuff.length; i++) {
            
            //npcs.put(names[i], stuff[i]);
        	dir = new int[stuff[i].length];
            for(int j = 0; j < stuff[i].length; j++) {
                //System.out.println(stuff[i][j]);
                
                
                if(stuff[i][j].contains("left")) {
                    directions.put(j, 1);
                    dir[j] = 0;
                    dxf++;
                }
                
                if(stuff[i][j].contains("right")) {
                	directions.put(j, -1);
                	dir[j] = 0;
                    dxf++;
                }
                
                if(stuff[i][j].contains("down")) {
                	directions.put(j, 1);
                	dir[j] = 1;
                    dyf++;
                }
                
                if(stuff[i][j].contains("up")) {
                	directions.put(j, -1);
                	dir[j] = 1;
                	dyf++;
                }
                
                if(stuff[i][j].contains(".txt")) {
                	directions.put(j, 0);
                	dir[j] = 2;
                    ddf++;
                    //commands[j] = stuff[i][j];
                    nDialouge = FileLoader.readDialouges(Destinyor.DestinyorDialougesFolder + "\\" + stuff[i][j]);
                }
                
//               if(stuff[i][j].contains("left")) {
//                   direction[0][j] = 1;
//                   dir[j] = 0;
//               }
//               
//               if(stuff[i][j].contains("right")) {
//                   direction[0][j] = -1;
//                   dir[j] = 0;
//               }
//               
//               if(stuff[i][j].contains("down")) {
//                   direction[1][j] = 1;
//                   dir[j] = 1;
//               }
//               
//               if(stuff[i][j].contains("up")) {
//                   direction[1][j] = -1;
//                   dir[j] = 1;
//               }
//               
//               if(stuff[i][j].contains(".txt")) {
//                   direction[2][j] = 0;
//                   dir[j] = 2;
//                   //commands[j] = stuff[i][j];
//                   nDialouge = FileLoader.readDialouges(Destinyor.DestinyorDialougesFolder + "\\" + stuff[i][j]);
//               }
               
               if(stuff[i][j].contains("x = ")) {
                   nX = Integer.parseInt(String.valueOf(stuff[i][j].charAt(4)));
               }
               
               if(stuff[i][j].contains("y = ")) {
                   nY = Integer.parseInt(String.valueOf(stuff[i][j].charAt(4)));
               }
               
               if(nX == 0)
                   nX = NPC.npcs.get(names[i]).getX();
               
               if(nY == 0)
                   nY = NPC.npcs.get(names[i]).getY();
               
               //System.out.println(dir[j]);
            }
            
//            int x = 1;
//            int y = 1;
//            int d = 1;
//            dir = new int[dirs.size()];
//            for(int v = 0; v < dirs.size(); v++) {
//            	dir[v] = dirs.get(v);
//            	System.out.println(dirs.get(v));
//            	switch(dirs.get(v)) {
//            	case 0: x++;
//            	break;
//            	case 1: y++;
//            	break;
//            	case 2: d++;
//            	break;
//            	}
//            }
            //int[] tempDirection = new int[directions.size()];
            
            
            
//            for(int j = 0; j < directions.size(); j++) {
//            	//System.out.println(dir[j]);
//            	//System.out.println(directions.get(j));
//            	//tempDirection[j] = directions.get(j);
//            }
            
//            System.out.println("");
            
            
            
            //System.out.println(dxf);
            //System.out.println(dyf);
            //System.out.println(ddf);
            direction = new int[3][];
            direction[0] = new int[dxf];
            direction[1] = new int[dyf];
            direction[2] = new int[ddf];
            //System.out.println(dir.length);
            
            dxf = 0;
            dyf = 0;
            ddf = 0;
            int d = 0;
            
            for(int j = 0; j < dir.length; j++) {
            	switch(dir[j]) {
            	case 0: direction[dir[j]][dxf] = directions.get(d);
            	//System.out.println("Adding X " + directions.get(d));
            	//System.out.println("Adding X " + direction[dir[j]][dxf]);
            		dxf++;
            		break;
            	case 1: direction[1][dyf] = directions.get(d);
            	//System.out.println("Adding Y " + directions.get(d));
            	//System.out.println("Adding Y " + direction[dir[j]][dyf]);
            		dyf++;
            		break;
            	case 2: direction[2][ddf] = directions.get(d);
            	//System.out.println("Adding Text " + directions.get(d));
            	//System.out.println("Adding Text " + direction[dir[j]][ddf]);
            		ddf++;
            		break;
            	}
            	d++;
            }
            
            
            
            
            
            //int dirs = 0;
            //for(int d1 = 0; d1 < direction.length; d1++) {
            //for(int[] d1 : direction) {
            	//for(int d2 = 0; d2 < d1.length; d2++) {
                //for(int d2 : d1) {
            		//System.out.println(dir[dirs]);
            		//System.out.println(direction[d1][d2]);
            		//dirs++;
            	//}
            //}
            dxf = 0;
            dyf = 0;
            ddf = 0;
            
            
            npc.put(names[i] + npcs, new NPCs(names[i] + npcs, NPC.npcs.get(names[i]).sFrame, nX, nY, nDialouge, npcs));
               npc.get(names[i] + npcs).direction = direction;
               
               
               npc.get(names[i] + npcs).dir = dir;
               npc.get(names[i] + npcs).init(NPC.npcs.get(names[i]).getMap(), input);
               dir = null;
               direction = null;
               nX = 0;
               nY = 0;
               npcs++;
        }
        cutscenes.put(name, this);
        DebugWriter.println("Adding Cutscene: " + name + " At: " + x + ", " + y);
    }
    
    public void startCutscene(Move move) {
        if(Player.X == x && Player.Y == y && LevelMap.level == level && !finished) {
        	
            if(!playing) {
            DebugWriter.println("Starting Cutscene: " + name + " At: " + x + ", " + y);
            for(NPCs n : this.npc.values()) {
            	n.stopDrawing();
            }
            playing = true;
            Thread_Controller.resumeCutscene();
            getName = this.name;
            move.canMove = false;
            Time.resetCutsceneTimer();
            }
        }
    }
    
    public void update(InputHandler input) {
//    	for(NPCs n : this.npc.values()) {
//    		for(int i = 0; i < n.dir.length; i++) {
//    			for(int j = 0; j < n.direction[n.dir[i]].length; j++) {
//    				System.out.println(n.name + ": " + n.dir[i] + " " + n.direction[n.dir[i]][j]);
//    			}
//    		}
//    	}
    	
    	if(cNpc != null) {
        	//System.out.println(cNpc.name);
        	if(cNpc.Moving && Time.cutsceneTimer(60)) {
        		cNpc.Moving = false;
        		cJ++;
        	}
        	if(!Time.cutsceneTimer(60) && cNpc.Moving) {
        		//System.out.println("Returning");
        		return;
        	}
        	
        	if(Keys.Enter() && cNpc.speaking && Time.getKeyTimer(50, true)) {
                cNpc.stopSpeaking();
                dd++;
                cJ++;
                Time.resetKeyTimer();
            }
        	if(!Keys.Enter() && cNpc.speaking) {
        		return;
        	}
        }
    	
    	
//    	for(int i = 0; i < names.length; i++) {
//    		cNpc = npc.get(names[cI] + cI);
//    		for(int j = 0; j < cNpc.dir.length; j++) {
//    			//if(cNpc.moving || cNpc.speaking) {
//    				//return;
//    			//} else {
//    				switch(cNpc.dir[j]) {
//    					case 0: cNpc.setX(cNpc.direction[cNpc.dir[j]][dx]);
//    					dx++;
//    					cNpc.moving = true;
//    					Time.resetCutsceneTimer();
//    					break;
//    					case 1: cNpc.setY(cNpc.direction[cNpc.dir[j]][dy]);
//    					cNpc.moving = true;
//    					dy++;
//    					Time.resetCutsceneTimer();
//    					break;
//    					case 2: cNpc.Speaking(dd);
//    					break;
//    				}
//    			}
//    		}
    	//}
    	
//        // an if loop that is similar to a for loop, without the return
    	
        if(this.cI < names.length) {
        	
            cNpc = npc.get(names[cI] + cI);
            //System.out.println(cNpc.name);
            //System.out.println(cNpc.dir.length);
            
            if(this.cJ < cNpc.dir.length) {
            	
            	if(cNpc.dir[cJ] == 0) {
            		moveX(cNpc.direction[cNpc.dir[cJ]][dx]);
            	} else if(cNpc.dir[cJ] == 1) {
            		moveY(cNpc.direction[cNpc.dir[cJ]][dy]);
            	} else if(cNpc.dir[cJ] == 2) {
            		cNpc.Speaking(dd);
            	}
            	
//            	
//                switch(cNpc.dir[cJ]) {
//                    case 0: cNpc.setX(cNpc.direction[cNpc.dir[cJ]][dx]);
//                    		cNpc.moving = true;
//                            dx++;
//                            Time.resetCutsceneTimer();
//                            break;
//                    case 1: cNpc.setY(cNpc.direction[cNpc.dir[cJ]][dy]);
//                    		cNpc.moving = true;
//                            dy++;
//                            Time.resetCutsceneTimer();
//                            break;
//                    case 2: cNpc.Speaking(dd);
//                    		break;
//                }  
                if(cNpc.Moving || cNpc.speaking) {
                	return;
                }
            }
            dx = 0;
            dy = 0;
            dd = 0;
            cJ = 0;
//                if(!cNpc.speaking) {
//                //cJ++;
//                } else {
//                	return;
//                
            cI++;
            return;
         }
        finished = true;
    }
    
    public void moveX(int x) {
    	cNpc.setX(x);
		cNpc.Moving = true;
        dx++;
        Time.resetCutsceneTimer();
    }
    
    public void moveY(int y) {
    	cNpc.setY(y);
		cNpc.Moving = true;
        dy++;
        Time.resetCutsceneTimer();
    }
    
    public void stopCutscene(Move move) {
    	finished = true;
        playing = false;
        delete();
    	move.canMove = true;
    	Thread_Controller.pauseCutscene();
    }
        
        public void delete(){
        	for(NPCs n : npc.values()) {
        		n.delete();
        	}
        }
        
    	public static void wait (int n){
    		long t0,t1;
    		t0 = System.currentTimeMillis();
    		do {
    			t1=System.currentTimeMillis();
    		} while (t1 - t0 < n);
    	}
    	
    	public static void waitSpeak (int n, InputHandler input){
    		long t0,t1;
    		t0 = System.currentTimeMillis();
    		do {
    			t1=System.currentTimeMillis();
    		} while (t1 - t0 < n || Keys.Enter());
    	}
        
        
        
        
//        name = location;
//        commands = FileLoader.readCutscenes(location);
//        direction = new int[commands.length][];
//        
//        for(int i = 0; i < commands.length; i++) {
//            //stuff[i] = commands[i];
//            if(commands[i].contains(".txt")) {
//                textHolder = FileLoader.readDialouges(commands[i]);
//                direction[i] = 0;
//            }
//            if(commands[i].contains("x = ")) {
//                x = Integer.parseInt(commands[i]);
//            }
//            if(commands[i].contains("y = ")) {
//                y = Integer.parseInt(commands[i]);
//            }
//            if(commands[i].contains("left")) {
//                direction[i] = -1;
//            }
//            
//            if(commands[i].contains("right")) {
//                direction[i] = 1;
//            }
//            
//            if(commands[i].contains("up")) {
//                direction[i] = -2;
//            }
//            
//            if(commands[i].contains("down")) {
//                direction[i] = 2;
//            }
//        } 
    //}
    
    
}
