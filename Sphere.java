package RAYTRACER;
import java.lang.Math;

public class Sphere extends Shape
{
	private Point3D Center ; 
	private double Radius2 ; 
	
	public Sphere ( Point3D Center , double Radius , Material Material )
	{
		this.Center = Center ; 
		this.Material = Material ;
		this.Radius2 = Radius*Radius ;
	}
	
	@Override
	public IntersectionResult Intersect( Ray ray ) 
	{
		Vector beam = new Vector ( ray.getPosition() , Center  ) ;
		double p =  beam.dot( ray.getDirection() );
		
		// Another direction of the beam
		if ( p < 0 ) return new IntersectionResult(null,null,null) ; 
		
		double d2 = beam.dot(beam) - p * p;
		
		// / If doesn't touch the sphere
		if ( d2 > Radius2 ) return new IntersectionResult(null,null,null) ;
		
		double thc = Math.sqrt(Radius2 - d2)  ;
		
		// Calculate two points of intersection with sphere
		double t0 = p - thc;
		double t1 = p + thc;
		
		// Consider the case first intersection behind the light
		if ( t0 < 1e-5f ) t0 = t1 ; 
		
		// Calculate Point of Intersection
		Point3D Intersection = new Point3D (    ray.getPosition().getX() + t0 * ray.getDirection().getX() ,
							ray.getPosition().getY() + t0 * ray.getDirection().getY() , 
							ray.getPosition().getZ() + t0 * ray.getDirection().getZ() ) ;	
		
		// Calculate normalized Normal Vector at the Point of Intersection
		Vector Normal = new Vector ( Center, Intersection ) ; 
		Normal = Normal.normalize() ; 
		
		// Slightly modify the intersection point to avoid precision issues
		double bias = 0.01f ;
		Intersection = new Point3D (  Intersection.getX() + bias * Normal.getX() ,
					      Intersection.getY() + bias * Normal.getY() ,
					      Intersection.getZ() + bias * Normal.getZ() ) ;
		
		// Return intersection characteristics
		return new IntersectionResult(Intersection, Normal, Material) ;
	}

}
