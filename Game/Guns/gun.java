package Game.Guns;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import General.GameObject;
import General.GameObjectID;
import General.GlobalVars;
import General.Tools;

public abstract class gun extends GameObject {
    public double delay;
    public boolean AllowedShoot;
    public boolean automatic;
    public int mx, my;
    private int tickcounter;
    private boolean TriggerPulled;
    

    
    public gun(double x, double y, GameObjectID GOID, GlobalVars GV, double delay, boolean automatic) {
        super(x, y, 0, 0, GOID, GV);
        this.GV=GV;
        this.delay=delay;
        this.automatic=automatic;
        tickcounter=0;
        TriggerPulled=false;
    }

    public void discard(){
        GV.GO.remove(this);
        return;
    }

    public void mouseMoved(int mx, int my) {
        this.mx=mx;
        this.my=my;
    }
    public void mouseDragged(int mx, int my) {
        this.mx=mx;
        this.my=my;
    }

    public abstract void shoot();


    @Override
    public abstract void render(Graphics2D g);

    public void mousePressed(int e) {
        if(e==MouseEvent.BUTTON1){
            TriggerPulled=true;
        }
    }

    public void mouseReleased(int e) {
        if(e==MouseEvent.BUTTON1){
            TriggerPulled=false;
        }
    }

    @Override
    public void tick(){
        initBody();
        tickcounter++;
        if((double)tickcounter/Tools.amountOfTicks>=delay){
            AllowedShoot=true;
            tickcounter=0;
        }

        if(TriggerPulled&&AllowedShoot){
            shoot();
            if(!automatic)TriggerPulled=false;
            AllowedShoot=false;
            tickcounter=0;
        }else if(TriggerPulled&&!AllowedShoot&&!automatic){
            TriggerPulled=false;
        }
    };
}
