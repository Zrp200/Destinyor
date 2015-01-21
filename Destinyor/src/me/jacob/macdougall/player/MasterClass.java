package me.jacob.macdougall.player;

import me.jacob.macdougall.enemies.Dummy;

// Class for the different classes example are Mage, Fighter, Thief, ect.
public abstract class MasterClass {
	
	public static enum Class {
		Fighter(0), Caster(1), Rogue(2);
		
		private final int masterClass;
		Class(int masterClass) {
			this.masterClass = masterClass;
		}
		
		public int getMasterClass() {
			return masterClass;
		}
	};
	
	public Class masterClass;

    protected float hpLevel;
    protected float strLevel;
    protected float sklLevel;
    protected float spdLevel;
    protected float lukLevel;
    protected float defLevel;
    protected float wisLevel;
    
    public MasterClass(Class masterClass) {
    	switch(masterClass) {
    		case Fighter: break;
    		case Caster: break;
    		case Rogue: break;
    		default: break;
    	}
    	this.masterClass = masterClass;
    }
    
    public MasterClass(Class masterClass, float hp, float str, float skl, float spd, float luk, float def, float wis) {
    	switch(masterClass) {
    		case Fighter: break;
    		case Caster: break;
    		case Rogue: break;
    		default: break;
    	}
    	this.masterClass = masterClass;
    	
    	this.hpLevel = hp;
    	this.strLevel = str;
    	this.sklLevel = skl;
    	this.spdLevel = spd;
    	this.lukLevel = luk;
    	this.defLevel = def;
    	this.wisLevel = wis;
    }
    
    /**
     * Classes will call this with a public version
     * @param dummy
     */
    protected void levelUpByClass(Dummy dummy) {
    	// If the outcome is >= 5 round up, else round down
    	// I decided that set rounding is just a bit to Underpowered or Overpowered
    	
    	//dummy.setStat(Dummy.HEALTH_POINTS, dummy.getMaxHp()); // Would return them to full hp
    	dummy.setStat(Dummy.HEALTH_POINTS, Math.round(dummy.getMaxStat(Dummy.HEALTH_POINTS) / hpLevel));
    }
}
