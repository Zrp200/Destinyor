package me.jacob.macdougall.npcs;

import graphic.engine.screen.Dialouge;
import me.jacob.macdougall.battles.AIBattle;
import me.jacob.macdougall.enemies.Boss;
import me.jacob.macdougall.enemies.Enemies;
import me.jacob.macdougall.world.LevelMap;

public class BossNpc extends NPC {

	public BossNpc(String name, String frame, int x, int y, String dialouge, boolean moving) {
		super(name, frame, x, y, dialouge, false);
	}
	
	public BossNpc(String name, String frame, int x, int y, String dialouge, LevelMap map) {
		super(name, frame, x, y, dialouge, false, map);
	}
	
	@Override
	public void stopSpeaking() {
		//this.speaking = false;
		Dialouge.setText(null, 0);
		Enemies[] boss = {
			Boss.bosses.get(0)
		};
		AIBattle.enemies = boss;
		//AIBattle.enemies = 
	}
	
}
