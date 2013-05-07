package cc.ngon.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author Ben Cochrane
 */
public class Resource<R> {

    public Resource(Loader loader) {
        this.loader = loader;
        this.map = new HashMap<>();
    }

    public Resource<R> load(String directory, String extension) throws Exception {
        File dir = new File(directory);
        ArrayList<File> files = new ArrayList<>();
        files.addAll(Arrays.asList(dir.listFiles()));
        for (File f : files) {
            if (!f.isDirectory() && f.getName().endsWith(extension)) {
                map.put(f.getName().replaceAll(extension, ""), (R) loader.load(f));
            }
        }
        return this;
    }

    public R get(String key) {
        return map.get(key);
    }

    public abstract static class Loader<T> {

        public abstract T load(File f) throws Exception;
    }
    
    protected Loader loader;
    protected HashMap<String, R> map;
}
