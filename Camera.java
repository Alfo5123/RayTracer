package RAYTRACER;
import java.lang.Math;

public class Camera 
{
	private float angle ; 
	private float wh ;  
	
	public Camera ( float fov , int width , int height ) 
	{
		this.angle = ( float ) ( Math.tan( Math.PI * 0.5f * fov / 180.0f) );
		this.wh = (float)(width) / (float)( height ) ;
	}
	
	public float getAngle()
	{
		return this.angle ; 
	}
	
	public float getWH( )
	{
		return this.wh ;
	}

}
