package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

import General.*;

public class Zombie extends GameObject {

    private double speed;
    private Shape body;
    private double width1;
    private double width2;
    private double height1;
    private double height2;
    private AffineTransform at;
    private double health;

    public Zombie(double x, double y, double vx, double vy, GameObjectID GOID, GlobalVars GV, double speed, double health) {
        super(x, y, vx, vy, GOID, GV);
        this.GV=GV;
        this.speed=speed;
        at = new AffineTransform();
        this.health = health;
        int[] tempx = {(int)x, (int)(x+25), (int)(x+25), (int)x};
        int[] tempy = {(int)y, (int)y, (int)(y+50), (int)(y+50)};
        ogbody = new Polygon(tempx, tempy, tempx.length);
        rotation(at, x+width2/2.0, y+height2/2.0);
    }

    public void hit(double dmg){
        health-=dmg;
    }

    public void render(Graphics2D g) {
        g.setColor(Color.red);
        if(body!=null)g.fill(body);
        if(getHitBox()!=null) g.draw(getHitBox());
    }

    public void tick() {
        if(health<=0){
            GV.GO.remove(this);
            return;
        }

        velx = Math.cos(rotation)*speed;
        vely = Math.sin(rotation)*speed;

        x+=velx;
        y+=vely;

        //System.out.println(x+" "+y);


        initBody();
        width2 = 25;
        height2 = 50;
        rotation(at, x+width2/2.0, y+height2/2.0);
        
        rotation = Math.atan2(GV.playerY-y, GV.playerX-x);
        rotation(at, x+width2/2.0, y+height2/2.0);
        width1 = this.getHitBox().width;
        height1 = this.getHitBox().height;
        collision();
        rotation(at, x+width2/2.0, y+height2/2.0);
    }
    public void rotation(AffineTransform at, double xaxis,double yaxis){ //rotates main body and updates hitbox
                initBody();
                at.rotate(rotation, xaxis, yaxis);
                body=at.createTransformedShape(ogbody);
                Rectangle2D.Double temp = (Rectangle2D.Double)body.getBounds2D();
                setHitBox(temp);
                at.rotate(-rotation, xaxis, yaxis);
    }

    public void collision(){ 
        for(int i = 0; i<GV.GO.size(); i++){
            if(this==GV.GO.get(i)) continue;
            if(GV.GO.get(i).getHitBox()==null) continue;
            //System.out.println(GV.GO.get(i).getHitBox().intersects(getHitBox()));
            if(GV.GO.get(i).getHitBox().intersects(getHitBox())){
                //going left and hitting left target
                double degreeL = getHitBox().getMinX()-GV.GO.get(i).getHitBox().getMaxX()+speed;
                degreeL=(degreeL<0?degreeL*-1:degreeL);

                double degreeR = getHitBox().getMaxX()-GV.GO.get(i).getHitBox().getMinX()-speed;
                degreeR=(degreeR<0?degreeR*-1:degreeR);

                double degreeT = getHitBox().getMinY()-GV.GO.get(i).getHitBox().getMaxY()+speed;
                degreeT=(degreeT<0?degreeT*-1:degreeT);

                double degreeB = getHitBox().getMaxY()-GV.GO.get(i).getHitBox().getMinY()-speed;
                degreeB=(degreeB<0?degreeB*-1:degreeB);
                
                double n[] = {degreeB, degreeL, degreeR, degreeT};
                Arrays.sort(n);

                if(n[0]==degreeR){
                    x-=velx;
                    if(Tools.inbounds(getHitBox().getMinX(), getHitBox().getMaxX(), GV.GO.get(i).getHitBox().getMinX())&&(degreeR<-0.5|degreeR>0.5)){
                     x=GV.GO.get(i).getHitBox().getMinX();
                     x-=(width1-width2)/2.0+width2;
                    }
                }else if(n[0]==degreeL){
                    x-=velx;
                    if(Tools.inbounds(getHitBox().getMinX(), getHitBox().getMaxX(), GV.GO.get(i).getHitBox().getMaxX())&&(degreeL<-0.5||degreeL>0.5)){
                        x=GV.GO.get(i).getHitBox().getMaxX();
                        x+=(width1-width2)/2.0+0.001;
                    }
                }else if(n[0]==degreeB){
                    y-=vely;
                    if(Tools.inbounds(getHitBox().getMinY(), getHitBox().getMaxY(), GV.GO.get(i).getHitBox().getMinY())&&(degreeB>0.5||degreeB<-0.5)){
                        y=GV.GO.get(i).getHitBox().getMinY(); 
                        y-=(height2-height1)/2.0+height1;
                    }
                }else{
                    y-=vely;
                    if(Tools.inbounds(getHitBox().getMinY(), getHitBox().getMaxY(), GV.GO.get(i).getHitBox().getMaxY())&&(degreeT>0.5||degreeT<-0.5)){
                        y=GV.GO.get(i).getHitBox().getMaxY();
                        y-=(height2-height1)/2.0;
                    }
                }
                // x/=2.0;
                // System.out.println(GV.GO.get(i).getHitBox().getMaxX()+" "+(this.getHitBox().width-(ogbody.getBounds2D()).getWidth())/2.0);
                //System.out.println(GV.GO.get(i).getHitBox().getMaxX()+" "+(at.createTransformedShape(new Rectangle2D.Double(GV.GO.get(i).getHitBox().getMaxX()+(width1-width2)/2.0, y, 25, 50))).getBounds2D().getMinX()+" "+(width1-width2)/2.0);
                rotation(at, x+width2/2.0, y+height2/2.0);
                //System.out.println(GV.GO.get(i).getHitBox().getMaxX()+" "+getHitBox().getMinX());
            }
        }
    }
    
}
