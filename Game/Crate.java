package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;

import Game.Items.FriedChicken;
import General.GameObject;
import General.GameObjectID;
import General.GlobalVars;

public class Crate extends GameObject {

    private boolean E;
    private boolean NoMoney;
    private int tickCounter;
    private Color col;


    public Crate(double x, double y, GlobalVars GV) {
        super(x, y, 0, 0, GameObjectID.Obstacle, GV);
        int tempx[] = {(int)x, (int)x, (int)x+15, (int)x+15};
        int tempy[] = {(int)y, (int)y+15, (int)y+15, (int)y};
        ogbody = new Polygon(tempx, tempy, tempx.length);
        E=false;
        tickCounter=0;
        col = Color.yellow;
        initBody();
    }

    public void render(Graphics2D g) {
        g.setColor(col);
        g.draw(body);
    }

    public void tick() {
        if(NoMoney){
            tickCounter++;
            if(tickCounter>=30){
                tickCounter=0;
                NoMoney = false;
                col = Color.yellow;
            }
        }

        initBody();
        setHitBox((Rectangle.Double)body.getBounds2D());
        if(getHitBox().intersects(GV.playHitBox)&&E){
            if(GV.Money>=20){
                GV.GO.add(new FriedChicken(x, y, GV));
            }else{
                NoMoney = true;
                col = Color.red;
            }
        }
    }
    public void keyPressed(int e) {
        if(e==KeyEvent.VK_E){
            E=true;
        }
    }

    public void keyReleased(int e) {
        if(e==KeyEvent.VK_E){
            E=false;
        }
    }
}
