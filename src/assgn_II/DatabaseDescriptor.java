package assgn_II;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DatabaseMetaData;
public class DatabaseDescriptor {
	Connection db_conn;
	Statement st;
	ResultSet res;
	DatabaseMetaData db_metadata;
	private String url;
	private String user;
	private String password;
	
	DatabaseDescriptor(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
		try {
			this.db_conn = DriverManager.getConnection(this.url, this.user, this.password);
			this.st = db_conn.createStatement();
			this.db_metadata = db_conn.getMetaData();
		}
		catch (SQLException e) {
			Logger _logger = Logger.getLogger(DatabaseDescriptor.class.getName());
			_logger.log(Level.SEVERE, e.getMessage(), e);
		}
		
	}
	public static void main(String[] args) throws IOException, SQLException {
		DatabaseDescriptor db_serve = new DatabaseDescriptor("jdbc:postgresql://localhost/farmhousefreshdb","giantspacemonster","Pm148e6#");
		BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
		int choice = -1;
		while(true) {
			System.out.print("\nDatabase Descriptor :\n"
					+ "\t1. Display Informations about the Database\n"
					+ "\t2. List all tables in the Database\n"
					+ "\t3. Display Columns of a Table\n"
					+ "\t4. Exit.\n"
					+ "Enter Menu Option : ");
			choice = Integer.parseInt(inputStream.readLine());
			switch(choice) {
			case 1:
				db_serve.res = db_serve.st.executeQuery("SELECT VERSION()");
				if(db_serve.res.next()) {
					System.out.println("postgreSQL Version : " + db_serve.res.getString(1));
					System.out.println("Connection String : " + db_serve.db_metadata.getURL());
					System.out.println("Database Major Version: " + db_serve.db_metadata.getDatabaseMajorVersion());
					System.out.println("Database Minor Version: " + db_serve.db_metadata.getDatabaseMinorVersion());
					System.out.println("Database Product Name:" + db_serve.db_metadata.getDatabaseProductName());
					System.out.println("Database Product Version:" + db_serve.db_metadata.getDatabaseProductVersion());
					System.out.println("Database User:" + db_serve.db_metadata.getUserName());
					System.out.println("Database Catalog Term:" + db_serve.db_metadata.getCatalogTerm());
				}
				db_serve.res = db_serve.db_metadata.getCatalogs();
				System.out.println("Databases: " +(db_serve.res.next() ? db_serve.res.getString(1) : "AnError Occured"));
				break;
			case 2:
				db_serve.res = db_serve.db_metadata.getTableTypes();
				String[] tableTypes = db_serve.res.next() ? db_serve.res.getString(1).split(" ") : null;
				System.out.println("Table Types : " + tableTypes[0] + "\t" + tableTypes[1]);
				db_serve.res = db_serve.db_metadata.getTables(null,null,null,tableTypes);
				while(db_serve.res.next()) {
					System.out.println("Table Names : "+db_serve.res.getString(3));
				}
				break;
			case 3:
				System.out.println("\n Enter Table Name : ");
				String table_name = inputStream.readLine();
				db_serve.res = db_serve.db_metadata.getColumns(null, null, table_name, null);
				while(db_serve.res.next()) {
					System.out.println("--------------------------------");
					System.out.println("Column Name : "+db_serve.res.getString("COLUMN_NAME"));
					System.out.println("Data Type: "+db_serve.res.getString("DATA_TYPE"));
					System.out.println("Column Size : "+db_serve.res.getString("COLUMN_SIZE"));
					System.out.println("Decimal Digits : "+db_serve.res.getString("DECIMAL_DIGITS"));
					System.out.println("Is Nullable : "+db_serve.res.getString("IS_NULLABLE"));
					System.out.println("--------------------------------");
				}
				break;
			case 4:
				System.exit(0);
				break;
				default:
					System.out.println("Please Enter A Valid Choice!!!");
			}
			
		}
	}

}
