/**
 * @date Apr 29, 2013
 * @author Ben Cochrane
 */
package cc.ngon.m1;

import cc.ngon.L;
import cc.ngon.m1.gfx.Tileset;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public final class Resources {

    private Resources() {
    }

    public static Texture getTexture(String key) {
        return texs.get(key);
    }

    public static Map getMap(String key) {
        return maps.get(key);
    }

    public static void loadAllFiles() {
        loadResourceDirectory("res/tex/", ".png", texs, new Delegate() {
            @Override
            public <T> T getValue(File f) throws Exception {
                return (T) TextureLoader.getTexture(".png", new FileInputStream(f));
            }
        });

        loadResourceDirectory("res/tex/tileset/", ".png", tilesets, new Delegate() {
            @Override
            public <T> T getValue(File f) throws Exception {
                return (T) TextureLoader.getTexture(".png", new FileInputStream(f));
            }
        });

        loadResourceDirectory("res/lev/", ".tmx", maps, new Delegate() {
            @Override
            public <T> T getValue(File f) throws Exception {
                return (T) loadMap(f);
            }
        });
    }
    
    private static String trim(String name) {
        return name.trim().substring(0, name.length() - 4);
    }

    private static <R> void loadResourceDirectory(String dir, String ext, HashMap<String, R> map, Delegate l) {
        File resDir = new File(dir);
        ArrayList<File> dirFiles = new ArrayList<>();
        dirFiles.addAll(Arrays.asList(resDir.listFiles()));
        for (File f : dirFiles) {
            if (!f.isDirectory() && f.getName().endsWith(ext)) {
                try {
                    l.apply(map, trim(f.getName()), l.getValue(f));
                } catch (Exception e) {
                    L.p(e);
                }
            }
        }
    }

    private static Map loadMap(File f) throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document d = reader.read(f.toURI().toURL());
        int w = 0, h = 0, tw = 0, th = 0;
        Element e = (Element) d.selectSingleNode("//map");
        w = Integer.parseInt(e.attributeValue("width"));
        h = Integer.parseInt(e.attributeValue("height"));
        tw = Integer.parseInt(e.attributeValue("tilewidth"));
        th = Integer.parseInt(e.attributeValue("tileheight"));
        Map m = new Map(w, h);
        d.selectSingleNode("//map");
        return m;
    }

    private abstract static class Delegate {

        public <T> void apply(HashMap<String, T> map, String key, Object value) {
            map.put(key, (T) value);
        }

        public abstract <T> T getValue(File f) throws Exception;
    }
    private static HashMap<String, Texture> texs = new HashMap<>();
    private static HashMap<String, Map> maps = new HashMap<>();
    private static HashMap<String, Tileset> tilesets = new HashMap<>();
}
