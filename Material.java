import java.awt.Color;

public class Material 
{
	public Color ambient  ;
	public Color diffuse ; 
	public Color specular ; 
	float reflectance ; 
	float PhongIndex ; 
	
	public Material(){} ; 
	
	public Material (  Color ambient, Color diffuse , Color specular ,
				       float reflectance, float PhongIndex)
	{
		this.ambient = ambient ; 
		this.diffuse = diffuse ; 
		this.specular = specular ;
		this.reflectance = reflectance ; 
		this.PhongIndex = PhongIndex ; 
	}
	
	public Vector getAmbient( )
	{
		return new Vector ( ambient ) ; 
	};
	
	public Vector getDiffuse ( )
	{
		return new Vector ( diffuse ) ; 
	};
	
	public Vector getSpecular ( )
	{
		return new Vector ( specular ) ;
	};
	
	public float getPhongIndex()
	{
		return this.PhongIndex ;
	};
}
