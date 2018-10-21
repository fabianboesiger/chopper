import java.awt.Dimension;
import javax.swing.JFrame;

public class MainFrame extends JFrame{

	public MainFrame(){
		
		setSize(640, 480);
		setMinimumSize(new Dimension(640, 480));
		//setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//setUndecorated(true);
		setVisible(true);
		setTitle("Chopper");
		
		GamePanel gamePanel = new GamePanel();
		add(gamePanel);
		
	}
	
}
