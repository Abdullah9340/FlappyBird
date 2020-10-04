

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Bird implements KeyListener{
	int posx, posy, size, speed, gravity;
	BufferedImage img;
	
	public Bird(Game game) {
		posx = 60;
		posy = Launcher.height / 2;
		size = 32;
		speed = 64;
		gravity = 3;
		loadBird();
		game.display.getJFrame().addKeyListener(this);
	}

	public void loadBird() {
		img = ImageLoader.loadImg("Flappy Bird Assets\\flappybird.png");

	}
	
	public void hitGround(Game game) {
		game.isGameover = false;
	}
	
	public void update(Game game) {
		posy += gravity;
		hitGround(game);
	}     
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.drawImage(img, posx,posy,size,size,null);
	}
	
	public void move() {
		posy -= speed;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 32) {
			move();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

		
	}
}
