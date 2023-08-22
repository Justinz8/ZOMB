package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import Game.Guns.*;
import General.*;

public class Player extends GameObject{

    private int mx1, my1;
    private gun EquippedGun;

    public Player(double x, double y, double vx, double vy, GameObjectID GOID, GlobalVars GV) {
        super(x, y, vx, vy, GOID, GV);
        Rifle p = new Rifle(0, 0, GameObjectID.Gun, GV);
        int[] tempx = {(int)x, (int)(x+25*GV.scale), (int)(x+25*GV.scale), (int)x};
        int[] tempy = {(int)y, (int)y, (int)(y+50*GV.scale), (int)(y+50*GV.scale)};
        ogbody = new Polygon(tempx, tempy, tempx.length);
        EquippedGun=p;
        GV.GO.add(EquippedGun);
    }



    @Override
    public void render(Graphics2D g) {
        initBody();
        g.setColor(Color.green);

        if(body!=null)g.fill(body);

        
        
        if(getHitBox()!=null) g.draw(getHitBox());
        g.setColor(Color.yellow);
        if(EquippedGun!=null&&EquippedGun.body!=null){
            g.fill(EquippedGun.body);
        } 

        // g.rotate(rotation, x+12.5, y+25);
        // g.fillRect((Integer)x, (Integer)y, 25, 50);
        // g.rotate(-rotation, x+12.5, y+25);
    }

    @Override
    public void tick() {
        x+=velx;
        y+=vely;
        x=Tools.clamp(-5000, 5000, x);
        y=Tools.clamp(-5000, 5000, y);

        initBody();

        GV.playerX=x;
        GV.playerY=y;

        int mx = mx1-(int)GV.getTransX();
        int my = my1-(int)GV.getTransY();
        rotation = Math.atan2(my-y, mx-x);
        at.rotate(rotation, (x+12.5), (y+25));
        body=at.createTransformedShape(ogbody);
        if(EquippedGun!=null){
            EquippedGun.x=x+25;
            EquippedGun.y=y;
            EquippedGun.initBody();
            EquippedGun.body=at.createTransformedShape(EquippedGun.ogbody);
        }
        at.rotate(-rotation, (x+12.5), (y+25));
        Rectangle2D.Double temp = (Rectangle2D.Double)body.getBounds2D();
        //System.out.println(x+" "+y+" "+temp.x+" "+temp.y);

        setHitBox(temp);
        GV.playHitBox=temp;
        initBody();

    }

    public void mouseMoved(int mx, int my) {
        this.mx1=mx;
        this.my1=my;
    }

    public void mouseDragged(int mx, int my) {
        this.mx1=mx;
        this.my1=my;
    }

    public void keyPressed(int e) {
        switch(e){
            case KeyEvent.VK_W:
                vely=(double)-10;
                break;
            case KeyEvent.VK_S:
                vely=(double)10;
                break;
            case KeyEvent.VK_D:
                velx=(double)10;
                break;
            case KeyEvent.VK_A:
                velx=(double)-10;
                break;
            case KeyEvent.VK_SPACE:
                break;
        }
    }

    public void keyReleased(int e) {
        switch(e){
            case KeyEvent.VK_W:
                vely=Math.max(vely, 0);
                break;
            case KeyEvent.VK_S:
                vely=Math.min(vely, 0);
                break;
            case KeyEvent.VK_D:
                velx=Math.min(velx, 0);
                break;
            case KeyEvent.VK_A:
                velx=Math.max(velx, 0);
                break;
        }
    }

}
