package raytracer;
import java.awt.Color;

public class Light
{
	private Point3D Position ; 
	private Color  Color ; 
	
	public Light( Point3D Position, Color Color )
	{
		this.Position = Position ; 
		this.Color = Color ;
	}
	
	public Point3D getPosition ( )
	{
		return Position ;
	}
	
	public Color getColor ( )
	{
		return Color ;
	}
}

