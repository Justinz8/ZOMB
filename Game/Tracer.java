package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import General.GameObject;
import General.GameObjectID;
import General.GlobalVars;
import General.Tools;

public class Tracer extends GameObject {


    private double tickcounter;
    private double counter;
    private GlobalVars GV;
    private double rotation;
    private Line2D line;
    private double x2, y2;
    private AffineTransform at;
    private Zombie hitzomb;

    public Tracer(double x, double y, double vx, double vy, GameObjectID GOID, double counter, GlobalVars GV, double x2, double y2) {
        super(x, y, vx, vy, GOID);
        this.counter=counter;
        tickcounter=0;
        this.GV=GV;
        at = new AffineTransform();
        rotation = Math.atan2(y2-y, x2-x);

        

        line = new Line2D.Double(x, y, (x2-x)*1000+x, (y2-y)*1000+y);

        double mindist = 1300;

        for(int i = 0; i<GV.GO.size(); i++){
            if(GV.GO.get(i).getHitBox()==null||GV.GO.get(i).getGOID()==GameObjectID.Player) continue;
            if(line.intersects(GV.GO.get(i).getHitBox())){
                double hitx = GV.GO.get(i).getx()-x;
                double hity = GV.GO.get(i).gety()-y;

                double dist = Math.sqrt(hitx*hitx+hity*hity);
                if(dist<mindist){
                    mindist=dist;
                    if(GV.GO.get(i).getGOID()==GameObjectID.Zombie) hitzomb=(Zombie)GV.GO.get(i);
                }
            }
        }
        //rotation = Math.atan2(y2, x2);

        if(hitzomb!=null) hitzomb.hit(20);
        at.rotate(rotation, x, y+0.5);
        body = at.createTransformedShape(new Rectangle2D.Double(x, y, mindist, 1));
        at.rotate(-rotation, x, y+0.5);
    }

    @Override
    public void initBody() {
        
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.white);
        g.fill(body);
    }

    @Override
    public void tick() {
        tickcounter++;
        if(tickcounter>=counter){
            this.GV.GO.remove(this);
            return;
        }
    }
    
}
