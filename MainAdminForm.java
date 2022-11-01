import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainAdminForm extends JFrame implements ActionListener{
	
	
	
	DatabaseConnection db;
	
	JPanel 	mainPanel;
	JMenuBar menuBar;
	JMenu user, manage;
	
    JMenuItem 	logOff, 
    			manageMusic, manageMusicGenre;
    
    Vector<Account> accountVector;
    
    Vector<Object> tableContent;

	public MainAdminForm() {

		// TODO Auto-generated constructor stub
		
				
				
				mainPanel = new JPanel();
				mainPanel.setBackground(Color.decode("#3D4242"));
				
				menuBar = new JMenuBar();
				
				//User menu
				user = new JMenu("User");
				logOff = new JMenuItem("Log Off");
				logOff.addActionListener(this);
				
				user.add(logOff);
				
				//Store Menu
				manage = new JMenu("Manage");
				
				manageMusic = new JMenuItem("Manage Music");
				manageMusic.addActionListener(this);
				
				manageMusicGenre = new JMenuItem("Manage Music Genre");
				manageMusicGenre.addActionListener(this);
				
				manage.add(manageMusic);
				manage.add(manageMusicGenre);
				
				
				menuBar.add(user);
				menuBar.add(manage);
				
				// Compile to frame		

				add(mainPanel);
				
				// Frame
						
						setJMenuBar(menuBar);
						setTitle("Clay's Music Store");
						setVisible(true);
						setSize(1020,680);
						setResizable(false);
						setLocationRelativeTo(null);
						setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == manageMusicGenre) {
			
			new manageMusicGenre();
//			getGenresData();
			this.dispose();
		}
		
		else if (e.getSource() == manageMusic) {
			
			this.dispose();
			new Manages();
			
		}
		
		else if (e.getSource() == logOff) {
			
			this.dispose();
			new LoginForm(accountVector);
			
		}
		
		
		
	}

	
	
	
}
