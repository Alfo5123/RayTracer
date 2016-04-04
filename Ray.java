package RAYTRACER;

public class Ray 
{
	private Point3D pos ; // Position 
	private Vector  dir ; // Direction
	
	public Ray ( Point3D pos , Vector dir )
	{
		this.pos = pos ; 
		this.dir = dir ; 
	}
	
	public Point3D getPos ( )
	{
		return this.pos ; 
	}
	
	public Vector getDir( )
	{
		return this.dir ;
	}
	
	public void normalize()
	{
		this.dir = dir.normalize() ;
	}

}
