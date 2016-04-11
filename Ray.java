package raytracer;

public class Ray 
{
	private Point3D Position ; 
	private Vector  Direction ; 
	
	public Ray ( Point3D Position , Vector Direction )
	{
		this.Position = Position ; 
		this.Direction = Direction ; 
	}
	
	public Point3D getPosition ( )
	{
		return Position ; 
	}
	
	public Vector getDirection( )
	{
		return Direction ;
	}
	
	public void normalize()
	{
		Direction = Direction.normalize() ;
	}
}
