import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EndPanel extends JPanel implements Scoreboard{
	
	String username = "Benutzername";
	
	public EndPanel(MainFrame mainFrame, int score){
		
		try {
			Scanner scanner = new Scanner(new File(System.getProperty("user.home")+"/username.txt"));
			username = scanner.nextLine();
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		setBorder(new EmptyBorder(32, 32, 32, 32));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
        // Title
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("<html><span style='font-size: 32px;'>Chopper</span></html>"), gbc);
        
        // Buttons
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
            	mainFrame.start();
            }
        });
        JButton homeButton = new JButton("Hauptmenü");
        homeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
            	mainFrame.home();
            }
        });
        JButton exitButton = new JButton("Verlassen");
        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
            	System.exit(0);
            }
        });
        
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.add(startButton, gbc);
        buttonsPanel.add(homeButton, gbc);
        buttonsPanel.add(exitButton, gbc);

        gbc.weighty = 1;
        add(buttonsPanel, gbc);
        
        // Score submission
        JLabel scoreLabel = new JLabel("Dein Score ist "+score+"!");
        JTextField usernameTextField = new JTextField(username);
        JButton submitButton = new JButton("Senden");
        JPanel scorePanel = new JPanel(new GridBagLayout());
        
        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
            	if(usernameTextField.getText().length() >= 2) {
            		if(!usernameTextField.getText().equals(username)) {
						try {
							FileWriter fileWriter = new FileWriter(System.getProperty("user.home")+"/username.txt");
							PrintWriter printWriter = new PrintWriter(fileWriter);
		            	    printWriter.print(usernameTextField.getText());
		            	    printWriter.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
	            	    
            		}
	            	int[] result = push(usernameTextField.getText(), score);
	            	scorePanel.remove(scoreLabel);
	            	scorePanel.remove(usernameTextField);
	            	scorePanel.remove(submitButton);
	            	scorePanel.add(new JLabel("Mit dem Score "+score+" bist du auf Rang "+result[0]+" von "+result[1]+"."));
	            	scorePanel.revalidate();
            	}
            }
        });
        
        scorePanel.add(scoreLabel, gbc);
        scorePanel.add(usernameTextField, gbc);
        scorePanel.add(submitButton, gbc);

        add(scorePanel, gbc);
		
	}
	
}
