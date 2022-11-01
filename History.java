import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class History extends JFrame implements MouseListener {
	
	JPanel mainPanel;
	JInternalFrame historyFrame;
	JDesktopPane desktopPane;
	
	JTable historyHeader, historyDetail;
	JScrollPane scrollHeader, scrollDetail;
	DefaultTableModel dtmHeader, dtmDetail;
	
	int selectedRow=-1;
	
	private Vector<Object> tableContentHeader;
	private Vector<Object> tableContentDetail;
	
	
	
	private DatabaseConnection connection = new DatabaseConnection();
	
	public void fetchTableDataHeader() {
		String query = "SELECT*FROM history_header ORDER BY id ASC";
		connection.resultSet = connection.executeQuery(query);
		
		
		
		String header[] = {"ID","Total Purchase (IDR)","Date Purchase"};	
		dtmHeader = new DefaultTableModel(header,0) {
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		try {
			while(connection.resultSet.next()) {
				tableContentHeader = new Vector<Object>();
				for(int i = 1; i<connection.resultSetMetaData.getColumnCount()+1;i++) {
					tableContentHeader.add(connection.resultSet.getObject(i)+"");
				}
				dtmHeader.addRow(tableContentHeader);
			}
			historyHeader.setModel(dtmHeader);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void fetchTableDataDetail (int id) {
		String query = "SELECT*FROM history_detail where historyid ="+id;
		connection.resultSet = connection.executeQuery(query);
		
		String header[] = {"History ID","Music Name","Music Artist","Music Price (IDR)"};	
		dtmDetail = new DefaultTableModel(header,0) {
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		try {
			while(connection.resultSet.next()) {
				tableContentDetail = new Vector<Object>();
				for(int i = 1; i<connection.resultSetMetaData.getColumnCount()+1;i++) {
					tableContentDetail.add(connection.resultSet.getObject(i)+"");
				}
				dtmDetail.addRow(tableContentDetail);
			}
			historyDetail.setModel(dtmDetail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public History() {
		//init
		mainPanel = new JPanel();
		historyFrame = new JInternalFrame();
		desktopPane = new JDesktopPane();
		
		//Bikin table1
		historyHeader = new JTable(dtmHeader);
		scrollHeader =  new JScrollPane(historyHeader);
		fetchTableDataHeader();
		mainPanel.add(scrollHeader);

		
		//Bikin table2
		Object[] columnsDetail = {"History ID","Music Name","Music Artist","Music Price (IDR)"};
		Object [][] dataDetail = {
		};
		
		dtmDetail = new DefaultTableModel(dataDetail,columnsDetail) {
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		historyDetail = new JTable(dtmDetail);
		scrollDetail = new JScrollPane(historyDetail);
		mainPanel.add(scrollDetail);
		
		
		//mainFrame
		historyFrame.add(mainPanel,BorderLayout.CENTER);
		desktopPane.add(historyFrame);
		
		
		//ActionListener
		historyHeader.addMouseListener(this);
		
		//InternalFrame
		historyFrame.setTitle("History");
		historyFrame.setVisible(true);
		historyFrame.setSize(1020,580);
		historyFrame.setMaximizable(true);
		historyFrame.setIconifiable(true);
		historyFrame.setClosable(true);
		historyFrame.setResizable(false);
		
		//Jframe
		setTitle("History");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1050,680);
		setResizable(false);
		setLocationRelativeTo(null);
		add(desktopPane);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==historyHeader) {
			String sid = historyHeader.getValueAt(historyHeader.getSelectedRow(),0).toString();
			Integer id = Integer.parseInt(sid);
			fetchTableDataDetail(id);		
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
