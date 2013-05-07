/**
 * @date May 7, 2013
 * @author Ben Cochrane
 */
package cc.ngon.m1.gfx;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

public class Tileset extends Graphic {

    public Tileset(Texture texture, Vector2f size) {
        super(texture);
        gridsize = size;
    }
    
    public Vector2f getUvFromTile(Vector2f tileCoord) {
        return new Vector2f(tileCoord.x / gridsize.x, tileCoord.y / gridsize.y);
    }
    
    public Vector2f getUvFromPixel(Vector2f pixelCoord) {
        return new Vector2f(pixelCoord.x / size.x, pixelCoord.y / size.y);
    }
    
    protected Vector2f gridsize;
}
