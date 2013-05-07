/**
 * @date Apr 29, 2013
 * @author Ben Cochrane
 */
package cc.ngon.m1;

import cc.ngon.engine.Map;
import cc.ngon.io.L;
import cc.ngon.gfx.Tileset;
import cc.ngon.io.Config;
import cc.ngon.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lwjgl.util.vector.Vector2f;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public final class R extends cc.ngon.io.ResourceTable {

    public R() {
    }

    @Override
    public void initializeResources() {
        try {
            addResource("textures", new Resource<Texture>(new Resource.Loader<Texture>() {
                @Override
                public Texture load(File f) throws Exception {
                    return TextureLoader.getTexture(".png", new FileInputStream(f));
                }
            })).get("textures").load("res/tex/", ".png");
        } catch (Exception e) { L.ex(e); }
        
        try {
            addResource("tilesets", new Resource<Tileset>(new TilesetLoader()))
                .get("tilesets").load("res/tex/tileset/", ".png");
        } catch (Exception e) { L.ex(e); }
        
        try {
            addResource("maps", new Resource<Map>(new MapLoader()))
                .get("maps").load("res/lev/", ".png");
        } catch (Exception e) { L.ex(e); }
    }

    private class TilesetLoader extends Resource.Loader {

        @Override
        public Tileset load(File f) throws Exception {
            String name = f.getName().replace(".png", "");
            return new Tileset(TextureLoader.getTexture(".png", new FileInputStream(f)),
                    new Vector2f(Config.getInt(name, "tilewidth"), Config.getInt(name, "tileheight")));
        }
    }

    private class MapLoader extends Resource.Loader {

        @Override
        public Map load(File f) throws Exception {
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
    }
}
