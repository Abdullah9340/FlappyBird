package dev.abdullah.launcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game implements Runnable {

	public Display display;

	Graphics g;
	BufferStrategy bs;

	Bird bird;
	ArrayList<Pipes> pipe;
	
	String title;
	int width, height, FPS = 60;
	int frameCount = 0;
	boolean running = false;

	Thread thread;

	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = width;
		this.start();
	}

	public void drawBackGround(Graphics g) {
		g.setColor(new Color(135, 206, 235));
		g.fillRect(0, 0, width, height);
		g.setColor(Color.green);
		g.fillRect(0, 350, width, 10);
		g.setColor(new Color(162, 42, 42));
		g.fillRect(0, 360, width, 40);
	}

	public void update() {
		bird.update();
		for(int i = pipe.size() - 1; i >=  0;i--) {
			pipe.get(i).update();
			if(pipe.get(i).posx < 0) {
				pipe.remove(i);
			}
			
		}
		
		if(frameCount == 60) {
			frameCount = 0;
			pipe.add(new Pipes());
		}
		System.out.println(pipe.size());
		
	}

	public void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear Screen
		drawBackGround(g);
		// Drawing
		bird.render(g);
		for(int i = 0; i < pipe.size(); i ++) {
			pipe.get(i).render(g);
		}
		// End Drawing
		bs.show();
		g.dispose();
	}

	@Override
	public void run() {
		init();

		double timePerTick = 1000000000 / FPS;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
	

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;

			if (delta >= 1) {
				update();
				render();
				delta--;
				frameCount++;
			}
			
		}
	}

	

	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		try {
			thread.join();
		} catch (Exception e) {
			System.exit(1);
		}
	}

	public void init() {
		display = new Display(title, width, height);
		bird = new Bird(this);
		pipe = new ArrayList<Pipes>();
	}

}
