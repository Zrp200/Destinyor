package me.jacob.macdougall.npcs.body;

import java.util.ArrayList;
import java.util.List;

//import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Entities {
	// Ordinal Number, http://en.wikipedia.org/wiki/Ordinal_number_(linguistics)

	public static Map<String, Entities> entities = new HashMap<>();
	public static List<String> names = new ArrayList<>();

	public List<Limb> limbs = new ArrayList<>();

	public String name;

	/**
	 * Direct call, can only be used internally
	 * @param name Name of the entity type
	 * @param limbAmount How many limbs the entity has
	 * @param limbs The value of the limbs
	 */
	public Entities(String name, Limb... limbs) {
		this.name = name;
		for(int i = 0; i < limbs.length; i++) {
			this.limbs.add(limbs[i]);
		}
	}

	public Entities(String name, String limbNames) {
		this.name = name;

		limbNames = limbNames.trim();
		limbNames = limbNames.replace(", ", ",");
		String[] limbs = limbNames.split(",");

		for(int i = 0; i < limbs.length; i++) {
			this.limbs.add(Limb.getLimb(limbs[i]));
		}
	}

	/**
	 * http://stackoverflow.com/questions/5802118/why-we-use-clone-method-in-java
	 * <br>
	 * Clone constructor
	 * @param entity
	 */
	private Entities(Entities entity) {
		this.limbs = entity.limbs;
		this.name = entity.name;
	}

	public Limb getLimb(String name) {
		for(Limb limb : limbs) {
			if(limb.name.equals(name))
				return limb;
		}
		return null;
	}

	public Limb[] getLimbs() {
		Limb[] limbs = new Limb[this.limbs.size()];

		for(int i = 0; i < limbs.length; i++) {
			limbs[i] = this.limbs.get(i);
		}

		return limbs;
	}

	public static Limb[] getHumaniod() {
		Limb[] limbs = new Limb[9];

		limbs[0] = Limb.getLimb("Head");
		limbs[1] = Limb.getLimb("Neck");
		limbs[2] = Limb.getLimb("Upper Torso");
		limbs[3] = Limb.getLimb("Shoulders");
		limbs[4] = Limb.getLimb("Arms");
		limbs[5] = Limb.getLimb("Lower Torso");
		limbs[6] = Limb.getLimb("Hands");
		limbs[7] = Limb.getLimb("Legs");
		limbs[8] = Limb.getLimb("Feet");

		for(int i = 0; i < limbs.length; i++) {
			limbs[i] = limbs[i].newInstance();
		}

		return limbs;
	}

	public static Entities newInstance(String name) {
		Entities entity = new Entities(entities.get(name));

		return entity;
	}

	public static Entities newInstance(Entities entity) {
		Entities e = new Entities(entity);

		return e;
	}

	public Entities newInstance() {
		Entities entity = new Entities(this);

		return entity;
	}

	public static void put(Entities entity) {
		if(entity != null) {
			entities.put(entity.name, entity);
			names.add(entity.name);
		}
	}
	
	public void put() {
		entities.put(name, this);
		names.add(name);
	}

}
