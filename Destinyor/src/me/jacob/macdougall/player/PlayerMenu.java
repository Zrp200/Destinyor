package me.jacob.macdougall.player;


import input.engine.keyboard.InputHandler;
import me.jacob.macdougall.Keys;
import me.jacob.macdougall.Time;
import me.jacob.macdougall.graphics.UI;

public class PlayerMenu {
    
    public int item = 0;
    public int player = 0;
    public int spell = 0;
    public int menu = 0;
    
    private InputHandler input;
    
    public PlayerMenu(InputHandler input) {
        this.input = input;
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
