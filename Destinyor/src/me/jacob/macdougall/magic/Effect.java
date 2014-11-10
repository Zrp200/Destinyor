package me.jacob.macdougall.magic;

import java.util.HashMap;
import java.util.Map;

public class Effect {
	
    public static Map<Integer, Effect> effects = new HashMap<>();
    
        public String name;
	public String effect;
        public String description;
	public float appliedAttribute;
	
	/**
	 * Creates a new effect
	 * @param name Name of the effect
	 * @param effect attribute to apply damage to
	 * @param damage damage amount or healing amount
	 * @param description description of the effect
	 */
	private Effect(String name, String effect, float damage, String description) {
                this.name = name;
		this.effect = effect;
		this.appliedAttribute = damage;
                this.description = description;
	}
	
	protected Effect(String name, String effect, String description) {
		
	}
        
        public static void setEffect(Effect effect) {
            effects.put(effects.size(), effect);
        }
	
	public static void setEffects(String name, String effect, float appliedAttribute,  String description) {
		Effect e = new Effect(name, effect, appliedAttribute, description);
        setEffect(e);
	}
	
	public static void setEffects() {
		Effect healing = new Effect("Healing", "HP", 50, "Heals the targets HP by 50");
		setEffect(healing);
		Effect damage = new Effect("Healing", "HP", 50, "Heals the targets HP by 50");
		setEffect(damage);
		Effect OHKO = new Effect("One Hit KO", "HP", 1.0f, "Kills the user instantly");
		setEffect(OHKO);
	}
	
	public static Effect getEffect(String name, String effect, float damage, String description) {
		for(Effect e : effects.values()) {
			if(e.name.equals(name) && e.effect.equals(effect) && e.appliedAttribute == damage && e.description.equals(description)) {
				return e;
			}
		}
		Effect eff = new Effect(name, effect, damage, description);
		setEffect(eff);
		return eff;
	}
	
}
