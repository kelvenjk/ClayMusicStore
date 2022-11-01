import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainUserForm extends JFrame implements ActionListener {

	JPanel 	mainPanel;
	JMenuBar menuBar;
	JMenu user, store;
	
    JMenuItem 	logOff, 
    			buyMusic, history;
    
    Vector<Account> accountVector;


	public MainUserForm() {
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
		store = new JMenu("Store");
		
		buyMusic = new JMenuItem("Buy Music");
		buyMusic.addActionListener(this);
		
		history = new JMenuItem("History");
		history.addActionListener(this);
		
		store.add(buyMusic);
		store.add(history);
		
		
		menuBar.add(user);
		menuBar.add(store);
		
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
		
		if (e.getSource() == logOff) {
			
			this.dispose();
			new LoginForm(accountVector);
			
		}
		
		else if (e.getSource() == buyMusic) {
			
			this.dispose();
			new BuyMusic();
			
		}
		
		else if (e.getSource() == history) {
			this.dispose();
			new History();
		}
		
		
	}

}
