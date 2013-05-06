/**
 * @date Apr 29, 2013
 * @author Ben Cochrane
 */
package cc.ngon.m1;

import cc.ngon.L;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.MalformedURLException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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
                    texs.put(trim(f.getName()), TextureLoader.getTexture(".png", new FileInputStream(f)));
                } catch (IOException ex) {
                    Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        File mapDir = new File("res/lev/");
        ArrayList<File> mapFiles = new ArrayList<>();
        mapFiles.addAll(Arrays.asList(mapDir.listFiles()));
        for (File f : mapFiles) {
            if (!f.isDirectory() && f.getName().endsWith(".tmx")) {
                try {
                    maps.put(trim(f.getName()), loadMap(f));
                } catch (IOException | DocumentException ex) {
                    L.p(ex);
                }
            }
        }
    }
    
    private static Map loadMap(File f) throws IOException, DocumentException {
        Map m = null;
        SAXReader reader = new SAXReader();
        Document d = reader.read(f.toURI().toURL());
        int w = 0, h = 0, tw = 0, th = 0;
        Element e = (Element)d.selectSingleNode("//map");
        w = Integer.parseInt(e.attributeValue("width"));
        h = Integer.parseInt(e.attributeValue("height"));
        tw = Integer.parseInt(e.attributeValue("tilewidth"));
        th = Integer.parseInt(e.attributeValue("tileheight"));
        m = new Map(w, h);
        return m;
    }
    
    private static String trim(String name) {
        return name.trim().substring(0, name.length() - 4);
    }
    
    private Resources() {}
    
    private static HashMap<String, Texture> texs = new HashMap<>();
    private static HashMap<String, Map> maps = new HashMap<>();
    
}
