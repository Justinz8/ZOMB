package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.tools.Tool;

import General.*;

public class GameHandler extends Handler {

    private boolean spawn;
    private int tickcounter;
    private double count;
    private boolean InvMenu;

    public GameHandler(ArrayList<GameObject> GO, GlobalVars GV) {
        super(GO, GV);
        tickcounter = 0;
        count = 5;
        InvMenu=false;
    }


    public void extraTick() {

    }

    public void extraRender(Graphics2D g) {
        
    }


    public void background(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect((int)(-GV.getTransX()), (int)(-GV.getTransY()), (int)(Tools.WIDTH/GV.scale), (int)(Tools.HEIGHT/GV.scale));
        g.setColor(Color.white);
        g.drawRect(-5000, -5000, 10000, 10000);
    }


    public void init() {
        GV.dmgMult=1;
        GV.InvMenu=false;
        //spawnWave();
        //GV.GO.add(new Zombie(400, 400, 0, 0, GameObjectID.Zombie, GV, 2.5, 60));
        // GV.GO.add(new Zombie(400, 600, 0, 0, GameObjectID.Zombie, GV, 2.5));
        // GV.GO.add(new Zombie(400, 500, 0, 0, GameObjectID.Zombie, GV, 2.5));
        GV.GO.add(new Player(400, 200, 0, 0, GameObjectID.Player, GV));
        //spawn = true;
        
    }

    private void spawnWave(){
        for(int i = 0; i<5; i++){
            int temp = GV.rand.nextInt(4);
            double tempx = GV.rand.nextInt(Tools.WIDTH)-Tools.WIDTH/2.0+GV.playerX;
            double tempy = GV.rand.nextInt(Tools.HEIGHT)-Tools.HEIGHT/2.0+GV.playerY;
            switch(temp){
                case 0:
                    GV.GO.add(new Zombie(tempx, GV.playerY-800, 0, 0, GameObjectID.Zombie, GV, 2.5, 60));
                    break;
                case 1:
                    GV.GO.add(new Zombie(tempx, GV.playerY+800, 0, 0, GameObjectID.Zombie, GV, 2.5, 60));
                    break;
                case 2:
                    GV.GO.add(new Zombie(GV.playerX+1000, tempy, 0, 0, GameObjectID.Zombie, GV, 2.5, 60));
                    break;
                case 3:
                    GV.GO.add(new Zombie(GV.playerX-1000, tempy, 0, 0, GameObjectID.Zombie, GV, 2.5, 60));
                    break;
            }
        }
    }


    public void PriorityTick() {
        InvMenu = GV.InvMenu;
        tickcounter++;
        if(tickcounter/Tools.amountOfTicks>=count){
            tickcounter = 0;
            spawnWave();
        }
    }

    public void PriorityRender(Graphics2D g) {
        if(InvMenu){
            Inventory(g);
        }
    }

    public void Inventory(Graphics2D g){
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(100-(int)GV.getTransX(), 100-(int)GV.getTransY(), Tools.WIDTH-200, Tools.HEIGHT-200);
        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.BOLD, 40));
        g.drawString("Damage x: "+GV.dmgMult, 150-(int)GV.getTransX(), 150-(int)GV.getTransY());
        for(int i = 0; i<GV.Inventory.size(); i++){
            int x = 150-(int)GV.getTransX();
            int y = 200+i*50-(int)GV.getTransY();
            String ItemName = (String)GV.Inventory.keySet().toArray()[i];
            g.drawString(ItemName, x, y);
            g.drawString(""+GV.Inventory.get(ItemName), x+500, y);
        }
    }
}
