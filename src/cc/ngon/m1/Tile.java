/**
 * @date Apr 29, 2013
 * @author Ben Cochrane
 */
package cc.ngon.m1;

import org.lwjgl.util.vector.Vector2f;

public class Tile extends Entity {
    
    public Tile(Map m, Vector2f position) {
        super(m, position);
    }
    
    @Override
    public Object clone() {
        Tile t = (Tile)super.clone();
        return t;
    }
}
