package me.jacob.macdougall.quests;

import java.util.HashMap;
import java.util.Map;

import me.jacob.macdougall.cutscenes.Cutscene;
import me.jacob.macdougall.enemies.Boss;
import me.jacob.macdougall.npcs.*;

public class Quest {
	
	// Item fetching quest
	// Monster hunting quest
	// Boss killing quest

	public static Map<Integer, Quest> quests = new HashMap<>();

	public Rewards[] rewards;
	private NPC questGiver;
	private NPC bossNpc;
	private Boss boss;
	public Cutscene[] cutscenes;

	public boolean Completed = false;

	public Quest(Rewards[] rewards, NPC questNpc, Boss boss, Cutscene[] cutscene, boolean completed) {
		this.boss = boss;
		this.questGiver = questNpc;
		if(boss != null)
		this.bossNpc = NPC.npcs.get(boss.name);
		this.rewards = rewards;
		this.Completed = completed;
	}

	public Boss getBosses() {
		return boss;
	}

	public boolean hasBoss() {
		return (boss != null);
	}

	public Rewards[] getRewards() {
		if(Completed)
			return rewards;
		return null;
	}

	public NPC getQuestGiver() {
		return questGiver;
	}

	public void setQuestGiver(NPC npc) {
		this.questGiver = npc;
	}

	public NPC getBossNpc() {
		return bossNpc;
	}

	public void setBossNpc(NPC boss) {
		bossNpc = boss;
	}
}
