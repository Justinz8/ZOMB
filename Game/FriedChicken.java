package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import General.GameObjectID;
import General.GlobalVars;

public class FriedChicken extends Item {

    public FriedChicken(double x, double y, GlobalVars GV) {
        super(x, y, 25, 25, GV);
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.drawRect((int)x, (int)y, 25, 25);
    }

    public void Picked() {
        GV.Inventory.put("Chicken", GV.Inventory.get("Chicken")+1);
        GV.dmgMult=GV.dmgMult+(double)0.1;        
        GV.dmgMult=(int)(GV.dmgMult*1000)/1000.0;
    }
    

}
