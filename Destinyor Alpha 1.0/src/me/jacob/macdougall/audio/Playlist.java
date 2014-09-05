package me.jacob.macdougall.audio;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import me.jacob.macdougall.world.LevelMap;

public class Playlist {
	
	public int levelID = 0;
	public Map<Integer, Sound> songs = new HashMap<>();
	
	public static Map<Integer, Playlist> playlists = new HashMap<>();
	
	public static Random rand = new Random();
	public static int nextSong;
	
	public boolean isPlaying = false;
	
	public Playlist(int levelID, Sound... sound) {
		this.levelID = levelID;
		for(int i = 0; i < sound.length; i++) {
			songs.put(i, sound[i]);
		}
	}
	
	public static void checkSongs() {
		for(Playlist playlist : playlists.values()) {
			for(Sound sounds : playlist.songs.values()) {
				if(sounds.isPlaying && sounds.loopable) {
					return;
				} else {
					if(playlist.levelID == LevelMap.level)
					nextSong = rand.nextInt(playlist.songs.size());
					playlist.songs.get(nextSong);
					playlist.isPlaying = true;
				}
			}
		}
	}
	
}
