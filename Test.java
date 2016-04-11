package raytracer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Test 
{
	public static void main ( String[] args )
	{
		int Width = 1200 ; 
		int Height = 1200 ; 
		
		Camera camera = new Camera ( 30.0f , Width , Height ) ; 
	
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
				                    40.0f
				        ) 
		        	)
		);
		
		shapes.add
		( 
				new Sphere
				( 		  
					new Point3D (1.5f, 1.0f, -15.0f),
	                		1.2f,
	                		new Material
		            		(
			                    	new Color( 0.2f, .11f, .15f ),
			                    	new Color(.4f, .35f, .32f),
			                    	new Color(0.8f, .7f, .2f),
			                    	40.0f
		            		) 
		        	)
		);

		shapes.add
		(
				new Plane 
				(
					new Vector (  0.0f , 1.0f , 0.0f ) , 
					new Point3D ( 0.0f , -1.0f , 0.0 ) ,
					new Material
				        (
			                    	new Color(0.12f , 0.29f, 0.12f ),
					        new Color(0.2f, 0.35f, 0.21f),
					        new Color(0.1f, 0.4f, 0.3f),
					        80.0f
				        ) 
	
				)
		);
	
	
	
		Renderer test = new Renderer 
	    	(	
			new Scene ( camera , lights , shapes ), 	// Scene Generation
			Width, // Width
			Height ,  // Height
			5// Max Depth of Recursion
		) ;		
		
		
		test.writeImage("Image_07");
	}
}
