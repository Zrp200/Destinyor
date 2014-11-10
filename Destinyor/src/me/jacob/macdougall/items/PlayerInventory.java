package me.jacob.macdougall.items;

import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;

import me.jacob.macdougall.Time;
import me.jacob.macdougall.graphics.UI;
import me.jacob.macdougall.input.Keys;
import me.jacob.macdougall.player.Player;

public class PlayerInventory {

	/**
	 * Class that handles player inventory
	 */
	public PlayerInventory() {

	}

	int currentItem = 0;

	public void renderInventory(Screen screen) {
		UI.renderInventory(screen);

		if(!Player.inventory.isEmpty()) {

			Equipment item = (Equipment) Player.inventory.get(currentItem);
			int x = 12;
			int y;

			GameFont.render("Name: " + item.name, screen, 12, 288);
			GameFont.render("Equipped: " + item.equipped, screen, 12, 288 + 16);
			GameFont.render("Attack: " + item.damage, screen, 12, 288 + 32);
			GameFont.render("Price: " + item.cost, screen, 12, 288 + 48);
			GameFont.render("Element: " + item.element.getElement(), screen, 12, 288 + 64);
			GameFont.render("Equipped to: " + item.limb.getName(), screen, 12, 288 - 16);

			for(int i = 0; i < Player.inventory.size(); i++) {
				y = 12 * (i + 1);

				if(Player.inventory.get(i).equippable) {
					Equipment equipment = (Equipment) Player.inventory.get(i);

					if(equipment != null) {
						equipment.render(screen, x, y);

						if(i == 11) {
							x += 128;
						}
					}
				}
			}
		}
	}

	public void updateInventory() {
		if(UI.menu == UI.Inventory) {
			if(Time.getKeyTimer(10, false)) {
				if(Keys.MoveUp()) {
					currentItem--;
					Time.resetKeyTimer();
				}
				if(Keys.MoveDown()) {
					currentItem++;
					Time.resetKeyTimer();
				}
			}
		}

		if(currentItem >= Player.inventory.size()) {
			currentItem = 0;
		}

		if(currentItem < 0) {
			currentItem = Player.inventory.size() - 1;
		}
	}

}
