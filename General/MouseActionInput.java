package General;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseActionInput implements MouseMotionListener{

    private GlobalVars GV;

    public MouseActionInput(GlobalVars GV){
        this.GV=GV;
    }

    public void mouseDragged(MouseEvent e) {
        for(int i = 0; i<GV.GO.size(); i++){
            GV.GO.get(i).mouseDragged((int)(e.getX()/GV.scale), (int)(e.getY()/GV.scale));
        }
    }

    public void mouseMoved(MouseEvent e) {
        for(int i = 0; i<GV.GO.size(); i++){
            GV.GO.get(i).mouseMoved((int)(e.getX()/GV.scale), (int)(e.getY()/GV.scale));
        }
    }

}