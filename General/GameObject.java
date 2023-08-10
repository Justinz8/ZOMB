package General;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.w3c.dom.events.MouseEvent;

public abstract class GameObject {
    protected double x;
	protected double y;
	protected double velx;
	protected double vely;
    protected GameObjectID GOID;

	protected Rectangle2D.Double hitbox;

	protected Shape ogbody;
	protected Shape body;
	
	public GameObject(double x, double y, double vx, double vy, GameObjectID GOID) {
		this.x=x;
		this.y=y;
		velx=vx;
		vely=vy;
        this.GOID=GOID;
		initBody();
	}

	public abstract void initBody();
	
	public abstract void render(Graphics2D g);
	
	public abstract void tick();


    public void keyTyped(int e) {}

    public void keyPressed(int e) {}

    public void keyReleased(int e) {}

    public void mouseDragged(int mx, int my) {}

    public void mouseMoved(int mx, int my) {}

    public Rectangle2D.Double getHitBox(){
		return hitbox;
    }

	public boolean IntersectHitBox(Rectangle2D n){
		System.out.println(n.intersects(hitbox));
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
