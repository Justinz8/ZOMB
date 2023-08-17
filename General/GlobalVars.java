package General;

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

    public void ChangePage(PageID n){
        GO.clear();
        curPageID=n;
        HandlerList.get(curPageID).init();
    }

    public GlobalVars(){
        GO = new ArrayList<GameObject>();
        HandlerList = new HashMap<PageID, GameHandler>();
        HandlerListInit();
        rand = new Random();
    }

    public double getTransX(){
        return (-playerX-12.5+Tools.WIDTH/2.0/scale);
    }
    public double getTransY(){
        return (-playerY-25+Tools.HEIGHT/2.0/scale);
    }

    public boolean roll(int percentChance){
        if(rand.nextInt(100)<=percentChance) return true;
        return false;
    }

    private void HandlerListInit(){
        HandlerList.put(PageID.Game, new GameHandler(GO, this));
    }
}
