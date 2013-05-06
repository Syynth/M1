/**
 * @date Apr 29, 2013
 * @author Ben Cochrane
 */
package cc.ngon.m1;

import cc.ngon.m1.gfx.Graphic;
import org.lwjgl.util.vector.Vector2f;

public class Entity {

    public Entity(Map m, Vector2f position) {
        this.m = m;
        this.position = new Vector2f(position);
    }
    public Vector2f position;
    public Graphic sprite;
    protected Map m;
}
