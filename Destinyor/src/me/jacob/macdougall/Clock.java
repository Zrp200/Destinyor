package me.jacob.macdougall;

import java.awt.Graphics;

public class Clock {

	public int seconds = 0;
	public int minutes = 0;
	public int hours = 0;
	public int days = 0;
	public int weeks = 0;

	public String getTime() {
		String time = weeks + " : " + days + " : " + hours + " : " + minutes + " : " + seconds;
		return time;
	}

	public void render(Graphics g) {
		g.drawString(getTime(), 1, 96);
	}

	public void tick() {
		if(Time.getTime(60)) {
			seconds++;
		}
		if(seconds == 60) {
			seconds = 0;
			minutes++;
		}
		if(minutes == 60) {
			minutes = 0;
			hours++;
		}
		if(hours == 24) {
			hours = 0;
			days++;
		}
		if(days == 7) {
			days = 0;
			weeks++;
		}
	}
        
}