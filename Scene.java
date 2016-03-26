import java.util.List;

public class Scene 
{
	public Camera camera ; 
	public List<Light> lights ; 
	public List<Shape> objects ;

	public Scene(){};
	
	public Scene ( Camera camera, List<Light> lights , List<Shape> objects )
	{
		this.camera = camera ; 
		this.lights = lights ; 
		this.objects = objects ;
	}
}
