package tutoriel;

import com.jme3.app.SimpleApplication;
import com.jme3.input.ChaseCamera;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;


public class CubesTest extends SimpleApplication {

	
	private Geometry geom;
	@Override
	public void simpleInitApp() {
		// TODO Auto-generated method stub
		Box b = new Box(1, 1, 1);
		this.geom = new Geometry("Box", b);
		Material mat = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Blue);
		geom.setMaterial(mat);
		rootNode.attachChild(geom);
		
		Box b2 = new Box(1, 1, 1);
		Geometry geom2 = new Geometry("Box", b2);
		Material mat2 = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat2.setColor("Color", ColorRGBA.Green);
		geom2.setMaterial(mat2);
		rootNode.attachChild(geom2);
		geom2.setLocalTranslation(0.0f, 3.0f, 0.0f);
		
		

		Box b3 = new Box(1, 1, 1);
		Geometry geom3 = new Geometry("Box", b3);
		Material mat3 = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat3.setColor("Color", ColorRGBA.Red);
		geom3.setMaterial(mat3);
		rootNode.attachChild(geom3);
		geom3.setLocalTranslation(3.0f, 0.0f, -3.0f);
	
		
		
		
		/*-----------------*/
		//Camera 
		
		flyCam.setEnabled(false);
		ChaseCamera chaseCam = new ChaseCamera(cam, geom, inputManager);
		chaseCam.setDragToRotate(true);

		chaseCam.setInvertVerticalAxis(true);
		chaseCam.setRotationSpeed(10.0f);
		chaseCam.setMinVerticalRotation((float) - (Math.PI/2 - 0.0001f));
		chaseCam.setMaxVerticalRotation((float) Math.PI/2);
		chaseCam.setMinDistance(7.5f);
		chaseCam.setMaxDistance(30.0f);
		
	
	}

	
	public Geometry getGeom()
	{
		return this.geom;
	}
	@Override
	public void simpleUpdate(float tpf)
	{
	
		this.getGeom().rotate(0.005f,0,0);
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 AppSettings settings = new AppSettings(true);
		 settings.setResolution(1024,768);
		 settings.setSamples(8);
		 
		 
		CubesTest app = new CubesTest();

		 app.setSettings(settings);
		 app.setShowSettings(false);
		 settings.setFrameRate(60);
		 settings.setVSync(true);
		      app.setDisplayStatView(false);
		      
		 app.createCanvas();
                   
		                             
	}

}
