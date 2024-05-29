package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * A class that will realize the scene we want to build
 */
public class Scene {
    /**
     * the name of the scene
     */
    public String _name;
    /**
     * the background color
     */
    public Color _background = Color.BLACK;
    /**
     * the ambient lighting
     */
    public AmbientLight _ambientLight = AmbientLight.NONE;
    /**
     * the 3D model
     */
    public Geometries _geometries = new Geometries();

    /**
     * Constructor for initializing the name
     *
     * @param name The name of the scene
     */
    public Scene(String name) {
        _name = name;
    }

    /**
     * Initialize the background color
     *
     * @param color the background color
     * @return the object for the scene
     */
    public Scene setBackground(Color color) {
        this._background = color;
        return this;
    }

    /**
     * Field initialization of the ambient lighting
     *
     * @param ambientLight the ambient lighting
     * @return the object for the scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this._ambientLight = ambientLight;
        return this;
    }

    /**
     * Initializing the field of the geometric body
     *
     * @param geometries the 3D model
     * @return the object for the scene
     */
    public Scene setGeometries(Geometries geometries) {
        this._geometries = geometries;
        return this;
    }

}
