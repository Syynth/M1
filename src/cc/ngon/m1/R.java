/**
 * @date Apr 29, 2013
 * @author Ben Cochrane
 */
package cc.ngon.m1;

import cc.ngon.engine.Layer;
import cc.ngon.engine.Map;
import cc.ngon.engine.Tile;
import cc.ngon.engine.TileMap;
import cc.ngon.io.L;
import cc.ngon.gfx.Tileset;
import cc.ngon.io.Config;
import cc.ngon.io.Resource;
import cc.ngon.io.ResourceTable;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.lwjgl.util.vector.Vector2f;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public final class R extends ResourceTable {

    public R() {
        super();
    }

    @Override
    public void initializeResources() {
        try {
            addResource("textures", new Resource<Texture>(new Resource.Loader<Texture>(this) {
                @Override
                public Texture load(File f) throws Exception {
                    return TextureLoader.getTexture(".png", new FileInputStream(f));
                }
            })).get("textures").load("res/tex/", ".png");
        } catch (Exception e) {
            L.ex(e);
        }

        try {
            addResource("tilesets", new Resource<Tileset>(new TilesetLoader(this)))
                    .get("tilesets").load("res/tex/tileset/", ".png");
        } catch (Exception e) {
            L.ex(e);
        }

        try {
            addResource("maps", new Resource<Map>(new MapLoader(this)))
                    .get("maps").load("res/lev/", ".tmx");
        } catch (Exception e) {
            L.ex(e);
        }
        L.p(get("maps"));
    }

    private class TilesetLoader extends Resource.Loader {

        public TilesetLoader(ResourceTable rt) {
            super(rt);
        }

        @Override
        public Tileset load(File f) throws Exception {
            String name = f.getName().replace(".png", "");
            return new Tileset(TextureLoader.getTexture(".png", new FileInputStream(f)),
                    new Vector2f(Config.getInt(name, "tilewidth"), Config.getInt(name, "tileheight")));
        }
    }

    private class MapLoader extends Resource.Loader {

        public MapLoader(ResourceTable rt) {
            super(rt);
        }

        @Override
        public Map load(File f) throws Exception {
            SAXReader reader = new SAXReader();
            Document d = reader.read(f.toURI().toURL());
            Element e = (Element) d.selectSingleNode("//map");

            int w = 0, h = 0, tw = 0, th = 0;
            w = Integer.parseInt(e.attributeValue("width"));
            h = Integer.parseInt(e.attributeValue("height"));
            tw = Integer.parseInt(e.attributeValue("tilewidth"));
            th = Integer.parseInt(e.attributeValue("tileheight"));

            Map m = new Map(w, h);
            m.addLayer("background", new Layer(m));
            m.addLayer("midground", new Layer(m));

            for (Iterator i = d.getRootElement().elementIterator("layer"); i.hasNext();) {
                Node layer = (Node) i.next();
                Node data = layer.selectSingleNode("data");

                int k = 0;
                TileMap tm = new TileMap(m, w, h);
                for (Iterator j = ((Element) data).elementIterator("tile"); j.hasNext();) {
                    Element tile = (Element) j.next();
                    tm.tiles[k % w][(int) Math.floor(k / w)] = getTileFromGid(d, m, Integer.parseInt(tile.attributeValue("gid")));
                    k++;
                }
                switch (((Element) layer).attributeValue("name")) {
                    case "background_tiles":
                        m.getLayer("background").backdrop = tm;
                        break;
                    case "midground_tiles":
                        m.getLayer("midground").backdrop = tm;
                        break;
                }
            }

            return m;
        }

        private Tile getTileFromGid(Document d, Map m, int gid) {
            if (gid == 0) {
                return null;
            }
            Tileset tileset = null;
            List tilesets = d.selectNodes("//map/tileset");
            ArrayList<Tileset> ts = new ArrayList<>();
            // get tileset name, then get tileset, then firstgid, then set gid
            for (Iterator it = tilesets.iterator(); it.hasNext();) {
                Node n = (Node) it.next();
                String name = ((Element) n.selectSingleNode("image")).attributeValue("source");
                name = name.substring(name.lastIndexOf("/") + 1).replaceAll(".png", "");
                ts.add(((Tileset) rt.get("tilesets").get(name)).setGidRange(Integer.parseInt(((Element) n).attributeValue("firstgid"))));
            }
            // find appropriate tileset to put in 
            for (Tileset t : ts) {
                if (t.inGidRange(gid)) {
                    tileset = t;
                    break;
                }
            }
            if (tileset != null) {
                Tile tile = new Tile(m, tileset.getTilePosFromGid(gid),
                        tileset.getTileSize(), tileset);
                L.p(tile);
                return new Tile(m, tileset.getTilePosFromGid(gid),
                        tileset.getTileSize(), tileset);
            } else {
                return null;
            }
        }
    }
}
