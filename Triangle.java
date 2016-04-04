package RAYTRACER;

public class Triangle extends Shape
{
	public Point3D p1 ; 
	public Point3D p2 ; 
	public Point3D p3 ; 
	public Material material ; 
	
	public float t ; 
	private float EPS = 0.0001f ;
	
	public Triangle( Point3D p1 , Point3D p2 , Point3D p3 , Material material  )
	{
		this.p1 = p1 ; 
		this.p2 = p2 ; 
		this.p3 = p3 ;
		this.material = material ;
	}
	
	@Override
	public boolean Intersect(Ray ray)
	{
		// Möller–Trumbore intersection algorithm
		
		Vector e1 = new Vector ( this.p1 , this.p2 ) ;
		Vector e2 = new Vector ( this.p1 , this.p3 ) ;
		
		//Begin calculating determinant - also used to calculate u parameter
		Vector P = ray.getDir().cross(e2) ;
		//if determinant is near zero, ray lies in plane of triangle
		float det = e1.dot(P) ; 
		//NOT CULLING
		if(det > -EPS && det < EPS ) return false ;
		
		float inv_det = 1.0f / det ; 
		
		// Calculate distance from V1 to ray origin
		Vector T = new Vector ( this.p1, ray.getPos()  ) ;
		
		// Calculate u parameter and test bound
		 float u = T.dot(P) * inv_det;
		 
		 // The intersection lies outside of the triangle
		 if(u < 0.0f || u > 1.0f) return false ; 

		 //Prepare to test v parameter
		 Vector Q = T.cross(e1) ;

		 //Calculate V parameter and test bound
		 float v = ray.getDir().dot(Q) * inv_det;
		 
		 // The intersection lies outside of the triangle
		 if( v < 0.0f || u + v  > 1.0f ) return false;

		 float t = e2.dot(Q) * inv_det;

		  if(t > EPS ) 
		  { //ray intersection
		    this.t = t;
		    return true;
		  }
		  // No hit, no win
		  return false;	
	}

	@Override
	public Vector getNormal(Point3D p) 
	{
		Vector u = new Vector ( this.p1 , this.p2 ) ;
		Vector v = new Vector ( this.p1 , this.p3 ) ;
		return  ( u.cross(v) ) ;
	}

	@Override
	public float getInter() 
	{
		return this.t ; 
	}

	@Override
	public Material getMaterial() 
	{
		return this.material ;
	}
	
}
