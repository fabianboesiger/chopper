import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	public void paint(Graphics g){
		
		g.setColor(Color.red);
		g.fillRect(50, 50, 100, 100);
		
	}
	
}
