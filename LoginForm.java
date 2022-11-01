import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginForm extends JFrame implements ActionListener, MouseListener{

	
	
	JPanel mainPanel, mainAtas,mainCenter,emailFieldPanel,emailLabPanel,pwLabPanel,pwFieldPanel,buttonPanel1,buttonPanel2;
	JLabel welcomeBanner, emailLab, pwLab;
	JTextField emailField;
	JPasswordField pwField;
	JButton login,donHepAkun;

	Vector<Account> accountVector;
	Account account;
	
	public LoginForm(Vector<Account> accountVector) {
		
	
		
		this.accountVector = accountVector;
		
	//MAIN PANEL
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.decode("#3D4242"));
		
	// Panel Atas
		mainAtas = new JPanel();	
		mainAtas.setBackground(Color.decode("#3D4242"));
		
		welcomeBanner = new JLabel("LOGIN");
		welcomeBanner.setFont(welcomeBanner.getFont().deriveFont(60.0f));
		welcomeBanner.setForeground(Color.WHITE);
		welcomeBanner.setBorder(new EmptyBorder(15,25,25,25));
		//.setBorder(new EmptyBorder(top, left, bottom, right));
		mainAtas.add(welcomeBanner);
		
		
		
	// Panel Tengah	
		mainCenter = new JPanel(new GridLayout(3, 2));
		mainCenter.setBackground(Color.decode("#3D4242"));
		
		//EMAIL SECTION
		// emailLab
		emailLabPanel = new JPanel();
		emailLabPanel.setBackground(Color.decode("#3D4242"));
		emailLabPanel.setBorder(new EmptyBorder(40,-100,0,0));
		//.setBorder(new EmptyBorder(top, left, bottom, right));
		
		emailLab = new JLabel("Email");
		emailLab.setForeground(Color.WHITE);
		emailLab.setFont(emailLab.getFont().deriveFont(18.0f));
		
		emailLabPanel.add(emailLab);
		
		
		// emailField
		emailFieldPanel = new JPanel();
		emailFieldPanel.setBackground(Color.decode("#3D4242"));
		emailFieldPanel.setBorder(new EmptyBorder(30,-85,0,0));
		
		emailField = new JTextField();
		emailField.setFont(emailField.getFont().deriveFont(18.0f));
		emailField.setPreferredSize(new Dimension(300,50));
		//new Dimension(width, height)
		
		emailFieldPanel.add(emailField);
		
		
		//PASWORD SECTION
		//Lab
		pwLabPanel = new JPanel();
		pwLabPanel.setBackground(Color.decode("#3D4242"));
		pwLabPanel.setBorder(new EmptyBorder(10,-100,0,0));
		
		pwLab = new JLabel("Password");
		pwLab.setForeground(Color.WHITE);
		pwLab.setFont(pwLab.getFont().deriveFont(18.0f));
		pwLab.setBorder(new EmptyBorder(0,60,50,25));
		
		pwLabPanel.add(pwLab);
		
		//Field
		pwFieldPanel = new JPanel();
		pwFieldPanel.setBackground(Color.decode("#3D4242"));
		pwFieldPanel.setBorder(new EmptyBorder(0,-85,0,0));
		//.setBorder(new EmptyBorder(top, left, bottom, right));
		
		pwField = new JPasswordField();
		pwField.setFont(pwField.getFont().deriveFont(18.0f));
		pwField.setPreferredSize(new Dimension(300,50));
		
		pwFieldPanel.add(pwField);
		
		// ke 3 yaitu button"nya
		
		
		buttonPanel1 = new JPanel();
		buttonPanel1.setBackground(Color.decode("#3D4242"));
		buttonPanel1.setBorder(new EmptyBorder(0,30,0,-60));
		
		
		login = new JButton("Login");
		login.setBackground(Color.decode("#efa1a2"));
		login.setForeground(Color.WHITE);
		login.setFont(login.getFont().deriveFont(18.0f));
		login.setPreferredSize(new Dimension(270,50));
		//login.setBorder(new EmptyBorder(0, 0, 0, 20));
		//.setBorder(new EmptyBorder(top, left, bottom, right));
		
		login.addActionListener(this);
		login.addMouseListener(this);
		
		buttonPanel1.add(login);
		
		buttonPanel2= new JPanel();
		buttonPanel2.setBackground(Color.decode("#3D4242"));
		buttonPanel2.setBorder(new EmptyBorder(0,-85,0,0));
		
		donHepAkun = new JButton("I don't have any account!");
		donHepAkun.setBackground(Color.decode("#efa1a2"));
		donHepAkun.setForeground(Color.WHITE);
		donHepAkun.setFont(donHepAkun.getFont().deriveFont(18.0f));
		donHepAkun.setPreferredSize(new Dimension(300,50));
		
		donHepAkun.addActionListener(this);
		donHepAkun.addMouseListener(this);
		
		buttonPanel2.add(donHepAkun);
		
		
		// COMPILE ke mainCenter
		
				mainCenter.add(emailLabPanel);
				mainCenter.add(emailFieldPanel);
				
				mainCenter.add(pwLabPanel);
				mainCenter.add(pwFieldPanel);
				
				mainCenter.add(buttonPanel1);
				mainCenter.add(buttonPanel2);
		

	// Masukin ke Main Panelllllllll
				
		mainPanel.add(mainAtas,BorderLayout.NORTH);
		mainPanel.add(mainCenter,BorderLayout.CENTER);
		
		
	// Compile ke frame
		add(mainPanel);		
		
	// Frame
		setTitle("Clay's Music Store");
		setVisible(true);
		setSize(780,480);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == login) {
			
			String EmailLogin = emailField.getText();
			String passwordLogin = pwField.getText();
			
			if (EmailLogin.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please input email","Alert",JOptionPane.WARNING_MESSAGE);
			}
			
			else if (passwordLogin.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please input password","Alert",JOptionPane.WARNING_MESSAGE);
			}
			
			else if (accountVector.isEmpty()) {
				JOptionPane.showMessageDialog(this,"No account registerd!","Alert",JOptionPane.WARNING_MESSAGE);
				
				emailField.setText("");
				pwField.setText("");
			}
			
			else if (validation(EmailLogin,passwordLogin)) {
				if (account.getRoles().equals("Admin") ) {
					
					JOptionPane.showMessageDialog(this,"Welcome "+account.getUserName(),"Message",JOptionPane.INFORMATION_MESSAGE);
					
					this.dispose();
					new MainAdminForm();
				}
				
				else if (account.getRoles().equals("User")) {
					
					JOptionPane.showMessageDialog(this,"Welcome "+account.getUserName(),"Message",JOptionPane.INFORMATION_MESSAGE);
					
					this.dispose();
					new MainUserForm();
				}
				
			}
			
//			else if (validation(EmailLogin, passwordLogin) == false) {
//				
////				JOptionPane.showMessageDialog(this,"Invalid email or password!","Alert",JOptionPane.WARNING_MESSAGE);
//				
//				emailField.setText("");
//				pwField.setText("");
//			}
			
		// end of login button
		
		}
		
// donhepakun BUTON
		
		else if (e.getSource() == donHepAkun) {
			
			this.dispose();
			new RegisterForm(accountVector);
		}
		
	}

	private boolean validation(String EmailLogin,String passwordLogin ) {
		
		for (Account account : accountVector) {
			
		
		if (EmailLogin.equals(account.getEmail()) && (passwordLogin.equals(account.getPassword()))) {
				this.account = account;
				return true;
			}
			
			else {
				JOptionPane.showMessageDialog(this,"Invalid email or password!","Alert",JOptionPane.WARNING_MESSAGE);
				emailField.setText("");
				pwField.setText("");
			}
		
			return false;
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		if (e.getSource() == login) {
			login.setBackground(Color.decode("#75217C"));
			
		}
		
		else if (e.getSource() == donHepAkun) {
			donHepAkun.setBackground(Color.decode("#75217C"));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == login) {
			login.setBackground(Color.decode("#efa1a2"));
		}
		
		else if (e.getSource() == donHepAkun) {
			donHepAkun.setBackground(Color.decode("#efa1a2"));
		}
		
	}
	
	


}
