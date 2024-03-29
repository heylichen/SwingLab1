package lab;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * A frame with a panel that demonstrates color change actions.
 */
public class ActionFrame extends JFrame {
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new ActionFrame();
				frame.setTitle("ActionFrame");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

	public ActionFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		buttonPanel = new JPanel();

		// define actions
		Action yellowAction = new ColorAction("Yellow", new ImageIcon(
				"yellow-ball.gif"), Color.YELLOW);
		Action blueAction = new ColorAction("Blue", new ImageIcon(
				"blueball.gif"), Color.BLUE);
		Action redAction = new ColorAction("Red", new ImageIcon("redball.gif"),
				Color.RED);

		// add buttons for these actions
		buttonPanel.add(new JButton(yellowAction));
		buttonPanel.add(new JButton(blueAction));
		buttonPanel.add(new JButton(redAction));

		// add panel to frame
		add(buttonPanel);

		// associate the Y, B, and R keys with names
		InputMap imap = buttonPanel
				.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
		imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
		imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");

		// associate the names with actions
		ActionMap amap = buttonPanel.getActionMap();
		amap.put("panel.yellow", yellowAction);
		amap.put("panel.blue", blueAction);
		amap.put("panel.red", redAction);
	}

	public class ColorAction extends AbstractAction {
		/**
		* Constructs a color action.
		* @param name the name to show on the button
		* @param icon the icon to display on the button
		* @param c the background color
		*/
		public ColorAction(String name, Icon icon, Color c) {
			putValue(Action.NAME, name);
			putValue(Action.SMALL_ICON, icon);
			putValue(Action.SHORT_DESCRIPTION,
					"Set panel color to " + name.toLowerCase());
			putValue("color", c);
		}

		public void actionPerformed(ActionEvent event) {
			Color c = (Color) getValue("color");
			buttonPanel.setBackground(c);
		}
	}
}