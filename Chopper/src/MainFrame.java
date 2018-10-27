import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	private StartPanel startPanel;
	private GamePanel gamePanel;
	private EndPanel endPanel;

	public MainFrame(){
		
		// TODO: Add button to switch between windowed and fullscreen mode
		
		// Windowed mode
		setSize(640, 480);
		setMinimumSize(new Dimension(640, 480));
		
		// Fullscreen mode
		// setExtendedState(JFrame.MAXIMIZED_BOTH); 
		// setUndecorated(true);
		
		setLocationRelativeTo(null);
		setTitle("Chopper");
		
		// Start up home screen
        home();
		
		setVisible(true);
		
	}
	
	public void start() {
		if(startPanel != null) {
			remove(startPanel);
			startPanel = null;
		}
		if(endPanel != null) {
			remove(endPanel);
			endPanel = null;
		}
		gamePanel = new GamePanel(this);
		add(gamePanel);
		revalidate();
	}
	
	public void end(int score) {
		if(gamePanel != null) {
			remove(gamePanel);
			gamePanel = null;
		}
		if(startPanel != null) {
			remove(startPanel);
			startPanel = null;
		}
		endPanel = new EndPanel(this, score);
		add(endPanel);
		revalidate();
	}
	
	public void home() {
		if(endPanel != null) {
			remove(endPanel);
			endPanel = null;
		}
		if(gamePanel != null) {
			remove(gamePanel);
			gamePanel = null;
		}
		startPanel = new StartPanel(this);
		add(startPanel);
		revalidate();
	}
	
}
