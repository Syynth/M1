/**
 * @date Apr 29, 2013
 * @author Ben Cochrane
 */
package cc.ngon.m1;

import cc.ngon.m1.gfx.Camera;

public class Map {

    public Map(Map source) {
        
    }
    
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void initialize() {
        camera = new Camera();
        camera.initGL();
    }

    public void update() {
        
    }

    public void render() {
        camera.renderMap(entityCache);
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
    
    protected int width;
    protected int height;
    protected Camera camera;
    protected Entity[] entityCache;
    
}