package me.jacob.macdougall.npcs;

import java.util.Locale;

import me.jacob.macdougall.player.Player;

public class Keywords {
	
	private static final String p1 = "p1", p2 = "p2", p3 = "p3", p4 = "p4";
	private static final String name = ".name", gender = ".gender";
	
	public static final String[] properties = {
		".name"
	};
	
	public static final String[] players = {
		p1, p2, p3, p4
	};
	
	public static Locale getLocale() {
		switch(System.getProperty("user.country")) {
		case "US": return Locale.US;
		case "UK": return Locale.UK;
		case "CA": return Locale.CANADA;
		}
		return null;
	}
	
	public static String setKeyWords(String args) {
		args = args.toLowerCase(Locale.getDefault());
		try {
			for(int i = 0; i < Player.getActualPlayers().length; i++) {
				String names = players[i] + name;
				String genders = players[i] + gender;
				if(args.contains(names)) {
					args = args.replaceAll(names, Player.getActualPlayers()[i].getName());
				}
				if(args.contains(genders)) {
					args = args.replaceAll(genders, Player.getMainCharacter().getGender().equalsIgnoreCase("male") ? "boy" : "girl");
				}
			}
		} catch(NullPointerException | ArrayIndexOutOfBoundsException e) {
			e.addSuppressed(e);
		}
		return args;
	}
	
	
}
