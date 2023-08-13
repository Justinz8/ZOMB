package General;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import org.w3c.dom.events.MouseEvent;


public abstract class GameObject {
    public double x;
	public double y;
	public double velx;
	public double vely;
	public double rotation;
    public GameObjectID GOID;
	public GlobalVars GV;

	public Rectangle2D.Double hitbox;

	public Polygon ogbody;
	public Shape body;
	
	public GameObject(double x, double y, double vx, double vy, GameObjectID GOID, GlobalVars GV) {
		this.x=x;
		this.y=y;
		velx=vx;
		vely=vy;
        this.GOID=GOID;
		this.GV=GV;
		UpdateScale();
	}
	
	public abstract void UpdateScale();

	public void initBody(){
		ogbody.translate((int)((x-ogbody.xpoints[0]/GV.scale)*GV.scale), (int)((y-ogbody.ypoints[0]/GV.scale)*GV.scale));
	};
	
	public abstract void render(Graphics2D g);
	
	public abstract void tick();

	public void collision(){}

	public void rotation(AffineTransform at, double xaxis,double yaxis){}

    public void keyTyped(int e) {}

    public void keyPressed(int e) {}

    public void keyReleased(int e) {}

    public void mouseDragged(int mx, int my) {}

    public void mouseMoved(int mx, int my) {}

	public void mouseClicked(int e) {}

    public void mousePressed(int e) {}

    public void mouseReleased(int e) {}

    public void mouseEntered(int e) {}

    public void mouseExited(int e) {}

    public Rectangle2D.Double getHitBox(){
		return hitbox;
    }

	public boolean IntersectHitBox(Rectangle2D n){
		return n.intersects(hitbox);
	}

	public void setHitBox(Rectangle2D.Double n){
		hitbox=n;
	}
	
	public double getx() {
		return x;
	}
	public double gety() {
		return y;
	}
	public double getvelx() {
		return velx;
	}
	public double getvely() {
		return vely;
	}
	public void setx(double n) {
		x=n;
	}
	public void sety(double n) {
		y=n;
	}
	public void setvelx(double n) {
		velx=n;
	}
	public void setvely(double n) {
		vely=n;
	}
    public void setGOID(GameObjectID GOID){
        this.GOID=GOID;
    }
    public GameObjectID getGOID(){
        return GOID;
    }
}
