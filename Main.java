import java.sql.SQLException;
import java.util.Vector;

public class Main {
	
	public Vector<Account> accountVector = new Vector <>();

	DatabaseConnection db;
	
	public Main() {
		
		new LoginForm(accountVector);
		getUsersData();
	}

	public void getUsersData() {
		
		db = new DatabaseConnection();
		
		try {
			db.resultSet = db.getusersData();
			
			
			while (db.resultSet.next()) {
				int userId = Integer.valueOf(String.valueOf(db.resultSet.getObject(1)));
				String userName = (String.valueOf(db.resultSet.getObject(2)));
				String email = (String.valueOf(db.resultSet.getObject(3)));
				String password = (String.valueOf(db.resultSet.getObject(4)));
				String gender = (String.valueOf(db.resultSet.getObject(5)));
				String roles = (String.valueOf(db.resultSet.getObject(6)));
				accountVector.add(new Account(userId, userName, email, password, gender, roles));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
		
	}
	
	
}
