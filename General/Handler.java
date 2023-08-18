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
        PriorityTick();
        
        for(int i = 0; i<GO.size(); i++){
            GO.get(i).tick();
        }
        extraTick();
    }
    public abstract void init();
    public abstract void extraTick();
    public abstract void PriorityTick();

    public void render(Graphics2D g){
        background(g);
        extraRender(g);
        for(int i = 0; i<GO.size(); i++){
            GO.get(i).render(g);
        }
        PriorityRender(g);
    }
    public abstract void extraRender(Graphics2D g);
    public abstract void background(Graphics2D g);
    public abstract void PriorityRender(Graphics2D g);
    
}
