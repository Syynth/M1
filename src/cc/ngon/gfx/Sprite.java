/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.ngon.gfx;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Ben
 */
public class Sprite extends Graphic {
    
    public Sprite(Texture t) {
        super(t);
    }
    
    public Vector2f size;
    public Vector2f offset;
    
}
