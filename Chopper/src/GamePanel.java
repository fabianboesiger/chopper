import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	private MainFrame mainFrame;
	private Thread runner;
	private static final int FPS = 60;
	private int counter = 0;
	
	public GamePanel(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		runner = new Thread(this);
		runner.start();
	}
	
	@Override
	public void paint(Graphics g){
		
		/*
		This method gets called 60 times per second, implement game logic here
		To end the game, simply call "mainFrame.end(score);"
		Remove the following example and start programming!
		*/
		
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.red);
		g.fillRect(counter*2, 50, 100, 100);
		
		if(counter*2 + 100 > getWidth()) {
			mainFrame.end(counter);
		}
	}
	
	@Override
	public void run(){
		
		long time = System.currentTimeMillis();
		
		while(true){
			counter++;
			repaint();
			// Delay
		    try{
		    	time += 1000.0/((double) FPS);
		        Thread.sleep(Math.max(0, time-System.currentTimeMillis()));
		    }catch(InterruptedException e){
		        e.printStackTrace();
		    }
		}
		
	}
	
}
