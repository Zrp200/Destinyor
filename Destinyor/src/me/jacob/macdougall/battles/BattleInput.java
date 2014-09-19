package me.jacob.macdougall.battles;

import java.util.HashMap;
import java.util.Map;

import input.engine.keyboard.InputHandler;
import input.engine.mouse.Mouse;
import me.jacob.macdougall.Keys;
import me.jacob.macdougall.graphics.Commands;
import me.jacob.macdougall.npcs.Enemy;
import me.jacob.macdougall.player.Player;

@SuppressWarnings("unused")
public class BattleInput {
	
	//private InputHandler input;
	public Pointer point;
	private final Battles battles;
	
	public Map<Integer, Commands> commands = new HashMap<>();
	
	public BattleInput(InputHandler input, Pointer point, Battles battles) {
		//this.input = input;
		this.point = point;
		this.battles = battles;
		
		commands.put(0, new Commands("Attack", 190, 305, (7 * "Attack".length()), 8));
		commands.put(1, new Commands("Spells", 190, 325, 8, 8));
		commands.put(2, new Commands("Enemy1", 32, 32, 32, 32));
		commands.put(3, new Commands("Enemy2", 32, 88, 32, 32));
		commands.put(4, new Commands("Enemy3", 32, 140, 32, 32));
		commands.put(5, new Commands("Enemy4", 32, 192, 32, 32));
		commands.put(6, new Commands("Enemy5", 32, 244, 32, 32));
		//this.point = point;
	}
	
	public void clicker(Mouse mouse, Enemy[] entities, InputHandler input) {
		for(int i = 0; i < commands.size(); i++) {
			if(commands.get(i).inBox(mouse.pressedX, mouse.pressedY)) {
				switch(i) {
				case 0: battles.Attack(Player.getPlayers(), entities, input); break;
				case 1: break;
				}
			}
		}
	}
	
	public boolean up() {
		return Keys.MoveUp();
	}
	
	public boolean down() {
		return Keys.MoveDown();
	}
	
	public boolean left() {
		return Keys.MoveLeft();
	}
	
	public boolean right() {
		return Keys.MoveRight();
	}
	
	public boolean enter() {
		return Keys.Enter();
	}
	
	public void assingTarget(Mouse mouse, Enemy[] entities) {
		//for(int i = 0; i < entities.length; i++) {
                for(Enemy enemy : entities) {
			if(entities.length > 0) {
				if(Keys.Enemy1() || commands.get(2).inBox(mouse.pressedX, mouse.pressedY)) {
					System.out.println("Targeting");
					enemy.Focused = false;
					entities[0].Focused = true;
				}
			}
			
			if(entities.length > 1) {
				if(Keys.Enemy2() || commands.get(3).inBox(mouse.pressedX, mouse.pressedY)) {
					System.out.println("Targeting");
					enemy.Focused = false;
					entities[1].Focused = true;
				}
			}
		
			if(entities.length > 2) {
				if(Keys.Enemy3() || commands.get(4).inBox(mouse.pressedX, mouse.pressedY)) {
					System.out.println("Targeting");
					enemy.Focused = false;
					entities[2].Focused = true;
				}
			}
		
			if(entities.length > 3) {
				if(Keys.Enemy4() || commands.get(5).inBox(mouse.pressedX, mouse.pressedY)) {
					System.out.println("Targeting");
					enemy.Focused = false;
					entities[3].Focused = true;
				}
			}
		
			if(entities.length > 4) {
				if(Keys.Enemy5() || commands.get(6).inBox(mouse.pressedX, mouse.pressedY)) {
					System.out.println("Targeting");
					enemy.Focused = false;
					entities[4].Focused = true;
				}
			}
		}
	}
	
	public void Combat(Player[] players, Enemy[] enemies, InputHandler input) {
        boolean[] canAttack = { false, false, false, false };
        int pl = 0;
        for(int p = 0; p < players.length; p++) {
            if(players[p].TA >= 500) {
                canAttack[p] = true;
                pl = p;
            }
        }
        if(canAttack[pl]) {
        	//battles.
        	point.point(players, input, enemies);
        }

    }
	
	
}
