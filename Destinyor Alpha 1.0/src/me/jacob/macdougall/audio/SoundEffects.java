package me.jacob.macdougall.audio;

import java.util.HashMap;
import java.util.Map;

public class SoundEffects {
	
	//SoundEffects will be called sfx in later versions
	
	public static Map<String, SoundEffects> soundEffects = new HashMap<>();
	public static Map<Integer, String> names = new HashMap<>();
	
	public Map<String, Sound> effects = new HashMap<>();
	public Map<Integer, String> effectNames = new HashMap<>();
	
	public String name;
	
	public SoundEffects(String name, Sound... sounds) {
		this.name = name;
		for(int i = 0; i < sounds.lenght; i++) {
			putEffect(sounds[i]);
		}
			
	}
	
	public Sound getSFX(String name) {
		return soundEffects.get(name);
	}
	
	public Sound getSFX(int id) {
		return soundEffects.get(names.get(id)));
	}
	
	public void putEffect(Sound sound) {
		effects.put(sound.name, sound);
		effectNames.put(effectNames.size(), sound);
	}
	
	public static void putSoundEffect(SoundEffects sfx) {
		soundEffects.put(sfx.name, sfx);
		names.put(names.size(), sfx.name);
	}
	
}
