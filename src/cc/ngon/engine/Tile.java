/**
 * @date Apr 29, 2013
 * @author Ben Cochrane
 */
package cc.ngon.engine;

import cc.ngon.gfx.Tileset;
import org.lwjgl.util.vector.Vector2f;

public class Tile extends Entity {
    
    public Tile(Map m, Vector2f position, Vector2f size, Tileset tileset) {
        super(m, position);
        this.size = size;
        this.tileset = tileset;
    }
    
    @Override
    public void render() {
        tileset.renderTile(new Vector2f(position.x * size.x, position.y * size.y), size, position);
    }
    
    @Override
    public Object clone() {
        Tile t = (Tile)super.clone();
        return t;
    }
    
    public Vector2f size;
    public Tileset tileset;
}
