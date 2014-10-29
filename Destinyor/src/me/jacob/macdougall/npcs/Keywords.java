package me.jacob.macdougall.npcs;

import java.util.Locale;

import me.jacob.macdougall.player.Player;

public class Keywords {
	
	private static final String name = ".name", gender = ".gender";
	
	public static final String[] properties = {
		".name"
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
		if(args.contains("p1" + name)) {
			args.replace("p1" + name, Player.getPlayers()[0].getName());
			//return args;
		}
		if(args.contains("p2" + name)) {
			args.replace("p2" + name, Player.getPlayers()[1].getName());
			//return args;
		}
		if(args.contains("p3" + name)) {
			args.replace("p3" + name, Player.getPlayers()[2].getName());
			//return args;
		}
		if(args.contains("p4" + name)) {
			args.replace("p4" + name, Player.getPlayers()[3].getName());
			//return args;
		}
		if(args.contains("p1" + gender)) {
			args.replace("p1" + gender, Player.getPlayers()[0].getGender().equalsIgnoreCase("male") ? "boy" : "girl");
			//return args;
		}
		if(args.contains("p2" + gender)) {
			args.replace("p2" + gender, Player.getPlayers()[1].getGender().equalsIgnoreCase("male") ? "boy" : "girl");
			//return args;
		}
		if(args.contains("p3" + gender)) {
			args.replace("p3" + gender, Player.getPlayers()[2].getGender().equalsIgnoreCase("male") ? "boy" : "girl");
			//return args;
		}
		if(args.contains("p4" + gender)) {
			args.replace("p4" + gender, Player.getPlayers()[3].getGender().equalsIgnoreCase("male") ? "boy" : "girl");
			//return args;
		}
		} catch(NullPointerException | ArrayIndexOutOfBoundsException e) {
			//return args;
			e.addSuppressed(e);
		}
		return args;
	}
	
//	public void setDialougeWithCharacter(String dialouge) {
//		if(Player.getPlayers()[0] != null) {
//			for(int i = 0; i < dialouge.length; i++) {
//				if(dialouge[i].contains("p1.name")) {
//					dialouge[i] = dialougePerm[i].replace("p1.name", Player.getPlayers()[0].Name);
//				}
//				if(dialouge[i].contains("p1.gender")) {
//					// if there are no actual players this will throw an exception and crash the game, which is kinda the point
//					String gender = "";
//					try {
//					gender = (Player.getActualPlayers()[0].Gender.equalsIgnoreCase("Male") ? "boy" : "girl");
//					} catch(NullPointerException e) {
//						e.printStackTrace();
//						System.exit(-1);
//					}
//					dialouge[i] = dialougePerm[i].replace("p1.gender", gender);
//				}
//			}
//		} else {
//			for(int i = 0; i < dialouge.length; i++) {
//				if(dialouge[i].contains("p1.name")) {
//					dialouge[i] = dialougePerm[i].replace("p1.name", "Player1 not Initialized what is going on!");
//				}
//				if(dialouge[i].contains("p1.gender")) {
//					//String gender = (Player.getActualPlayers()[0].Gender.equalsIgnoreCase("Male") ? "Boy" : "Girl");
//					dialouge[i] = dialougePerm[i].replace("p1.gender", "you");
//				}
//			}
//			
//		}
//			
//		if(Player.getPlayers()[1] != null) {
//			for(int i = 0; i < dialouge.length; i++) {
//				if(dialouge[i].contains("p2.name")) {
//					dialouge[i] = dialougePerm[i].replace("p2.name", Player.getPlayers()[1].Name);
//				}
//				if(dialouge[i].contains("p2.gender")) {
//						if(Player.getActualPlayers() != null) {
//					// if there are no actual players this will throw an exception and crash the game, which is kinda the point
//						String gender = "";
//						gender = (Player.getActualPlayers()[1].Gender.equalsIgnoreCase("Male") ? "boy" : "girl");
//						dialouge[i] = dialougePerm[i].replace("p2.gender", gender);
//					} else {
//						dialouge[i] = dialougePerm[i].replace("p2.gender", "you");
//					}
//				}
//			}
//		} else {
//			for(int i = 0; i < dialouge.length; i++) {
//				if(dialouge[i].contains("p3.name")) {
//					dialouge[i] = dialougePerm[i].replace("p2.name", "Hero");
//				}
//				if(dialouge[i].contains("p2.gender")) {
//					//String gender = (Player.getActualPlayers()[0].Gender.equalsIgnoreCase("Male") ? "Boy" : "Girl");
//					dialouge[i] = dialougePerm[i].replace("p2.gender", "you");
//				}
//			}
//		}
//		
//		if(Player.getPlayers()[2] != null) {
//			for(int i = 0; i < dialouge.length; i++) {
//				if(dialouge[i].contains("p3.name")) {
//					dialouge[i] = dialougePerm[i].replace("p3.name", Player.getPlayers()[2].Name);
//				}
//				if(dialouge[i].contains("p3.gender")) {
//					if(Player.getActualPlayers() != null) {
//				// if there are no actual players this will throw an exception and crash the game, which is kinda the point
//						String gender = "";
//						gender = (Player.getActualPlayers()[2].Gender.equalsIgnoreCase("Male") ? "boy" : "girl");
//						dialouge[i] = dialougePerm[i].replace("p3.gender", gender);
//					} else {
//						dialouge[i] = dialougePerm[i].replace("p3.gender", "you");
//					}
//				}
//			}
//		} else {
//			for(int i = 0; i < dialouge.length; i++) {
//				if(dialouge[i].contains("p3.name")) {
//					dialouge[i] = dialougePerm[i].replace("p3.name", "Hero");
//				}
//				if(dialouge[i].contains("p3.gender")) {
//					//String gender = (Player.getActualPlayers()[0].Gender.equalsIgnoreCase("Male") ? "Boy" : "Girl");
//					dialouge[i] = dialougePerm[i].replace("p3.gender", "you");
//				}
//			}
//		}
//		
//		if(Player.getPlayers()[3] != null) {
//			for(int i = 0; i < dialouge.length; i++) {
//				if(dialouge[i].contains("p4.name")) {
//					dialouge[i] = dialougePerm[i].replace("p4.name", Player.getPlayers()[3].Name);
//				}
//				if(dialouge[i].contains("p4.gender")) {
//					if(Player.getActualPlayers() != null) {
//				// if there are no actual players this will throw an exception and crash the game, which is kinda the point
//						String gender = "";
//						gender = (Player.getActualPlayers()[3].Gender.equalsIgnoreCase("Male") ? "boy" : "girl");
//						dialouge[i] = dialougePerm[i].replace("p4.gender", gender);
//					} else {
//						dialouge[i] = dialougePerm[i].replace("p4.gender", "you");
//					}
//				}
//			}
//		} else {
//			for(int i = 0; i < dialouge.length; i++) {
//				if(dialouge[i].contains("p4.name")) {
//					dialouge[i] = dialougePerm[i].replace("p4.name", "Hero");
//				}
//				if(dialouge[i].contains("p4.gender")) {
//					//String gender = (Player.getActualPlayers()[0].Gender.equalsIgnoreCase("Male") ? "Boy" : "Girl");
//					dialouge[i] = dialougePerm[i].replace("p4.gender", "you");
//				}
//			}
//		}
//	}
	
	
}
