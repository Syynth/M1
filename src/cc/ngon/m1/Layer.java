/**
 * @date May 6, 2013
 * @author Ben Cochrane
 */
package cc.ngon.m1;

import java.util.ArrayList;


public class Layer {
    
    public Layer(Map m) {
        this.m = m;
        this.objects = new ArrayList<>();
        this.fx = new ArrayList<>();
    }
    
    public Layer(Layer l) {
        this.m = l.m;
        this.objects = new ArrayList<>();
        for (Entity e : l.objects) {
            objects.add((Entity) e.clone());
        }
        this.fx = new ArrayList<>();
        for (Entity e : l.fx) {
            fx.add((Entity) e.clone());
        }
    }
    
    public void render() {
        backdrop.render();
        for (Entity e : objects) {
            e.render();
        }
        for (Entity e : fx) {
            e.render();
        }
    }
    
    public Map m;
    
    public Entity backdrop;
    public ArrayList<Entity> objects;
    public ArrayList<Entity> fx;
    public int z;
    
}
