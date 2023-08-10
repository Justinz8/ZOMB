package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import General.*;

public class Player extends GameObject{

    private double rotation;
    private int mx, my;
    private GlobalVars GV;
    public Player(double x, double y, double vx, double vy, GameObjectID GOID, GlobalVars GV) {
        super(x, y, vx, vy, GOID);
        this.GV=GV;
    }

    @Override
    public void render(Graphics2D g) {
        initBody();
        g.setColor(Color.green);
        if(body!=null)g.fill(body);
        if(getHitBox()!=null) g.draw(getHitBox());
        // g.rotate(rotation, x+12.5, y+25);
        // g.fillRect((Integer)x, (Integer)y, 25, 50);
        // g.rotate(-rotation, x+12.5, y+25);
    }

    @Override
    public void tick() {
        rotation = Math.atan2(my-y, mx-x);

        AffineTransform at = new AffineTransform();
        at.rotate(rotation, x+12.5, y+25);
        body=at.createTransformedShape(ogbody);
        setHitBox((Rectangle2D.Double)body.getBounds2D());

        x+=velx;
        initBody();
        y+=vely;
        initBody();



        GV.playerX=x;
        GV.playerY=y;
    }

    public void mouseMoved(int mx, int my) {
        this.mx=mx;
        this.my=my;
    }

    public void keyPressed(int e) {
        switch(e){
            case KeyEvent.VK_UP:
                vely=(double)-10;
                break;
            case KeyEvent.VK_DOWN:
                vely=(double)10;
                break;
            case KeyEvent.VK_RIGHT:
                velx=(double)10;
                break;
            case KeyEvent.VK_LEFT:
                velx=(double)-10;
                break;
        }
    }

    public void keyReleased(int e) {
        switch(e){
            case KeyEvent.VK_UP:
                vely=Math.max(vely, 0);
                break;
            case KeyEvent.VK_DOWN:
                vely=Math.min(vely, 0);
                break;
            case KeyEvent.VK_RIGHT:
                velx=Math.min(velx, 0);
                break;
            case KeyEvent.VK_LEFT:
                velx=Math.max(velx, 0);
                break;
        }
    }

    @Override
    public void initBody() {
        ogbody = new Rectangle2D.Double(x, y, 25, 50);
    }
    
}
