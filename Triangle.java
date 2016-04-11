package raytracer;

public class Triangle extends Shape
{
	public Point3D P1 ; 
	public Point3D P2 ; 
	public Point3D P3 ; 
	public Material Material ; 
	
	public Triangle( Point3D P1 , Point3D P2 , Point3D P3 , Material Material  )
	{
		this.P1 = P1 ; 
		this.P2 = P2 ; 
		this.P3 = P3 ; 
		this.Material = Material ;
	}
	
	@Override
	public IntersectionResult intersect(Ray ray)
	{
		// Möller–Trumbore intersection algorithm
		
		double EPS = 0.0001f ;
		
		Vector e1 = new Vector ( P1 , P2 ) ;
		Vector e2 = new Vector ( P1 , P3 ) ;
		
		//Begin calculating determinant - also used to calculate u parameter
		Vector P = ray.getDirection().cross(e2) ;
		//if determinant is near zero, ray lies in plane of triangle
		double det = e1.dot(P) ; 
		//NOT CULLING
		if(det > -EPS && det < EPS ) return new IntersectionResult ( null, null, null );
		
		double inv_det = 1.0f / det ; 
		
		// Calculate distance from V1 to ray origin
		Vector T = new Vector ( P1 , ray.getPosition()  ) ;
		
		// Calculate u parameter and test bound
		 double u = T.dot(P) * inv_det;
		 
		 // The intersection lies outside of the triangle
		 if(u < 0.0f || u > 1.0f) return new IntersectionResult ( null, null, null ); 

		 //Prepare to test v parameter
		 Vector Q = T.cross(e1) ;

		 //Calculate V parameter and test bound
		 double v = ray.getDirection().dot(Q) * inv_det;
		 
		 // The intersection lies outside of the triangle
		 if( v < 0.0f || u + v  > 1.0f ) return new IntersectionResult ( null, null, null );

		 double t = e2.dot(Q) * inv_det;

		  if( t > EPS ) 
		  { 
			//Ray intersection
			  
			 // Calculate Point of Intersection
			  Point3D Intersection = new Point3D (    ray.getPosition().getX() + t * ray.getDirection().getX() ,
								  ray.getPosition().getY() + t * ray.getDirection().getY() , 
								  ray.getPosition().getZ() + t * ray.getDirection().getZ() ) ; 
			  
			 // Calculate normalized Normal Vector at the Point of Intersection
			  Vector x = new Vector ( P1 , P2 ) ;
			  Vector y = new Vector ( P1 , P3 ) ;
		          Vector Normal = x.cross(y);
		          Normal = Normal.normalize() ;
		      
		     // Slightly modify the intersection point to avoid precision issues
			  double bias = 0.01f ;
			  Intersection = new Point3D (  Intersection.getX() + bias * Normal.getX() ,
							Intersection.getY() + bias * Normal.getY() ,
							Intersection.getZ() + bias * Normal.getZ() ) ;
			  
			  return new IntersectionResult( Intersection, Normal, Material ) ;
		      
		      
		  }
		  // No hit, no win
		  return new IntersectionResult ( null, null, null );	
	}
}
