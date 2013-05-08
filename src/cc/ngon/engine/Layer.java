/**
 * @date May 6, 2013
 * @author Ben Cochrane
 */
package cc.ngon.engine;

import java.util.ArrayList;
import java.util.Comparator;

public class Layer {

    public Layer(Map m) {
        this.m = m;
        this.objects = new ArrayList<>();
        this.fx = new ArrayList<>();
        this.backdrop = null;
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
        if (backdrop != null) {
            backdrop.render();
        }
        for (Entity e : objects) {
            e.render();
        }
        for (Entity e : fx) {
            e.render();
        }
    }

    @Override
    public Object clone() {
        return new Layer(this);
    }

    public static class LayerComparator implements Comparator<Layer> {

        @Override
        public int compare(Layer o1, Layer o2) {
            return o1.z - o2.z;
        }
    }
    public Map m;
    public Entity backdrop;
    public ArrayList<Entity> objects;
    public ArrayList<Entity> fx;
    public int z;
}
