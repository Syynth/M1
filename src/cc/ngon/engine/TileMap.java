/**
 * @date May 6, 2013
 * @author Ben Cochrane
 */
package cc.ngon.engine;

import cc.ngon.engine.Tile;
import cc.ngon.engine.Entity;
import org.lwjgl.util.vector.Vector2f;


public class TileMap extends Entity {
    
    public TileMap(Map m, int width, int height) {
        super(m, new Vector2f());
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
    }
    
    @Override
    public void render() {
        for (Tile[] ts : tiles) {
            for (Tile t : ts) {
                t.render();
            }
        }
    }
    
    @Override
    public Object clone() {
        TileMap e = (TileMap)super.clone();
        e.width = width;
        e.height = height;
        e.tiles = new Tile[width][height];
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                e.tiles[i][j] = (Tile)tiles[i][j].clone();
            }
        }
        return e;
    }
    
    public Tile[][] tiles;
    
    protected int width;
    protected int height;
    
}
