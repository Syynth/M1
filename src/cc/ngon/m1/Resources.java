/**
 * @date Apr 29, 2013
 * @author Ben Cochrane
 */
package cc.ngon.m1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public final class Resources {
    
    public static Texture getTexture(String key) {
        return texs.get(key);
    }
    
    public static Map getMap(String key) {
        return maps.get(key);
    }

    public static void loadAllFiles() {
        File texDir = new File("res/tex/");
        ArrayList<File> texFiles = new ArrayList<>();
        texFiles.addAll(Arrays.asList(texDir.listFiles()));
        for (File f : texFiles) {
            if (!f.isDirectory() && f.getName().endsWith(".png")) {
                try {
                    texs.put(trimFilename(f.getName()), TextureLoader.getTexture(".png", new FileInputStream(f)));
                } catch (IOException ex) {
                    Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private static String trimFilename(String name) {
        return name.trim().substring(0, name.length() - 4);
    }
    
    private Resources() {}
    
    private static HashMap<String, Texture> texs = new HashMap<>();
    private static HashMap<String, Map> maps = new HashMap<>();
    
}
