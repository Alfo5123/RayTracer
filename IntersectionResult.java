package RAYTRACER;

public class IntersectionResult 
{
	private Point3D Inter = null ; 
	private Vector N = null ; 
	private Material material = null ; 
	
	public IntersectionResult ( Ray ray , Scene scene )
	{
		ray.normalize() ;
		
		// Check intersection with other Objects
		float tmin = Float.MAX_VALUE ; 
		int ind = -1 ; 
		
		for ( int i = 0 ; i < scene.objects.size() ; i++ )
		{
			if ( scene.objects.get(i).Intersect( ray ) )
			{
				 float t =  scene.objects.get(i).getInter()  ;
				 
				 if ( t < tmin && t > 0.0001f ) // && t > 0.000001f
				 {
					 tmin = t;
					 ind = i ; 
				 } 
			}
		}
		
		// Intersection Point
		Point3D Inter = new Point3D (   ray.getPos().getX() + tmin * ray.getDir().getX() ,
										ray.getPos().getY() + tmin * ray.getDir().getY() , 
										ray.getPos().getZ() + tmin * ray.getDir().getZ() ) ;

		if ( ind != -1 )
		{
			// Normal Vector 
			Vector N = scene.objects.get(ind).getNormal(Inter) ;
			this.N = N.normalize();
			
			// Bias
			float bias = 0.01f ;
			
			// Slightly modifying intersection point
			this.Inter = new Point3D (  Inter.getX() + bias * N.getX() ,
								  		            Inter.getY() + bias * N.getY() ,
								  		            Inter.getZ() + bias * N.getZ() ) ;
			
			this.material = scene.objects.get(ind).getMaterial() ;
		}
		
	}
	
	public Vector getNorm ( )
	{
		return this.N ; 
	}
	
	public Point3D getIntersection ( )
	{
		return this.Inter ; 
	}
	
	public Material getMaterial ( )
	{
		return this.material ;
	}
}
