package me.jacob.macdougall.threads;

import me.jacob.macdougall.battles.AIBattle;
import me.jacob.macdougall.battles.Battles;
import me.jacob.macdougall.cutscenes.Cutscene;
import me.jacob.macdougall.cutscenes.NPCs;
import me.jacob.macdougall.graphics.UI;
import me.jacob.macdougall.npcs.NPC;

public class NPC_Thread extends Thread_Controller implements Runnable {

	@Override
	public void start() {
		this.NPCs = true;
		this.NPCThread = new Thread(this, "NpcHandler");
		this.NPCThread.setPriority(Thread.NORM_PRIORITY);
		this.NPCThread.start();
	}

	@Override
	public void resume() {
		NPCs = true;
	}

	@Override
	public void pause() {
		NPCs = false;
	}

	@Override
	public void stop() {
		this.NPCs = false;
		try {
			this.NPCThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void update() {
		if(UI.menu == 0 || UI.menu == 2) {

			if(!Cutscene.playing) {
				for(NPC n : NPC.npcs) {
					n.tick();
					if(n.inRange()) {
						n.Speaking();
						if(n.isSpeaking()) {
							npc = n;
						}
					}
				}
			} else {
				if(cutscene != Cutscene.cutscenes.get(Cutscene.getName))
				cutscene = Cutscene.cutscenes.get(Cutscene.getName);
				cutscene.update();
				for(NPCs n : cutscene.npc.values()) {
					n.tick();
					if(n.isSpeaking()) {
						cNpc = n;
					}
				}
				if(cutscene.finished) {
					cutscene.stopCutscene();
				}
			}

		}

		if(UI.menu == UI.Fight) {
			if(!Battles.enemiesCreated) {

				AIBattle.enemies = Battles.SetEnemies();
				Battles.enemiesCreated = true;
			}
			AIBattle.update();
			for(int i = 0; i < AIBattle.enemies.length; i++) {
				AIBattle.enemies[i].tick();
			}
		}
	}

	@Override
	protected void render() {

	}

	@Override
	public synchronized void run() {
		//int fps = 0, update = 0;
		long fps_Timer = System.currentTimeMillis();
		double unsPerUpdate = 1000000000 / 60;
		// Last update in nanoseconds
		double uthen = System.nanoTime();
		double unprocessed = 0;
		while (this.NPCs) {
			double unow = System.nanoTime();
			unprocessed += (unow - uthen) / unsPerUpdate;
			uthen = unow;
			// Update queue
			while (unprocessed >= 1) {
				// Update
				//update++;
				update();
				unprocessed--;
			}

			// FPS Timer
			if(System.currentTimeMillis() - fps_Timer > 1000) {
				//System.out.printf("\n Npc_Thread: %d fps, %d updates", fps, update);
				//fps = 0;
				//update = 0;
				fps_Timer += 1000;

				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
