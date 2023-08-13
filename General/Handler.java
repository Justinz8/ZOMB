package General;

import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class Handler {
    public ArrayList<GameObject> GO;
    public GlobalVars GV;

    public Handler(ArrayList<GameObject> GO, GlobalVars GV){
        this.GO=GO;
        this.GV=GV;
    }

    public void tick(){
        extraTick();
        for(int i = 0; i<GO.size(); i++){
            GO.get(i).tick();
        }
        if(GV.UpdatedScale==true) GV.UpdatedScale=false;
    }
    public abstract void init();
    public abstract void extraTick();

    public void render(Graphics2D g){
        background(g);
        extraRender(g);
        for(int i = 0; i<GO.size(); i++){
            GO.get(i).render(g);
        }
        
    }
    public abstract void extraRender(Graphics2D g);
    public abstract void background(Graphics2D g);
    
}
