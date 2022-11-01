import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RegisterForm extends JFrame implements ActionListener, MouseListener{
 
	
	Vector<Account> accountVector;
	
	JPanel 	mainPanel, mainAtas,mainCenter,
			genderOptionPanel, userFieldPan, emailFieldPan, pwFieldPan, conPwFieldPan,  femalePan,
			buttonPan1, buttonPan2,
			userLabPan, emailLabPan, pwLabPan, conPwLabPan, genderLabPan, rolesLabPan;
	
	//rolesBoxPan,
	PreparedStatement preparedStatement;
	Connection connection;
	Statement statement;
	
	
	
	JLabel registerBanner, userNameLab, emailLab, pwLab, conPwLab, gender;
	//rolesLab;
	JTextField userNameField, emailField;
	JPasswordField pwField, pwConfirmPass;
	JButton register, hepakun;
	
	JRadioButton femaleRadioButton, maleRadioButton;
	ButtonGroup genderButtonGroup;
	
	
	
	public RegisterForm(Vector<Account> accountVector) {
		
		this.accountVector = accountVector;
		
		
	//MAIN PANEL
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.decode("#3D4242"));
		
	
	// Panel ATAS
		mainAtas = new JPanel();	
		mainAtas.setBackground(Color.decode("#3D4242"));
				
		registerBanner = new JLabel("REGISTER");
		registerBanner.setFont(registerBanner.getFont().deriveFont(60.0f));
		registerBanner.setForeground(Color.WHITE);
		registerBanner.setBorder(new EmptyBorder(15,60,25,25));
		//.setBorder(new EmptyBorder(top, left, bottom, right));
		
		mainAtas.add(registerBanner);

		
	// Panel TENGAH
		mainCenter = new JPanel(new GridLayout(7,2));
		mainCenter.setBackground(Color.decode("#3D4242"));
		
		//KOMPONEN Form
		
		//UserName
			//LabelName
				userLabPan = new JPanel();
				userLabPan.setBackground(Color.decode("#3D4242"));
				
				userNameLab = new JLabel("Username");
				userNameLab.setForeground(Color.WHITE);
				userNameLab.setFont(userNameLab.getFont().deriveFont(18.0f));
				userNameLab.setPreferredSize(new Dimension(250,50));
				userLabPan.add(userNameLab);
				
			//NameField
				userFieldPan = new JPanel();
				userFieldPan.setBackground(Color.decode("#3D4242"));
			
				userNameField = new JTextField();
				userNameField.setFont(userNameLab.getFont().deriveFont(18.0f));
				userNameField.setPreferredSize(new Dimension(350,50));
				
				userFieldPan.add(userNameField);
		
		
				
		//Email
			//emailLab
				
				emailLabPan = new JPanel();
				emailLabPan.setBackground(Color.decode("#3D4242"));
				
				emailLab = new JLabel("Email");
				emailLab.setForeground(Color.WHITE);
				emailLab.setFont(emailLab.getFont().deriveFont(18.0f));
				emailLab.setPreferredSize(new Dimension(250,50));
				
				emailLabPan.add(emailLab);
				
			//emailField
				emailFieldPan = new JPanel();
				emailFieldPan.setBackground(Color.decode("#3D4242"));
				
				emailField = new JTextField();
				emailField.setFont(emailLab.getFont().deriveFont(18.0f));
				emailField.setPreferredSize(new Dimension(350,50));
				
				emailFieldPan.add(emailField);
		
				
	
		//Password
			//passwordLab
				pwLabPan = new JPanel();
				pwLabPan.setBackground(Color.decode("#3D4242"));
				
				pwLab = new JLabel("Password");
				pwLab.setForeground(Color.WHITE);
				pwLab.setFont(pwLab.getFont().deriveFont(18.0f));
				pwLab.setPreferredSize(new Dimension(250,50));
				
				pwLabPan.add(pwLab);
				
			//pwField
				pwFieldPan = new JPanel();
				pwFieldPan.setBackground(Color.decode("#3D4242"));
				
				
				pwField = new JPasswordField();
				pwField.setFont(pwLab.getFont().deriveFont(18.0f));
				pwField.setPreferredSize(new Dimension(350,50));
		
				pwFieldPan.add(pwField);
	
				
				
		//PasswordConfirm
			//passwordLab
				conPwLabPan = new JPanel();
				conPwLabPan.setBackground(Color.decode("#3D4242"));
				
				conPwLab = new JLabel("Confirm Password");
				conPwLab.setForeground(Color.WHITE);
				conPwLab.setFont(conPwLab.getFont().deriveFont(18.0f));
				conPwLab.setPreferredSize(new Dimension(250,50));
				
				conPwLabPan.add(conPwLab);
				
			//pwField 
				conPwFieldPan = new JPanel();
				conPwFieldPan.setBackground(Color.decode("#3D4242"));
				
				pwConfirmPass = new JPasswordField();
				pwConfirmPass.setFont(conPwLab.getFont().deriveFont(18.0f));
				pwConfirmPass.setPreferredSize(new Dimension(350,50));
		
				conPwFieldPan.add(pwConfirmPass);
				
				
		// Gender Button group
				
				genderLabPan = new JPanel();
				genderLabPan.setBackground(Color.decode("#3D4242"));
				
				gender = new JLabel("Gender");
				gender.setFont(gender.getFont().deriveFont(18.0f));
				gender.setForeground(Color.WHITE);
				gender.setPreferredSize(new Dimension(250,50));
				
				genderLabPan.add(gender);
				
				genderOptionPanel = new JPanel(new GridLayout(1, 2));
				genderOptionPanel.setBackground(Color.decode("#3D4242"));
				
				
				genderButtonGroup = new ButtonGroup();
		 		maleRadioButton = new JRadioButton("Male");
		 		femaleRadioButton = new JRadioButton("Female");
		 		//femaleRadioButton.setBounds(10, 10, 250, 50);
		 		
		 		maleRadioButton.setFont(maleRadioButton.getFont().deriveFont(18.0f));
		 		femaleRadioButton.setFont(femaleRadioButton.getFont().deriveFont(18.0f));
		 		maleRadioButton.setForeground(Color.WHITE);
		 		femaleRadioButton.setForeground(Color.WHITE);
		 		maleRadioButton.setBackground(Color.decode("#3D4242"));
		 		femaleRadioButton.setBackground(Color.decode("#3D4242"));
		 		
		 		femalePan = new JPanel();
		 		femalePan.setBackground(Color.decode("#3D4242"));
		 		femalePan.setBorder(new EmptyBorder(10, 70, 0, 0));
				//.setBorder(new EmptyBorder(top, left, bottom, right));
		 		
		 		genderOptionPanel.setBounds(0, 0, 0, 0);
		 		//.setBounds(x, y, width, height);o
		 		femalePan.add(femaleRadioButton);
		 		
		 		genderButtonGroup.add(femaleRadioButton);
				genderButtonGroup.add(maleRadioButton);
				
				genderOptionPanel.add(femalePan);
				genderOptionPanel.add(maleRadioButton);
		
				
				buttonPan1 = new JPanel();
				buttonPan1.setBackground(Color.decode("#3D4242"));
				
				register = new JButton("Register");
				register.setBackground(Color.decode("#efa1a2"));
				register.setForeground(Color.WHITE);
				register.setFont(register.getFont().deriveFont(18.0f));
				register.setPreferredSize(new Dimension(350,50));
				//new Dimension(width, height)
				register.addActionListener(this);
				register.addMouseListener(this);
				buttonPan1.setBorder(new EmptyBorder(0, 65, 0, 0));
				//buttonPan1.setBorder(new EmptyBorder(top, left, bottom, right));
				buttonPan1.add(register);
				
				buttonPan2 = new JPanel();
				buttonPan2.setBackground(Color.decode("#3D4242"));
				
				hepakun = new JButton("I Have an account");
				hepakun.setBackground(Color.decode("#efa1a2"));
				hepakun.setForeground(Color.WHITE);
				hepakun.setFont(hepakun.getFont().deriveFont(18.0f));
				hepakun.setPreferredSize(new Dimension(350,50));
				hepakun.addActionListener(this);
				hepakun.addMouseListener(this);
				
				buttonPan2.add(hepakun);
				
		// COMPILE ke mainCenter
				//userLabPan, emailLabPan, pwLabPan, conPwLabPan, genderLabPan, rolesLabPan;
			mainCenter.add(userLabPan);
			mainCenter.add(userFieldPan);
				
			mainCenter.add(emailLabPan);
			mainCenter.add(emailFieldPan);
				
			mainCenter.add(pwLabPan);
			mainCenter.add(pwFieldPan);
				
			mainCenter.add(conPwLabPan);
			mainCenter.add(conPwFieldPan);	
				
			mainCenter.add(genderLabPan);
			mainCenter.add(genderOptionPanel);
			
			
			mainCenter.add(buttonPan1);
			mainCenter.add(buttonPan2);
		
	// Masukin ke Main Panelllllllll		
		mainPanel.add(mainAtas,BorderLayout.NORTH);
		mainPanel.add(mainCenter, BorderLayout.CENTER);
				
	// Compile ke frame
		add(mainPanel);	
				
	// Frame
		setTitle("Clay's Music Store");
		setVisible(true);
		setSize(780,600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == register) {
			
				String userName = userNameField.getText();
				String email = emailField.getText();
				String password = pwField.getText();
				String conpw = pwConfirmPass.getText();
				String gender= (maleRadioButton.isSelected()) ? "Male" : "Female";
				String roles = "User" ;
				int i = 0 ;
				int userId = i++ ;
				
				if (validation(userName, email,password,conpw, gender)) {
					
					
					Account newAccount = new Account(userId , userName, email, password, gender,roles);
					
					accountVector.add(newAccount);
					
					
					try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clay's music store","root","");
					         Statement stmt = conn.createStatement();
					      ) {  
						
						
						try {
							Class.forName("com.mysql.jdbc.Driver");
							
							connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clay's music store","root","");
							
							statement = connection.createStatement();
							
						} catch (Exception a) {
							// TODO Auto-generated catch block
							a.printStackTrace();
						}
						         							
					         
					     			preparedStatement = connection.prepareStatement(
					    			"INSERT INTO users (id, username, email, password ,gender,roles)"
					    			+ "VALUES (?,?,?,?,?,?)");
					    			
					    		
					    			preparedStatement.setInt(1, userId);
					    			preparedStatement.setString(2, userName);
					    			preparedStatement.setString(3, email);
					    			preparedStatement.setString(4, password);
					    			preparedStatement.setString(5, gender);
					    			preparedStatement.setString(6, roles);

					    			preparedStatement.execute();
					         
					         
					        // System.out.println("SUDAH MASUK");      
					      } catch (SQLException a) {
					         a.printStackTrace();
					      }
					
					JOptionPane.showMessageDialog(this,"Account Succesfully Registered","Added Succesfully",JOptionPane.INFORMATION_MESSAGE);
					
					
					this.dispose();
					new LoginForm(accountVector);
				}
				

		}
		
		else if (e.getSource() == hepakun) {
			
			this.dispose();
			new LoginForm(accountVector);
		}
	}
	
	
	//BIKIN VALIDASI
	//DSA

	public boolean validation(String userName, String email, String password, String conpw, String gender) {
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
		
		if (!userName.isEmpty()) {
			if (!email.isEmpty()) {
				if (email.matches(emailRegex)) {
					if (!password.isEmpty()) {
						if (!conpw.isEmpty()) {
							if (conpw.equals(password)) {
								if (maleRadioButton.isSelected()) {
								
										return true;
										
			

								}
								else {
									
									if (femaleRadioButton.isSelected()) {
										
											return true;
											
										

									}
									else {
										JOptionPane.showMessageDialog(this,"Gender must be choosen !","Alert",JOptionPane.WARNING_MESSAGE);
									}
									
								}
								
							}
							
							else {
								JOptionPane.showMessageDialog(this,"Password must be same with the confirmation password","Alert",JOptionPane.WARNING_MESSAGE);
								pwConfirmPass.setText("");
							}
						}
						
						else {
							JOptionPane.showMessageDialog(this,"Confirmation Password cannot be empty!","Alert",JOptionPane.WARNING_MESSAGE);
		
						}
					}
					
					else {
						JOptionPane.showMessageDialog(this,"Password cannot be empty!","Alert",JOptionPane.WARNING_MESSAGE);
					}
				}
				
				else {
					JOptionPane.showMessageDialog(this,"Please input a valid email!","Alert",JOptionPane.WARNING_MESSAGE);
				}
			}
			
			else {
				JOptionPane.showMessageDialog(this,"Email cannot be empty!","Alert",JOptionPane.WARNING_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(this,"Username cannot be empty!","Alert",JOptionPane.WARNING_MESSAGE);
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

		if (e.getSource() == register) {
			register.setBackground(Color.decode("#75217C"));
			
		}
		
		else if (e.getSource() == hepakun) {
			hepakun.setBackground(Color.decode("#75217C"));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {

		if (e.getSource() == register) {
			register.setBackground(Color.decode("#efa1a2"));
		}
		
		else if (e.getSource() == hepakun) {
			hepakun.setBackground(Color.decode("#efa1a2"));
		}
		
	}

	
}
