/**
 * @date Apr 29, 2013
 * @author Ben Cochrane
 */
package cc.ngon.engine;

import cc.ngon.gfx.Camera;

public class Map {

    public Map(Map source) {
        if (source != null) {
            this.width = source.width;
            this.height = source.height;
            this.bg = new Layer(source.bg);
            this.mg = new Layer(source.mg);
            this.fg = new Layer(source.fg);
            this.display = new Layer(source.display);
        } else {
            throw new IllegalArgumentException("source paramter in cc.ngon.engine.Map(Map source) must not be null!");
        }
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
        camera.applyTransform();
        bg.render();
        mg.render();
        fg.render();
        display.render();
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