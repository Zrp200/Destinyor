package me.jacob.macdougall;

import graphic.engine.window.Resolution;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class Debug extends Canvas {
	
	private static final long serialVersionUID = 8341857801876146189L;
	
	BufferedImage bi;
	
	public void render() {
		BufferStrategy strategy = this.getBufferStrategy();
		if(strategy == null){
			// Create Strategy
			createBufferStrategy(2);
			requestFocus();
			return;
		}
		
		Graphics g = strategy.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.draw3DRect(0, 0, Resolution.width(), Resolution.height(), false);
		g.fill3DRect(0, 0, Resolution.width(), Resolution.height(), false);
		
		g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 12));
		
		for(int i = 0; i < DebugWriter.strings.size(); i++)
				g.drawString(String.valueOf(DebugWriter.strings.get(i)), 1, (i + 1) * 16);
		
		g.drawImage(bi, 128 + 64, 32, null);
		
		g.dispose();
		strategy.show();
	}
	
	public void spritesheet() {
		try {
			bi = ImageIO.read(Destinyor.class.getResourceAsStream("/icon0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		return bi;
	}
	
	public Debug() {
		JFrame frame2 = new JFrame("Debug");
		if(Destinyor.Debug) {
			frame2.add(this);
			frame2.pack();
			DebugWriter.println("Game: Hello " + System.getProperty("user.name"));
			frame2.setVisible(true);
			frame2.setSize(800, 768);
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			spritesheet();
		} else {
			frame2 = null;
		}
	}
	
}
