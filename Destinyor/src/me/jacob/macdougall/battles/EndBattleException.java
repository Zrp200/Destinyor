package me.jacob.macdougall.battles;

import me.jacob.macdougall.graphics.UI;
import me.jacob.macdougall.player.Player;

public class EndBattleException extends Exception {
	
	private static final long serialVersionUID = -2927013274180124529L;
	
	public EndBattleException() {
	}
	
	public void EndBattle() {
		Battles.endBattle = true;
		UI.menu = UI.Map;
		Player.Gold -= (int) (Player.Gold * 0.02f);
	}
	

}
