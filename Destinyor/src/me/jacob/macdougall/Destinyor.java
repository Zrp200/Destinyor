// http://www.youtube.com/watch?v=psWSGP7qrN4&list=PLE2vW4kTRjCDqxFSI0QqzigRwpdS-gMH6&index=1

// 3 year project, been working on it for around 2 years.
// Made by me.jacob.macdougall@gmail.com

/* To Do:
 * Fix Destinyor game code to make it look pretty and easier to find and change methods and variables // this.setMinPriority(); // HAHA GOOD JOKE!
 * Implement Music fully sorta done
 * Implement SoundEffects working on it.
 * Implement Spells fully
 * Fix menu for all resolution // TWO YEARS LATER AND IT IS DONE!
 * Get more fps // this.setMinPriority();
 * Add random npc save file // this.setMinPriority();
 * add reader for random npc save file
 * finish implementing items Done?
 * Implement bigger monsters
 * Implement Bosses Working
 * Add non moving npcs for shopkeepers and other essinstial npcs //NOT HAVING IT
 * Add Shopkeepers
 * Make economy 
 * Add Quests and rewards // DONE-ish
 * Add txt moddability to quests and rewards
 * Add UI Adjustments as needed
 * Add Battle UI to give more feedback
 * Fix bugs
 * Add random text to the npcs
 * Make a timer class DONE
 * Maybe add semi random duengeons
 * Create element txt file //Why? Because Modding
 * Create effect txt file
 * Restructre all the Map<> Variables aka add a Name<String> = new HashMap<>();
 * Change all the reader files to a simple for(int i = 0; i < x lines of code; i++) { br.skip(Stats[i].length); statHolder[i] = br.readLine(); }
 * add txt mod file for limbs
 * add txt file for keys
 * add moddable keys aka remapable WORKING ON IT SHEESH
 * 
 * it should be attack stat +plus weapon attack - armour and defense right
 * Armour effects psyical damage as well as magic, weapons do not, but can have a wis+ stat.
 * Ninizak: we should be able to target different limbs mayb
	Ninizak: ala VATS
	 well we'll try somethings and if we cant get it to work well then we'll scrap it
	 
	 JacobIT: Yo izak,
JacobIT: should keys be double bindable?
JacobIT: aka space is jump and sprint?
Ninizak: ye why not
JacobIT: ... Because people hate that.
Ninizak: i hate being told no
JacobIT: I didn't say no.
Ninizak: just make it appear red if its doubled
Ninizak: so then they can decide ye or ne
JacobIT: I told you why not.
 * XML uses around 65,736 bytes of data, might be better to find a way around that or declare it null at some point
 * init uses  8,896 bytes for the strings, then npcs at 3,904
 * NPCs take up the most amount of memory at 12,992 bytes
 * Databuffer takes the most amount of memory at 10,223,600 bytes which is 10 Megabytes
 * The set methods in Art take up 434,864 bytes of data
 * 
 * Heavy armour will have an effect on tp generation
 */

package me.jacob.macdougall;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
//import java.awt.RenderingHints;
//import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;

import me.jacob.macdougall.audio.Playlist;
import me.jacob.macdougall.audio.Sound;
import me.jacob.macdougall.battles.*;
import me.jacob.macdougall.cutscenes.*;
import me.jacob.macdougall.files.*;
//import me.jacob.macdougall.graphics.Shadows;
import me.jacob.macdougall.graphics.UI;
import me.jacob.macdougall.items.Equipment;
//import me.jacob.macdougall.items.Items;
import me.jacob.macdougall.magic.*;
import me.jacob.macdougall.minimap.Minimap;
import me.jacob.macdougall.npcs.*;
import me.jacob.macdougall.player.*;
//import me.jacob.macdougall.text.TextGetter;
import me.jacob.macdougall.threads.Thread_Controller;
import me.jacob.macdougall.world.*;
import graphic.engine.screen.Art;
import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;

import java.awt.Font;
//import java.io.IOException;

//import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;


import input.engine.keyboard.InputHandler;
import input.engine.keyboard.Key;
import input.engine.mouse.Mouse;
import graphic.engine.screen.Dialouge;
import graphic.engine.window.Resolution;

public class Destinyor extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String title = "Destinyor";
	public static final String build = "000.000.001.000";
	
	public static final String home = System.getProperty("user.home");
	public static final String fileSplit = System.getProperty("file.separator");
    public static final String DestinyorFolder = home + fileSplit + "Documents" + fileSplit + title;
    public static final String DestinyorSaves = DestinyorFolder + fileSplit + "Save" + ".destinyor";
    public static final String DestinyorEnemies = DestinyorFolder + fileSplit + "Enemies" + ".destinyor";
    public static final String DestinyorSettings = DestinyorFolder + fileSplit + "settings" + ".ini";
    public static final String DestinyorSpells = DestinyorFolder + fileSplit + "Spells" + ".destinyor";
    public static final String DestinyorNpcs = DestinyorFolder + fileSplit + "Npcs" + ".destinyor";
    public static final String DestinyorItems = DestinyorFolder + fileSplit + "Items" + ".destinyor";
    public static final String DestinyorDialougesFolder = DestinyorFolder + fileSplit + "Dialouges";
    public static final String DestinyorCutsceneFolder = DestinyorFolder + fileSplit + "Cutscenes";
    
    public static final int EASY = 0, NORMAL = 1, HARD = 2;
        
    public static int difficulty = 0;
    
    
    public static boolean create = true; // Create the files
    public static boolean write = false; // Write over the files
    public static boolean read = false; // Read the files
    
	public static boolean Override = false;
	public static boolean Debug = false;
	
	public static boolean Refresh = false;
	public static boolean menu = false;
	
	public static boolean FPSLock = true;
	
	public static int FramesPerSecond;
	public static int UpdatesPerSecond;
	public static int RealFPS;
	public static int RealFramesPerSecond;
	
	public static final int fpsLimit = 60;
	
	public static String Song = "";
	
	//private Image minimap;
    //private Image buttons;
	// Java Objects
	private Image image;
	private Thread thread;
	public JFrame frame;
	
	// Input Objects
	private InputHandler input;
	private Mouse mouse;
	private Move move;
	private Battles battle;
	
	// Graphic Objects
	private Minimap minimap;
	private Debug debug;
	private Screen screen;
	
	// Entities Objects
	private Player player;
    private Player[] players;
    public static Enemy[] enemies;
    
    // Other Objects
    private LevelMap map;
	private Menus menus;
	private Cutscene cutscene;
    //private TextGetter text;
    
    private Cities city1;
    //private Boss boss1;
    
    //private final Sound music1;
    //private final Sound music2;
    //private final Playlist playlist1;
    
    //private Shadows shadow;
    //private Shadows shadow1;
    //private Shadows shadow2;
    //private Shadows shadow3;
    
    private final Clock clock = new Clock();
	
    private boolean running = false;
    
	public Destinyor() {              
		//screen = new Screen(1024, 768);
		//screenUI = new Screen(1024, 768);
                //Window window = new Window(screen);
    
    //Sound music1 = new Sound(Destinyor.class.getResource("/Lady Java.wav").getPath().replace("%20", " "), true);
    Sound music2 = new Sound(Destinyor.class.getResource("/Orc March.wav").getPath().replace("%20", " "), true);
    
    @SuppressWarnings("unused")
	Playlist playlist = new Playlist(1, music2);
            //Key.setKeys();
            //System.out.println(Options.Resolutions.length);
    //Key.setKeys();
                
        
        //System.out.println(Options.Resolutions.length);
            //System.exit(0);
        
	}
	
	public void init() {
   	 screen = new Screen(1024 / 2, 768 / 2);
		//screen = new Screen(1024, 768);
   	
   	 input = new InputHandler(this);	
        battle = new Battles(input);   
        menus = new Menus(this, input);
        //shadow = new Shadows(Art.getSpritesheet()[3][0]);
//        shadow1 = new Shadows(Art.getSpritesheet()[0][0]);
//        shadow2 = new Shadows(Art.getSpritesheet()[0][0]);
//        shadow3 = new Shadows(Art.getSpritesheet()[0][0]);
        Thread_Controller.init(screen, battle, input);
        Thread_Controller.startAudio();
        //String Name, int[] frameStart, int frameEnd[], String Gender, int lvl, int exp, int hp, int str, int skl, int spd, int luk, int def, int gold, Element resistance, Spells[] spells, int[] pos, String npc)
        //boss1 = new Boss("Default", 2, 5, 16, 19, "Male", 1, 10, 10, 10, 10, 10, 10, 10, 10, Element.get(Element.Physical), null, 7, 7, null);
        
        //for(int i = 0; i < 10; i++) {
//        	for(int j = 0; j < 10; j++) {
//        		Objects o = new Objects("test" + i + j, i, j, true, "0, 5, 3");
//        	}
//        }
        
        //Objects o = new Objects("test" + 0 + 0, 6, 6, true, "0, 5, 3");
        Objects o = new Objects("test" + 1 + 1, 7, 7, true, "0, 5, 3");
//        try {
//			music1.open();
//			music2.open();
//		} catch (LineUnavailableException | IOException e) {
//			e.printStackTrace();
//		}
        //music1.playSound();
        //text = new TextGetter();
        
        if(Debug) {
       	 debug = new Debug();
        }
        map = LevelMap.Maps.get(1);
        minimap = new Minimap(this);
	
        
        //map = LevelMap.Maps.get(LevelMap.level + 1);
        //RandomLevel rl = new RandomLevel();
        //map = LevelMap.Maps.get(LevelMap.Maps.size() - 1);
        city1 = new Cities("Default", 12, 12, 100, 2, 5, 16, 19, 1, 2, 1, 1);
        //map = rl;
        move = new Move(this, map);
        player = Player.getPlayer(FileLoader.PKeys().get(0));
        players = Player.getPlayers();
        Thread_Controller.setNpc(move);

        // Start making random npcs
        Random random = new Random();

        int gender = random.nextInt(2);
        int name = random.nextInt(NPC.names[gender].length);
        int x = random.nextInt(512 / 28);
        int y = random.nextInt(512 / 28);

        //for(int i = 0; i < NPC.names.length; i++) {
       	 //for(int j = 0; j < NPC.names[i].length; j++) {
        for(String[] NAME : NPC.names) {
       		 NPC.RandomNpcs(name, gender, x, y, map);
       		 gender = random.nextInt(2);
       		 name = random.nextInt(NPC.names[gender].length);
       		 x = random.nextInt(512);
       		 y = random.nextInt(512);
       	 }
        //}

        for(int i = 0; i < 32; i++) {
       	 for(int j = 0; j < 32; j++) {
       		 x = random.nextInt(510) + 1;
			//x = i + 1;
       		 y = random.nextInt(510) + 1;
			//y = j + 1;
       		 if(!map.getTileAt(x, y).isSolid()) {
       			 NPC.npcs.put(i + ":" + j, new NPC(i + " "+ j, "0,12", x, y, i + ":" + i, false));
       		 }
       	 }
		//String name, String frame, int x, int y, String Dialouge, boolean moving
        }

        for(NPC n : NPC.npcs.values()) {
       	 n.init(map, input);
        }

        // Manually adds Items until File exists
        Equipment sword =  new Equipment("Sword", 10, 0, Element.get(Element.Physical), Equipment.Right_Hand, null);
        Equipment.equipment.put(sword.name, sword);
        Equipment sheild =  new Equipment("Sheild", 10, 0, Element.get(Element.Physical), Equipment.Left_Hand, null);
        Equipment.equipment.put(sheild.name, sheild);
        Equipment breastplate = new Equipment("Breastplate", 10, 0, Element.get(Element.Physical), Equipment.Body, null);
        Equipment.equipment.put(breastplate.name, breastplate);
        Equipment gauntlets = new Equipment("Gauntlets", 10, 0, Element.get(Element.Physical), Equipment.Hands, null);
        Equipment.equipment.put(gauntlets.name, gauntlets);
        Equipment boots = new Equipment("Boots", 10, 0, Element.get(Element.Physical), Equipment.Feet, null);
        Equipment.equipment.put(boots.name, boots);
        Equipment pauldrons = new Equipment("Pauldrons", 10, 0, Element.get(Element.Physical), Equipment.Shoulders, null);
        Equipment.equipment.put(pauldrons.name, pauldrons);
        // Item.items.put("Legging", new Item("Legging", 4, 0, Element.get(Element.Physical), "Armour", null));
        // Item.items.put("Mace", new Item("Mace", 8, 0, Element.get(Element.Physical), "Weapon", null));
        // Item.items.put("BattleAxe", new Item("BattleAxe", 9, 0, Element.get(Element.Physical), "Weapon", null));
        // Item.items.put("Hammer", new Item("Hammer", 9, 0, Element.get(Element.Physical), "Weapon", null));
        // Item.items.put("Axe", new Item("Axe", 7, 0, Element.get(Element.Physical), "Weapon", null));
        // Item.items.put("GreatSword", new Item("GreatSword", 12, 0, Element.get(Element.Physical), "Weapon", null));
        // Item.items.put("WarHammer", new Item("WarHammer", 11, 0, Element.get(Element.Physical), "Weapon", null));
        // Item.items.put("ChainMail", new Item("ChainMail", 3, 0, Element.get(Element.Physical), "Armour", null));
        // Item.items.put("Helmet", new Item("Helmet", 2, 0, Element.get(Element.Physical), "Armour", null));
        // Item.items.put("Cloak", new Item("Cloak", 0, 100, Element.get(Element.Fire), "Armour", null));
        
        // for(int i = 0; i < Item.names.size(); i++)
        // Item.addToInventory(Item.names.get(i));
        //Player.putItInTheBag(sword.newInstance());
        Player.putItInTheBag(sword.newInstance());
        Player.putItInTheBag(sheild.newInstance());
        Player.putItInTheBag(breastplate.newInstance());
        Player.putItInTheBag(gauntlets.newInstance());
        Player.putItInTheBag(boots.newInstance());
        Player.putItInTheBag(pauldrons.newInstance());
        
        player.Equip((Equipment) Player.inventory.get(0));
        player.Equip((Equipment) Player.inventory.get(1));
        player.Equip((Equipment) Player.inventory.get(2));
        player.Equip((Equipment) Player.inventory.get(3));
        player.Equip((Equipment) Player.inventory.get(4));
        player.Equip((Equipment) Player.inventory.get(5));
        
		// player.Equip(Item.names.get(0));
		// player.Equip(Item.names.get(1));
		// player.Equip(Item.names.get(2));
		// players[1].Equip(Item.names.get(1));
		// players[2].Equip(Item.names.get(1));
		// players[3].Equip(Item.names.get(1));
       //public Item(String name, int damage, int cost, String attribute, Element element, String type, boolean equipped, String user)
        
		 Thread_Controller.startNPC();
		 
		 cutscene = new Cutscene(DestinyorCutsceneFolder + fileSplit + "default.txt", input);
		 
		 Thread_Controller.startCutscene();
		 Thread_Controller.pauseCutscene();
   }
	
	public static void RemoveDebug() {
		for(int i = 0; i < DebugWriter.strings.size(); i++) {
			if(DebugWriter.getln(i).contains("Menu:")) {
				DebugWriter.removeln(DebugWriter.getln(i));
				i--;
			}
		}
	}
	
    private void RenderInventory() {
    	UI.renderInventory(screen);
    	
        if(!Player.inventory.isEmpty()) {
        	//return;
        //} else {
        	Equipment item = (Equipment) Player.inventory.get(0);
        	int x = 12;
            //int y = 12;
                int y;
            
            GameFont.render("Name: " + item.name, screen, 12, 288);
            GameFont.render("Equipped: " + item.equipped, screen, 12, 288 + 16);
            GameFont.render("Attack: " + item.damage, screen, 12, 288 + 32);
            GameFont.render("Price: " + item.cost, screen, 12, 288 + 48);
            GameFont.render("Element: " + item.element.getElement(), screen, 12, 288 + 64);
            
            for(int i = 0; i < Player.inventory.size(); i++) {
            		y = 12 * (i + 1);
            		
            		if(Player.inventory.get(i).equippable) {
            			Equipment equipment = (Equipment) Player.inventory.get(i);
            			
            			if(equipment != null) {
            				equipment.render(screen, x, y);
                        
            				if(i == 11) {
            					x += 128;
            					//y = -1;
            				}
            			}
            		}
            		
            	}
        }
            
    }
        
    private void RenderSpellBook(Player player) {
        UI.renderInventory(screen);
        
        if(!player.spells.isEmpty() || player.spells.size() >= 1) {
        	//return;
        //} else {
        	for(int i = 1; i < player.spells.size(); i++) {
        		Spells spell = player.spells.get(i);
        		GameFont.render(spell.name + ", " + spell.damage + ", " + spell.cost, screen, 12, 8);
        		GameFont.render("Name: " + spell.name, screen, 12, 288);
                GameFont.render("Useable: " + spell.UseAbleOutsideCombat, screen, 12, 288 + 16);
                GameFont.render("Attack: " + spell.damage, screen, 12, 288 + 32);
                GameFont.render("Price: " + spell.cost, screen, 12, 288 + 48);
                GameFont.render("Element: " + spell.element.getElement(), screen, 12, 288 + 64);
        	}
        }    	
    }
        
    private void RenderEquipment() {
        int p = UI.menu;
        int px = 30;
        int py = 0;
        int x = 6;
        int y = 0;
            
        UI.renderInventory(screen);
            
        if(UI.menu >= 6 && UI.menu <= 9) {
        	for(int i = 0; i < Equipment.items.size(); i++) {
                GameFont.render(players[p - 6].Name, screen, px, py);
                players[p - 6].Equipment(Equipment.names.get(i)).render(screen, x, y + 12 * (i + 1));
                    
                if(!players[p - 6].Equipment(Equipment.names.get(i)).name.equals("null")) {
                	//continue;
                //} else {
                	y += 6;
                }
            }
            px += 144 - 10;
            x += ((768 / 2) / 4) + 50;
            //y = 0;
        }
    }
        
	private void RenderUI(){
		Player.Attackable = false; // Makes Sure Player Can't be Attacked By Enemy
		UI.render(screen); // Renders UI
		battle.render(Player.getActualPlayers(), screen, enemies); // Renders Enemies and Players
		
	}
	
	public void renderMenu() {
		menus.render(screen);
	}
		
	private void RenderMaps() { 
		Player.Attackable = true; // Makes Sure Player Can be Attacked By Enemy
		map.render(screen, -Camera.cX, -Camera.cY);
		if(UI.menu == 0 || UI.menu == 2 && !Destinyor.menu)  {
			//if(Cutscene.playing) {
				//return;
				//if(Thread_Controller.cNpc != null) {
					//Thread_Controller.cNpc.render(screen);
					//System.out.println(Thread_Controller.cNpc.name);
//				if(Thread_Controller.cNpc.speaking) {
//                    UI.TextBox(screen);
//                    Dialouge.render(screen, Thread_Controller.cNpc.dialouge, Thread_Controller.cNpc.dialougeLocation);
//				}
                //}
			//} else {
			for(NPC n : NPC.npcs.values()) {
	            if(n.inRange() && n.render) {
	            	n.render(screen);
	            	if(n.speaking) {
 	                   	Dialouge.setText(n.dialouge, n.dialougeLocation);         
                    } 
                            	
                }
            }
			for(Objects o : Objects.objects.values()) {
				o.render(map, screen);
			}
			Time.getObjectTimer(30, true);
			city1.render(screen);
		}
		//player.render(screen, Camera.pX, Camera.pY);
		player.render(screen);
        //GameFont.render(GameFont.pointer, screen, 30, 30);
	}
	
	// Starts Game
	public void start(){
		running = true;
		thread = new Thread(this, "MainThread");
        thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
	}
	
	// Stops Game
	public void stop(){
		running = false;
		System.exit(0);
	}
	
	private void render() {
			// Get Current Canvas
			BufferStrategy strategy = this.getBufferStrategy();
			if(strategy == null){
				// Create Strategy
				createBufferStrategy(3);
				requestFocus();
				return;
			}
			
			// Draw Things with Screen 			
            if(UI.menu == UI.Map || UI.menu == UI.Minimap)
            	RenderMaps();
            if(UI.menu == UI.Fight)
            	RenderUI();
            if(UI.menu == UI.Inventory)
            	RenderInventory();
            if(UI.menu == UI.Equipment || (UI.menu >= UI.Player1 && UI.menu <= UI.Player4))
            	RenderEquipment();
            if(UI.menu == UI.Spellbook)
            	RenderSpellBook(player);
            if(Cutscene.playing)
            	Thread_Controller.renderCutscene();
            menus.render(screen);
            //text.render(screen);
            if(Dialouge.isEmpty())
            UI.TextBox(screen);
            Dialouge.render(screen);
            //screen.render(Shadows.getShadows(Art.getSpritesheet()[0][0]), 6 * 32, 7 * 32);
            //screen.render(shadow.getShadows(Shadows.LEFT_CORNER), 10 * 32, 4 * 32);
            //screen.render(shadow1.getShadows(Shadows.RIGHT_UP_CORNER), 5 * 32, 7 * 32);
            //screen.render(shadow2.getShadows(Shadows.RIGHT), 3 * 32, 7 * 32);
            //screen.render(shadow3.getShadows(Shadows.LEFT), 1 * 32, 7 * 32);
            //screen.render(Art.getSpritesheet()[0][0], 6 * 32, 6 * 32);
            
			// Draw Screen onto Frame
			Graphics g = strategy.getDrawGraphics();
			
			//Image image = graphic.engine.screen.Art.getScaledInstance(screen.image, Resolution.width(), Resolution.height(), RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC, true);
			//image = graphic.engine.screen.Art.getScaledInstance(screen.image, Resolution.width(), Resolution.height(), RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON, true);
			g.drawImage(screen.image, 0, 0, Resolution.Width(), Resolution.Height(), null);//screen.image, 0, 0, Camera.width, Camera.height, null);
				//g.drawImage(screen.image, 0, 0, Resolution.width(), Resolution.height(), null);
			
			if(UI.menu == 1) {
				battle.renderTime(Player.getActualPlayers(), g);
			}
			
//			g.setColor(Color.MAGENTA);
			
//			if(menu) {
////				g.drawRect(Window.startX, Window.startY, MainMenu.startW, MainMenu.startH);
////				g.drawRect(MainMenu.optionX, MainMenu.optionY, MainMenu.startW, MainMenu.startH);
//			}
			
			g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 16));
			minimap.render(g);
			
			g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 16));    
			g.drawString("Fps: " + String.valueOf(FramesPerSecond) + ", " + "Ups: " + String.valueOf(UpdatesPerSecond), 1, 16);
			g.drawString(Song, this.getWidth() - (Song.length() * 9), 16);
			
			if(Debug) {
            g.drawString("Player X Pos = " + Player.X + ", Player Y Pos = " + Player.Y, 1, 33); // Displays Player x and y position
            g.drawString("Steps: " + Move.steps, 1, 65);
			debug.render();
			}
			
//			int[] pixel = new int[32 * 32];
//			for(int i = 0; i < 32 / 2; i++) {
//				for(int j = 0; j < 32 / 2; j++) {
//					pixel[i + j] = 1;
//				}
//			}
			
//			for(int i = 0; i < 32; i++) {
//				for(int j = 0; j < 32; j++) {
//					if(pixel[i + j] == 1)
//					g.drawRect(i, j, 1, 1);
//				}
//			}
			clock.render(g);
			
//			for(int i = 0; i < 32 / 2; i++) {
//				for(int j = 0; j < 32 / 2; j++) {
//					g.drawRect(i, j, 1, 1);
//					g.fillRect(i, j, 1, 1);
//				}
//			}
			
			// Finalize & Dispose
			g.dispose();
			strategy.show();
	}
	
	// Updates Game Logic
	private void update() {
//		for(Items items : Items.items.values()) {
//			items.getColor();
//		}
		Time.tick();
		clock.tick();
		//text.GetText(input);
		input.tick();
		
//		if(input.enter.down && Time.getKeyTimer(10, true)) {
//			map = new RandomLevel();
//		}
		
		//input.tick();
		//for(InputHandler.Key key : InputHandler.)
		for(Key key : Key.keys.values())  {
			key.tick();
		}
		
		
		
		move.Movement();
		
		city1.goToTown(this);
		
		menus.mouseChecker(mouse, Resolution.width(), Resolution.height());
		cutscene.startCutscene(move);
		//minimap.zoom(input);
        //cutscene.startCutscene(move, input);
		//Save();
                
         if(Refresh) {
            UI.REFRESH(screen);
            Refresh = false;
         }
                
		if(UI.menu == 1) {
                    if(Battles.enemiesCreated) {
                    	
                            battle.assignTarget(mouse, enemies);
                        //move.Combat(Player.getPlayers());
                            //System.out.println(enemies[0].attacking);
                            battle.bInput.clicker(mouse, enemies, input);
		battle.calculateDamage(input, enemies, move);
                    }
                }
	}
	
	// Thread
		@Override
       // @SuppressWarnings({"SleepWhileInLoop", "CallToPrintStackTrace"})
	public void run() {
		int fps = 0, update = 0;
        long fps_Timer = System.currentTimeMillis();
        double nsPerUpdate = 1000000000 / 60;
        double fsPerUpdate = 1000000000 / fpsLimit;
        // Last update in nanoseconds
        double then = System.nanoTime();
        double fThen = System.currentTimeMillis();
        double unprocessed = 0;
        double fUnprocessed = 0; //= 0;
        int j = 0;
        while(running && Thread_Controller.doneLoading) {
        	double now = System.nanoTime();
        	unprocessed += (now - then) / nsPerUpdate;
        	then = now;
        	//System.out.println(unprocessed);
        	// Update queue
        	while(unprocessed >= 1){
        		// Update
                update++;
                update();
                unprocessed--;
                //System.out.println(unprocessed);
        	}
        	//if(fUnprocessed >= 16 || fUnprocessed == 0) {
        	
        	//}
        	
        	//unprocessed += (fNow - fThen) / fsPerUpdate;
        	
        	// Render
        	//if(fps >= 60) {
        	//if(!FPSLock) {
        	//fps++;
        	if(FPSLock) {
        		double fNow = System.nanoTime();
        		//fUnprocessed -= fUnprocessed;
            	fUnprocessed += (fNow - fThen) / (fsPerUpdate);
            	//System.out.println((fNow - fThen) / fsPerUpdate);
            	fThen = fNow;
            	if(fUnprocessed >= 1) {
            	fps++;
    			render();
    			fUnprocessed--;
    			//System.out.println(fUnprocessed);
            	}
        		//System.out.println(fUnprocessed);
        		//while(fUnprocessed >= 1) {
//        		if(fUnprocessed >= 1) {
//        			//RealFPS++;
//        			
//        			
//        		}
        		//}
        	} else {
        		fps++;
        		render();
        	}
        	//j++;
        	//}
        	//}
        	
        	// FPS Timer
        	if(System.currentTimeMillis() - fps_Timer > 1000){
        		//RealFPS = fps;
        		System.out.printf("\n Main_Thread: %d fps, %d updates", fps, update);
        		j = 0;
                FramesPerSecond = fps;
                UpdatesPerSecond = update;
                fUnprocessed = 0;
                //RealFramesPerSecond = fps;
                //RealFPS = fps;
                fps = 0;
                update = 0;
                fps_Timer += 1000;
                
                try {
    				Thread.sleep(1000 / fpsLimit); // 1000m = 1s so (1000 / 60) = 1/10th of a second
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
        	}
        }
        pauseLoading();
    }
	
	public static void main(String[] args){
		System.out.println(System.getProperty("user.country"));
		FileLoader.CreateFile(DestinyorSettings);
		FileLoader.ReadFromFiles(DestinyorSettings);
		Override = FileLoader.Override();
        Debug = FileLoader.Debug();
        Limbs limb; //http://www.innerbody.com/anatomy/integumentary
        // Will add txt mod file later
        limb = new Limbs("Head", 0, false);
        limb = new Limbs("Neck", 1, false);
        limb = new Limbs("Upper Torso", 2, false);
        limb = new Limbs("Left Shoulder", 3, false);
        limb = new Limbs("Right Shoulder", 4, false);
        limb = new Limbs("Left Wing", 5, false);
        limb = new Limbs("Right Wing", 6, false);
        limb = new Limbs("Left Arm", 7, false);
        limb = new Limbs("Right Arm", 8, false);
        limb = new Limbs("Lower Torso", 9, false);
        limb = new Limbs("Left Hand", 10, false);
        limb = new Limbs("Right Hand", 11, false);
        //limb = new Limbs("Belt", 0, false);
        //limb = new Limbs("Grion", 0, false);
        limb = new Limbs("Left Leg", 12, false);
        limb = new Limbs("Right Leg", 13, false);
        limb = new Limbs("Left Foot", 14, false);
        limb = new Limbs("Right Foot", 15, false);
        limb = null;
        
        Entities entity;
        
//      public Entities(String name, int limbAmount, Limbs[] limbs) {
//			this.name = name;
//			this.limbAmount = limbAmount;
//			currentLimbs = limbs;
//	}
        Limbs[] limbs = new Limbs[13];
        for(int i = 0; i < limbs.length; i++) {
        	if(i == 5) {
        		i += 2;
        	}
        	if(i == 6) {
        		i += 1;
        	}
        	limbs[i] = Limbs.getLimb(i);
        	System.out.println(i);
        	System.out.println(Limbs.getName(i));
        }
        entity = new Entities("Humaniod", 13, limbs);
        limbs = new Limbs[15];
        for(int i = 0; i < limbs.length; i++) {
        	limbs[i] = Limbs.getLimb(i);
        	System.out.println(i);
        	System.out.println(Limbs.getName(i));
        }
        entity = new Entities("Angelic", 15, limbs);
        
        //entity = new Entities("Angelic", 15, limbs);
        entity = null;
       // entity = new Entities(name, FramesPerSecond, 0);
        //			Left_Wing = 14,    Head = 0, Right_Wing = 15, // A minority of creatures will have wings so that's why it's higher
//			Left_Shoulder = 1, Body = 2, Right_Shoulder = 3,
//				Left_Arm = 4, Waist = 5, Right_Arm = 6,
//				Left_Hand = 7, Belt = 8, Right_Hand = 9,
//				Left_Leg = 10, Groin = 11, Right_Leg = 11,
//				Left_Foot = 12,			Right_Foot = 13;
		
		// If Resolution does not equal any of the pre-defined resolutions, print error log and exit game
        // I did this because the code for the mouse has to be made for the Resolutions
//		boolean RESOKAY = false;
//		for(int i = 0; i != Options.Resolutions.length; i++) {
//			if(!Override) {
//				if(Resolution.height() != Options.Height[i] || Resolution.width() != Options.Width[i] && !RESOKAY) {
//					if(Resolution.height() == Options.Height[i] && Resolution.width() == Options.Width[i] || RESOKAY || Override) {
//						// Exits the if and for Statement
//					} else {
//						if(i == Options.Resolutions.length) {
//							Errors.setBError2(true);
//							Errors.WriteErrorLog(home, "null");
//							System.exit(0);
//						}
//					}
//				} else {
//					RESOKAY = true;
//				}
//			}
//    	}
                
		Dimension Res = new Dimension(Resolution.width(), Resolution.height());
		Destinyor game = new Destinyor();
                Art.setSpritesheet("/icon0.png", 32, 32, game);
                Art.setFont("/8fontTest.png", 8, 8, game);
                Art.setButtons("/button.png", 120, 20, game);
                Art.setMap("/map.png", 512, 512, game);
                DynamicsLoader.init();
		//System.exit(0);
		
		FileLoader.CreateFolder(DestinyorDialougesFolder);
    	FileLoader.CreateFolder(DestinyorCutsceneFolder);
    	Writer.writeCutscene(DestinyorCutsceneFolder);
    	//String[] stats = new String[1];
    	//stats[0] = "Hello I am Jacob! END Welcome to Destinyor!";
    	//file.engine.writer.Writer.WriteDefault(DestinyorDialougesFolder + fileSplit + "jacob.txt", stats);
    	//stats = null;
    	//stats[0] = "Hello I am Izak! END Welcome to Destinyor!";
    	//file.engine.writer.Writer.WriteDefault(DestinyorDialougesFolder + fileSplit + "izak.txt", stats);
    	
    	Default.setEnemies();
	    Default.setSpells();
	    Default.setNpcs();
	    Default.SetItems();
	    
	    Element.setElements();
	    
        Thread_Controller.startLoading();
		
		 
	        
	    Destinyor.waitForLoading();
	    
	    
	        
		game.mouse = new Mouse();
		
		game.setSize(Res);
		
		if(game.getSize().getWidth() != Res.getWidth() || game.getSize().getHeight() != Res.getHeight()) {
			//System.out.println(true);
			game.setSize(Resolution.width(), Resolution.height());
		}
		
		// Frame
		JFrame frame = new JFrame(title + " " + build);
				
		frame.setResizable(false);
                
                switch(Resolution.Fullscreen) {
                    case "Fullscreen": frame.setUndecorated(true);
                        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        break;
                    case "Borderless Window": frame.setUndecorated(true);
                        break;
                }
                		
		frame.add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		//frame.setLocationByPlatform(true);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		frame.requestFocus();
		frame.requestFocusInWindow();
		game.addMouseListener(game.mouse.new MouseHandler());
		game.addMouseMotionListener(game.mouse.new MouseHandler());
		
		System.out.println("Destinyor.main(): " + Resolution.width() + " x " + Resolution.height() + " = " + Res);
		System.out.println("Destinyor.main(): " + game.getSize());
		
		if(Res != game.getSize() || Res != frame.getSize()) {
			if(frame.getExtendedState() != JFrame.MAXIMIZED_BOTH) {
			game.setSize(Res);
			frame.setSize(Res);
			frame.setLocationRelativeTo(null);
			} else {
				Resolution.setWidth(frame.getWidth());
				Resolution.setHeight(frame.getHeight());
			}
		}
		
//		if(game.getWidth() != Resolution.width()) {
//			System.exit(0);
//		}
		// Starts Game
		game.init();
		game.frame = frame;
		game.start();
		
		if(Debug) {
			Player.Attackable = false;
		}
	}
	
        // Testing only, in move class/file
	public void ChangeScreenToUI(){
		if(Keys.PageDown()){
                                        Refresh = true;
                    UI.menu = UI.Fight;
					//UI.MapOn = false;
					//UI.FightOn = true;
					//audio.startMusic = true;
				}
	}
	
        // Testing only, in move class/file
	public void ChangeScreenToMap(){
		if(Keys.Home()){
					Refresh = true;
					UI.menu = UI.Map;
//					UI.MapOn = true;
//					UI.FightOn = false;
					//audio.startMusic = true;
				}
	}
	
	public void Save() {
		if(Keys.Escape()) {
			FileLoader.WriteToFiles(DestinyorSaves);
			System.out.println("Saving");
		}
	}
        
        public static void waitForLoading() {
                do {
                    if(Thread_Controller.doneLoading) {
                        Thread_Controller.pauseLoading();
                    }
                } while(!Thread_Controller.doneLoading);
        }
        
        public static void pauseLoading() {
            if(Thread_Controller.doneLoading) {
                Thread_Controller.pauseLoading();
            }
        }
        
        public static void setSettings() {
        	int amount = 3;
        	if(Override) {
        		amount++;
        	}
        	if(Debug) {
        		amount++;
        	}
        	String[] Stats = new String[amount];
        	int i = 0;
        	if(Override) {
        		Stats[i] = "@Override";
        		i++;
        	}
        	
        	if(Debug) {
        		Stats[i] = "@Debug";
        		i++;
        	}
        	Stats[i] = "Width = " + Resolution.width();
        	i++;
        	Stats[i] = "Height = " + Resolution.height();
        	i++;
        	Stats[i] = "Window Mode = " + Resolution.Fullscreen;
        	file.engine.writer.Writer.WriteDefault(DestinyorSettings, Stats);
        }
        
        public void changeLevel(int level) {
        	Refresh = true;
        	map = LevelMap.Maps.get(level);
        	minimap = new Minimap(this);
        }
        
        public static boolean getBoolean(String args) {
        	if(args.equalsIgnoreCase("yes") || args.equalsIgnoreCase("true") || args.equals("1")) {
        		return true;
        	} else {
        		return false;
        	}
        }
        // cannot figure out how to handle 0
//        // none crashing alternitive to Integer.ParseInt(String);
//        public int toInt(String amount) {
//        	int number = 0;
//        	int letter = 0;
//        	int j = 1;
//        	char[] info = amount.toCharArray();
//            int remove = 0;
//            
//            for (int i = 0; i < info.length; i++)
//            {
//                if (info[i] != '1' && info[i] != '2' && info[i] != '3' && info[i] != '4' && info[i] != '5' && info[i] != '6' && info[i] != '7' && info[i] != '8' && info[i] != '9' && info[i] != '0')
//                {
//                    remove++;
//                }
//            }
//        	
//            for (int i = 0; i < amount.length(); i++)
//            {
//                switch (info[amount.length() - i - 1])
//                {
//                    case '1': number += 1 * (getSize(j - remove)); break;
//                    case '2': number += 2 * (getSize(j - remove)); break;
//                    case '3': number += 3 * (getSize(j - remove)); break;
//                    case '4': number += 4 * (getSize(j - remove)); break;
//                    case '5': number += 5 * (getSize(j - remove)); break;
//                    case '6': number += 6 * (getSize(j - remove)); break;
//                    case '7': number += 7 * (getSize(j - remove)); break;
//                    case '8': number += 8 * (getSize(j - remove)); break;
//                    case '9': number += 9 * (getSize(j - remove)); break;
//                    case '0': number += 1 * (getSize(j - remove)); break;
//                    default: letter++; break;
//                }
//                j++;
//            }
//        	return number;
//        }
//        
//        private static int getSize(int length)
//        {
//            int number = 0;
//            switch (length)
//            {
//                case 1: number += 1; break;
//                case 2: number += 10; break;
//                case 3: number += 100; break;
//                case 4: number += 1000; break;
//                case 5: number += 10000; break;
//                case 6: number += 100000; break;
//                case 7: number += 1000000; break;
//                case 8: number += 10000000; break;
//                case 9: number += 100000000; break;
//                //default: number += 100000000; break;
//                default: number += 1; break;
//
//            }
//            return number;
//        }

}
