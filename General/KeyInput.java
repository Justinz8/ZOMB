package General;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private GlobalVars GV;
    public KeyInput(GlobalVars GV){
        this.GV=GV;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0; i<GV.GO.size(); i++){
            GV.GO.get(i).keyTyped(key);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0; i<GV.GO.size(); i++){
            GV.GO.get(i).keyPressed(key);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0; i<GV.GO.size(); i++){
            GV.GO.get(i).keyReleased(key);
        }
    }
    
}
