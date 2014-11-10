package me.jacob.macdougall.cutscenes;

import me.jacob.macdougall.DebugWriter;
import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.npcs.Keywords;
import me.jacob.macdougall.npcs.NPC;

import java.util.Map;
import java.util.HashMap;

public class NPCs extends NPC {
	// Temporay cloning class used for cutscenes, then gets deleted
	public static Map<Integer, NPCs> cNpcs = new HashMap<>();

	public int[][] direction;
	public int[] dir;
	public boolean Moving = false;
	public String absoluteName;
	public String trueName;

	public int id;
	public int parentID;
	
	/**
	 * 
	 * @param name
	 * @param frame
	 * @param x
	 * @param y
	 * @param Dialouge
	 * @param id
	 * @param parentID
	 */
	public NPCs(String name, String frame, int x, int y, String Dialouge, int id, int parentID) {
		super(name + "c", frame, x, y, Dialouge, false);
		this.parentID = parentID;
		absoluteName = name + "c";
		DebugWriter.println("Adding NPC: " + absoluteName);
		trueName = name.replace("" + id, "");
		this.cutscene = true;
		cNpcs.put(id, this);
		this.id = id;
		init();
	}
	
	/**
	 * 
	 */
	public void init() {
		for (int i = 0; i < dialouge.length; i++) {
			dialouge[i] = dialouge[i].replaceAll(absoluteName, trueName);
		}
	}

	public void stopDrawing() {
		String temp = this.name.replace(id + "c", "");
		NPC.getNpc(temp, 0).render = false;
	}

	public void Speaking(int i) {
		Destinyor.Refresh();
		this.speaking = true;
		this.dialougeLocation = i;
		dialouge[dialougeLocation] = Keywords.setKeyWords(dialouge[dialougeLocation]);
		this.cutscene = true;
	}

	public NPC getParent() {
		return NPC.getNpc(parentID);
	}

	public void delete() {
		NPCs.npcs.remove(this.getID());
		NPCs.cNpcs.remove(id);
		getParent().render = true;
	}

	public void CTick() {
		if (System.currentTimeMillis() - t > 400) {
			frame++;
			t = System.currentTimeMillis();
		}
		if (frame >= frames.length)
			frame = 0;
	}
}
