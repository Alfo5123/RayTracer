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
		
	};
	
	public Vector Trace2 ( Ray dir , int depth )
	{
				dir.normalize();
				// Check intersection with other Objects
				float tmin = Float.MAX_VALUE ; 
				int ind = -1 ; 
				
				for ( int i = 0 ; i < this.scene.objects.size() ; i++ )
				{
					if ( this.scene.objects.get(i).Intersect( dir ) )
					{
						 float t =  this.scene.objects.get(i).getInter()  ;
						 
						 if ( t < tmin && t > 0.001f ) // && t > 0.000001f
						 {
							 tmin = t;
							 ind = i ; 
						 } 
					}
				}
				
				if ( ind == -1 ) // No intersection
				{
					return ( new Vector ( 0.0f , 0.0f , 0.0f ) ) ; // Black Background
				}
				
				
				// Intersection Point
				Point3D Inter = new Point3D (   dir.getPos().getX() + tmin * dir.getDir().getX() ,
												dir.getPos().getY() + tmin * dir.getDir().getY() , 
												dir.getPos().getZ() + tmin * dir.getDir().getZ() ) ;

				// Normal Vector 
				Vector N = this.scene.objects.get(ind).getNormal(Inter) ;
				N.normalize();
				
				// Bias
				float bias = 0.01f ;
				
				// Slightly modifying intersection point
				Inter = new Point3D ( Inter.getX() + bias * N.getX() ,
									  Inter.getY() + bias * N.getY() ,
									  Inter.getZ() + bias * N.getZ() ) ;
				
				// Viewing vector (surface to eye) 
				Vector V = new Vector ( Inter, new Point3D ( 0.0f , 0.0f, 0.0f ) ) ;
				V.normalize(); 
				
				// Material of Surface
				Material m = this.scene.objects.get(ind).material ;

				
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
					float L_2 = L.norm() ;
					L.normalize();
					
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
						// Phong Illumination Model Contribution with Attenuation
						FinalColor = FinalColor.sum( Lc.times( m.getDiffuse().prod( Math.max(0.0f, L.dot(N) ) ).sum( m.getSpecular().prod( ( float ) Math.pow( ( double )( Math.max( 0.0f , R.dot(V) ) ) , ( double ) m.getPhongIndex() ) ) ) ) ).prod( 80.0f / L_2) ;
					}
				}
				
				// If depth is less than a maximum value
				if ( depth < MAX_DEPTH ) FinalColor = FinalColor.sum ( Trace2 ( Reflection , depth + 1 ) );
			
				Vector ans = new Vector ( m.ambient ) ;
				ans = ans.sum( FinalColor ) ;
				return new Vector ( Math.min(ans.getX(), 1.0f) ,  Math.min(ans.getY(), 1.0f) , Math.min(ans.getZ(), 1.0f) ) ;
				
	}
	/*
	public Vector Trace ( Ray dir  , int depth , Shape object )
	{
		// Check intersection with other Objects
		float tmin = Float.MAX_VALUE ; 
		int ind = -1 ; 
		
		for ( int i = 0 ; i < this.scene.objects.size() ; i++ )
		{
			if ( this.scene.objects.get(i).Intersect( dir ) )
			{
				 float t =  this.scene.objects.get(i).getInter()  ;
				 
				 if ( t < tmin && t > 0.01f ) 
				 {
					 tmin = t;
					 ind = i ; 
				 } 
			}
		}
		
		 
		 // No intersection with other object
		if ( ind == -1  ) 
		{
			if ( object == null )
			{
				return ( new Vector ( 0.0f , 0.0f , 0.0f ) ) ; // Black Background
			}
			else
			{
				System.out.println("Alfredo" + " " + Integer.toString(depth));
				// Normal Vector
				Vector N = object.getNormal( dir.getPos() ) ;
				N.normalize() ; 
				
				Point3D Inter = dir.getPos() ; 
				
				// Viewing vector (surface to eye) 
				Vector V = new Vector ( Inter, new Point3D ( 0.0f , 0.0f, 0.0f ) ) ;
				V.normalize(); 
				
				// Initialize Final Color 
				Vector FinalColor = new Vector ( 0.0f , 0.0f , 0.0f ) ; 
				
				// Material 
				Material m = object.material ;
				
				for ( int i = 0 ; i < this.scene.lights.size() ; i++ )
				{
				
					Light cur = this.scene.lights.get(i) ; 
					
					// Vector from Surface to Light
					
					Vector L = new Vector ( Inter, cur.getPos() ) ;
					float L_2 = L.norm2() ;
					L.normalize();
					
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
						
						// Phong Illumination Model Contribution with Attenuation
						FinalColor = FinalColor.sum( Lc.times( m.getDiffuse().prod( Math.max(0.0f, L.dot(N) ) ).sum( m.getSpecular().prod( ( float ) Math.pow( ( double )( Math.max( 0.0f , R.dot(V) ) ) , ( double ) m.getPhongIndex() ) ) ) ) ).prod( 1.0f / L_2) ;
					}
				}
				
				Vector ans = new Vector ( m.ambient ) ; 
				ans = ans.sum( FinalColor ) ;
				return new Vector ( Math.min(ans.getX(), 1.0f) ,  Math.min(ans.getY(), 1.0f) , Math.min(ans.getZ(), 1.0f) ) ;
				
			}
			
		}

		// Intersection Point
		Point3D Inter = new Point3D (   dir.getPos().getX() + tmin * dir.getDir().getX() ,
										dir.getPos().getY() + tmin * dir.getDir().getY() , 
										dir.getPos().getZ() + tmin * dir.getDir().getZ() ) ;

		// Normal Vector 
		Vector N = this.scene.objects.get(ind).getNormal(Inter) ;
		N.normalize();
		
		boolean inside = false;
		
		if (dir.getDir().dot(N) > 0) 
		{
			N = N.prod(-1.0f) ; 
			inside = true;
		}
		
		// Bias
		float bias = 0.001f ;
		
		// Slightly modifying intersection point
		Inter = new Point3D ( Inter.getX() + bias * N.getX() ,
							  Inter.getY() + bias * N.getY() ,
							  Inter.getZ() + bias * N.getZ() ) ;
		
		// Viewing vector (surface to eye) 
		Vector V = new Vector ( Inter, new Point3D ( 0.0f , 0.0f, 0.0f ) ) ;
		V.normalize(); 
		
		// Material of Surface
		Material m = this.scene.objects.get(ind).material ;

		
		// Calculate Reflection Ray
		Vector RL = dir.getDir().diff( N.prod( 2.0f*N.dot( dir.getDir() ) ) ) ;
		Ray Reflection = new Ray ( Inter, RL ) ;
		
		// Initialize Final Color 
		Vector FinalColor = new Vector ( 0.0f , 0.0f , 0.0f ) ; 
		
		
		if ( depth < this.MAX_DEPTH )
		{
			FinalColor = Trace ( Reflection , depth + 1 , this.scene.objects.get(ind) ) ;
		}		
		else
		{
			/*
			System.out.println("ALfredo");
			for ( int i = 0 ; i < this.scene.lights.size() ; i++ )
			{
			
				Light cur = this.scene.lights.get(i) ; 
				
				// Vector from Surface to Light
				
				Vector L = new Vector ( Inter, cur.getPos() ) ;
				float L_2 = L.norm2() ;
				L.normalize();
				
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
					
					// Phong Illumination Model Contribution with Attenuation
					FinalColor = FinalColor.sum( Lc.times( m.getDiffuse().prod( Math.max(0.0f, L.dot(N) ) ).sum( m.getSpecular().prod( ( float ) Math.pow( ( double )( Math.max( 0.0f , R.dot(V) ) ) , ( double ) m.getPhongIndex() ) ) ) ) ).prod( 1.0f / L_2) ;
				}
			}
			
		}
		
		Vector ans = new Vector ( m.ambient ) ; 
		ans = ans.sum( FinalColor ) ;
		return new Vector ( Math.min(ans.getX(), 1.0f) ,  Math.min(ans.getY(), 1.0f) , Math.min(ans.getZ(), 1.0f) ) ;
	};
	
	*/
	
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

	    		   c  = Trace2 ( EyeRay , 0 ).toColor() ;
	    		   image0.setRGB(x, y, c.getRGB() );
	    	   }
	       } 
	                
	       // Saving picture using png format
	       File f = new File(imagename);

	       try{ ImageIO.write(image0, "png", f); } catch (IOException ex) 
	       { ex.printStackTrace(); }
	   };
	
}
