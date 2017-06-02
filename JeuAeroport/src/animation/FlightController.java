package animation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FlightController extends JFrame {

	private static int SIZE = 500;
	Animator animator;

	FlightController() {
		super("Simple Flight Control");
		this.setSize(new Dimension(SIZE, SIZE));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Fenêtre principale
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		content.setBackground(Color.GRAY);
		// Zone de contrôle des avions
		animator = new Animator();
		content.add(animator, BorderLayout.CENTER);
		// Boutons de contrôle
		JPanel menu = new JPanel();
		menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
		JButton buttonSpeed = new JButton("Accélérer");
		buttonSpeed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// ************ à modifier ************
				// ************************************
			}
		});
		menu.add(buttonSpeed);
		menu.add(Box.createHorizontalGlue());
		JButton buttonPause = new JButton("Pause");
		buttonPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JButton button = (JButton) event.getSource();
				if (button.getText().equals("Pause")) {
					animator.stop();
					button.setText("Continuer");
				} else {
					animator.start();
					button.setText("Pause");
				}
			}
		});
		menu.add(buttonPause);
		content.add(menu, BorderLayout.NORTH);
		animator.start();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new FlightController();
	}

}
