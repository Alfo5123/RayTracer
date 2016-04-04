package RAYTRACER;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Test 
{
	public static void main ( String[] args )
	{
		int Width = 1200 ; 
		int Height = 1200 ; 
		
		Camera camera = new Camera ( 40.0f , Width , Height ) ; 
	
		List<Light> lights = new ArrayList<Light>() ; 
		
		lights.add 
		( 
				new Light 
				( 
						new Point3D( 30.0f , 50.0f, 20.0f) ,
						new Color ( 1.0f , 1.0f , 1.0f ) 
				) 
	    ) ;
		
		
		List<Shape> shapes = new ArrayList<Shape>() ;
		/*
		shapes.add
		( 
				new Sphere
				( 		  
					new Point3D (0.0f, 0.0f, -20.0f),
	                1.0f,
	                new Material
		            (
		                    new Color(0.01f , 0.02f, 0.09f ),
		                    new Color(0.4f, 0.35f, 0.4f),
		                    new Color(0.5f, 0.45f, 0.3f),
		                    20.0f,
		                    100.0f
		            ) 
		        )
		);
		
		shapes.add
		( 
				new Sphere
				( 		  
					new Point3D (-2.0f, 0.0f, -20.0f),
	                1.0f,
	                new Material
		            (
		                    new Color( 0.1f, .02f, .02f ),
		                    new Color(.8f, .5f, .2f),
		                    new Color(1.0f, .2f, .2f),
		                    20.0f,
		                    80.0f
		            ) 
		        )
		);
		
		shapes.add
		( 
				new Sphere
				( 		  
					new Point3D (-0.0f, 0.0f, -15.0f),
	                1.0f,
	                new Material
		            (
		                    new Color( 0.02f, .1f, .05f ),
		                    new Color(.8f, .5f, .2f),
		                    new Color(1.0f, .2f, .2f),
		                    20.0f,
		                    90.0f
		            ) 
		        )
		);
		*/
		/*
		shapes.add
		( 
				new Sphere
				( 		  
					new Point3D (0.5f, -1.5f, -5.0f),
	                1.0f,
	                new Material
		            (
		                    new Color( 0.12f, .1f, .35f ),
		                    new Color(.8f, .5f, .2f),
		                    new Color(1.0f, .8f, .2f),
		                    20.0f,
		                    60.0f
		            ) 
		        )
		);
		*/
		
		shapes.add
		( 
				new Sphere
				( 		  
					new Point3D (-1.5f, 1.0f, -10.0f),
	                1.0f,
	                new Material
		            (
		                    new Color( 0.12f, .1f, .35f ),
		                    new Color(.3f, .5f, .2f),
		                    new Color(1.0f, .8f, .2f),
		                    20.0f,
		                    40.0f
		            ) 
		        )
		);
		

		shapes.add
		(
				new Triangle
				(
						new Point3D ( 1.0f , 1.0f ,  -4.0f ) ,
						new Point3D ( 0.0f , 0.0f , -4.0f ) ,
						new Point3D ( 0.0f , 1.0f , -5.0f ) ,
						
						new Material
			            (
			                    new Color(0.18f , 0.13f, 0.12f ),
			                    new Color(0.6f, 0.5f, 0.4f),
			                    new Color(0.2f, 0.3f, 0.2f),
			                    10.0f,
			                    70.0f
			            ) 
	
				)
		);
		
		shapes.add
		(
				new Triangle
				(
						new Point3D ( 1.0f , 0.0f ,  -5.0f ) ,
						new Point3D ( 0.0f , 0.0f , -4.0f ) ,
						new Point3D ( 1.0f , 1.0f ,  -4.0f ) ,

						new Material
			            (
			                    new Color(0.1f , 0.15f, 0.2f ),
			                    new Color(0.3f, 0.5f, 0.4f),
			                    new Color(0.3f, 0.1f, 0.2f),
			                    10.0f,
			                    50.0f
			            ) 
	
				)
		);
		
		shapes.add
		(
				new Triangle
				(
						new Point3D ( 1.0f , 0.0f ,  -5.0f ) ,
						new Point3D ( 1.0f , 1.0f ,  -4.0f ) ,
						new Point3D ( 0.0f , 1.0f , -5.0f ) ,
						

						new Material
			            (
			                    new Color(0.1f , 0.15f, 0.2f ),
			                    new Color(0.3f, 0.5f, 0.4f),
			                    new Color(0.3f, 0.1f, 0.2f),
			                    10.0f,
			                    50.0f
			            ) 
	
				)
		);
		
		shapes.add
		(
				new Triangle
				(
						new Point3D ( 1.0f , 1.0f ,  -4.0f ) ,
						new Point3D ( 0.0f , 0.0f , -4.0f ) ,
						new Point3D ( 0.0f , 1.0f , -5.0f ) ,
						

						new Material
			            (
			                    new Color(0.1f , 0.15f, 0.2f ),
			                    new Color(0.3f, 0.5f, 0.4f),
			                    new Color(0.3f, 0.1f, 0.2f),
			                    10.0f,
			                    50.0f
			            ) 
	
				)
		);
	

		shapes.add
		(
				new Plane 
				(
						new Vector (  0.0f , 1.0f , 0.0f ) , 
						new Point3D ( 0.0f , -1.0f , 0.0f ) ,
						new Material
			            (
			                    new Color(0.12f , 0.11f, 0.12f ),
			                    new Color(0.4f, 0.2f, 0.5f),
			                    new Color(0.1f, 0.2f, 0.3f),
			                    20.5f,
			                    80.0f
			            ) 
	
				)
		);
/*		
		shapes.add
		(
				new Plane 
				(
						new Vector (  -1.0f , 0.0f , -0.5f ) , 
						new Point3D ( 5.0f , 0.0f , 0.0f ) ,
						new Material
			            (
			                    new Color(0.15f , 0.18f, 0.12f ),
			                    new Color(0.4f, 0.55f, 0.5f),
			                    new Color(0.1f, 0.2f, 0.3f),
			                    20.5f,
			                    80.0f
			            ) 
	
				)
		);
		
		
		*/
		Renderer test = new Renderer 
	    (	
			new Scene ( camera , lights , shapes ), 	// Scene Generation
			Width, // Width
			Height ,  // Height
			5// Max Depth of Recursion
		) ;		
		
		
		test.writeImage("Image_01");
	}
}
