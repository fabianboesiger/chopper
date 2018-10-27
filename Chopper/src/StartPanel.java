import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class StartPanel extends JPanel implements Scoreboard{
	
	public StartPanel(MainFrame mainFrame){
		
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
        buttonsPanel.add(exitButton, gbc);

        gbc.weighty = 1;
        add(buttonsPanel, gbc);
        
        // Scoreboard table
        DefaultTableModel rankingModel = new DefaultTableModel(players(), new String[] {"Benutzername", "Score"}){
            @Override
            public boolean isCellEditable(int row, int column){
               return false;
            }
        };
        JTable rankingTable = new JTable(rankingModel);
        rankingTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane rankingScrollPane = new JScrollPane(rankingTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.BOTH;
        add(rankingScrollPane, gbc);
        
	}
	
}
