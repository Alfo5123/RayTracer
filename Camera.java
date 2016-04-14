package raytracer;
import java.lang.Math;

public class Camera 
{
	 private double InvWidth ;
	 private double InvHeight  ;
	 private Vector AxisX ;
	 private Vector AxisY ; 
	 private Vector AxisOrigin ; 
	 private Ray CameraDirection ;

	 public Camera ( Ray CameraDirection , double Distance , double WidthAngle , double HeightAngle , Vector Axis )
	 {
	 	this.CameraDirection = CameraDirection ; 
	 	this.CameraDirection.normalize(); 
	 	
	 	// Calculate Axis Origin
	 	this.AxisOrigin = CameraDirection.getDirection().prod( Distance ) ;
	 	
	 	// Calculate distance between pixels in both directions
	 	this.InvWidth = 1.0f / ( 2.0f * Distance * Math.tan ( Math.PI * WidthAngle / 180.0f ) );
	 	this.InvHeight = 1.0f / ( 2.0f * Distance * Math.tan ( Math.PI * HeightAngle / 180.0f ) ) ;
	 	
	 	// Get Coordinate in the Projection Image Plane
	 	this.AxisX = Axis.normalize() ; 
	 	this.AxisY = Axis.cross(CameraDirection.getDirection()).normalize() ;
	 }
	 
	 public int getWidth ( )
	 {
		 return ( int ) ( 1.0f / InvWidth ) ;
	 }
	 
	 public int getHeight ( )
	 {
		 return ( int ) ( 1.0f / InvHeight ) ;
	 }
	 
	 public Ray getRay ( int x , int y  )
	 {
		 Vector dx = AxisX.prod( ( x - 0.5f ) * InvWidth * 2.0f  ) ;
		 Vector dy = AxisY.prod( ( y - 0.5f ) * InvHeight * 2.0f ) ;
		 Vector DirectionToPixel = AxisOrigin.sum(dx).sum(dy) ;
		 return new Ray ( CameraDirection.getPosition() , DirectionToPixel) ;
	 } 
}
