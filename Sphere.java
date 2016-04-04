package RAYTRACER;
import java.lang.Math;

public class Sphere extends Shape
{
	public Point3D center ; 
	public float radius ; 
	private float radius2 ;
	private float t0 ; 
	private float t1 ; 
	
	private float EPS = 1e-5f ;
	
	public Sphere ( Point3D center , float radius , Material material )
	{
		this.center = center ; 
		this.radius = radius ; 
		this.material = material ;
		this.radius2 = radius*radius ;
	}
	
	@Override
	public boolean Intersect( Ray ray ) 
	{
		Vector beam = new Vector ( ray.getPos() , this.center  ) ;
		float p =  beam.dot( ray.getDir() );
		
		// Another direction of the beam
		if ( p < 0 ) return false; 
		
		float d2 = beam.dot(beam) - p * p;
		
		// / If doesn't touch the sphere
		if ( d2 > radius2 ) return false; 
		
		float thc = ( float ) ( Math.sqrt(radius2 - d2) ) ;
		
		// Calculate two points of intersection with sphere
		t0 = p - thc;
		t1 = p + thc;
		
		return true;
	}
	
	@Override
	public Vector getNormal ( Point3D p ) 
	{
		return ( new Vector ( this.center , p ) ) ;
	}
	
	@Override
	public float getInter() 
	{
		if ( this.t0 < EPS ) this.t0 = this.t1 ; 
		return  ( this.t0 );
	}

	@Override
	public Material getMaterial() 
	{
		return this.material ;
	}

}
