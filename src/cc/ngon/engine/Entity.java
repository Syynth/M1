/**
 * @date Apr 29, 2013
 * @author Ben Cochrane
 */
package cc.ngon.engine;

import cc.ngon.io.L;
import cc.ngon.gfx.Graphic;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.util.vector.Vector2f;

public class Entity {

    public Entity(Map m, Vector2f position) {
        this.m = m;
        this.position = new Vector2f(position);
    }
    
    public void update() {
        
    }
    
    public void render() {
        
    }
    
    @Override
    public Object clone() {
        Entity e = new Entity(m, position);
        e.position = new Vector2f(position);
        e.sprite = new Graphic(sprite);
        return e;
    }
    
    public Vector2f position;
    public Graphic sprite;
    protected Map m;

    private Vector2f Vector2f(Vector2f position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
