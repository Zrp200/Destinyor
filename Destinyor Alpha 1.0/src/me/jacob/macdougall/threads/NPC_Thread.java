package me.jacob.macdougall.threads;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.battles.Battles;
import me.jacob.macdougall.graphics.UI;
import me.jacob.macdougall.npcs.NPC;
import me.jacob.macdougall.player.Player;

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
            
            for(NPC n : NPC.npcs.values()) {
            	n.tick();
            	if(n.inRange()) {
            		n.Speaking();
                            if(n.speaking) {
                                npc = n;
                            }
            	}
            }
            
        }
//        if(UI.FightOn) {
//        	if(Battles.enemiesCreated)
//        	for(int i = 0; i < Destinyor.enemies.length; i++) {
//				Destinyor.enemies[i].tick();
//			}
//        }
        
        if(UI.menu == 1) {
			if(!Battles.enemiesCreated) {
				//audio.startMusic = true;
                                
				enemies = Battles.SetEnemies();
		    Destinyor.enemies = enemies; 	
				Battles.enemiesCreated = true;
			}
		battle.EnemyAI(Player.getPlayers(), enemies);
		//battle.calculateDamage(input, entities, move);
                for(int i = 0; i < Destinyor.enemies.length; i++) {
				Destinyor.enemies[i].tick();
			}
                }
//        if(UI.FightOn) {
//			if(!Battles.enemiesCreated) {
//				enemies = Battles.SetEnemies();
//				Battles.enemiesCreated = true;
//			}
//			for(int i = 0; i < enemies.length; i++) {
//				enemies[i].tick();
//			}
//        }
	}

	@Override
	protected void render() {
            
	}
	
	@Override
	public void run() {
	int fps = 0, update = 0;
        long fps_Timer = System.currentTimeMillis();
        double unsPerUpdate = 1000000000 / 60;
        //double fnsPerUpdate = 1000000000 / 120;
        // Last update in nanoseconds
        double uthen = System.nanoTime();
        //double fthen = System.nanoTime();
        double unprocessed = 0;
        //double undrawed = 0;
        while(this.NPCs) {
        	double unow = System.nanoTime();
        	unprocessed += (unow - uthen) / unsPerUpdate;
        	uthen = unow;
        	// Update queue
        	while(unprocessed >= 1){
        		// Update
                update++;
                update();
                unprocessed--;
        	}
        	
        	
        	//double fnow = System.nanoTime();
        	//undrawed += (fnow - fthen) / fnsPerUpdate;
        	//fthen = fnow;
        	// Render
        	//while(undrawed >= 1) {
        	//fps++;
        	//render();
        	//undrawed--;
        	//}
        	
        	// FPS Timer
        	if(System.currentTimeMillis() - fps_Timer > 1000){
        		//System.out.println("NPCING");
        		System.out.printf("\n Npc_Thread: %d fps, %d updates", fps, update);
                fps = 0;
                update = 0;
                fps_Timer += 1000;
                
                try {
    				Thread.sleep(5);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	}
        }
	}

	
	
	
}
