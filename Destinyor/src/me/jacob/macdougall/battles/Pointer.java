package me.jacob.macdougall.battles;

import graphic.engine.screen.Art;
import graphic.engine.screen.Screen;
import input.engine.keyboard.InputHandler;
import me.jacob.macdougall.Time;
import me.jacob.macdougall.npcs.Enemy;
import me.jacob.macdougall.player.Player;

public class Pointer {
	
	public int spell = 1;
    // Main = 0, Spells = 1, Items = 2
    public int menu = 0;
    public int commands = 0;
    
    public static final int commandsMax = 1;
    public static final int menuMax = 1;
    public static final int spellMax = 1; // Assing Somewhere
    
    public int PointerX = 178;
    public int PointerY = 305;
    
    public int ePointerX = 20;
    public int ePointerY = 38;
    
    public boolean ep = false;
    public boolean p = false;
    
    private final BattleInput bInput;
    private final Battles battle;
	
    public Pointer(BattleInput input, Battles battle) {
    	this.bInput = input;
    	this.battle = battle;
    }
    
    public void reset() {
    	PointerX = 178;
    	PointerY = 305;
    }
    
    //public void enemyPoint(Enemy[] entities) {
//    	for(Enemy enemy : entites) {
//    		if(enemy.Focused = true) {
//    			
//    		}
//    	}
    //}
    
    public void render(Screen screen) {
    	if(ep)
    	screen.render(Art.getFont()[35][1], ePointerX, ePointerY);
    	if(p)
    	screen.render(Art.getFont()[35][1], PointerX, PointerY);
    }
    
    public void point(Player player, InputHandler input, Enemy[] enemies) {
        switch(menu) {
        case 0:
            if(Time.getKeyTimer(10, false)) {
                if(bInput.up()) {
                    if(commands > 0 && commands < commandsMax) {
                    	commands--;
                    	PointerY -= 10;
                    } else {
                    	commands = 0;
                    	PointerX = 178;
                    	PointerY = 305;
                    }
                    Time.resetKeyTimer();
                }
                
            if(bInput.down()) {
                if(commands >= 0 && commands < commandsMax) {
                	commands++;
                	PointerY += 20;
                } else {
                	commands = 0;
                	PointerX = 178;
                	PointerY = 305;
                }
                Time.resetKeyTimer();
            }
        if(bInput.right()) {
            if(commands == 1) {
            BattleRender.DrawSpells = true;
            menu = 1;
            Time.resetKeyTimer();
            }
            }
        }
            switch(commands) {
                case 0: if(bInput.enter() && Time.getKeyTimer(10, false)) {
                    battle.Attack(player, enemies, input);
                    Time.resetKeyTimer();
                }
                    break;
                case 1: if(bInput.enter() && Time.getKeyTimer(10, false)) {
                    menu = 1;
                    BattleRender.DrawSpells = true;
                    Time.resetKeyTimer();
                }     
            }
            break;
            
        case 1: 
            if(Time.getKeyTimer(10, false)) {
            //for(int i = 0; i < Player.AmountOFPlayers(); i++) {
            if(bInput.up()) {
                if(spell >= 1 && spell <= player.spells.size()) {
                    spell--;
                } else {
                    spell = 1;
                }
                Time.resetKeyTimer();
            }
            if(bInput.down()) {
                //if(spell >= 1 && spell <= players[i].spells.size()) {
            	if(spell >= 1 && spell <= player.spells.size()) {
                    spell++;
                } else {
                 spell = 1;
                }
                Time.resetKeyTimer();
            }
            if(bInput.left()) {
            	BattleRender.DrawSpells = false;
                menu = 0;
                Time.resetKeyTimer();
            }
            if(bInput.enter()) {
            	//for(int i = 0;)
                battle.spellHandler(player, input, enemies);
                menu = 0;
                spell = 1;
                commands = 0;
                return;
            }
        //}
            }
            break;
    }
    }
    
    public void pointEnemy() {
    	if(!bInput.targetSelected) {
    		
    	}
    }
}
