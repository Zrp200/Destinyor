package me.jacob.macdougall.threads;

import graphic.engine.screen.Dialouge;

import me.jacob.macdougall.cutscenes.Cutscene;
import me.jacob.macdougall.cutscenes.NPCs;
import me.jacob.macdougall.graphics.UI;

public class Cutscene_Thread extends Thread_Controller implements Runnable {
	
	@Override
	public void run() {
		int fps = 0, update = 0;
        long fps_Timer = System.currentTimeMillis();
        double unsPerUpdate = 1000000000 / 60;
        double uthen = System.nanoTime();
        double unprocessed = 0;
        while(cutscenes) {
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
        	
        	
        	
        	// FPS Timer
        	if(System.currentTimeMillis() - fps_Timer > 1000){
        		System.out.printf("\n Cutscene_Thread: %d fps, %d updates", fps, update);
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

	@Override
	public void start() {
		cutscenes = true;
		this.CutsceneThread = new Thread(this, "CutsceneHandler");
        this.CutsceneThread.setPriority(Thread.NORM_PRIORITY);
		this.CutsceneThread.start();
		
	}

	@Override
	public void stop() {
		cutscenes = false;
		try {
			this.CutsceneThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void pause() {
		cutscenes = false;
		
	}

	@Override
	public void resume() {
		start();
	}

	@Override
	protected void update() {
		if(Cutscene.playing) {
            cutscene = Cutscene.cutscenes.get(Cutscene.getName);
            cutscene.update(input);
            for(NPCs n : cutscene.npc.values()) {
            	n.CTick();
                if(n.speaking) {
                    cNpc = n;
                }
            }
            if(cutscene.finished) {
            	cutscene.stopCutscene(move);
            }
        }
		
	}
	
	@Override
	protected void render() {
		if(Thread_Controller.cNpc != null) {
            if(Thread_Controller.cNpc.speaking) {
                UI.TextBox(screen);
                Dialouge.setText(Thread_Controller.cNpc.dialouge, Thread_Controller.cNpc.dialougeLocation);
            }
        }
		if(cutscene != null) {
		for(NPCs n : cutscene.npc.values()) {
            n.render(screen);
        }
		}
	}
	
	
}
