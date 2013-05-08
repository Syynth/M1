/**
 * @date Apr 29, 2013
 * @author Ben Cochrane
 */
package cc.ngon.engine;

import cc.ngon.gfx.Camera;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class Map {

    public Map(Map source) {
        this(source.width, source.height);
    }
    
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        layerCache = new Layer[0];
        layers = new HashMap<>();
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
        for (Layer l : layerCache) {
            l.render();
        }
        return this;
    }
    
    public Map addLayer(String name, Layer layer) {
        layers.put(name, layer);
        buildLayerCache();
        return this;
    }
    
    public Layer getLayer(String name) {
        return layers.get(name);
    }
    
    private void buildLayerCache() {
        layerCache = new Layer[layers.size()];
        Collection<Layer> c = layers.values();
        layerCache = c.toArray(layerCache);
        Arrays.sort(layerCache, new Layer.LayerComparator());
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
    
    public HashMap<String, Layer> layers;
    protected Layer[] layerCache;
    
    protected int width;
    protected int height;
    protected Camera camera;
    
}