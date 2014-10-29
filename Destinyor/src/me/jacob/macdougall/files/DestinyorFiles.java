package me.jacob.macdougall.files;

import java.util.Locale;

import me.jacob.macdougall.Destinyor;

public class DestinyorFiles {
	
	// Contains every single file to be used by the game
	
	public static final String home = System.getProperty("user.home");
	public static final String fileSplit = System.getProperty("file.separator");
	//public static final String locale = System.getProperty("user.language") + "_" + System.getProperty("user.country");
	public static final Locale LOCALE = Locale.getDefault();
    public static final String DestinyorFolder = home + fileSplit + "Documents" + fileSplit + Destinyor.title;
    
    public static String DestinyorCharacters = DestinyorFolder + fileSplit + "Save" + ".destinyor";
    public static String DestinyorEnemies = DestinyorFolder + fileSplit + "Enemies" + ".destinyor";
    public static String DestinyorSettings = DestinyorFolder + fileSplit + "settings" + ".ini";
    public static String DestinyorSpells = DestinyorFolder + fileSplit + "Spells" + ".destinyor";
    public static String DestinyorNpcs = DestinyorFolder + fileSplit + "Npcs" + ".destinyor";
    public static String DestinyorItems = DestinyorFolder + fileSplit + "Items" + ".destinyor";
    public static String DestinyorDialougesFolder = DestinyorFolder + fileSplit + "Dialouges";
    public static String DestinyorCutsceneFolder = DestinyorFolder + fileSplit + "Cutscenes";
    public static String DestinyorModFolder = DestinyorFolder + fileSplit + "mod";
    
    public static String DestinyorSpriteSheet = "/icon0.png";
    public static String DestinyorCharacter1Sheet = "/Character1.png";
    public static String DestinyorCharacter2Sheet = "/Character2.png";
    public static String DestinyorCharacter3Sheet = "/Character3.png";
    public static String DestinyorCharacter4Sheet = "/Character4.png";
    public static String DestinyorEnemiesSheet = "/Enemies.png";
    public static String DestinyorMap = "/map.png";
    public static String DestinyorFont = "/8font.png";
    
    
    public static void setFile(String file, String url) {
    	if(DestinyorCharacters.contains(file) || file.contains("Characters.destinyor")) {
    		DestinyorCharacters = url;
    	}
    	else
    	if(DestinyorEnemies.contains(file)) {
        	DestinyorEnemies = url;
        }
    	else
    	if(DestinyorSpells.contains(file)) {
    		DestinyorSpells = url;
    	}
    	else
    	if(DestinyorNpcs.contains(file)) {
    		DestinyorNpcs = url;
    	}
    	else
    	if(DestinyorItems.contains(file)) {
    		DestinyorItems = url;
    	}
    		
    }
    
    public static void setPictures(String file) {
    	if(DestinyorSpriteSheet.contains(file)) {
    		DestinyorSpriteSheet = fileSplit + file;
    		DestinyorSpriteSheet = DestinyorModFolder + DestinyorSpriteSheet;
    	}
    	if(DestinyorCharacter1Sheet.contains(file)) {
    		DestinyorCharacter1Sheet = fileSplit + file;
    		DestinyorCharacter1Sheet = DestinyorModFolder + DestinyorCharacter1Sheet;
    	}
    	if(DestinyorCharacter2Sheet.contains(file)) {
    		DestinyorCharacter2Sheet = fileSplit + file;
    		DestinyorCharacter2Sheet = DestinyorModFolder + DestinyorCharacter2Sheet;
    	}
    	if(DestinyorCharacter3Sheet.contains(file)) {
    		DestinyorCharacter3Sheet = fileSplit + file;
    		DestinyorCharacter3Sheet = DestinyorModFolder + DestinyorCharacter3Sheet;
    	}
    	if(DestinyorCharacter4Sheet.contains(file)) {
    		DestinyorCharacter4Sheet = fileSplit + file;
    		DestinyorCharacter4Sheet = DestinyorModFolder + DestinyorCharacter4Sheet;
    	}
    	if(DestinyorEnemiesSheet.contains(file)) {
    		DestinyorEnemiesSheet = fileSplit + file;
    		DestinyorEnemiesSheet = DestinyorModFolder + DestinyorEnemiesSheet;
    	}
    	if(DestinyorMap.contains(file)) {
    		DestinyorMap = fileSplit + file;
    		DestinyorMap = DestinyorModFolder + DestinyorMap;
    	}
    	if(DestinyorFont.contains(file)) {
    		DestinyorFont = fileSplit + file;
    		DestinyorFont = DestinyorModFolder + DestinyorFont;
    	}
    }
	
	
}
