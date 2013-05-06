/**
 * @date May 6, 2013
 * @author Ben Cochrane
 */
package cc.ngon.m1;

import cc.ngon.L;
import cc.ngon.m1.gfx.Graphic;
import org.lwjgl.util.vector.Vector2f;


public class TileMap extends Entity {
    
    public TileMap(Map m, Vector2f position, int width, int height) {
        super(m, position);
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
        // TODO: Finish all deep copies
        return null;
    }
    
    public Tile[][] tiles;
    
    protected int width;
    protected int height;
    
}
