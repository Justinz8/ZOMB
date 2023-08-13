package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.tools.Tool;

import General.*;

public class GameHandler extends Handler {

    private boolean spawn;
    private int tickcounter;
    private double count;

    public GameHandler(ArrayList<GameObject> GO, GlobalVars GV) {
        super(GO, GV);
        tickcounter = 0;
        count = 0.5;
    }


    public void extraTick() {
        // tickcounter++;
        // if(tickcounter/Tools.amountOfTicks>=count){
        //     tickcounter = 0;
        //     spawn=true;
        //     while(spawn==true){
        //         int nextx = GV.rand.nextInt(Tools.WIDTH-60);
        //         //GV.GO.add(new Zombie(nextx, Tools.HEIGHT, 0, 0, GameObjectID.Zombie, GV, 2.5, 60));
        
        //         for(int i = 0; i<GV.GO.size(); i++){
        //             if(GV.GO.get(i).y>=Tools.HEIGHT) spawn=false;
        //         }
        //     }
        // }


    }

    public void extraRender(Graphics2D g) {
        
    }


    public void background(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Tools.WIDTH, Tools.HEIGHT);
    }


    public void init() {
        
        GV.GO.add(new Zombie(400, 400, 0, 0, GameObjectID.Zombie, GV, 2.5, 60));
        // GV.GO.add(new Zombie(400, 600, 0, 0, GameObjectID.Zombie, GV, 2.5));
        // GV.GO.add(new Zombie(400, 500, 0, 0, GameObjectID.Zombie, GV, 2.5));
        GV.GO.add(new Player(400, 200, 0, 0, GameObjectID.Player, GV));
        spawn = true;
        
    }
    
}
