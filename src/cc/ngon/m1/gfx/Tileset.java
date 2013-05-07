/**
 * @date May 7, 2013
 * @author Ben Cochrane
 */
package cc.ngon.m1.gfx;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

public class Tileset extends Graphic {

    public Tileset(Texture texture, Vector2f position, int tileswide, int tileshigh) {
        super(texture, position);
        this.width = texture.getImageWidth();
        this.height = texture.getImageHeight();
        this.twidth = tileswide;
        this.theight = tileshigh;
    }
    
    public Vector2f getUvFromTile(Vector2f tileCoord) {
        return new Vector2f(tileCoord.x / twidth, tileCoord.y / theight);
    }
    
    public Vector2f getUvFromPixel(Vector2f pixelCoord) {
        return new Vector2f(pixelCoord.x / width, pixelCoord.y / height);
    }
    
    protected float width;
    protected float height;
    protected float twidth;
    protected float theight;
}
