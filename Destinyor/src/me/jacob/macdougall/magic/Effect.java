package me.jacob.macdougall.magic;

import java.util.HashMap;
import java.util.Map;

public class Effect {
	
    public static Map<Integer, Effect> effects = new HashMap<>();
    
        public String name;
	public String effect;
        public String description;
	public int appliedAttribute;
	
	private Effect(String name, String effect, int appliedAttribute, String description) {
                this.name = name;
		this.effect = effect;
		this.appliedAttribute = appliedAttribute;
                this.description = description;
	}
        
        private void setEffect(Effect effect) {
            effects.put(effects.size(), effect);
        }
	
	public static void setEffects(String name, String effect, int appliedAttribute,  String description) {
		Effect Effect = new Effect(name, effect, appliedAttribute, description);
                Effect.setEffect(Effect);
	}
	
	
}
