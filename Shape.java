package RAYTRACER;

public abstract class Shape 
{
	public Material Material;

    	public abstract IntersectionResult Intersect( Ray ray );
}
