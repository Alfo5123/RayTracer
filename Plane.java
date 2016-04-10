package RAYTRACER;

public class Plane extends Shape
{
	public Vector Normal ; 
	public Point3D P ; 
	
	public Plane ( Vector Normal , Point3D P , Material Material )
	{
		this.Normal = Normal ; 
		this.P = P ;  
		this.Material = Material ; 
	}

	@Override
	public IntersectionResult  Intersect(Ray ray) 
	{
		Vector v = new Vector (  ray.getPosition() , P  ) ;
		ray.normalize();
		
		// Calculate normalized Normal Vector at the Point of Intersection
		Normal = Normal.normalize() ;
		
		double den = ray.getDirection().dot( Normal ) ; 
		
		// Check if crossing the plane
		if ( den < 0.00001f ) 
		{
			double t = v.dot( Normal ) / den ;
			if ( t < 0 ) return new IntersectionResult( null, null, null ) ;
			
			// Calculate Point of Intersection
			Point3D Intersection = new Point3D (    ray.getPosition().getX() + t * ray.getDirection().getX() ,
								ray.getPosition().getY() + t * ray.getDirection().getY() , 
								ray.getPosition().getZ() + t * ray.getDirection().getZ() ) ;	

			// Slightly modify the intersection point to avoid precision issues
			double bias = 0.01f ;
			Intersection = new Point3D (  Intersection.getX() + bias * Normal.getX() ,
						      Intersection.getY() + bias * Normal.getY() ,
						      Intersection.getZ() + bias * Normal.getZ() ) ;
			
			return new IntersectionResult(Intersection, Normal, Material) ;
			
		}
		
		return new IntersectionResult( null, null, null ) ;
	}

}
