package me.jacob.macdougall.magic;

import java.util.HashMap;
import java.util.Map;

public class Element {
	
	
	private static Map<String, Element> elements = new HashMap<>();
	
	public static final String Physical = "Physical", Fire = "Fire", Water = "Water", Ice = "Ice";
        public static final String Lightning = "Lightning", Poison = "Poison", Dark = "Dark", Light = "Light";
    public static final String Psychic = "Psychic";
    
    private static String[] types = {
	Physical, Fire, Water, Ice, Lightning, Poison, Dark, Light, Psychic
    };
	
	private String type = "";
    private String weakness = "";
	
	private Element(String type) {
		if(type != null) {
                    if(type.equals(Physical)) this.weakness = Psychic;
                    if(type.equals(Fire)) this.weakness = Water;
                    if(type.equals(Water)) this.weakness = Lightning;
                    if(type.equals(Ice)) this.weakness = Fire;
                    if(type.equals(Lightning)) this.weakness = Poison;
                    if(type.equals(Poison)) this.weakness = Psychic;
                    if(type.equals(Dark)) this.weakness = Light;
                    if(type.equals(Light)) this.weakness = Dark;
                    if(type.equals(Psychic)) this.weakness = Physical;
		this.type = type;
                }
	}
	
	public String getElement() {
		return type;
	}
        
        public static Element getElement(int index) {
            //Element e;
                for(Element e : elements.values()) {
                    if(e.type.equals(types[index]))
                        return e;
                }
                //e = types[i]; 
            return null;
        }
        
        public static String[] getElements() {
            return types;
        }
        
    public String getWeakness() {
    	return weakness;
    }
	
	public static void setElements() {
			for(int i = 0; i < types.length; i++) {
				Element element = new Element(types[i]);
				put(types[i], element);
			}
	}
	
	public static void put(String key, Element value) {
		elements.put(key, value);
	}
	
	public static Element get(String key) {
		return elements.get(key);
	}
	
}
