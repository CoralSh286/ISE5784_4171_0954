package geometries;

/**
 * A class for a box for delimiting geometric bodies
 */
public interface Boundable {
    /**
     * Creates a box around the object, adds the object to its list.
     *
     * @return The bounding box of the object
     */
    AxisAlignedBoundingBox getAxisAlignedBoundingBox();
}
