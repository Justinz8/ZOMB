package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.PriorityQueue;

import General.GameObject;
import General.GameObjectID;
import General.GlobalVars;
import General.Tools;

public class Tracer extends GameObject {


    private double tickcounter;
    private double counter;
    private double rotation;
    private Line2D line;
    private double x2, y2;
    private double pen;
    private AffineTransform at;
    private Zombie hitzomb;
    private double dmg;

    public class zomb implements Comparable<zomb>{
        double dist;
        GameObject go;
        
        public zomb(double dist, GameObject go){
            this.dist=dist;
            this.go=go;
        }

        public int compareTo(zomb o) {
            return Double.compare(this.dist, o.dist);
        }
    }

    public Tracer(double x, double y, GameObjectID GOID, GlobalVars GV, double x2, double y2, double length, double dmg, int pen) {
        super(x, y, 0, 0, GOID, GV);
        this.pen=pen;
        this.counter=0.3;
        this.dmg=dmg;
        tickcounter=0;
        at = new AffineTransform();

        rotation = Math.atan2(y2-y, x2-x);

    
        line = new Line2D.Double(x, y, Math.cos(rotation)*length+x, Math.sin(rotation)*length+y);

        double mindist = length;

        PriorityQueue<zomb> pq = new PriorityQueue<zomb>();

        for(int i = 0; i<GV.GO.size(); i++){
            if(GV.GO.get(i).getHitBox()==null||GV.GO.get(i).getGOID()==GameObjectID.Player||GV.GO.get(i).getGOID()==GameObjectID.Tracer||GV.GO.get(i).getGOID()==GameObjectID.Gun) continue;
            if(line.intersects(GV.GO.get(i).getHitBox())){
                double hitx = (GV.GO.get(i).getHitBox().getMaxX()+GV.GO.get(i).getHitBox().getMinX())/2.0-x;
                double hity = (GV.GO.get(i).getHitBox().getMaxY()+GV.GO.get(i).getHitBox().getMinY())/2.0-y;

                double dist = Math.sqrt(hitx*hitx+hity*hity);
                pq.add(new zomb(dist, GV.GO.get(i)));
            }
        }
        //rotation = Math.atan2(y2, x2);

        while(!pq.isEmpty()&&pen-->0&&pq.peek().go.GOID==GameObjectID.Zombie){
            mindist = pq.peek().dist;
            hitAction((Zombie)pq.poll().go);
        }
        if(pen!=0) mindist = length;

        at.rotate(rotation, x, (y+0.5));
        body = at.createTransformedShape(new Rectangle2D.Double(x, y, mindist, 1));
        at.rotate(-rotation, x, (y+0.5));
    }

    public void hitAction(Zombie n){
        n.hit(dmg);
    }

    @Override
    public void initBody() {
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(255, 255, 255, (int)((counter-(tickcounter/Tools.amountOfTicks))/counter*100)));
        g.fill(body);
    }

    @Override
    public void tick() {
        tickcounter++;
        if(tickcounter/Tools.amountOfTicks>=counter){
            this.GV.GO.remove(this);
            tickcounter = 0;
            return;
        }
    }
    
}
