package me.jacob.macdougall.files.mod;

import graphic.engine.screen.Art;

import java.io.File;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.files.DestinyorFiles;
import me.jacob.macdougall.files.FileLoader;

public class FileChecker {
	
	//public static final String home = System.getProperty("user.home");
	//public static final String fileSplit = System.getProperty("file.separator");
	public static final String characters = "Characters.destinyor";
	public static final String enemies = "Enemies.destinyor";
	public static final String spells = "Spells.destinyor";
	public static final String npcs = "Npcs.destinyor";
	public static final String items = "Items.destinyor";
	
	public static final String spriteSheet = "Icon.png";
	public static final String character1Sheet = "Character1.png";
	public static final String character2Sheet = "Character2.png";
	public static final String character3Sheet = "Character3.png";
	public static final String character4Sheet = "Character4.png";
	//public static final String save = "Characters.destinyor";
//    public static final String DestinyorFolder = home + fileSplit + "Documents" + fileSplit + title;
//    public static final String DestinyorSaves = DestinyorFolder + fileSplit + "Save" + ".destinyor";
//    public static final String DestinyorEnemies = DestinyorFolder + fileSplit + "Enemies" + ".destinyor";
//    public static final String DestinyorSettings = DestinyorFolder + fileSplit + "settings" + ".ini";
//    public static final String DestinyorSpells = DestinyorFolder + fileSplit + "Spells" + ".destinyor";
//    public static final String DestinyorNpcs = DestinyorFolder + fileSplit + "Npcs" + ".destinyor";
//    public static final String DestinyorItems = DestinyorFolder + fileSplit + "Items" + ".destinyor";
//    public static final String DestinyorDialougesFolder = DestinyorFolder + fileSplit + "Dialouges";
//    public static final String DestinyorCutsceneFolder = DestinyorFolder + fileSplit + "Cutscenes";
    
    public static String[] fileNames = {
    	characters, enemies, spells, npcs, items
    };
    
    public static String[] pictureNames = {
    	spriteSheet, character1Sheet, character2Sheet, character3Sheet, character4Sheet
    };
	
	public FileChecker() {
		File file = FileLoader.CreateFolderAndReturn(DestinyorFiles.DestinyorModFolder);
		//File file = new File(DestinyorFiles.DestinyorModFolder);
		//if(!file.exists()) {
			//FileLoader.CreateFolder(DestinyorFiles.DestinyorModFolder);
		//}
		if(file.isDirectory()) {
			 for(File files : file.listFiles()) {
				 //System.out.println(files.toString());
				 System.out.println(files.getPath());
				 for(int i = 0; i < fileNames.length; i++) {
					 
				 if(files.getPath().contains(fileNames[i])) {
					 DestinyorFiles.setFile(fileNames[i], files.getPath());
				 }
				 }
				
				 //} else {
					// 
					 //System.out.println(".png");
					 //if(!files.getPath().contains("png")) {
					 for(int i = 0; i < pictureNames.length; i++) {
						 if(files.getPath().contains(pictureNames[i])) {
							 DestinyorFiles.setPictures(pictureNames[i]);
							 //Art.setSpritesheet(res, w, h, game)
						 }
					 }
				 //}
			 }
		}
	}
	
	
}
