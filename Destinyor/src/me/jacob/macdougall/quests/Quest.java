package me.jacob.macdougall.quests;

import graphic.engine.screen.Dialouge;
import graphic.engine.screen.Screen;

import java.util.HashMap;
import java.util.Map;

import me.jacob.macdougall.cutscenes.Cutscene;
import me.jacob.macdougall.enemies.Boss;
import me.jacob.macdougall.npcs.*;

public class Quest {
	
	// Item fetching quest
	// Monster hunting quest
	// Boss killing quest
	// previous quest completion

	public static Map<Integer, Quest> quests = new HashMap<>();

	public Rewards[] rewards;
	private NPC questGiver;
	private Boss boss;
	public Cutscene[] cutscenes;
	private boolean isAccepted = false;
	
	private String startDialouge;
	private String endDialouge;

	public boolean Completed = false;

	public Quest(Rewards[] rewards, NPC questNpc, Boss boss, Cutscene[] cutscene, String startDialouge, String endDialouge, boolean completed) {
		this.boss = boss;
		this.questGiver = questNpc;
		if(boss != null)
		//this.bossNpc = NPC.npcs.get(boss.getName());
		this.rewards = rewards;
		this.startDialouge = startDialouge;
		this.endDialouge =  endDialouge;
	}
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
	
	public boolean isAccepted() {
		return isAccepted;
	}
	
	public String getStartDialouge() {
		return startDialouge;
	}
	
	public String getEndDialouge() {
		return endDialouge;
	}
	
	public static void checkQuest(String questType) {
		
	}
	
	/**
	 * Mock example of the prompt dialouge
	 */
	public void prompt(Screen screen) {
		String[] dialouge = {
				"Are you sure you want to turn in this quest?"
		};
		Dialouge.setText(dialouge, 0);
		Dialouge.render(screen); // hand in the "/*+*/ /*items.length*/ /*+*/ /*item.name*/ + "s");
	}

	public NPC getBossNpc() {
		return bossNpc;
	}

	public void setBossNpc(NPC boss) {
		bossNpc = boss;
	}
}
