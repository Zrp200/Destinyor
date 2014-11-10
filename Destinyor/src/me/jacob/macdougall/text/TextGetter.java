package me.jacob.macdougall.text;

import graphic.engine.screen.GameFont;
import graphic.engine.screen.Screen;

import input.engine.keyboard.InputHandler;

import java.util.List;
import java.util.ArrayList;

public class TextGetter {
	
	public TextGetter() {
		//game.addKeyListener(this);
	}
	
	public List<String> text = new ArrayList<>();
	
	public int keyId = -1;
	//public String text = "";
	//public String tempText = text;
	
	public void GetText(InputHandler input) {
//		for(InputHandler.Key key : input.keys) {
//			if(key.down) {
//				if(key.kevent != KeyEvent.VK_BACK_SPACE) {
//					keyId = key.kevent;
//					System.out.println(KeyEvent.getKeyText(key.kevent));
//					text.add(text.size(), KeyEvent.getKeyText(key.kevent));
//					//tempText = text;
//					//text += KeyEvent.getKeyText(key.id);
//				} else {
//					text.remove(text.size());
//					System.out.println(text.get(text.size()));
//					//text = tempText;
//				}
//			}
//		}
		//this.keyPressed(KeyEvent);
		//System.out.println(e);
	}
	
	public void render(Screen screen) {
		if(!text.isEmpty()) {
		String tempText = "";
		for(int i = 0; i < text.size(); i++) {
			tempText += text.get(i);
		}
		
		GameFont.render(tempText, screen, 512 / 2, 384 / 2);
		}
	}
}
