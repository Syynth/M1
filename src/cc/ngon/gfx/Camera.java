/**
 * @date Apr 30, 2013
 * @author Ben Cochrane
 */
package cc.ngon.gfx;

import org.lwjgl.util.vector.Vector2f;
import static org.lwjgl.opengl.GL11.*;

public class Camera {
    
    public Camera() {
        this(new Vector2f(640, 480), new Vector2f());
    }
    
    public Camera(Vector2f size, Vector2f position) {
        this.size = size;
        this.position = position;
    }

    public Camera moveTo(Vector2f position) {
        this.position.x = position.x;
        this.position.y = position.y;
        return this;
    }

    public Camera moveBy(Vector2f delta) {
        this.position.x += delta.x;
        this.position.y += delta.y;
        return this;
    }

    public Camera initGL() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, size.x, size.y, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        return this;
    }
    
    public Camera applyTransform() {
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();
        glTranslatef((int)position.x, (int)position.y, 0);
        return this;
    }
    
    public Vector2f size;
    public Vector2f position;
    
}
