
public class Plane extends Shape
{
	public Vector Normal ; 
	public Point3D P ; 
	
	float t ; 
	private float EPS = 0.00001f ; 
	
	
	public Plane(){} ; 
	
	public Plane ( Vector Normal , Point3D P , Material material )
	{
		this.Normal = Normal ; 
		this.P = P ;  
		this.material = material ; 
	};

	@Override
	public boolean Intersect(Ray ray) 
	{
		ray.normalize();
		Vector v = new Vector ( this.P  , ray.getPos() ) ;
		
		// Check if parallel
		if ( ray.getDir().dot(Normal)  < EPS ) return false ;
		
		t = v.dot( Normal ) / ray.getDir().dot( Normal ) ;
		
		return true ;
		
		/*
		Vector v = new Vector ( this.P  , ray.getPos() ) ;
		t = v.dot( Normal ) / ray.getDir().dot( Normal ) ;
		//System.out.println( t );
		return ( ray.getDir().dot(this.Normal) < EPS ) ; 
		*/	
	};

	@Override
	public Vector getNormal(Point3D p)
	{
		 return this.Normal ; 
	};

	@Override
	public float getInter() 
	{
		return this.t ; 
	};

	@Override
	public Material getMaterial() 
	{
		return this.material;
	};
	
}
