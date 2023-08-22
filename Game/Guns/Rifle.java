package Game.Guns;

import java.awt.Graphics2D;
import java.awt.Polygon;

import General.GameObjectID;
import General.GlobalVars;

public class Rifle extends gun {
    
    public Rifle(double x, double y, GameObjectID GOID, GlobalVars GV) {
        super(x, y, GOID, GV, 0.5, true);
        int[] tempx = {(int)x, (int)(x+35*GV.scale), (int)(x+35*GV.scale), (int)x};
        int[] tempy = {(int)y, (int)y, (int)(y+5*GV.scale), (int)(y+5*GV.scale)};
        ogbody = new Polygon(tempx, tempy, tempx.length);
    }


    @Override
    public void shoot() {
        GV.GO.add(new Tracer(x-12.5, y+25, GameObjectID.Player, GV, mx, my, 500, 10, 50));
    }


    @Override
    public void render(Graphics2D g) {
    }

}
