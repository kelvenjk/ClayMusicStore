import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DatabaseConnection {

	public Connection connection;
	public Statement statement;
	public ResultSet resultSet;
	public ResultSetMetaData resultSetMetaData;
	public PreparedStatement preparedStatement;
	
	
	public DatabaseConnection(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clay's music store","root","");
			
			statement = connection.createStatement();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	

	public ResultSet executeQuery(String query) {
		try {
			resultSet = statement.executeQuery(query);
			resultSetMetaData = resultSet.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	
	
	// get ACCOUNT
	
	public ResultSet getusersData() {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
			
			resultSet = preparedStatement.executeQuery();
			resultSetMetaData = resultSet.getMetaData();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	// GET SEMUA
	
	
//	public ResultSet getgenresData() {
//		try {
//			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM music_genres");
//			
//			resultSet = preparedStatement.executeQuery();
//			resultSetMetaData = resultSet.getMetaData();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return resultSet;
//	}

	

	
	 public void insert(String name, String genre_name, int price, String artistname, String release_date) {
		  
		 
		 
		  String query = "INSERT INTO musics (music_name, music_genre, music_price, artist_name, release_date) VALUES ('"+name+"','"+genre_name+"', '"+price+"', '"+artistname+"', '"+release_date+"')";
		  
		  try {
			  statement.executeUpdate(query);
		  } catch (SQLException e) {
		   // TODO Auto-generated catch block
//		   System.out.println("Error to input");
		   e.printStackTrace();
		  }
		  
		 }
		 
	 
	 
	 
	
		 public void insertGenre(String genre) {
				String query = "INSERT INTO music_genres(genre_name) VALUES ('"+genre+"')";
				
				try {
					statement.execute(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//System.out.println("insert failed");
					e.printStackTrace();
				}
				
				
			}
			
		public void execUpdate(String query) {
				
				try {
					statement.execute(query);
			
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("kabur");
				}
				
			}
			
		
		public void execUpdate1(String query) {
			
			try {
				statement.executeUpdate(query);
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		public void prepinserthistoryheader(int totalPurchase, String datePurchase) {
			String query = "INSERT INTO history_header (totalPurchase, datePurchase) VALUES (?,?)";
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, totalPurchase);
				preparedStatement.setString(2, datePurchase);
				preparedStatement.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void prepinserthistorydetail(int id, String name, String artistName, int price ) {
			String query = "INSERT INTO history_detail(historyid,musicname, musicartist, musicprice) VALUES (?,?,?,?)";
					
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, name);
				preparedStatement.setString(3, artistName);
				preparedStatement.setInt(4, price);
				preparedStatement.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		 
}
