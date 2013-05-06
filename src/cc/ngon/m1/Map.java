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
        bg = new Layer(this);
        mg = new Layer(this);
        fg = new Layer(this);
        display = new Layer(this);
    }
    
    public Map initialize() {
        camera = new Camera();
        camera.initGL();
        return this;
    }

    public Map update() {
        return this;
    }

    public Map render() {
        camera.renderMap(this);
        return this;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
    
    public Layer bg;
    public Layer mg;
    public Layer fg;
    public Layer display;
    
    protected int width;
    protected int height;
    protected Camera camera;
    
}