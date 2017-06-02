package animation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Animator extends JPanel implements ActionListener {

	Timer timer;
	int delay;
	PlanePath path;
	ArrayList<PlanePath> paths;
	ArrayList<Plane> planes;

	Animator() {
		this(30);
	}

	Animator(int delay) {
		super();
		// Timer
		this.delay = delay;
		timer = new Timer(delay, this);
		// Chemins et avions
		paths = new ArrayList<PlanePath>();
		planes = new ArrayList<Plane>();
		// Listeners
		addMouseListener(new MouseAdapter() {
			double speedbalek;
			
			public void mousePressed(MouseEvent event) {
				// ************ à modifier ************
				//for(int i = 0; i < paths.size()-1; i++)
				
			

				path = new PlanePath(event.getX(), event.getY());				
			
				for(Plane p : planes)
				{
					
					if(p.contains(event.getPoint()))
					{
						paths.remove(p.path);

						p.setSelected(true);
						speedbalek = p.getSpeed();
						p.setSpeed(0);
						paths.add(path);
					}
					
				}
				// ************************************
				repaint();
			}

			public void mouseReleased(MouseEvent event) {
				// ************ à modifier ************
				for(Plane p : planes)
				{
					if(p.isSelected())
					{
						p.setSelected(false);
						p.setSpeed(speedbalek);
						p.setPath(path);
					}
				}
				// ************************************
				repaint();
			}
		});
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent event) {
				for(Plane p : planes)
				{
					if(p.isSelected())
					{
						path.addPoint(event.getX(), event.getY());
						repaint();
					}
				}
			}
			@Override
			public void mouseMoved(MouseEvent event) {
				// Rien si la souris bouge sans bouton enfoncé
			}
		});
	}

	public void start() {
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	public void setSpeed(int factor) {
		timer.setDelay(delay / factor);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		// Couleur de trait pour les chemins
		g2d.setColor(Color.RED);
		
		float dash1[] = {2.0f};
		g2d.setStroke(new BasicStroke(1.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                5.0f, dash1, 0.0f));
		
		/*changer le code*/
		
		// Dessin des chemins
		for (PlanePath path : paths) {
			path.draw(g2d);
		}
		// Dessin des avions
		for (Plane plane : planes) {
			plane.draw(g2d);
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// ************ à modifier ************
		ArrayList<Plane> arrivedPlanes = new ArrayList<Plane>();
		// Mise à jour des avions
		for (Plane plane : planes) {
			if (plane.progress()) {
				if (plane.getPath().getLeadsToAirport()) {
					// L'avion a atterri
					arrivedPlanes.add(plane);
				}
				paths.remove(plane.getPath());
				plane.setPath(null);
			}

			if (plane.getLocation().getX() <= 0 || plane.getLocation().getX() >= 500 
					|| plane.getLocation().getY() <= 0 || plane.getLocation().getY() >= 420)
			{
					if(plane.getLocation().getX() <= 0)
						plane.getLocation().setLocation(plane.getLocation().getX()+10, plane.getLocation().getY());
					if(plane.getLocation().getY() <= 0)
						plane.getLocation().setLocation(plane.getLocation().getX(), plane.getLocation().getY()+10);
					if(plane.getLocation().getX() >= 500)
						plane.getLocation().setLocation(plane.getLocation().getX()-10, plane.getLocation().getY());
					if(plane.getLocation().getY() >= 420)
						plane.getLocation().setLocation(plane.getLocation().getX(), plane.getLocation().getY()-10);
					plane.setAngle(plane.getAngle()+Math.PI/4);
			}
			
			
		}
		//pour diminuer la fréquence d'apparition
		//on peut augmenter la range
		int range = 15;

		if(((int)(Math.random() * range) + 1) == 2)
		{
			int pos = (int)(Math.random() * range) + 1;
			int murPos = (int)(Math.random() * 420) + 1;

		
			if (pos == 1)
			{
				Point2D p = new Point2D.Double(0,murPos);
				Plane pl = new Plane ("images/cancer.png", 
						p, Math.PI, 0.03, 8);
				planes.add(pl);
			}					
			else if (pos == 2)
			{
				Point2D p = new Point2D.Double(murPos,0);
				Plane pl = new Plane ("images/cancer.png", p, Math.PI*(3/4), 0.5, 0.9);
				planes.add(pl);
				
				
				System.out.println("Position de l'avion Airplane =" + pos + "\n");


			}
			else if (pos == 3)
			{
				Point2D p = new Point2D.Double(murPos,500);
				Plane pl = new Plane(p);
				planes.add(pl);

			}
			else if (pos == 4)
			{
				Point2D p = new Point2D.Double(500,murPos);
				Plane pl = new Plane(p);
				planes.add(pl);
			}			
			
		}
		
		// Mise à jour de l'affichage
		repaint();
		// Suppression des avions atterri
		for (Plane plane : arrivedPlanes) {
			planes.remove(plane);
		}
		// ************************************
	}
}
