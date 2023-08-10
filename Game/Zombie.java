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

    private GlobalVars GV;
    private double rotation;
    private double speed;
    private Shape body;

    public Zombie(double x, double y, double vx, double vy, GameObjectID GOID, GlobalVars GV, double speed) {
        super(x, y, vx, vy, GOID);
        this.GV=GV;
        this.speed=speed;
    }

    public void render(Graphics2D g) {
        g.setColor(Color.red);
        if(body!=null)g.fill(body);
        if(getHitBox()!=null) g.draw(getHitBox());
    }

    public void tick() {
        initBody();
        

        velx = Math.cos(rotation)*speed;
        vely = Math.sin(rotation)*speed;



        x+=velx;
        y+=vely;

        AffineTransform at = new AffineTransform();
        rotation = Math.atan2(GV.playerY-y, GV.playerX-x);
        at.rotate(rotation, x+12.5, y+25);
        initBody();
        body=at.createTransformedShape(ogbody);
        setHitBox((Rectangle2D.Double)body.getBounds2D());
        at.rotate(-rotation, x+12.5, y+25);

        double width1 = this.getHitBox().width;
        double width2 = (ogbody.getBounds2D()).getWidth();
        double height1 = this.getHitBox().height;
        double height2 = (ogbody.getBounds2D()).getHeight();
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
                initBody();
                at.rotate(rotation, x+12.5, y+25);
                body=at.createTransformedShape(ogbody);
                setHitBox((Rectangle2D.Double)body.getBounds2D());
                at.rotate(-rotation, x+12.5, y+25);
                //System.out.println(GV.GO.get(i).getHitBox().getMaxX()+" "+getHitBox().getMinX());
            }
        }
        
       

    }

    @Override
    public void initBody() {
        ogbody = new Rectangle2D.Double(x, y, 25, 50);
    }
    
}
