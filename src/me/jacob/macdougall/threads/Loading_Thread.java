package me.jacob.macdougall.threads;

import static me.jacob.macdougall.Destinyor.DestinyorEnemies;
import static me.jacob.macdougall.Destinyor.DestinyorNpcs;
import static me.jacob.macdougall.Destinyor.DestinyorSaves;
import static me.jacob.macdougall.Destinyor.DestinyorSpells;
import static me.jacob.macdougall.Destinyor.create;
import static me.jacob.macdougall.Destinyor.read;
import static me.jacob.macdougall.Destinyor.write;
import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.files.FileLoader;
import me.jacob.macdougall.world.LevelMap;

public class Loading_Thread extends Thread_Controller implements Runnable { 
	
    int creator = 0;
    int writer = 0;
    int reader = 0;
    
    boolean setShadows = true;;
    
    //int max = 20;
    
    int fps;
    int ups;
    
    @Override
    public void start() {
        this.Loading = true;
        this.LoadingThread = new Thread(this, "LoadingHandler");
        this.LoadingThread.start();
    }
    
    @Override
    public void resume() {
        doneLoading = false;
        start();
    }
    
    @Override
    public void pause() {
        Loading = false;
        doneLoading = true;
    }
    
    @Override
    public void stop() {
        this.Loading = false;
		try {
			this.LoadingThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    private void reader() {
    	System.out.println("Reading");
        switch(reader) {
            case 0: FileLoader.ReadFromFiles(Destinyor.DestinyorSpells);
            reader++;
            break;
            
            case 1: FileLoader.ReadFromFiles(Destinyor.DestinyorSaves);
            reader++;
            break;
            
            case 2: FileLoader.ReadFromFiles(Destinyor.DestinyorEnemies);
            reader++;
            break;
            
            case 3: FileLoader.ReadFromFiles(Destinyor.DestinyorNpcs);
            reader++;
            break;
                
            
                
            
            
            default: read = false;
                reader = 10;
                break;
        }
    }
    
    private void writer() {
    	System.out.println("Writing");
        switch(writer) {
            case 0: FileLoader.WriteToFiles(DestinyorSaves);
            writer++;
            break;
            
            case 1: FileLoader.WriteToFiles(DestinyorEnemies);
            writer++;
            break;
            
            case 2: FileLoader.WriteToFiles(DestinyorSpells);
            writer++;
            break;
            
            case 3: FileLoader.WriteToFiles(DestinyorNpcs);
            writer++;
            break;
            
            default: write = false;
                writer = 10;
                read = true;
                break;
        }
    }
    
    private void creater() {
    	System.out.println("Creating");
        switch(creator) {
            case 0: FileLoader.CreateFile(Destinyor.DestinyorSaves);
            creator++;
            break;
            
            case 1: FileLoader.CreateFile(Destinyor.DestinyorEnemies);
            creator++;
            break;
            
            case 2: FileLoader.CreateFile(Destinyor.DestinyorSpells);
            creator++;
            break;
            
            case 3: FileLoader.CreateFile(Destinyor.DestinyorNpcs);
            creator++;
            break;
            
            default: create = false;
            creator = 10;
            read = true;
            break;
        }
    }
    
    @Override
    protected void update() {
        if(create)
        creater();
        if(write)
        writer();
        if(read)
        reader();
        
        ups++;
        
//        doneLoading = true;
//        pause();
    }
    
    @Override
    protected void render() {
        fps++;
    }
    
    @Override
    public void run() {
        while(Loading) {
            update();
            render();
            if(!create) {
                if(!write) {
                    if(!read) {
                        creator = 0;
                        writer = 0;
                        reader = 0;
                        updateTiles();
                        if(!setShadows)
                        pause();
                        System.out.printf("\n Loading: %d fps, %d updates", fps, ups);
                    }
                }
            }
        }
        try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			return; // If interrupted continue from the start and check if loading is true else try again
		}
    }
    
    protected void updateTiles() {
//    	for(LevelMap map : LevelMap.Maps.values()) {
//    		System.out.println("Checking shadows");
//    		map.checkShadows();
//    	}
    	setShadows = false;
    }
    
}
