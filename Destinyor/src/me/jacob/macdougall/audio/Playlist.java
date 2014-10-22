package me.jacob.macdougall.audio;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;

import me.jacob.macdougall.Destinyor;
import me.jacob.macdougall.world.LevelMap;

public class Playlist {
	
	public int levelID = 0;
	public Map<Integer, Sound> songs = new HashMap<>();
	
	public static Map<Integer, Playlist> playlists = new HashMap<>();
	
	public static Random rand = new Random();
	public static int nextSong;
	
	public static Sound song;
	
	public boolean isPlaying = false;
	
	public Playlist(int levelID, Sound... sound) {
		this.levelID = levelID;
		for(int i = 0; i < sound.length; i++) {
			songs.put(i, sound[i]);
			System.out.println(sound[i].name + " " + i);
		}
		playlists.put(playlists.size(), this);
	}
	
	public void Play() throws LineUnavailableException, IOException {
		System.out.println(nextSong);
		if(levelID == LevelMap.level) {
			for(Sound song : songs.values()) {
				if(checkSongs())
					return;
			}
			nextSong();
		}
	}
	
	public void nextSong() throws LineUnavailableException, IOException {
		int oldSong = nextSong;
		nextSong = rand.nextInt(this.songs.size());
		if(oldSong == nextSong && this.songs.size() >= 2) {
			while(oldSong == nextSong) {
				nextSong = rand.nextInt(this.songs.size());
			}
		}
		song = songs.get(nextSong);
		Destinyor.Song = song.getName();
		song.open();
		song.playSound();
	}
	
	public static boolean checkSongs() {
		if(Sound.checkSongs()) {
			return true;
		}
		
		for(Playlist playlist : playlists.values()) {
			for(Sound sounds : playlist.songs.values()) {
				if(sounds.isPlaying) { //Checks to make sure the song is a song and not a sound effect
					return true;
				}
			}
		}
		return false;
	}
	
}
