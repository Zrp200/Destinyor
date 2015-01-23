package me.jacob.macdougall.threads;

import static me.jacob.macdougall.Destinyor.create;
import static me.jacob.macdougall.Destinyor.read;
import static me.jacob.macdougall.Destinyor.write;
import me.jacob.macdougall.files.Default;
import me.jacob.macdougall.files.DestinyorFiles;
import me.jacob.macdougall.files.FileLoader;
import me.jacob.macdougall.files.Reader;
import me.jacob.macdougall.files.Writer;

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
        switch(reader) {
        	
        	case 0: Reader.readEntities(DestinyorFiles.DestinyorEntities);
        	reader++;
        	break;
        	
            case 1: FileLoader.ReadFromFiles(DestinyorFiles.DestinyorSpells);
            reader++;
            break;
            
            case 2: Reader.ReadItems(DestinyorFiles.DestinyorItems);
            reader++;
            break;
            
            case 3: FileLoader.ReadFromFiles(DestinyorFiles.DestinyorCharacters);
            reader++;
            break;
            
            case 4: FileLoader.ReadFromFiles(DestinyorFiles.DestinyorEnemies);
            reader++;
            break;
            
            case 5: FileLoader.ReadFromFiles(DestinyorFiles.DestinyorNpcs);
            reader++;
            break;
                
            
                
            
            
            default: read = false;
                reader = 10;
                break;
        }
    }
    
    private void writer() {
        switch(writer) {
        	
        	case 0: Writer.WriteDefault(DestinyorFiles.DestinyorEntities, "Entities", Default.getEntities(), Default.getEntitiesFormat());
        	writer++;
        	break;
        	
        	case 1: FileLoader.WriteToFiles(DestinyorFiles.DestinyorSpells);
            writer++;
            break;
        	
        	case 2: Writer.WriteDefault(DestinyorFiles.DestinyorItems, "Items", Default.getItems(), Default.getItemsFormat());
        	writer++;
        	break;
            
            case 3: FileLoader.WriteToFiles(DestinyorFiles.DestinyorCharacters);
            writer++;
            break;
            
            case 4: Writer.WriteDefault(DestinyorFiles.DestinyorEnemies, "Enemies", Default.getEnemies(), Default.getEnemiesFormat());
            writer++;
            break;
            
            case 5: FileLoader.WriteToFiles(DestinyorFiles.DestinyorNpcs);
            writer++;
            break;
            
            default: write = false;
                writer = 10;
                read = true;
                break;
        }
    }
    
    private void creater() {
        switch(creator) {
        	
        	case 0: FileLoader.CreateDefaultFile(DestinyorFiles.DestinyorEntities, "Entities", Default.getEntities(), Default.getEntitiesFormat());
        	creator++;
        	break;
        	
        	case 1: FileLoader.CreateFile(DestinyorFiles.DestinyorSpells);
            creator++;
            break;
            
        	case 2: FileLoader.CreateDefaultFile(DestinyorFiles.DestinyorItems, "Items", Default.getItems(), Default.getItemsFormat());
        	creator++;
        	break;
        	
            case 3: FileLoader.CreateFile(DestinyorFiles.DestinyorCharacters);
            creator++;
            break;
            
            case 4: FileLoader.CreateDefaultFile(DestinyorFiles.DestinyorEnemies, "Enemies", Default.getEnemies(), Default.getEnemiesFormat());
            creator++;
            break;
            
            case 5: FileLoader.CreateFile(DestinyorFiles.DestinyorNpcs);
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
    public synchronized void run() {
        while(Loading) {
            update();
            render();
            if(!create) {
                if(!write) {
                    if(!read) {
                        creator = 0;
                        writer = 0;
                        reader = 0;
                        pause();
                        //System.out.printf("\n Loading: %d fps, %d updates", fps, ups);
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
    
}
