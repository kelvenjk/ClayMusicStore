
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;
import java.util.jar.JarInputStream;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class Manages implements ActionListener, MouseListener {
	
	JFrame frame = new JFrame();
	JLabel label = new JLabel();
	JTable table = new JTable();
	JDesktopPane desktop = new JDesktopPane();
	JScrollPane pane;
	JInternalFrame internal = new JInternalFrame();
	DefaultTableModel model;
	JLabel name, genre, artistname, price;
	JTextField fieldid, fieldname, fieldgenre, fieldartist, fieldprice,fieldDate;;
	JComboBox box;
	JSpinner spinner;
	JButton delete, add, update;
	
	
	Vector<Object> contentTable;
	
	DatabaseConnection connection = new DatabaseConnection();
	
	public void dataTable() {
		
		String query = "SELECT * FROM musics";
		connection.resultSet = connection.executeQuery(query);
		
		Object[] column = {"ID", "Name", "Genre", "Price", "Artist Name", "Realese Date"};		
		model = new DefaultTableModel(column, 0);
		
		try {
			while(connection.resultSet.next()) {
				
				contentTable = new Vector<Object>();
				
				for (int i = 1; i < connection.resultSetMetaData.getColumnCount() + 1; i++) {
					contentTable.add(connection.resultSet.getObject(i) + "");
				}
				
				model.addRow(contentTable);
				
			}
			table.setModel(model);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add() {
		
		String music_name = fieldname.getText();
		String genre_name =  box.getSelectedItem().toString();
		String artist_name = fieldartist.getText();
		String strprice = spinner.getValue().toString();
		String release_date = "17-02-2002";
		int music_price = Integer.parseInt(strprice);
		
		if(music_name.trim().isEmpty()) {
			fieldname.getText();
			JOptionPane.showMessageDialog(frame, "Please Input Namer", "Manages", 0);								
							
		}
		else if(genre_name.trim().isEmpty()) {
			box.getSelectedItem().toString();
			JOptionPane.showMessageDialog(frame, "Please Select the Genre", "Manages", 0);
		}
		
		else if(artist_name.trim().isEmpty()) {
			fieldartist.getText();
			JOptionPane.showMessageDialog(frame, "Please Input the Artist Name", "Manages", 0);
		}
		
		else if(music_price <= 0) {
			spinner.getValue().toString();
        	JOptionPane.showMessageDialog(frame, "Please Input The Price", "Manages", 0);
		}
		else {
			//model.addRow(newRow);
			
		}
		
		fieldDate.setText(release_date);
		
//		int music_genre_id =0 ;
//		
//		if (box.getSelectedItem().equals("Jazz")) {
//			music_genre_id = 1;
//			
//		}
		
		//System.out.println(box.getSelectedItem());
		
		connection.insert(music_name, genre_name, music_price, artist_name, release_date);
		dataTable();
		
	}
	
	public void update() {
		

		String id = fieldid.getText();
		String name = fieldname.getText();
		String genre = box.getSelectedItem().toString();
		String artistname = fieldartist.getText();
		String strprice = spinner.getValue().toString();
		int price = Integer.parseInt(strprice);
		
		if(name.trim().isEmpty()) {
			fieldname.getText();
			JOptionPane.showMessageDialog(frame, "Please Input Namer", "Manages", 0);								
							
		}
		else if(genre.trim().isEmpty()) {
			box.getSelectedItem().toString();
			JOptionPane.showMessageDialog(frame, "Please Select the Genre", "Manages", 0);
		}
		
		else if(artistname.trim().isEmpty()) {
			fieldartist.getText();
			JOptionPane.showMessageDialog(frame, "Please Input the Artist Name", "Manages", 0);
		}
		
		else if(price <= 0) {
			spinner.getValue().toString();
        	JOptionPane.showMessageDialog(frame, "Please Input The Price", "Manages", 0);
		}
		
			String query = "UPDATE musics SET music_name = '"+name+"', music_genre = '"+genre+"', music_price = '"+price+"', artist_name = '"+artistname+"' WHERE id =" +id;
			
																							
			//connection.execUpdate(query);
			//connection.execUpdate(query);
			
			connection.execUpdate1(query);
			
			System.out.println(query);
			dataTable();
		
	}
	
	public void delete() {
		
		String id = fieldid.getText();
		String name = fieldname.getText();		
		String strprice = spinner.getValue().toString();
		int price = Integer.parseInt(strprice);
		


		String query = "DELETE FROM musics WHERE id =  " + id;
		
		connection.execUpdate(query);
		System.out.println(query);
		dataTable();
		
		}
	
	Manages(){
		
		//INTERNAL
		internal.setTitle("Manages Music Form");
		internal.setSize(1020, 680);
		internal.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		
		//FRAME
		frame.setTitle("Manages Music Form");
		frame.setSize(1030, 690);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		//LABELNAME
		name = new JLabel("Music name:");
		name.setBounds(750, 50, 80 , 25);
		internal.add(name);
		
		genre = new JLabel("Genre:");
		genre.setBounds(750, 100, 80 , 25);	
		internal.add(genre);
		
		artistname = new JLabel("Artist Name:");
		artistname.setBounds(750, 150, 80, 25);
		internal.add(artistname);
		
		price = new JLabel("Price:");
		price.setBounds(750, 200, 80, 25);
		internal.add(price);
		
		//TEXTFIELD
		fieldid = new JTextField();
		fieldid.setBounds(750, 30, 165, 25);

		
		fieldDate = new JTextField();
		fieldDate.setBounds(750, 30, 165, 25);
		
		
		fieldname = new JTextField();
		fieldname.setBounds(750, 70, 165, 25);
		internal.add(fieldname);
		
		fieldartist = new JTextField();
		fieldartist.setBounds(750, 170, 165, 25);
		internal.add(fieldartist);
		
		//TABLE
		pane = new JScrollPane(table);
		pane.setBounds(100, 20, 600, 600);
		
		dataTable();
		
		internal.add(pane);
		
		//COMBOBOX
		String[] inside = {"", "Rock", "Pop", "Funk","Jazz"}; 
		box = new JComboBox(inside);
		box.setBounds(750, 120, 165, 25);
		internal.add(box);
		
		//SPINNER
		//SpinnerModel value = new SpinnerNumberModel(0, 0, 100, 1);
		spinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		spinner.setBounds(750, 220, 165, 25);
		internal.add(spinner);
		
		//BUTTON
		delete = new JButton("Delete");
		delete.setBounds(715, 270, 70, 25);
		internal.add(delete);
		
		add = new JButton("Add");
		add.setBounds(815, 270, 70, 25);
		internal.add(add);
		
		update = new JButton("Update");
		update.setBounds(915, 270, 75, 25);
		internal.add(update);
		
		frame.add(label);
		internal.add(label);
		desktop.add(internal);
		internal.setVisible(true);
		frame.add(desktop);
		frame.setVisible(true);
				
		table.addMouseListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		add.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == add) {
			
			add();						
		}
		  		
		else if(e.getSource() == delete) {			
			delete();
			} 
		
		else if(e.getSource() == update) {
			
			update();
			
		} else {	   
        	JOptionPane.showMessageDialog(frame, "Please Select Row", "Manages", 0);
        	}
		

	}		

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == table) {

			int select = table.getSelectedRow();
			
			fieldid.setText(model.getValueAt(select, 0).toString());
			fieldname.setText(model.getValueAt(select, 1).toString());
			box.setSelectedItem(model.getValueAt(select, 2).toString());
			fieldartist.setText(model.getValueAt(select,4 ).toString());
			spinner.setValue(model.getValueAt(select, 3));

		} 
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Manages();
	}
}
