package me.jacob.macdougall.world;

import java.util.Random;

public class RandomLevel extends LevelMap {

	public final Random random = new Random();
	
	public RandomLevel() {
		GenerateLevel();
		maps.put(maps.size() + 1, this);
	}
	
	@SuppressWarnings("static-access")
	public void GenerateLevel() {
		for(int i = 0; i < this.FloorWidth; i++) {
			for(int j = 0; j < this.FloorHeight; j++) {
				tiles[i][j] = random.nextInt(4);
				if(i == 0) {
					tiles[i][j] = 1;
				}
				if(j == 0) {
					tiles[i][j] = 1;
				}
				if(i == this.FloorWidth - 1) {
					tiles[i][j] = 1;
				}
				if(j == this.FloorHeight - 1) {
					tiles[i][j] = 1;
				}
			}
		}
		
	}
	
}
