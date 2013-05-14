package cc.ngon.m1;

import cc.ngon.engine.*;
import cc.ngon.gfx.*;
import cc.ngon.io.*;
import cc.ngon.Utils;
import cc.ngon.engine.Layer;
import cc.ngon.engine.Map;
import cc.ngon.engine.Tile;
import cc.ngon.engine.TileMap;
import cc.ngon.gfx.Tileset;
import cc.ngon.io.L;
import cc.ngon.io.Config;
import cc.ngon.io.Resource;
import cc.ngon.io.ResourceTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @date Apr 29, 2013
 * @author Ben Cochrane
 */
public final class R extends ResourceTable {

    public R() {
        super();
    }

    @Override
    public void initializeResources() {
        /// <editor-fold defaultstate="closed" desc="Old loading code">
        try {
            addResource("textures", new Resource<Texture>(new Resource.Loader<Texture>(this) {
                @Override
                public Texture load(File f) throws Exception {
                    return TextureLoader.getTexture(".png", new FileInputStream(f));
                }
            })).get("textures").loadDirectory("res/tex/", ".png");
        } catch (Exception e) {
            L.ex(e);
        }

        try {
            addResource("tilesets", new Resource<Tileset>(new TilesetLoader(this)))
                    .get("tilesets").loadDirectory("res/tex/ts/", ".png");
        } catch (Exception e) {
            L.p("Did you change a directory name or move something?");
            L.ex(e);
        }

        try {
            addResource("maps", new Resource<Map>(new TiledMapLoader(this)))
                    .get("maps").loadDirectory("res/lev/", ".tmx");
        } catch (Exception e) {
            L.ex(e);
        }
        /// </editor-fold>
        try {
            DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
            fac.setNamespaceAware(true);
            Document d = fac.newDocumentBuilder().parse(new File("res/metronome.xml"));
            
            NodeList tilesets = d.getElementsByTagName("tileset");
            NodeList maps = d.getElementsByTagName("level");
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            L.ex(ex);
        }
        
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

    private class TiledMapLoader extends Resource.Loader {

        public TiledMapLoader(ResourceTable rt) {
            super(rt);
        }

        @Override
        public Map load(File f) throws Exception {
            DocumentBuilderFactory factoruu = DocumentBuilderFactory.newInstance();
            factoruu.setNamespaceAware(true);
            Document d = factoruu.newDocumentBuilder().parse(f);
            
            Node n = (Node) getXPathExpr("//map").evaluate(d, XPathConstants.NODE);
            Element e = (Element) n;
            
            int w = Utils.i(e.getAttribute("width"));
            int h = Utils.i(e.getAttribute("height"));
            int tw = Utils.i(e.getAttribute("tilewidth"));
            int th = Utils.i(e.getAttribute("tileheight"));

            Map m = new Map(w, h);
            m.addLayer("midground", new Layer(m));         
            m.addLayer("background", new Layer(m));

            NodeList layers = d.getElementsByTagName("layer");
            for (int i = 0; i < layers.getLength(); ++i) {
                Element data = (Element) ((Element) layers.item(i)).getElementsByTagName("data").item(0);
                TileMap tm = new TileMap(m, w, h);
                NodeList tiles = data.getElementsByTagName("tile");
                for (int k = 0; k < tiles.getLength(); ++k) {
                    Element tile = (Element) tiles.item(k);
                    int x = k % w, y = (int) Math.floor(k / w);
                    int gid = Utils.i(tile.getAttribute("gid"));
                    tm.tiles[x][y] = getTileFromGid(d, m, x, y, gid);
                }
                switch (((Element) layers.item(i)).getAttribute("name")) {
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
        
        private XPathExpression getXPathExpr(String xpath) {
            try {
                return XPathFactory.newInstance().newXPath().compile(xpath);
            } catch (XPathExpressionException ex) {
                L.ex(ex);
                return null;
            }
        }

        private Tile getTileFromGid(Document d, Map m, int x, int y, int gid) {
            if (gid == 0) {
                return null;
            }
            Tileset tileset = null;
            NodeList tilesets = d.getElementsByTagName("tileset");
            ArrayList<Tileset> ts = new ArrayList<>();
            for (int i = 0; i < tilesets.getLength(); ++i) {
                Element e = (Element) tilesets.item(i);
                String name = ((Element) e.getElementsByTagName("image").item(0)).getAttribute("source");
                name = name.substring(name.lastIndexOf("/") + 1).replaceAll(".png", "");
                ts.add(((Tileset) rt.get("tilesets").get(name)).setGidRange(Utils.i((e.getAttribute("firstgid")))));
            }
            for (Tileset t : ts) {
                if (t.inGidRange(gid)) {
                    tileset = t;
                    break;
                }
            }
            if (tileset != null) {
                return new Tile(m,
                        new Vector2f(x, y),
                        tileset.getTileSize(),
                        tileset.getTilePosFromGid(gid),
                        tileset);
            } else {
                return null;
            }
        }
    }
}
