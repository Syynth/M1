/**
 * @date May 7, 2013
 * @author Ben Cochrane
 */
package cc.ngon.gfx;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

public class Tileset extends Graphic {

    public Tileset(Texture texture, Vector2f size) {
        super(texture);
        gridsize = size;
        gidrange = new Vector2f(-1, -1);
    }
    
    public Tileset setGidRange(int firstgid) {
        gidrange = new Vector2f(firstgid, firstgid + gridsize.x * gridsize.y);
        return this;
    }
    
    public boolean inGidRange(int gid) {
        return gid >= gidrange.x && gid <= gidrange.y;
    }
    
    public Vector2f getUvFromTile(Vector2f tileCoord) {
        return new Vector2f(tileCoord.x / gridsize.x, tileCoord.y / gridsize.y);
    }
    
    public Vector2f getUvFromPixel(Vector2f pixelCoord) {
        return new Vector2f(pixelCoord.x / size.x, pixelCoord.y / size.y);
    }
    
    @Override
    public void render(Vector2f position) {
        
    }
    
    public void renderTile(Vector2f position, Vector2f size, Vector2f tile) {
        v[0] = new Vector2f();
        v[1] = new Vector2f(size.x, 0);
        v[2] = new Vector2f(size);
        v[3] = new Vector2f(0, size.y);
        t[0] = new Vector2f(getUvFromTile(tile));
        t[1] = new Vector2f(getUvFromTile(tile.translate(1, 0)));
        t[2] = new Vector2f(getUvFromTile(tile.translate(1, 1)));
        t[3] = new Vector2f(getUvFromTile(tile.translate(0, 1)));
        super.render(position);
    }
    
    protected Vector2f gridsize;
    protected Vector2f gidrange;
}
