package me.jacob.macdougall.world;

import me.jacob.macdougall.buildings.Buildings;

public class Houses extends Buildings {
	
	public String owner;
	public int cost;
	
	public Houses() {
		buildings.add(this);
	}
	
	@Override
	public void render() {
		
	}
	
	@Override
	public void update() {
		
	}
	
	
}
