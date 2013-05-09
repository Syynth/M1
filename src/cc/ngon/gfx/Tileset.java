/**
 * @date May 7, 2013
 * @author Ben Cochrane
 */
package cc.ngon.gfx;

import cc.ngon.io.L;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

public class Tileset extends Graphic {

    public Tileset(Texture texture, Vector2f tilesize) {
        super(texture);
        this.tilesize = new Vector2f(tilesize);
        this.gridsize = new Vector2f((float) Math.ceil(size.x / tilesize.x),
                (float) Math.ceil(size.y / tilesize.y));
        this.gidrange = new Vector2f(-1, -1);
    }

    public Tileset setGidRange(int firstgid) {
        gidrange = new Vector2f(firstgid, firstgid + gridsize.x * gridsize.y);
        return this;
    }

    public boolean inGidRange(int gid) {
        return gid >= gidrange.x && gid <= gidrange.y;
    }

    public Vector2f getTilePosFromGid(int gid) {
        if (inGidRange(gid)) {
            return new Vector2f((gid - gidrange.x) % gridsize.x,
                    (float) Math.floor((gid - gidrange.x) / gridsize.y));
        } else {
            return new Vector2f();
        }
    }

    public Vector2f getTileSize() {
        return tilesize;
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
        Vector2f c = new Vector2f(tile.x * size.x, tile.y * size.y);
        v[0] = new Vector2f();
        v[1] = new Vector2f(size.x, 0);
        v[2] = new Vector2f(size);
        v[3] = new Vector2f(0, size.y);
        t[0] = new Vector2f((c.x) / this.size.x, c.y / this.size.y);
        t[1] = new Vector2f((c.x + size.x) / this.size.x, c.y / this.size.y);
        t[2] = new Vector2f((c.x + size.x) / this.size.x, (c.y + size.y) / this.size.y);
        t[3] = new Vector2f(c.x / this.size.x, (c.y + size.y) / this.size.y);
        for (int i = 0; i < 4; ++i) {
            t[i].x *= this.size.x / this.texture.getTextureWidth();
            t[i].y *= this.size.y / this.texture.getTextureHeight();
        }
        super.render(position);
    }
    protected Vector2f gridsize;
    protected Vector2f tilesize;
    protected Vector2f gidrange;
}
