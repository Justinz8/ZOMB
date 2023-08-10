package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import General.*;

public class GameHandler extends Handler {

    public GameHandler(ArrayList<GameObject> GO, GlobalVars GV) {
        super(GO, GV);
    }


    public void extraTick() {
        
    }

    public void extraRender(Graphics2D g) {
        
    }


    public void background(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Tools.WIDTH, Tools.HEIGHT);
    }


    public void init() {
        GV.GO.add(new Player(400, 200, 0, 0, GameObjectID.Player, GV));
        GV.GO.add(new Zombie(400, 100, 0, 0, GameObjectID.Zombie, GV, 2.5));
        GV.GO.add(new Zombie(400, 400, 0, 0, GameObjectID.Zombie, GV, 2.5));
    }
    
}
