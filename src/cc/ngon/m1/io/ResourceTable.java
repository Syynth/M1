package cc.ngon.m1.io;

import java.util.HashMap;

/**
 *
 * @author Ben Cochrane
 */
public abstract class ResourceTable {
    
    public ResourceTable() {
        res = new HashMap<>();
    }
    
    public Resource getResource(String key) {
        return res.get(key);
    }
    
    protected abstract void initializeResources();
    
    protected final ResourceTable addResource(String key, Resource value) {
        res.put(key, value);
        return this;
    }
    
    private HashMap<String, Resource> res;
    
}
