package raytracer;
import java.util.List;

public class Scene 
{
	private Camera Camera ; 
	private List<Light> Lights ; 
	private List<Shape> Objects ;
	
	public Scene ( Camera Camera, List<Light> Lights , List<Shape> Objects )
	{
		this.Camera = Camera ; 
		this.Lights = Lights ; 
		this.Objects = Objects ;
	}
	
	public Camera getCamera()
	{
		return Camera ;
	}
	
	public List<Light> getLights()
	{
		return Lights ;
	}
	
	public List<Shape> getObjects()
	{
		return Objects ;
	}
}


