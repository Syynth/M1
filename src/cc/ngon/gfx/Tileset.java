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
        this.gridsize = new Vector2f((float) Math.floor(size.x / tilesize.x),
                (float) Math.floor(size.y / tilesize.y));
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
                    (float) Math.floor((gid - gidrange.x) / gridsize.x));
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
        v[0] = new Vector2f();
        v[1] = new Vector2f(texture.getImageWidth(), 0);
        v[2] = new Vector2f(texture.getImageWidth(), texture.getImageHeight());
        v[3] = new Vector2f(0, texture.getImageHeight());
        float fx = (float) texture.getImageWidth() / (float) texture.getTextureWidth(),
            fy = (float) texture.getImageHeight() / (float) texture.getTextureHeight();
        t[0] = new Vector2f(0, 0);
        t[1] = new Vector2f(fx, 0);
        t[2] = new Vector2f(fx, fy);
        t[3] = new Vector2f(0, fy);
        super.render(position);
    }

    public void renderTile(Vector2f position, Vector2f size, Vector2f tile) {
        Vector2f c = new Vector2f((tile.x) * size.x, tile.y * size.y);
        Vector2f st = new Vector2f(texture.getTextureWidth(), texture.getTextureHeight());
        v[0] = new Vector2f();
        v[1] = new Vector2f(size.x, 0);
        v[2] = new Vector2f(size);
        v[3] = new Vector2f(0, size.y);
        t[0] = new Vector2f((c.x)          / st.x,  c.y           / st.y);
        t[1] = new Vector2f((c.x + size.x) / st.x,  c.y           / st.y);
        t[2] = new Vector2f((c.x + size.x) / st.x, (c.y + size.y) / st.y);
        t[3] = new Vector2f((c.x)          / st.x, (c.y + size.y) / st.y);
        super.render(position);
    }
    protected Vector2f gridsize;
    protected Vector2f tilesize;
    protected Vector2f gidrange;
}
