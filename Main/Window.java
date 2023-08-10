package Main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import General.GlobalVars;

public class Window extends WindowAdapter {
    
    private Game game;
    private GlobalVars GV;
    public Window(String name, Game game, GlobalVars GV, int width, int height){
        this.game=game;
        this.GV=GV;
        JFrame frame = new JFrame(name);

        frame.addWindowListener(this);

        frame.setBounds(0, 0, width, height);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game);
        frame.setVisible(true);
    }
    public void windowClosing(WindowEvent e) {
        GV.running=false;
    }
    
}
