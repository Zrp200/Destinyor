package me.jacob.macdougall;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

import graphic.engine.screen.Screen;
import graphic.engine.screen.Art;
import me.jacob.macdougall.world.Tile;

public class Debug extends Canvas {
	
	private static final long serialVersionUID = 8341857801876146189L;
	
	//BufferedImage bi;
        Screen screen;
	
	public void render() {
		BufferStrategy strategy = this.getBufferStrategy();
		if(strategy == null){
			// Create Strategy
			createBufferStrategy(2);
			requestFocus();
			return;
		}
		
                spritesheet();
                
		Graphics g = strategy.getDrawGraphics();
                //g.setColor(Color.DARK_GRAY);
		g.drawImage(screen.image, 224, 32, null);
                
		//g.setColor(Color.BLACK);
		//g.draw3DRect(0, 0, Resolution.width(), Resolution.height(), false);
		//g.fill3DRect(0, 0, Resolution.width(), Resolution.height(), false);
		
		g.setColor(Color.GREEN);
                drawBoxes(g);
        g.setFont(new Font("Arial", Font.BOLD, 12));
		
		for(int i = 0; i < DebugWriter.strings.size(); i++)
				g.drawString(String.valueOf(DebugWriter.strings.get(i)), 1, (i + 1) * 16);
                //int z = 16;
                for(int i = 0; i < screen.w / 32; i++) {
                    g.drawString(i + "", 192 + 16, (i * 32) + 52);
                    //z+=16;
                }
                for(int j = 0; j < screen.h / 32; j++) {
                    g.drawString(j + "", (j * 32) + 14 + 224, 28);
                }
		
		//g.drawImage(bi, 128 + 64, 32, null);
		
		g.dispose();
		strategy.show();
	}
	
	public void spritesheet() {
            Tile.renderSpritesheet(screen);
		//try {
			//bi = ImageIO.read(Destinyor.class.getResourceAsStream("/icon0.png"));
		//} catch (IOException e) {
			//e.printStackTrace();
		//}
//		return bi;
	}
        
        public void drawBoxes(Graphics g) {
            for(int i = 0; i < Art.getSpritesheet().length; i++) {
                for(int j = 0; j < Art.getSpritesheet()[i].length; j++) {
                    g.drawRect((i + 7) * 32, (j + 1) * 32, 32, 32);
                }
            }
        }
	
	public Debug() {
                if(Destinyor.Debug) {
		JFrame frame2 = new JFrame("Debug");
		
			frame2.add(this);
			frame2.pack();
			DebugWriter.println("Game: Hello " + System.getProperty("user.name"));
			frame2.setVisible(true);
			frame2.setSize(1024, 768);
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        System.out.println(Art.getSpritesheet().length * 32);
                        screen = new Screen(Art.getSpritesheet().length * 32, Art.getSpritesheet()[0].length * 32);
                        //frame2.setBackground(Color.BLACK);
                        this.setBackground(Color.BLACK);
			//spritesheet(screen);
		} //else {
			//frame2 = null;
		//}
	}
	
}