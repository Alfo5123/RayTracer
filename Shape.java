package raytracer;

public abstract class Shape 
{
	public Material Material;

    	public abstract IntersectionResult intersect( Ray ray );
}
