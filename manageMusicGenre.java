
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class manageMusicGenre implements MouseListener, ActionListener {

	JFrame frame = new JFrame();
	JLabel label = new JLabel();
	JInternalFrame internal = new JInternalFrame();
	JDesktopPane desktop = new JDesktopPane();
	
	
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scroll;
	JLabel genreLbl;
	JTextField genreTxt, idTxt;
	JButton add, update, delete;
	int index = 8;
	
	DatabaseConnection connection = new DatabaseConnection();
	Vector<Object> tableContent;
	
	String idName,genreName,genre;
	
	
	public void dataTable() {
		
		String query = "SELECT * FROM music_genres";
		connection.resultSet = connection.executeQuery(query);;
		
		Object [] columns = {"ID","Genre"};		
		dtm  = new DefaultTableModel(columns, 0);
		
		try {
			while(connection.resultSet.next()) {
				
				tableContent  = new Vector<Object>();
				
				for (int i = 1; i < connection.resultSetMetaData.getColumnCount() + 1; i++) {
					tableContent.add((connection.resultSet.getObject(i) + ""));
				}
				dtm.addRow(tableContent);
			}
			table.setModel(dtm);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public manageMusicGenre() {
		//Internal Frame
		internal.setTitle("Manage Music Genre");
		internal.setSize(1020,680);
		internal.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
				
		//JFrame
		frame.setTitle("Manage Music Genre");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1020,680);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	
		//Genre
		//GenreLabel
		genreLbl = new JLabel("Genre Name");
		genreLbl.setBounds(700,50,80,25);
		internal.add(genreLbl);
		
		//GenreTextField
		genreTxt = new JTextField();
		genreTxt.setBounds(700,70,240,25);
		internal.add(genreTxt);
					
		idTxt = new JTextField();
		idTxt.setBounds(700,70,240,25);
			
		//Button
		//Button 1 - Add
		add = new JButton("Add");
		add.setBounds(700,100,70,25);
		internal.add(add);
		
		//Button 2 - Update
		update = new JButton("Update");
		update.setBounds(780,100,75,25);
		internal.add(update);
		update.addActionListener(this);
		
		//Button 3 - Delete
		delete = new JButton("Delete");
		delete.setBounds(865,100,75,25);
		internal.add(delete);

			
		table = new JTable(dtm);
		
		scroll = new JScrollPane(table);
		scroll.setBounds(80, 20, 600, 600);
		dataTable();
		internal.add(scroll);
		
		//Listener
		add.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		table.addMouseListener(this);
				
		frame.add(label);
		internal.add(label);
		desktop.add(internal);
		internal.setVisible(true);
		frame.add(desktop);
		frame.setVisible(true);	
	}

	

	public static void main(String[] args) {
		new manageMusicGenre();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource() == table) {
			int select = table.getSelectedRow();
			idTxt.setText(dtm.getValueAt(select, 0).toString());
			genreTxt.setText(dtm.getValueAt(select, 1).toString());}
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			
		//Event Add
		if (e.getSource() == add) {
			if (!(genreTxt.getText().isEmpty())) {

				String genre = genreTxt.getText();
				connection.insertGenre(genre);
					
				dataTable();
					
				} else {
					JOptionPane.showMessageDialog(frame, "Please Input Genre Name","Alert",0);
				}	
			}
			
		//Event Delete
			if (e.getSource() == delete ) {
				
				String id = idTxt.getText();				
				dtm = (DefaultTableModel) table.getModel();
				
				if (table.getSelectedRowCount() == 1) {
					dtm.removeRow(table.getSelectedRow());
					
					String query = "DELETE FROM music_genres WHERE id ='"+id+"'";
					
					connection.execUpdate(query);
					dataTable();
				}else if (table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(frame, "Table is Empty");
				}else {
						JOptionPane.showMessageDialog(frame, "Please Select Single Row to Delete");
				}
				
			}
			
			//Event Update
			if (e.getSource() == update) {
				
				if(idTxt.getText().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Please select single row for update");
				}	
				
				else if(table.getSelectedRow()==1) {
					String id = idTxt.getText();
					String genre = genreTxt.getText();
					
					String query = "UPDATE music_genres SET genre_name = '"+genre+"' WHERE id =  " + id; 
					connection.execUpdate(query);
					
					dataTable();
					
					JOptionPane.showMessageDialog(frame, "Update Successful");
					
				} else if (table.getRowCount()== 0) {
					JOptionPane.showMessageDialog(frame, "Table is Empty");
				}
				
	
			}

	}
}
