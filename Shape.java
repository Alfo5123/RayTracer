
public abstract class Shape 
{
	public Material material;

    public abstract boolean Intersect( Ray ray );
    
    public abstract Vector getNormal ( Point3D p );
    
    public abstract float getInter ( ) ;
    
    public abstract Material getMaterial() ; 
};
