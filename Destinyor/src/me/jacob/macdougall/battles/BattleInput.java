package me.jacob.macdougall.battles;

import java.util.HashMap;
import java.util.Map;

import input.engine.mouse.Mouse;
import me.jacob.macdougall.enemies.Enemy;
import me.jacob.macdougall.gui.Commands;
import me.jacob.macdougall.input.Keys;
import me.jacob.macdougall.player.Player;

public class BattleInput {

	public Pointer point;

	public static boolean targetSelected = false;

	public Map<Integer, Commands> commands = new HashMap<>();

	public BattleInput(Pointer point) {
		this.point = point;

		commands.put(0, new Commands("Attack", 190, 305, (7 * "Attack".length()), 8));
		commands.put(1, new Commands("Spells", 190, 325, 8, 8));
		commands.put(2, new Commands("Enemy1", 32, 32, 32, 32));
		commands.put(3, new Commands("Enemy2", 32, 88, 32, 32));
		commands.put(4, new Commands("Enemy3", 32, 140, 32, 32));
		commands.put(5, new Commands("Enemy4", 32, 192, 32, 32));
		commands.put(6, new Commands("Enemy5", 32, 244, 32, 32));
	}

	public void clicker(Mouse mouse, Enemy[] entities) {
		for(int i = 0; i < commands.size(); i++) {
			if(commands.get(i).inBox(mouse.pressedX, mouse.pressedY)) {
				switch (i) {
					case 0:
						if(Player.getPlayerAttack() != null)
							Player.getPlayerAttack().attack(Player.getPlayerAttack().getTarget());
						break;
					case 1:
						break;
				}
			}
		}
	}

	public void assingTarget(Mouse mouse, Enemy[] enemies) {

		if(enemies != null) {
			Player player = Player.getNextAttackPlayer();

			if(player.getTarget() != null && !player.getTarget().alive()) {
				for(Enemy enemy : enemies) {
					if(player.getTarget() == null) {
						player.setTarget(enemy);
						break;
					} else {
						if(enemy.alive())
							player.setTarget(enemy);
					}
				}
			}

			if(enemies.length > 0) {
				if(enemies[0].alive()) {
					if(Keys.Enemy1() || commands.get(2).inBox(mouse.pressedX, mouse.pressedY) || point.getEnemy(enemies) == 0) {
						targetSelected = true;
						Player.getNextAttackPlayer().setTarget(enemies[0]);
						System.out.println("Targeting 1");
						Pointer.ep = false;
					}
				}
			}

			if(enemies.length > 1) {
				if(enemies[1].alive()) {
					if(Keys.Enemy2() || commands.get(3).inBox(mouse.pressedX, mouse.pressedY) || point.getEnemy(enemies) == 1) {
						targetSelected = true;
						Player.getNextAttackPlayer().setTarget(enemies[1]);
						System.out.println("Targeting 2");
						Pointer.ep = false;
					}
				}
			}

			if(enemies.length > 2) {
				if(enemies[2].alive()) {
					if(Keys.Enemy3() || commands.get(4).inBox(mouse.pressedX, mouse.pressedY) || point.getEnemy(enemies) == 2) {
						targetSelected = true;
						Player.getNextAttackPlayer().setTarget(enemies[2]);
						System.out.println("Targeting 3");
						Pointer.ep = false;
					}
				}
			}

			if(enemies.length > 3) {
				if(enemies[3].alive()) {
					if(Keys.Enemy4() || commands.get(5).inBox(mouse.pressedX, mouse.pressedY) || point.getEnemy(enemies) == 3) {
						targetSelected = true;
						Player.getNextAttackPlayer().setTarget(enemies[3]);
						System.out.println("Targeting 4");
						Pointer.ep = false;
					}
				}
			}

			if(enemies.length > 4) {
				if(enemies[4].alive()) {
					if(Keys.Enemy5() || commands.get(6).inBox(mouse.pressedX, mouse.pressedY) || point.getEnemy(enemies) == 4) {
						targetSelected = true;
						Player.getNextAttackPlayer().setTarget(enemies[4]);
						System.out.println("Targeting 5");
						Pointer.ep = false;
					}
				}
			}
		}

		mouse.pressedX = 0;
		mouse.pressedY = 0;
	}

}
