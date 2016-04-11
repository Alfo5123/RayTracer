package raytracer;

public class Point3D
{
	private double X ; 
	private double Y ; 
	private double Z ; 
	
	public Point3D ( double X , double Y , double Z )
	{
		this.X = X ; 
		this.Y = Y ; 
		this.Z = Z ; 
	}
	
	public double getX ( )
	{
		return X ; 
	}
	
	public double getY ( )
	{
		return Y ;
	}
	
	public double getZ ( )
	{
		return Z ;
	}
}
