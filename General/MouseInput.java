package General;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

public class MouseInput extends MouseInputAdapter {
    GlobalVars GV;
    public MouseInput(GlobalVars GV){
        this.GV=GV;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int key = e.getButton();
        for(int i = 0; i<GV.GO.size(); i++){
            GV.GO.get(i).mouseClicked(key);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int key = e.getButton();
        for(int i = 0; i<GV.GO.size(); i++){
            GV.GO.get(i).mousePressed(key);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int key = e.getButton();
        for(int i = 0; i<GV.GO.size(); i++){
            GV.GO.get(i).mouseReleased(key);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        int key = e.getButton();
        for(int i = 0; i<GV.GO.size(); i++){
            GV.GO.get(i).mouseEntered(key);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        int key = e.getButton();
        for(int i = 0; i<GV.GO.size(); i++){
            GV.GO.get(i).mouseExited(key);
        }
    }
    
}
