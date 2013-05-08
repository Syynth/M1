/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.ngon.m1;

import cc.ngon.engine.Map;
import cc.ngon.io.Config;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 *
 * @author Ben Cochrane
 */
public class Main {

    public Main() {
        Config.LoadConfig("res/config.xml");
        fps = Config.getInt("prefs", "fps");
        
        try {
            Display.setDisplayMode(new DisplayMode(Config.getInt("prefs", "scrWidth"), Config.getInt("prefs", "scrHeight")));
            Display.create();
        } catch (LWJGLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        res = new R();
        res.initializeResources();
        
    }
    
    public void execute() {
        
        Map m = ((Map) res.get("maps").get("tiledmap0")).initialize();
        //new Map((Map)res.get("maps").get("tiledmap0")).initialize();
        
        while (!Display.isCloseRequested()) {
            m.update();
            m.render();
            Display.update();
            Display.sync(fps);
        }
        
        Display.destroy();
    }
    
    private int fps;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main app = new Main();
        app.execute();
    }
    
    private R res;
}
