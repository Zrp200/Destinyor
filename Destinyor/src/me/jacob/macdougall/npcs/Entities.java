package me.jacob.macdougall.npcs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Entities {
	// Ordinal Number, http://en.wikipedia.org/wiki/Ordinal_number_(linguistics)
	
	//public static Map<String, Entities> entities = new HashMap<>();
	//public static Map<Integer, String> names = new HashMap<>();
	
	private static String[] ON = {
		"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth", "Eleventh", "Twelfth",
		"Thirteenth", "Fourtheenth", "Fifthteenth", "Sixthteenth", "Seventhteen", "Eighteenth", "Ninthteen", "Twentieth"
	};
	// if there are more than twenty of one limb, either something went wrong, or someone really likes that limb.
	
//	public static final int 	
//			Left_Wing = 14,    Head = 0, Right_Wing = 15, // A minority of creatures will have wings so that's why it's higher
//			Left_Shoulder = 1, Body = 2, Right_Shoulder = 3,
//				Left_Arm = 4, Waist = 5, Right_Arm = 6,
//				Left_Hand = 7, Belt = 8, Right_Hand = 9,
//				Left_Leg = 10, Groin = 11, Right_Leg = 11,
//				Left_Foot = 12,			Right_Foot = 13;
	
	public String name;
	public int limbAmount;
	public String[] limbs;
	//public String[] names;
	
	public Limbs[] currentLimbs;
	
	public Entities(String name, int limbAmount, String limb) {
		//Entities entity = (Entities) entities.getObject(name);
		this.name = name;
		this.limbAmount = limbAmount - 1; // LimbAmount will be called for for() constructors, as such it will need to count from 0 to the END
		limbs = new String[limbAmount]; //limbAmount is used because of the -1 for this.limbAmount
		try {
		limbs = limb.split(","); // limbs cannot be greater than limbAmount, if it is the game needs to end.
		// This is because a for loop which adds an extra 1 to an array will crash the thread it is on but not the rest, therefore we need to close all the threads.
		} catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.exit(1);
		}
		//int amount = 0;
		for(int i = 0; i < limbs.length; i++) {
			for(Limbs l : Limbs.limbs.values()) {
				if(limbs[i].equalsIgnoreCase(l.name)) {
					currentLimbs[i] = l;
				}
			}
//			if(limbs[i].contains("Arms")) {
//				if(currentLimbs[amount] != Limbs.getNO("Left Arm") || currentLimbs[amount] != Right_Arm) {
//				currentLimbs[amount] = Left_Arm;
//				amount++;
//				currentLimbs[amount] = Right_Arm; 
//				}
//			}
//			if(limbs[i].contains("Left Arm")) {
//				currentLimbs[i] = Left_Arm;
//			}
			//amount++;
		}
	}
	
	
	/**
	 * Direct call, only works for setting entities internally
	 * @param name Name of the entity type
	 * @param limbAmount How many limbs the entity has
	 * @param limbs The number value of the limbs
	 */
	public Entities(String name, int limbAmount, int[] limbs) {
		this.name = name;
		this.limbAmount = limbAmount;
		for(int i = 0; i < limbs.length; i++) {
			for(Limbs l : Limbs.limbs.values()) {
				if(limbs[i] == l.no) {
					currentLimbs[i] = l;
				}
			}
		}
		//currentLimbs = limbs;
	}
	
	/**
	 * Direct call, can only be used internally
	 * @param name Name of the entity type
	 * @param limbAmount How many limbs the entity has
	 * @param limbs The value of the limbs
	 */
	public Entities(String name, int limbAmount, Limbs[] limbs) {
		this.name = name;
		this.limbAmount = limbAmount;
		currentLimbs = limbs;
		//currentLimbs = limbs;
	}
	
//	public static int getNO(String name) {
//		return limbs.get(name).no;
//	}
//	
//	public static String getName(int NO) {
//		return names.get(NO);
//	}
//	
//	public static Limbs getLimb(String name) {
//		return limbs.get(name);
//	}
//	
//	public static Limbs getLimb(int NO) {
//		return limbs.get(names.get(NO));
//	}
//	
//	public static Collection<Limbs> getValues() {
//		return limbs.values();
//	}
//	
//	public static Collection<String> getNames() {
//		return names.values();
//	}
//	
//	public static int getLength() {
//		return limbs.size();
//	}
//	
//	public static int getNamesSize() {
//		return names.size();
//	}
	
	
}
