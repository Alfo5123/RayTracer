package raytracer;
import java.lang.Math;

public class Camera 
{
	private double Angle ; 
	private double WidthHeightRatio ;  
	
	public Camera ( double fov , int width , int height ) 
	{
		Angle = Math.tan( Math.PI * 0.5f * fov / 180.0f);
		WidthHeightRatio  = (double)width/(double)height;
	}
	
	public double getWidthHeightRatio ( )
	{
		return WidthHeightRatio  ;
	}
	
	public double getAngle()
	{
		return Angle ;
	}
}
