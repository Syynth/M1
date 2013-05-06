/**
 * @date Apr 29, 2013
 * @author Ben Cochrane
 */
package cc.ngon.m1;

import cc.ngon.m1.gfx.Graphic;
import cc.ngon.Config;
import cc.ngon.L;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

public class Tile extends Entity {

    public Tile(Map m, Vector2f position, boolean weather, float impediment, float defense) {
        this(m, new TerrainInfo(impediment, defense, weather, Resources.getTexture("grass_terrain_tex")), position);
    }
    
    public Tile(Map m, TerrainInfo t, Vector2f position) {
        super(m, position);
        this.info = new TerrainInfo(t);
    }
    
    @Override
    public String toString() {
        return position.toString();
    }

    public TerrainInfo info;
    Map map;

    public static final int FLOOR_ID = 0;
    public static final TerrainInfo FLOOR_INFO = new TerrainInfo(1, 0, false, Resources.getTexture("sand_terrain_tex"));
    
    public static final int GROUND_ID = 1;
    public static final TerrainInfo GROUND_INFO = new TerrainInfo(1, 0, true, Resources.getTexture("grass_terrain_tex"));
    
    public static final int WALL_ID = 2;
    public static final TerrainInfo WALL_INFO = new TerrainInfo(999, 999, false, Resources.getTexture("wall_terrain_tex"));
    
    public static final int NULL_ID = 3;
    public static final TerrainInfo NULL_INFO = new TerrainInfo(999, 999, false, Resources.getTexture("grass_terrain_tex"));
    
    public static final int WATER_ID = 4;
    public static final TerrainInfo WATER_INFO = new TerrainInfo(1, -1, true, Resources.getTexture("grass_terrain_tex"));
    
    public static final int TREE_ID = 5;
    public static final TerrainInfo TREE_INFO = new TerrainInfo(1, 1, true, Resources.getTexture("grass_terrain_tex"));
    
    public static class TerrainInfo {
        boolean weather;
        float impediment;
        float defense;
        Texture texture;
        Vector2f size;
        
        public TerrainInfo(float impediment, float defense, boolean weather, Texture texture) {
            this.impediment = impediment;
            this.defense = defense;
            this.weather = weather;
            this.texture = texture;
            this.size = new Vector2f(Config.getInt("game", "tileWidth"), Config.getInt("game", "tileHeight"));
        }
        
        public TerrainInfo(TerrainInfo tinf) {
            this.impediment = tinf.impediment;
            this.defense = tinf.defense;
            this.weather = tinf.weather;
            this.texture = tinf.texture;
            this.size = new Vector2f(tinf.size);
        }
    }
}
