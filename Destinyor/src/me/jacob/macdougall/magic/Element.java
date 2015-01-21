package me.jacob.macdougall.magic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Element {

	private static final Map<String, Element> elements = new HashMap<>();

	public static final String PHYSICAL = "Physical", FIRE = "Fire",
			WATER = "Water", EARTH = "Earth";
	public static final String AIR = "Air", LIGHTNING = "Lightning",
			LIGHT = "Light", DARK = "Dark";
	
	private String type = "";
	private String weakness = "";

	private Element firstElement;
	private Element secondElement;

	private Element(String type) {
		switch(type) {
			case EARTH: this.weakness = AIR; break; // Earth can't touch air
			case FIRE: this.weakness = WATER; break; // Fire can't burn water
			case AIR: this.weakness = FIRE; break; // Air amplifies fire
			case WATER: this.weakness = LIGHTNING; break; // Water amplifies lightning
			case LIGHTNING: this.weakness = EARTH; break; // Earth absorbs lightning
			case LIGHT: this.weakness = DARK; break;
			case DARK: this.weakness = LIGHT; break;
		}
		this.type = type;
	}

	public Element(String type, Element firstElement, Element secondElement, Element weakness) {
		this.type = type;
		this.firstElement = firstElement;
		this.secondElement = secondElement;
		this.weakness = weakness.type;
	}

	public String getElement() {
		return type;
	}
	
	public static Collection<Element> getElements() {
		return elements.values();
	}

	public String getWeakness() {
		return weakness;
	}
	
	public static void setElements() {
		put(PHYSICAL);
		put(EARTH);
		put(FIRE);
		put(AIR);
		put(WATER);
		put(LIGHTNING);
		put(LIGHT);
		put(DARK);
	}

	public static void put(String key, Element value) {
		elements.put(key, value);
	}
	
	public static void put(Element value) {
		elements.put(value.getElement(), value);
	}
	
	public static void put(String element) {
		elements.put(element, new Element(element));
	}

	public static Element get(String key) {
		return elements.get(key);
	}

	public Element getFirstElement() throws NullPointerException {
		return firstElement;
	}

	public Element getSecondElement() throws NullPointerException { // Just here so I remember that this can be null
		return secondElement;
	}

}
