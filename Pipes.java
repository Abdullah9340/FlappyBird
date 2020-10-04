package dev.abdullah.launcher;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Pipes {
	int posy,posx, speed, space;
	
	public Pipes() {
		Random rand = new Random();
		this.posy = rand.nextInt(220);
		this.posx = Launcher.width - rand.nextInt(75);
		this.space = 100;
		this.speed = 4;
		
	}
	
	public void update() {
		posx -= speed;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(posx, 0, 30, posy);
		g.fillRect(posx, posy + space, 30, Launcher.height - 50 - (posy+space) );
		
	}
}

