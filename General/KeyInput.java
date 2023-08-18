package General;

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

        if(key==KeyEvent.VK_L&&GV.scale<1.2){
            GV.scale+=0.1;
            GV.UpdatedScale=true;
        } 
        if(key==KeyEvent.VK_K&&GV.scale>0.7){
            GV.scale-=0.1;
            GV.UpdatedScale=true;
        } 

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
