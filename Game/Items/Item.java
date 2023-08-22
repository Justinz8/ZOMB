package Game.Items;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import General.GameObject;
import General.GameObjectID;
import General.GlobalVars;

public abstract class Item extends GameObject {

    public Item(double x, double y, double width, double height, GlobalVars GV) {
        super(x, y, 0, 0, GameObjectID.Item, GV);  
        setHitBox(new Rectangle2D.Double(x, y, width, height));
    }

    public abstract void render(Graphics2D g);

    public abstract void Picked();

    public void tick(){
        if(getHitBox().intersects(GV.playHitBox)){
            Picked();
            GV.GO.remove(this);
            return;
        }
    }
}
