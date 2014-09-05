package me.jacob.macdougall.world;

import graphic.engine.screen.Art;
import graphic.engine.screen.Bitmap;
import graphic.engine.screen.Screen;
import me.jacob.macdougall.npcs.NPC;

public class Tile {
	
	public static final int SIZE = 32;
	
	protected boolean solid = false;
	
	public static Tile[] tiles = new Tile[Byte.MAX_VALUE];
	
	protected int cf = 0, ci = 500;
	protected long lt = System.currentTimeMillis();
	
	protected String name;
	
	protected Bitmap[] frames;
	
	public byte id;
	
	public Tile(int id, String name, boolean isSolid, String frames) {
		
		if(tiles[id] != null) {
			throw new RuntimeException("Duplicated Tile ID! " + id);
		} else {
			tiles[id] = this;
			this.id = (byte) id;
			this.name = name;
			
			String[] framelets = frames.split(">");
			this.frames = new Bitmap[framelets.length];
			
			for(int i = 0; i < framelets.length; i++) {
				String[] ind = framelets[i].split(",");
				int fx = Integer.parseInt(ind[0]);
				int fy = Integer.parseInt(ind[1]);
				//if(Destinyor.Debug || Destinyor.Override) {
				//this.frames[i] = Art.debugspritesheet[fx][fy];
				//} else {
					this.frames[i] = Art.getSpritesheet()[fx][fy];
				//}
			}
			
			solid = isSolid;
		}
	}
	
	public void render(Screen screen, int x, int y, NPC n){
				screen.render(frames[cf], x, y);
				if(n != null)
					screen.render(n.getFrames(n.frame), x, y);
				if(System.currentTimeMillis() - lt > ci) {
					if(cf >= frames.length - 1) cf = 0;
					else cf++;
					lt += ci;
				}
				
	}
	
	public static void renderSpritesheet(Screen screen) {
		for(int i = 0; i < 24; i++){
			for(int j = 0; j < 24; j++) {
			screen.render(Art.getSpritesheet()[i][j], 32 * i, 32 * j);
			}
		}
	}
	
	public boolean isSolid() {
		return solid;
	}
				
}
