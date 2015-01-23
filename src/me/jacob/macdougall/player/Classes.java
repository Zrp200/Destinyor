package me.jacob.macdougall.player;

import me.jacob.macdougall.enemies.Dummy;

public class Classes extends MasterClass {
	
	String name;
	
	public static Classes Assassin() {
		Classes assassin = new Classes(MasterClass.Class.Rogue, "Assassin", 0.05f, 0.10f, 0.25f, 0.25f, 0.10f, 0.5f, 0.5f);
		return assassin;
	}
	
	public Classes(Class classes) {
		super(classes);
		// TODO Auto-generated constructor stub
	}
	
	public Classes(String name) {
		super(null);
	}
	
	public Classes(Class classes, String name, float hp, float str, float skl, float spd, float luk, float def, float wis) {
		super(classes);
		this.name = name;
		this.hpLevel = hp;
		this.strLevel = str;
		this.sklLevel = skl;
		this.spdLevel = spd;
		this.lukLevel = luk;
		this.defLevel = def;
		this.wisLevel = wis;
	}

	public void levelUp(Dummy dummy) {
		this.levelUpByClass(dummy);
	}
	
}
