/**
 * @date Apr 29, 2013
 * @author Ben Cochrane
 */
package cc.ngon.engine;

import cc.ngon.gfx.Tileset;
import cc.ngon.io.L;
import org.lwjgl.util.vector.Vector2f;

public class Tile extends Entity {
    
    public Tile(Map m, Vector2f position, Vector2f size, Vector2f texpos, Tileset tileset) {
        super(m, position);
        this.size = size;
        this.tileset = tileset;
        this.texpos = texpos;
    }
    
    @Override
    public void render() {
        tileset.renderTile(new Vector2f(position.x * size.x, position.y * size.y), size, texpos);
    }
    
    @Override
    public Object clone() {
        Tile t = (Tile)super.clone();
        return t;
    }
    
    @Override
    public String toString() {
        return "[size:" + size + ", position:" + position + ", texpos:" + texpos + "]";
    }
    
    public Vector2f size;
    public Vector2f texpos;
    public Tileset tileset;
}
