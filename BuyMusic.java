import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BuyMusic extends JFrame implements ActionListener, MouseListener{

	JDesktopPane desktopPane;
	JInternalFrame internalFrame;
	
	JTextField idTxt,namaTxt, genreTxt, priceTxt, artistTxt, releaseTxt,
				cartId, cartNama, cartGenre, cartPrice, cartArtist, cartRelease; 
	
	
	JPanel tablePanel,btnPanel,buyPanel;
	JTable tableMusic, tableCart;
	JScrollPane scrollPane, scrollPane1;
	DefaultTableModel dtm, dtm1;
	int totalPurchase=0;
	
	
	JButton addBtn, removeBtn, buyBtn;
	
	int selectedRow=-1;
	
	private Vector<Object> tableContent;
	
	private DatabaseConnection connection = new DatabaseConnection();
	private History his = new History();
	
	
	public void fetchTableData() {
		String query = "SELECT*FROM musics ORDER BY id ASC";
		connection.resultSet = connection.executeQuery(query);
		
		String header[] = {"ID","Name","Genre", "Price", "Artist Name","Release Date"};	
		dtm = new DefaultTableModel(header,0) {
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		try {
			while(connection.resultSet.next()) {
				tableContent = new Vector<Object>();
				for(int i = 1; i<connection.resultSetMetaData.getColumnCount()+1;i++) {
					tableContent.add(connection.resultSet.getObject(i)+"");
				}
				dtm.addRow(tableContent);
			}
			tableMusic.setModel(dtm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public BuyMusic() {
		//initPanel
		desktopPane = new JDesktopPane();
		internalFrame = new JInternalFrame();
		tablePanel = new JPanel ();
		btnPanel = new JPanel ();
		buyPanel = new JPanel();
		
		//northPanel
			//bikin table1
			tableMusic = new JTable(dtm);
			scrollPane = new JScrollPane(tableMusic);
			fetchTableData();
			tablePanel.add(scrollPane);
		
			//BikinTable2
			Object[] columns1 = {"ID","Name","Genre","Price","Artist Name","Release Date"};
			Object [][] cart = {		

			};
						
			dtm1 = new DefaultTableModel (cart, columns1) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			tableCart = new JTable(dtm1);
			scrollPane1 = new JScrollPane (tableCart);
			tablePanel.add(scrollPane1);
	
			
		//btnPanel
		addBtn = new JButton("Add To Cart");
		addBtn.setPreferredSize(new Dimension(450,30));
		removeBtn = new JButton("Remove From Cart");
		removeBtn.setPreferredSize(new Dimension(450,30));
		
		btnPanel.add(addBtn);
		btnPanel.add(removeBtn);
			
		//SouthPanel
		buyBtn = new JButton("Buy");
		buyBtn.setPreferredSize(new Dimension(400,30));
		buyPanel.add(buyBtn);
		
					
		//MainPanel
		internalFrame.add(tablePanel,BorderLayout.NORTH);
		internalFrame.add(btnPanel,BorderLayout.CENTER);
		internalFrame.add(buyPanel,BorderLayout.SOUTH);
		desktopPane.add(internalFrame);
		
		//AddListener
		tableMusic.addMouseListener(this);
		tableCart.addMouseListener(this);
		addBtn.addMouseListener(this);
		removeBtn.addMouseListener(this);
		buyBtn.addMouseListener(this);
		
		//InternalFrame
		internalFrame.setTitle("Buy Music");
		internalFrame.setVisible(true);
		internalFrame.setSize(1020,580);
		internalFrame.setMaximizable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setClosable(true);
		internalFrame.setResizable(false);
		
		//txt
		idTxt = new JTextField();
		namaTxt = new JTextField();
		genreTxt = new JTextField();
		priceTxt = new JTextField();
		artistTxt = new JTextField();
		releaseTxt = new JTextField();
		cartId= new JTextField();
		cartNama= new JTextField();
		cartGenre= new JTextField();
		cartPrice= new JTextField();
		cartArtist= new JTextField();
		cartRelease= new JTextField(); 
		
		
		//Jframe
		setTitle("Buy Music");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1050,680);
		setResizable(false);
		setLocationRelativeTo(null);
		add(desktopPane);
		

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		if (arg0.getSource() == tableMusic) {
			idTxt.setText(tableMusic.getValueAt(tableMusic.getSelectedRow(), 0).toString());
			namaTxt.setText(tableMusic.getValueAt(tableMusic.getSelectedRow(), 1).toString());
			genreTxt.setText(tableMusic.getValueAt(tableMusic.getSelectedRow(), 2).toString());
			priceTxt.setText(tableMusic.getValueAt(tableMusic.getSelectedRow(), 3).toString());
			artistTxt.setText(tableMusic.getValueAt(tableMusic.getSelectedRow(), 4).toString());
			releaseTxt.setText(tableMusic.getValueAt(tableMusic.getSelectedRow(), 5).toString());
			
		}
		
		if (arg0.getSource() == addBtn) {
			
			if(!(idTxt.getText().isEmpty() || namaTxt.getText().isEmpty() || genreTxt.getText().isEmpty() || 
				priceTxt.getText().isEmpty() || artistTxt.getText().isEmpty() || releaseTxt.getText().isEmpty())) 
				{	
					Object[] data = {idTxt.getText(), namaTxt.getText(), genreTxt.getText(),
							priceTxt.getText(),artistTxt.getText(),releaseTxt.getText()};
					
					dtm1.addRow(data);
					
					idTxt.setText("");
					namaTxt.setText("");
					genreTxt.setText("");
					priceTxt.setText("");
					artistTxt.setText("");
					releaseTxt.setText("");
				}else {
					JOptionPane.showMessageDialog(this, "Please Select Any Music From Music Table");
				}
			
		}
		
		if (arg0.getSource() == tableCart) {
			selectedRow = tableCart.getSelectedRow();
		}
		
		if (arg0.getSource() == removeBtn) {
			if (selectedRow != -1) {
				dtm1.removeRow(selectedRow);
				selectedRow = -1;
				
			}else {
				JOptionPane.showMessageDialog(this, "Please Select Any Music From Cart");
			}
		}
		
		if (arg0.getSource() == buyBtn) {
			if(tableCart.getRowCount()==0 ) {
				JOptionPane.showMessageDialog(this, "Please Select Any Music To Cart");
			}else {
				
				//masukan historyHeader
				for(int i = 0; i<dtm1.getRowCount();i++) {
					String sprice = (String) dtm1.getValueAt(i,3);
					Integer price = Integer.parseInt(sprice);
					totalPurchase = totalPurchase + price;
				}
				
				//Date
			    Date date = new Date();
			    SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
			    String datePurchase = formatter.format(date);
				
			    connection.prepinserthistoryheader(totalPurchase, datePurchase);
				totalPurchase=0;

			    //masukkan historyDetail
				int id = his.historyHeader.getRowCount()+1;
				
				for(int i = 0; i<dtm1.getRowCount();i++) {
					String name =  (String) tableCart.getValueAt(i, 1);
					String artistName =  (String) tableCart.getValueAt(i, 4);
					String sprice = (String) dtm1.getValueAt(i,3);
					Integer price = Integer.parseInt(sprice);
					connection.prepinserthistorydetail(id, name, artistName, price);
				}
			
				this.dispose();
				new History();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
