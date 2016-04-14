package raytracer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Test 
{
	public static void main ( String[] args )
	{
		
		Camera camera = new Camera 
						( 
								new Ray
								( 
									new Point3D ( 0.0f , 0.0f , 1000.0f ) ,
									new Vector ( 0.0f , -0.00001f , -1.0f ) 
								), 
								1200.0f ,
					 			30.0f , 
					 			30.0f , 
					 			new Vector ( new Point3D ( 1.0f , 0.0f , 0.0f ) ) 	
						) ;
		
	
		List<Light> lights = new ArrayList<Light>() ; 
		
		lights.add 
		( 
				new Light 
				( 
						new Point3D( 4000.0f , 2000.0f, -6000.0f) ,
						new Color ( 1.0f , 1.0f , 1.0f ) 
				) 
	    ) ;
		
		
		List<Shape> shapes = new ArrayList<Shape>() ;
		/*
		shapes.add
		( 
				new Sphere
				( 		  
					new Point3D (5.0f, 0.0f, -13500.0f),
	                5.0f,
	                new Material
		            (
		                    new Color( 0.10f, .2f, .1f ),
		                    new Color(.3f, .5f, .4f),
		                    new Color(0.4f, .4f, .3f),
		                    80.0f
		            ) 
		        )
		);
		
		shapes.add
		( 
				new Sphere
				( 		  
					new Point3D (-7.0f, -2.0f, -15500.0f),
	                8.0f,
	                new Material
		            (
		                    new Color( 0.20f, .1f, .1f ),
		                    new Color(.5f, .3f, .4f),
		                    new Color(0.3f, .4f, .3f),
		                    80.0f
		            ) 
		        )
		);
		

		shapes.add
		(
				new Triangle
				(
						new Point3D ( 10.0f , 10.0f ,  -12000.0f ) ,
						new Point3D ( 0.0f , 15.0f , -12500.0f ) ,
						new Point3D ( 0.0f , 0.0f , -1200.0f ) ,

						new Material
			            (
			                    new Color(0.1f , 0.25f, 0.2f ),
			                    new Color(0.5f, 0.3f, 0.4f),
			                    new Color(0.3f, 0.1f, 0.2f),
			                    50.0f
			            ) 
	
				)
		);
		
		*/
		
		shapes.add
		(
				new Plane 
				(
						new Vector (  0.0f , 1.0f , 0.0f ) , 
						new Point3D ( 0.0f , -10.0f , 0.0 ) ,
						new Material
			            (
			                    new Color(0.12f , 0.29f, 0.12f ),
			                    new Color(0.2f, 0.35f, 0.21f),
			                    new Color(0.1f, 0.4f, 0.3f),
			                    80.0f
			            ) 
	
				)
		);
		
		shapes.add
		(
				new Plane 
				(
						new Vector (  5f , 0.0f , 0.0f ) , 
						new Point3D ( -5f , 0.0f , 0.0f ) ,
						new Material
			            (
			                    new Color(0.1f , 0.1f, 0.1f ),
			                    new Color(0.2f, 0.2f, 0.2f),
			                    new Color(0.1f, 0.1f, 0.1f),
			                    40.0f
			            ) 
	
				)
		);
		
		

		Renderer test = new Renderer 
	    (	
			new Scene ( camera , lights , shapes ), 	// Scene Generation
			5// Max Depth of Recursion
		) ;		
		
		System.out.println(camera.getHeight());
		System.out.println(camera.getWidth());
		test.writeImage("Image_07");
	}
}
