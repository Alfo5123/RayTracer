import java.awt.Color;

public class Light
{
	private Point3D pos ; 
	private Color  col ; 
	
	public Light(){};
	
	public Light( Point3D pos , Color col )
	{
		this.pos = pos ; 
		this.col = col ;
	};
	
	public Point3D getPos ( )
	{
		return this.pos ;
	};
	
	public Color getCol ( )
	{
		return this.col ;
	};
}
