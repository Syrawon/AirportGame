package tutoriel;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.input.ChaseCamera;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import com.jme3.math.Vector3f;


import com.jme3.input.ChaseCamera;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Line;


public class EarthTest extends SimpleApplication {

	

	@Override
	public void simpleInitApp() {
		// TODO Auto-generated method stub
		assetManager.registerLocator("earth.zip", ZipLocator.class);
		Spatial earth_geom = assetManager.loadModel("earth/Sphere.mesh.xml");
		Node earth_node = new Node("earth");
		earth_node.attachChild(earth_geom);
		earth_geom.setLocalScale(5.0f);
		rootNode.attachChild(earth_node);
		
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(-2, -10, 1));
		directionalLight.setColor(ColorRGBA.White.mult(1.3f));
		rootNode.addLight(directionalLight);
		
		viewPort.setBackgroundColor(new ColorRGBA(0.1f, 0.1f, 0.1f, 1.0f));
		
		

		
		/*-----------------*/
		//Camera 
		
		flyCam.setEnabled(false);
		ChaseCamera chaseCam = new ChaseCamera(cam, earth_geom, inputManager);
		chaseCam.setDragToRotate(true);

		chaseCam.setInvertVerticalAxis(true);
		chaseCam.setRotationSpeed(10.0f);
		chaseCam.setMinVerticalRotation((float) - (Math.PI/2 - 0.0001f));
		chaseCam.setMaxVerticalRotation((float) Math.PI/2);
		chaseCam.setMinDistance(7.5f);
		chaseCam.setMaxDistance(30.0f);
		
		/*FAIRE UNE LIGNE*/
		
		
		
		Node LinesNode = new Node("LinesNode");
		Vector3f oldVect = new Vector3f(10,0,0);
		Vector3f newVect = new Vector3f(-10, 10, 0);
		
		Line line = new Line(oldVect, newVect);
		
		Geometry lineGeo = new Geometry("lineGeo", line);
		
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.getAdditionalRenderState().setLineWidth(4.0f);
		
		mat.setColor("Color", ColorRGBA.Green);
		LinesNode.setMaterial(mat);
		LinesNode.attachChild(lineGeo);
		lineGeo.setMaterial(mat);
		rootNode.attachChild(LinesNode);
		
		Vector3f oldHeli = new Vector3f(100,0,0);
		
		for (int i = 0; i<1000; i++)
			
		{
			float t = i/5.0f;
			Vector3f newHeli = new Vector3f(FastMath.cos(t), t/5.0f, FastMath.sin(t));
			
			
			Node LinesNode2 = new Node("LinesNode");
			
			Line line2 = new Line(oldHeli, newHeli);
			
			Geometry lineGeo2 = new Geometry("lineGeo", line2);
			
			Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
			mat.getAdditionalRenderState().setLineWidth(4.0f);
			
			mat2.setColor("Color", ColorRGBA.Red);
			LinesNode2.setMaterial(mat2);
			LinesNode2.attachChild(lineGeo2);
			lineGeo2.setMaterial(mat2);
			rootNode.attachChild(LinesNode2);
			
			oldHeli = newHeli;
		}
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 AppSettings settings = new AppSettings(true);
		 settings.setResolution(1024,768);
		 settings.setSamples(8);
		 
		 
		 EarthTest app = new EarthTest();

		 app.setSettings(settings);
		 app.setShowSettings(false);
		 settings.setFrameRate(60);
		 settings.setVSync(true);
		 app.setDisplayStatView(false);
		 app.start();
	}

}
