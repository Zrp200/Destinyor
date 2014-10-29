package me.jacob.macdougall.npcs.body;

import graphic.engine.screen.Bitmap;
import graphic.engine.screen.Screen;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import me.jacob.macdougall.files.DestinyorFiles;

public class Limbs extends Limb {
	
	public static int[] multiLimbs;
	
	public Limbs(String name, boolean renderable) {
		super(name, renderable);
	}
	
	public static void setLimbs(Limb[] limbs) {
		for(int i = 0; i < limbs.length; i++) {
			if(limbs[i].getName().contains("Right") || limbs[i].getName().contains("Left"))
			//System.out.println(limbs[i].name);
				continue;
			else
				return;
		}
		String name1 = "";
		String name2 = "";
		String[] names1;
		String[] names2;
		String toLower1;
		String toLower2;
		Limbs limb = null;
		for(int i = 0; i < limbs.length; i++) {
			for(int j = 0; j < limbs.length; j++) {
				name1 = limbs[i].getName();
				name2 = limbs[j].getName();
				names1 = null;
				names2 = null;
				if(name1.contains(" ") && name2.contains(" ")) {
				names1 = name1.split(" ");
				names2 = name2.split(" ");
				for(int k = 0; k < names1.length; k++) {
					for(int l = 0; l < names2.length; l++) {
						toLower1 = names1[k].toLowerCase();
						toLower2 = names2[l].toLowerCase();
						if(names1[k].contains(names2[l]) && !toLower1.contains("right") && !toLower1.contains("left") && !names1[k].endsWith("s")) {
							if(Limb.getLimb(names1[k] + "s") == null)
							limb = new Limbs(names1[k] + "s", limbs[i].renderable);
						} else if(names2[l].contains(names1[k]) && !toLower2.contains("right") && !toLower2.contains("left") && !names2[l].endsWith("s")) {
							if(Limb.getLimb(names2[l] + "s") == null)
							limb = new Limbs(names2[l] + "s", limbs[j].renderable);
						}
					}
				}
			}
		}
	}
}
}
