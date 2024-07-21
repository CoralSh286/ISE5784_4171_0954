package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * A class that represents the scene we want to build
 */
public class Scene {

    /**
     * Flag indicating if improvements should be applied
     */
    public boolean Improve = false;

    /**
     * Setter for the Improve flag
     *
     * @param improve true if improvements should be applied, false otherwise
     */
    public void setImprove(boolean improve) {
        this.Improve = improve;
    }

    /**
     * List of light sources in the scene
     */
    public List<LightSource> _lights = new LinkedList<>();

    /**
     * Method for updating the list of lights
     *
     * @param lights the list of light sources to set
     * @return the Scene object
     */
    public Scene setLights(List<LightSource> lights) {
        this._lights = lights;
        return this;
    }

    /**
     * The name of the scene
     */
    public String _name;

    /**
     * The background color of the scene
     */
    public Color _background = Color.BLACK;

    /**
     * The ambient lighting of the scene
     */
    public AmbientLight _ambientLight = AmbientLight.NONE;

    /**
     * The geometries (3D model) in the scene
     */
    public Geometries _geometries = new Geometries();

    /**
     * Constructor for initializing the scene with a name
     *
     * @param name The name of the scene
     */
    public Scene(String name) {
        _name = name;
    }

    /**
     * Setter for the background color of the scene
     *
     * @param color the background color to set
     * @return the Scene object
     */
    public Scene setBackground(Color color) {
        this._background = color;
        return this;
    }

    /**
     * Setter for the ambient lighting of the scene
     *
     * @param ambientLight the ambient light to set
     * @return the Scene object
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this._ambientLight = ambientLight;
        return this;
    }

    /**
     * Setter for the geometries (3D model) in the scene
     *
     * @param geometries the geometries to set
     * @return the Scene object
     */
    public Scene setGeometries(Geometries geometries) {
        this._geometries = geometries;
        return this;
    }
}
