package raytracer;

public class IntersectionResult 
{
	private Point3D Intersection;; 
	private Vector Normal;
	private Material Material;
	
	public IntersectionResult( )
	{
		this.Intersection = null ; 
		this.Normal = null ; 
		this.Material = null ; 
	}
	
	public IntersectionResult( Point3D Intersection , Vector Normal , Material Material )
	{
		this.Intersection = Intersection; 
		this.Normal = Normal; 
		this.Material = Material; 
	}
	
	public Vector getNormalVector ( )
	{
		return Normal ; 
	}
	
	public Point3D getIntersection ( )
	{
		return Intersection ; 
	}
	
	public Material getMaterial ( )
	{
		return Material ;
	}
}
