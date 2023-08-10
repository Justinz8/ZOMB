package General;

import java.util.ArrayList;
import java.util.HashMap;

import Game.GameHandler;

public class GlobalVars {
    public PageID curPageID;

    public ArrayList<GameObject> GO;

    public HashMap<PageID, GameHandler> HandlerList;

    public boolean running;

    public double playerX, playerY;

    public void ChangePage(PageID n){
        GO.clear();
        curPageID=n;
        HandlerList.get(curPageID).init();
    }

    public GlobalVars(){
        GO = new ArrayList<GameObject>();
        HandlerList = new HashMap<PageID, GameHandler>();
        HandlerListInit();
    }

    private void HandlerListInit(){
        HandlerList.put(PageID.Game, new GameHandler(GO, this));
    }
}
