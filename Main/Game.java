package Main;

import General.*;
import Game.GameHandler;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.HashMap;

public class Game extends Canvas implements Runnable{
    private Thread thread;
    private GlobalVars GV;


    public Game(){
        GV = new GlobalVars();
        GV.running=true;
        new Window("ZOMB", this, GV, Tools.WIDTH, Tools.HEIGHT);

        this.addKeyListener(new KeyInput(GV));
        this.addMouseMotionListener(new MouseActionInput(GV));

        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        GV.ChangePage(PageID.Game);

        long lastTime = System.nanoTime();
		double amountOfTicks = 30; //amount of ticks/s we want
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(GV.running){
			long now = System.nanoTime();
			delta += (now - lastTime) / (1000000000 / amountOfTicks); //amount of ticks we suppose to run this loop
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			//render every loop
			if(GV.running)
				render();
			
			frames++;

			if(System.currentTimeMillis() - timer > 1000){//output fps
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
        stop();
    }

    public void stop(){
        System.out.println("STOPPED");
        System.exit(0);
    }

    private void tick(){
        GV.HandlerList.get(GV.curPageID).tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(2);
            return;
        }

        Graphics2D g = (Graphics2D)bs.getDrawGraphics();

        GV.HandlerList.get(GV.curPageID).render(g);

        g.dispose();
        bs.show();
    }
}