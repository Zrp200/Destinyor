package me.jacob.macdougall;

public class Time {
	
	private static int moveTime = 0;
	private static int cutTime = 0;
	private static int mapTime = 0;
	private static int inventoryTime = 0;
	private static int frameTime = 0;
        private static int playersTime = 0;
        private static int playerTime = 0;
        private static int entityTime = 0;
        private static int keyTime = 0;
	
	public static void tick() {
		moveTime++;
		mapTime++;
		inventoryTime++;
		frameTime++;
                playersTime++;
                playerTime++;
                entityTime++;
                keyTime++;
                cutTime++;
	}
	
	public static boolean getCutsceneTimer(int amount, boolean UseTime) {
    	if(UseTime) {
                return cutsceneTimer(0);
    	} else {
    		if(cutTime >= amount)
    			return true;
    	}
    	return false;
    }
    
    public static void resetCutsceneTimer() {
    	cutsceneTimer(0);
    }
	
	public static boolean cutsceneTimer(int amount) {
		if(cutTime >= amount) {
			cutTime = 0;
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean moveTimer(int amount) {
		if(moveTime >= amount) {
			moveTime = 0;
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean mapTimer(int amount) {
		if(mapTime >= amount) {
			mapTime = 0;
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean inventoryTimer(int amount) {
		if(inventoryTime >= amount) {
			inventoryTime = 0;
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean frameTimer(int amount) {
		if(frameTime >= amount) {
			frameTime = 0;
			return true;
		} else {
			return false;
		}
	}
        
        public static boolean playersTimer(int amount) {
		if(playersTime >= amount) {
			playersTime = 0;
			return true;
		} else {
			return false;
		}
	}
        
        public static boolean playerTimer(int amount) {
		if(playerTime >= amount) {
			playerTime = 0;
			return true;
		} else {
			return false;
		}
	}
        
        public static boolean entityTimer(int amount) {
		if(entityTime >= amount) {
			entityTime = 0;
			return true;
		} else {
			return false;
		}
	}
        
        public static boolean getKeyTimer(int amount, boolean UseTime) {
        	if(UseTime) {
                    return keyTimer(amount);
        	} else {
        		if(keyTime >= amount)
        			return true;
        	}
        	return false;
        }
        
        public static void resetKeyTimer() {
        	keyTimer(0);
        }
        
        private static boolean keyTimer(int amount) {
		if(keyTime >= amount) {
			keyTime = 0;
			return true;
		} else {
			return false;
		}
	}
        
        
	
}
