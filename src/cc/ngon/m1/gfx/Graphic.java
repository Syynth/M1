/**
 * @date May 6, 2013
 * @author Ben Cochrane
 */
package cc.ngon.m1.gfx;

import cc.ngon.m1.Resources;

import org.newdawn.slick.opengl.Texture;

import org.lwjgl.util.vector.Vector2f;
import static org.lwjgl.opengl.GL11.*;


public class Graphic {
    
    public Graphic(Graphic g) {
        this(g.texture(), g.size());
    }
    
    public Graphic(Texture tex, Vector2f size) {
        this.texture = tex;
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
    
    public void render(float x, float y) {
        texture.bind();
        glBegin(GL_QUADS);
        for (int i = 0; i < 4; ++i) {
            glTexCoord2f(t[i].x, t[i].y);
            glVertex2f(v[i].x, v[i].y);
        }
        glEnd();
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
