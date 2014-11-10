package me.jacob.macdougall.enemies;

import graphic.engine.screen.Bitmap;
import graphic.engine.screen.Screen;
import me.jacob.macdougall.graphics.MassSprites;
import me.jacob.macdougall.magic.Element;
import me.jacob.macdougall.magic.Spells;
import me.jacob.macdougall.npcs.NPC;

public class Boss extends MassSprites {
	
    public static Boss noBoss = new Boss("?", 0, 0, 0, 0, "?", 0, 0, 0, 0, 0, 0, 0, 0, 0, Element.get(Element.Physical), null, 0, 0, null);
    
	public int battleX, battleY;
        public int mapX, mapY;
	public int w,h, w1, h1;
	
	public String name;
	public String gender;
	
	public int Lvl;
	public int Exp;
	public int Gold;
	public int HP;
	public int Str;
	public int Wis;
	public int Skl;
	public int Spd;
	public int Luk;
	public int Def;
	public int Vit;
	
	public int frame;
	
	public Element Resistance;
	public String Condition;
        // [] Full enemy, [] animation
        protected Bitmap[][] frames;
        
        public boolean Defeated;
        public boolean isNpc;
        
        //public Boss(String Name, int[] frameStart, int frameEnd[], String Gender, int lvl, int exp, int hp, int str, int skl, int spd, int luk, int def, int gold, Element resistance, Spells[] spells, int[] pos, String npc) {
        public Boss(String Name, int x1, int x2, int y1, int y2, String Gender, int lvl, int exp, int hp, int str, int skl, int spd, int luk, int def, int gold, Element resistance, Spells[] spells, int x, int y, String npc) {
        super(0, 0, x1, x2, y1, y2, null);
        	//public Boss(String name, String Gender, int x, int y, int wStart, int hStart, int wStop, int hStop, int animation) {
            //this.frames = new Bitmap[(wStop - wStart) + (hStop - hStart)][animation];
            //for(int a = 0; a < animation; a++) {
                //for(int f = 0; f < (wStop - wStart) + (hStop - hStart); f++) {
                    //for(int i = wStart; i < wStop; i++) {
                        //for(int j = hStart; j < hStop; j++) {                   
                            //this.frames[f][a] = Art.spritesheet[i][j];
                        //}
                    //}
                //} 
            //}
            
//            if(frameStart != null && frameEnd != null) {
//            int[] frameX = { frameStart[0], frameEnd[0] };
//            int[] frameY = { frameStart[1], frameEnd[1] };
            
            //Bitmap[][] framez = new Bitmap[(frameX[1] - frameX[0]) * (frameY[1] - frameY[0])][(frameY[1] - frameY[0]) * (frameX[1] - frameX[0])];
            
            //frames = new Bitmap[(frameX[1] - frameX[0]) * (frameY[1] - frameY[0])][(frameY[1] - frameY[0]) * (frameX[1] - frameX[0])];
            
//            int k = 0;
//            int l = 0;
            
//            for(int i = frameX[0]; i < frameX[1]; i++) {
//                for(int j = frameY[0]; j < frameY[1]; j++) {
//                    frames[k][l] = Art.getSpritesheet()[i][j];
//                    l++;
//                }
//                k++;
//            }
            
            this.name = Name;
            this.gender = Gender;
            this.Lvl = lvl;
            this.Exp = exp;
            this.HP = hp;
            this.Str = str;
            this.Skl = skl;
            this.Spd = spd;
            this.Luk = luk;
            this.Def = def;
            this.Gold = gold;
            
            this.Resistance = resistance;
            
            this.mapX = x;
            this.mapY = y;
            if(npc != null)
            if(npc.equalsIgnoreCase("true") || npc.equalsIgnoreCase("yes")) {
                isNpc = true;
            }
            // Add spells to this class
            //Spells[] spells
//            int frame = 0;
//            for(int i = 0; i < framez.length; i++) {
//                for(int j = 0; j < framez[i].length; j++) {
//                    
//                    frames[frame] = framez[i];
//                }
//                }
//            }
            
            
            }
            
            
            
        //}
        
//        public void render(Screen screen, int x, int y, int animation) {
//        	for(int i = 0; i < frames.length; i++)
//                    for(int j = 0; j < frames[i].length; j++)
//                        screen.render(frames[i][j], x, y);
//        }
        
        public void renderAsNpc(Screen screen) {
            // All Npcs are in the npc file, as such any new boss that is a npc is also in the npc folder.
        	if(isNpc)
        		NPC.npcs.get(this.name).render(screen);
			//Tile.tiles[map.tiles[mapX][mapY]].render(screen, map.MapX_Pos + mapX * LevelMap.SIZE, map.MapY_Pos + mapY * LevelMap.SIZE, NPC.npcs.get(this.name), null, 0);
        }
        
        
}
