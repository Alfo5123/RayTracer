package RAYTRACER;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Renderer 
{
	Scene scene ; 
	
	int width ; 
	int height ; 
	
	float invWidth ;
	float invHeight ;
	
	int MAX_DEPTH ; 
	
	public Renderer (){} ; 
	
	public Renderer ( Scene scene , int width , int height , int MAX_DEPTH )
	{
		
		this.width = width ; 
		this.height = height ; 
		
		this.invWidth = 1.0f / ( float ) ( width ) ; 
		this.invHeight = 1.0f / ( float ) ( height ) ;
		
		this.scene = scene ;
		
		this.MAX_DEPTH = MAX_DEPTH ; 
		
	}
	
	public Vector Trace ( Ray dir , int depth )
	{
				IntersectionResult IR = new IntersectionResult( dir , this.scene ) ;
				
				if ( IR.getIntersection() == null ) // No intersection
				{
					return ( new Vector ( 0.0f , 0.0f , 0.0f ) ) ; // Black Background
				}
				
				// Intersection Point
				Point3D Inter = IR.getIntersection() ;

				// Normal Vector 
				Vector N = IR.getNorm() ;
				N = N.normalize();
				
				// Material of Surface
				Material m = IR.getMaterial();
				
				// Viewing vector (surface to eye) 
				Vector V = new Vector ( Inter, new Point3D ( 0.0f , 0.0f, 0.0f ) ) ;
				V = V.normalize(); 

				// Calculate Reflection Ray
				Vector RL = dir.getDir().diff( N.prod( 2.0f*N.dot( dir.getDir() ) ) ) ;
				Ray Reflection = new Ray ( Inter, RL ) ;
				
				// Initialize Final Color 
				Vector FinalColor = new Vector ( 0.0f , 0.0f , 0.0f ) ; 
				
				// Return Direct Component
				for ( int i = 0 ; i < this.scene.lights.size() ; i++ )
				{
					Light cur = this.scene.lights.get(i) ; 
					
					// Vector from Surface to Light
					Vector L = new Vector ( Inter, cur.getPos() ) ;
					float L_2 = L.norm() ; L = L.normalize();
					
					Ray shadowray = new Ray ( Inter , L ) ; 
					
					boolean flag = false ; 
					
					for ( int j = 0 ; j < this.scene.objects.size() ; j++ )
					{
						if (  this.scene.objects.get(j).Intersect(shadowray)  )
						{
							flag = true ; 
							break;
						}
					}
					
					
					if ( !flag )
					{
						Vector Lc = new Vector ( cur.getCol() ) ;
						Vector R = N.prod(2.0f*N.dot(L)).diff(L) ;
						
				    // Phong Illumination Model Contribution 
						
						// Diffusive Componente
						FinalColor = FinalColor.sum( Lc.times( m.getDiffuse().prod( Math.max(0.0f, L.dot(N) ) ) ) ) ; 
						// Specular component 
						FinalColor = FinalColor.sum( m.getSpecular().prod( ( float ) Math.pow( ( double )( Math.max( 0.0f , R.dot(V) ) ) , ( double ) m.getPhongIndex() ) ) ) ;
						// Atenuation
						FinalColor = FinalColor.prod( 70.0f / L_2) ;
					}
				}
				
				// If depth is less than a maximum value
				if ( depth < MAX_DEPTH ) FinalColor = FinalColor.sum ( Trace ( Reflection , depth + 1 ) );
				
				// Ambient Component 
				Vector ans = new Vector ( m.ambient ) ;
				ans = ans.sum( FinalColor ) ;
				
				return new Vector ( Math.min(ans.getX(), 1.0f) ,  Math.min(ans.getY(), 1.0f) , Math.min(ans.getZ(), 1.0f) ) ;
				
	}
	
	
	public void writeImage ( String imagename )
	{
	     
	       BufferedImage image0;
	       // create image
	       image0 = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB ) ;
	       
	       // Set camera conditions
	       float angle = this.scene.camera.getAngle() ; 
	       float wh = this.scene.camera.getWH() ;
	       Point3D pos = new Point3D ( 0.0f , 0.0f , 0.0f ) ;
	        
	       //Writing image
	       Color c ; 
	       
	       for ( int y = 0 ; y < height ; y++ )
	       {
	    	   for ( int x = 0 ; x < width ; x++ )
	    	   {
	    		   float dx = (2.0f * (( x  + 0.5f) * invWidth) - 1.0f) * angle * wh;
	    		   float dy = (1.0f - 2.0f * ((y + 0.5f) * invHeight)) * angle;
	    		   
	    		   Vector dir = new Vector ( dx , dy , -1.0f ) ; dir.normalize() ;
	    		   Ray EyeRay = new Ray (  pos ,  dir ) ; 

	    		   c  = Trace ( EyeRay , 0 ).toColor() ;
	    		   image0.setRGB(x, y, c.getRGB() );
	    	   }
	       } 
	                
	       // Saving picture using png format
	       File f = new File(imagename);

	       try{ ImageIO.write(image0, "png", f); } catch (IOException ex) 
	       { ex.printStackTrace(); }
	   }
	
}
