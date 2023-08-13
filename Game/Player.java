package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import General.*;

public class Player extends GameObject{

    private int mx, my;
    private gun EquippedGun;

    public Player(double x, double y, double vx, double vy, GameObjectID GOID, GlobalVars GV) {
        super(x, y, vx, vy, GOID, GV);
        Rifle p = new Rifle(0, 0, GameObjectID.Gun, GV);
        EquippedGun=p;
        GV.GO.add(EquippedGun);
    }

    public void UpdateScale(){
        int[] tempx = {(int)x, (int)(x+25*GV.scale), (int)(x+25*GV.scale), (int)x};
        int[] tempy = {(int)y, (int)y, (int)(y+50*GV.scale), (int)(y+50*GV.scale)};
        ogbody = new Polygon(tempx, tempy, tempx.length);
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
        if(GV.UpdatedScale) UpdateScale();
        initBody();
        rotation = Math.atan2(my-y, mx-x);
        AffineTransform at = new AffineTransform();
        at.rotate(rotation, (x+12.5)*GV.scale, (y+25)*GV.scale);
        body=at.createTransformedShape(ogbody);
        if(EquippedGun!=null){
            EquippedGun.x=x+25;
            EquippedGun.y=y;
            EquippedGun.initBody();
            EquippedGun.body=at.createTransformedShape(EquippedGun.ogbody);
        }

        Rectangle2D.Double temp = (Rectangle2D.Double)body.getBounds2D();
        temp.x/=GV.scale;
        temp.y/=GV.scale;
        temp.width/=GV.scale;
        temp.height/=GV.scale;
        //System.out.println(x+" "+y+" "+temp.x+" "+temp.y);

        setHitBox(temp);

        x+=velx;
        y+=vely;


        initBody();

        GV.playerX=x;
        GV.playerY=y;
    }

    public void mouseMoved(int mx, int my) {
        this.mx=mx;
        this.my=my;
    }

    public void mouseDragged(int mx, int my) {
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
            case KeyEvent.VK_SPACE:
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

}
