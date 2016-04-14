package raytracer;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.util.List;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Renderer 
{
	private Scene Scene ; 
	private int RecursionDepth ; 
	
	public Renderer ( Scene Scene , int RecursionDepth )
	{	
		this.Scene = Scene ;
		this.RecursionDepth = RecursionDepth ; 
	}
	
	public IntersectionResult findClosestIntersection( Ray ray )
	{		
		// Check closest intersection with other Objects
		List<Shape> Objects = Scene.getObjects() ;
		IntersectionResult ClosestIntersection = new IntersectionResult() ;
		IntersectionResult Intersection ; 
		double tmin = Double.MAX_VALUE ;
				
		for ( int i = 0 ; i < Objects.size() ; i++ )
		{
			Intersection = Objects.get(i).intersect(ray) ;
					
			if ( Intersection.getIntersection() != null )
			{
					Vector aux = new Vector ( ray.getPosition() , Intersection.getIntersection()  );
					double t =   aux.norm2();
						 
					if ( t < tmin && t > 0.0001f ) 
					{
							 ClosestIntersection = Intersection ; 
							 tmin = t ; 
					} 
			}
		}
				
		return ClosestIntersection ; 
	}
	
	public Vector Trace ( Ray ray , int depth )
	{
		// Normalize ray direction
		ray.normalize();
		
		IntersectionResult ClosestIntersection = findClosestIntersection( ray ) ; 
				
		if ( ClosestIntersection.getIntersection() == null ) // No intersection
		{
			return ( new Vector ( 0.0f , 0.0f , 0.0f ) ) ; // Black Background
		}
				
		// Intersection Point
		Point3D Inter = ClosestIntersection.getIntersection() ;

		// Normal Vector 
		Vector N = ClosestIntersection.getNormalVector() ;
		N = N.normalize();
				
		// Material of Surface
		Material m = ClosestIntersection.getMaterial();
				
		// Viewing vector (surface to eye) 
		Vector V = new Vector ( Inter, new Point3D ( 0.0f , 0.0f, 0.0f ) ) ;
		V = V.normalize(); 

		// Calculate Reflection Ray
		Vector RL = ray.getDirection().diff( N.prod( 2.0f*N.dot( ray.getDirection() ) ) ) ;
		Ray ReflectionRay = new Ray ( Inter, RL ) ;
				
		// Initialize Final Color 
		Vector FinalColor = new Vector ( 0.0f , 0.0f , 0.0f ) ; 
				
		// Return Direct Component
		List<Light> Lights = Scene.getLights() ;
		
		for ( int i = 0 ; i < Lights.size() ; i++ )
		{
			Light cur = Lights.get(i) ; 
					
			// Vector from Surface to Light
			Vector L = new Vector ( Inter, cur.getPosition() ) ;
			double distance = L.norm() ; L = L.normalize();
			
			// Normalized ShadowRay
			Ray Shadowray = new Ray ( Inter , L ) ; 
			Shadowray.normalize(); 
					
			if ( findClosestIntersection( Shadowray ).getIntersection() == null ) // No Light Obstruction
			{
				Vector LightColor = new Vector ( cur.getColor() ) ;
				Vector R = N.prod(2.0f*N.dot(L)).diff(L) ;
						
				// Phong Illumination Model Contribution 
						
				// Diffusive Component
				FinalColor = FinalColor.sum( LightColor.times( m.getDiffuseComponent().prod( Math.max(0.0f, L.dot(N) ) ) ) ) ; 
				
				// Specular component 
				FinalColor = FinalColor.sum( m.getSpecularComponent().prod( Math.pow( ( Math.max( 0.0f , R.dot(V) ) ) , m.getPhongIndex() ) ) ) ;
				
				// Attenuation
				FinalColor = FinalColor.prod( 8000.0f / distance ) ;
			}
		}
				
		// If depth is less than a maximum value
		if ( depth < RecursionDepth ) FinalColor = FinalColor.sum ( Trace ( ReflectionRay , depth + 1 ) );
				
		// Ambient Component 
		FinalColor.sum(m.getAmbientComponent());
		
		return FinalColor ;		
	}
	
	public void writeImage ( String imagename )
	{
		   // Set camera conditions
	       Camera Camera = Scene.getCamera() ;

	       int Width = Camera.getWidth() ; 
	       int Height = Camera.getHeight()  ;
	       
	       // Create image
	       BufferedImage image;
	       image = new BufferedImage(Width, Height,BufferedImage.TYPE_INT_ARGB ) ;
	       
	       //Writing image
	       Color color ; 
	       Vector v ;
	       
	       for ( int y = 0 ; y < Height ; y++ )
	       {
	    	   for ( int x = 0 ; x < Width ; x++ )
	    	   {
	    		   // Throw ray from camera into each pixel
	    		   
	    		   Ray EyeRay = Camera.getRay( x + 1 - Width/2 ,  Height/2 - y - 1 ) ;
	    		   EyeRay.normalize();
	    		   
	    		   // Calculate the final color obtained by Ray Tracing algorithm
	    		   v = Trace ( EyeRay , 0 ).saturate() ; 
	    		   color = v.toColor() ;
	    		   
	    		   // Set the final color to that pixel
	    		   image.setRGB(x, y, color.getRGB() );
	    	   }
	       } 
	                
	       // Saving picture using png format
	       File f = new File(imagename);

	       try{ ImageIO.write(image, "png", f); } catch (IOException ex) 
	       { ex.printStackTrace(); }
	   }
}

