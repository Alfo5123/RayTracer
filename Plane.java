package RAYTRACER;

public class Plane extends Shape
{
	public Vector Normal ; 
	public Point3D P ; 
	
	private float t ; 
	private float EPS = 0.00001f ; 
	
	public Plane ( Vector Normal , Point3D P , Material material )
	{
		this.Normal = Normal ; 
		this.P = P ;  
		this.material = material ; 
	}

	@Override
	public boolean Intersect(Ray ray) 
	{
		Vector v = new Vector (  ray.getPos() , this.P  ) ;
		ray.normalize();
		//v.norm() ; 
		this.Normal = this.Normal.normalize() ;
		
		float den = ray.getDir().dot(this.Normal) ; 
		
		// Check if crossing the plane
		if ( den < EPS ) 
		{
			this.t = v.dot(this.Normal ) / den ;
			return ( this.t >= 0 ) ; 
		}
		
		return false ;
	}

	@Override
	public Vector getNormal(Point3D p)
	{
		 return this.Normal ; 
	}

	@Override
	public float getInter() 
	{
		return this.t ; 
	}

	@Override
	public Material getMaterial() 
	{
		return this.material;
	}
	
}
