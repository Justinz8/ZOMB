package General;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import Game.GameHandler;

public class GlobalVars {
    public PageID curPageID;

    public ArrayList<GameObject> GO;

    public HashMap<PageID, GameHandler> HandlerList;

    public boolean running;

    public double playerX, playerY;

    public Random rand;

    public double scale;

    public boolean UpdatedScale;

    public Rectangle2D.Double playHitBox;

    public HashMap<String, Integer> Inventory;

    public void ChangePage(PageID n){
        GO.clear();
        curPageID=n;
        HandlerList.get(curPageID).init();
    }

    public GlobalVars(){
        GO = new ArrayList<GameObject>();
        Inventory = new HashMap<String, Integer>();
        HandlerList = new HashMap<PageID, GameHandler>();
        HandlerListInit();
        rand = new Random();

        Inventory.put("Chicken", 0);
    }

    public double getTransX(){
        return (-playerX+Tools.WIDTH/2.0/scale);
    }
    public double getTransY(){
        return (-playerY+Tools.HEIGHT/2.0/scale);
    }

    public boolean roll(int percentChance){
        if(rand.nextInt(100)<=percentChance) return true;
        return false;
    }

    private void HandlerListInit(){
        HandlerList.put(PageID.Game, new GameHandler(GO, this));
    }
}
