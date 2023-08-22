package Game.Guns;

import java.awt.Graphics2D;
import java.awt.Polygon;

import General.GameObjectID;
import General.GlobalVars;

public class Shotgun extends gun {

    public Shotgun(double x, double y, GameObjectID GOID, GlobalVars GV) {
        super(x, y, GOID, GV, 0.25, true);
        int[] tempx = {(int)x, (int)x+30, (int)x+30, (int)x};
        int[] tempy = {(int)y, (int)y, (int)y+7, (int)y+7};
        ogbody = new Polygon(tempx, tempy, tempx.length);
    }

    @Override
    public void shoot() {
        for(int i = 0; i<8; i++){
            double var1 = GV.rand.nextInt(900)/10.0-45;
            double var2 = GV.rand.nextInt(900)/10.0-45;
            GV.GO.add(new Tracer(x-12.5, y+25, GameObjectID.Player, GV, mx+var1, my+var2, 175, 8, 1));
        }
    }

    @Override
    public void render(Graphics2D g) {

    }


    
}
