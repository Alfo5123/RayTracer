package RAYTRACER;
import java.awt.Color;

public class Material 
{
	private Color Ambient  ;
	private Color Diffuse ; 
	private Color Specular ; 
	private double PhongIndex ; 
	
	
	public Material ( Color Ambient, Color Diffuse , Color Specular , float PhongIndex )
	{
		this.Ambient = Ambient ; 
		this.Diffuse = Diffuse ; 
		this.Specular = Specular ;
		this.PhongIndex = PhongIndex ; 
	}
	
	public Vector getAmbientComponent ( )
	{
		return new Vector ( Ambient ) ; 
	}
	
	public Vector getDiffuseComponent ( )
	{
		return new Vector ( Diffuse ) ; 
	}
	
	public Vector getSpecularComponent ( )
	{
		return new Vector ( Specular ) ;
	}
	
	public double getPhongIndex()
	{
		return PhongIndex ;
	}
}
