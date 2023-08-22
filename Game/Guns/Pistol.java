package Game.Guns;

import java.awt.Graphics2D;
import java.awt.Polygon;

import General.GameObject;
import General.GameObjectID;
import General.GlobalVars;

public class Pistol extends gun{

    public Pistol(double x, double y, GameObjectID GOID, GlobalVars GV) {
        super(x, y, GOID, GV, 1, false);
        int[] tempx = {(int)x, (int)((x+10)*GV.scale), (int)((x+10)*GV.scale), (int)x};
        int[] tempy = {(int)y, (int)y, (int)((y+5)*GV.scale), (int)((y+5)*GV.scale)};
        ogbody = new Polygon(tempx, tempy, tempx.length);
    }

    @Override
    public void shoot() {
        GV.GO.add(new Tracer((x-12.5)*GV.scale, (y+25)*GV.scale, GameObjectID.Player, GV, mx, my, 500, 20, 1));
    }

    @Override
    public void initBody() {
        ogbody.translate((int)((x-ogbody.xpoints[0]/GV.scale)*GV.scale), (int)((y-ogbody.ypoints[0]/GV.scale)*GV.scale));
    }

    @Override
    public void render(Graphics2D g) {

    }


    
}
