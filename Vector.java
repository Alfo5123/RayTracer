package raytracer;
import java.lang.Math;
import java.awt.Color;

public class Vector 
{
	private double X ; 
	private double Y ; 
	private double Z ; 
	
	public Vector ( double X , double Y , double Z )
	{
		this.X = X ;
		this.Y = Y ; 
		this.Z = Z ; 
	};
	
	public Vector ( Point3D p1 , Point3D p2 )
	{
		this.X =  p2.getX() - p1.getX() ; 
		this.Y =  p2.getY() - p1.getY() ;  
		this.Z =  p2.getZ() - p1.getZ() ; 
	}
	
	public Vector ( Point3D p )
	{
		this.X = p.getX() ; 
		this.Y = p.getY() ; 
		this.Z = p.getZ() ; 
	}
	
	public Vector ( Color c )
	{
		this.X = ( double )c.getRed() / 255.0f  ; 
		this.Y = ( double )c.getGreen() / 255.0f  ;  
		this.Z = ( double )c.getBlue() / 255.0f  ; 
	}
	
	public Color toColor ( )
	{
		return new Color (( float )X , (float)Y , (float)Z);
	}
	
	public double getX ()
	{
		return X ; 
	}
	
	public double getY ()
	{
		return Y ; 
	}
	
	public double getZ ()
	{
		return Z ; 
	}
	
	public double norm2 ( )
	{
		double l =  X*X + Y*Y + Z*Z ;
		return l ;
	}
	
	public double norm ( )
	{
		return  Math.sqrt ( this.norm2() ) ;
	}
	
	public Vector normalize ( )
	{
		double r = this.norm() ; 
		return new Vector ( X / r , Y / r , Z / r ) ; 
	}
	
	public double dot ( Vector v )
	{
		double d = X * v.getX() + Y * v.getY() + Z * v.getZ() ; 
		return d ;
	}
	
	public Vector sum ( Vector v )
	{
		return new Vector ( X + v.getX(), Y + v.getY() , Z + v.getZ() ) ;
	}
	
	public Vector diff ( Vector v )
	{
		return new Vector ( X - v.getX() , Y - v.getY() , Z - v.getZ() )  ;
	}
	
	public Vector prod(  double t )
	{
		return new Vector ( X * t , Y * t , Z * t ) ;
	}
	
	public Vector times ( Vector v )
	{
		return new Vector ( X * v.getX() , Y * v.getY() , Z * v.getZ() );
	}
	
	public Vector cross ( Vector v )
	{
		Vector w = new Vector ( Y * v.getZ() - Z * v.getY() , 
					-X * v.getZ() + Z * v.getX() ,
					X * v.getY() - Y * v.getX() ) ; 
		return w ; 
	}
	
	public Vector saturate( )
	{
		 return new Vector( Math.min(X, 1.0f) ,  Math.min(Y, 1.0f) , Math.min(Z, 1.0f) ) ;
	}

}
