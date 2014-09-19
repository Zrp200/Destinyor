package me.jacob.macdougall.audio;

import java.util.HashMap;
import java.util.Map;

public class SFX {
	
	//SoundEffects will be called sfx in later versions
	
	public static Map<String, SFX> sfxs = new HashMap<>();
	public static Map<Integer, String> names = new HashMap<>();
	
	public Map<String, Sound> effects = new HashMap<>();
	public Map<Integer, String> effectNames = new HashMap<>();
	
	public String name;
	
	public SFX(String name, Sound... sounds) {
		this.name = name;
		//for(int i = 0; i < sounds.length; i++) {
                for(Sound sound : sounds) {
			putEffect(sound);
		}
			
	}
	
	public SFX getSFX(String name) {
		return sfxs.get(name);
	}
	
	public SFX getSFX(int id) {
		return sfxs.get(names.get(id));
	}
	
	public void putEffect(Sound sound) {
		effects.put(sound.name, sound);
		effectNames.put(effectNames.size(), sound.name);
	}
	
	public static void putSoundEffect(SFX sfx) {
		sfxs.put(sfx.name, sfx);
		names.put(names.size(), sfx.name);
	}
	
}
