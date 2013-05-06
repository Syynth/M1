/**
 * @date May 6, 2013
 * @author Ben Cochrane
 */
package cc.ngon.m1.gfx;

import cc.ngon.m1.Resources;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;


public class Graphic {
    
    public Graphic(String texID, Vector2f size) {
        this.texture = Resources.getTexture(texID);
        this.size = new Vector2f(size);
        v[0] = new Vector2f(0, 0);
        v[1] = new Vector2f(size.x, 0);
        v[2] = new Vector2f(size.x, size.y);
        v[3] = new Vector2f(0, size.y);
        
        float fx = texture.getImageWidth() / texture.getTextureWidth(),
            fy = texture.getImageHeight() / texture.getTextureHeight();
        t[0] = new Vector2f(0, 0);
        t[1] = new Vector2f(fx, 0);
        t[2] = new Vector2f(fx, fy);
        t[3] = new Vector2f(0, fy);
        
    }
    
    public Texture texture() {
        return texture;
    }
    
    public Vector2f size() {
        return size;
    }
    
    public float width() {
        return size.x;
    }
    
    public float height() {
        return size.y;
    }
    
    protected Texture texture;
    protected Vector2f size;
    
    public Vector2f[] v;
    public Vector2f[] t;
}
