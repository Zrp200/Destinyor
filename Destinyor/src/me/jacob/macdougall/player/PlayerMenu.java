package me.jacob.macdougall.player;

import me.jacob.macdougall.Time;
import me.jacob.macdougall.graphics.UI;
import me.jacob.macdougall.input.Keys;

public class PlayerMenu {
    
    public int item = 0;
    public int player = 0;
    public int spell = 0;
    public int menu = 0;
    
    public PlayerMenu() {
    }
    
    public void switchMenu() {
        if(Time.getKeyTimer(6, false)) {
        if(Keys.MoveLeft()) {
                menu++;
                Time.resetKeyTimer();
        }
        if(Keys.MoveRight()) {
            menu--;
            Time.resetKeyTimer();
        }
        }
        if(menu > 7)
            menu = 1;
        if(menu < 0)
            menu = 7;
        
        switch(menu) {
            default: break;
            case 1: UI.menu = 2; break;
            case 2: UI.menu = 3; break;
        }
    }
}
