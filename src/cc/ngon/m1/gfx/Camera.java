/**
 * @date Apr 30, 2013
 * @author Ben Cochrane
 */
package cc.ngon.m1.gfx;

import cc.ngon.m1.Tile;
import cc.ngon.m1.Map;
import cc.ngon.L;
import cc.ngon.m1.Entity;
import org.lwjgl.util.vector.Vector3f;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;

public class Camera {
    
    public Camera() {
        this(60, 640/480, 0.1f, 1000);
    }

    public Camera(float angle, float aspect, float znear, float zfar) {
        this.angle = angle;
        this.aspect = aspect;
        this.znear = znear;
        this.zfar = zfar;

        position = new Vector3f();
        rotation = new Vector3f();
    }

    public Camera moveTo(Vector3f position) {
        this.position.x = position.x;
        this.position.y = position.y;
        this.position.z = position.z;
        return this;
    }

    public Camera moveBy(Vector3f delta) {
        this.position.x += delta.x;
        this.position.y += delta.y;
        this.position.z += delta.z;
        return this;
    }

    public Camera rotateTo(Vector3f rotation) {
        this.rotation.x = rotation.x;
        this.rotation.y = rotation.y;
        this.rotation.z = rotation.z;
        return this;
    }

    public Camera rotateBy(Vector3f delta) {
        this.rotation.x += delta.x;
        this.rotation.y += delta.y;
        this.rotation.z += delta.z;
        return this;
    }
    
    public Camera initGL() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        return this;
    }
    
    public Camera renderMap(Map m) {
        glTranslatef(position.x, position.y, 0);
        glClear(GL_COLOR_BUFFER_BIT);
        m.bg.render();
        m.mg.render();
        m.fg.render();
        m.display.render();
        return this;
    }
    
    public float angle;
    public float aspect;
    public float znear;
    public float zfar;
    public Vector3f position;
    public Vector3f rotation;
    
}
