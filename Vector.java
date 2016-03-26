import java.lang.Math;
import java.awt.Color;

public class Vector 
{
	private float x ; 
	private float y ; 
	private float z ; 
	
	public Vector ( ){ };
	
	public Vector ( float x , float y , float z )
	{
		this.x = x ;
		this.y = y ; 
		this.z = z ; 
	};
	
	public Vector ( Point3D p1 , Point3D p2 )
	{
		this.x =  p2.getX() - p1.getX() ; 
		this.y =  p2.getY() - p1.getY() ;  
		this.z =  p2.getZ() - p1.getZ() ; 
	};
	
	public Vector ( Point3D p )
	{
		this.x = p.getX() ; 
		this.y = p.getY() ; 
		this.z = p.getZ() ; 
	};
	
	public Vector ( Color c )
	{
		this.x = ( float ) ( c.getRed() ) / 255.0f  ; 
		this.y = ( float ) ( c.getGreen() ) / 255.0f  ;  
		this.z = ( float ) ( c.getBlue() ) / 255.0f  ; 
	};
	
	public Color toColor ( )
	{
		return new Color ( this.x , this.y , this.z ) ;
	}
	
	public float getX ()
	{
		return this.x ; 
	};
	
	public float getY ()
	{
		return this.y ; 
	};
	
	public float getZ ()
	{
		return this.z ; 
	};
	
	public float norm2 ( )
	{
		float l =  ( this.x * this.x ) + ( this.y * this.y ) + ( this.z * this.z ) ;
		return l ;
	};
	
	public float norm ( )
	{
		return ( float ) ( Math.sqrt ( this.norm2() ) ) ;
	};
	
	public void normalize ( )
	{
		float r = this.norm() ; 
		this.x /= r ; 
		this.y /= r ; 
		this.z /= r ; 
	};
	
	public float  dot ( Vector v )
	{
		float d = this.x * v.getX() + this.y * v.getY() + this.z * v.getZ() ; 
		return d ;
	};
	
	public Vector sum ( Vector v )
	{
		return ( new Vector ( this.x + v.getX(), this.y + v.getY() , this.z + v.getZ() )  ) ;
	};
	
	public Vector diff ( Vector v )
	{
		return ( new Vector ( this.x - v.getX() , this.y - v.getY() , this.z - v.getZ() )  ) ;
	};
	
	public Vector prod(  float t )
	{
		return ( new Vector ( this.x * t , this.y * t , this.z * t ) ) ;
	};
	
	public Vector times ( Vector v )
	{
		return ( new Vector ( this.x * v.getX() , this.y * v.getY() , this.z * v.getZ() )  ) ;
	};

}
