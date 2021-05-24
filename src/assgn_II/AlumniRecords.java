package assgn_II;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
public class AlumniRecords {
	Connection db_conn;
	Statement db_statement;
	ResultSet db_response;
	
	private String db_url;
	private String db_username;
	private String db_password;
	
	protected static String alumni_name;
	protected static String alumni_address;
	protected static String alumni_designation;
	protected static String alumni_contact;
	protected static String alumni_email;
	protected static Integer alumni_year;
	AlumniRecords(){
		this.db_url = "jdbc:postgresql://localhost/giantspacemonster";
		this.db_username = "giantspacemonster";
		this.db_password = "Pm148e6#";
		try {
			this.db_conn = DriverManager.getConnection(this.db_url, this.db_username, this.db_password);
			this.db_statement = db_conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}
		catch (SQLException e) {
			Logger _logger = Logger.getLogger(DatabaseDescriptor.class.getName());
			_logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	public String insertRow(AlumniRecords db_serve) throws SQLException {
			try {
			db_serve.db_response = db_serve.db_statement.executeQuery("INSERT INTO alumni VALUES('"
					+getName()+"','"
					+getAddress()+"','"
					+getDesignation()+"','"
					+getContact()+"','"
					+getEmail()+"',"
					+getYear()+");");
			db_serve.db_conn.commit();
			}
			catch(SQLException e) {
				if(e.getSQLState() != "02000")
				{
					return (e.getMessage());
				}
			}
			return "TRANSACTION COMMITTED SUCESSFULLY";
	}
	
	public String deleteRow(AlumniRecords db_serve, String name) throws SQLException {
		try {
			db_serve.db_response = db_serve.db_statement.executeQuery("DELETE FROM alumni WHERE name='"+name+"';");
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			if(e.getSQLState() != "02000") {
				return e.getMessage();
			}
		}
		return "Record Sucessfully Deleted!";
	}
	public ResultSet searchRecord(AlumniRecords db_serve, String name) throws SQLException{
		//ResultSet res = db_serve.db_response;
		try {
			db_serve.db_response = db_serve.db_statement.executeQuery("SELECT * FROM alumni WHERE name LIKE '"+name+"%';");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return db_serve.db_response;
		
	}
	public String getName() {
		return alumni_name;
	}
	public static void setName(String name) {
		alumni_name = name;
	}
	
	public String getAddress() {
		return alumni_address;
	}
	public static void setAddress(String address) {
		alumni_address = address;
	}
	
	public String getDesignation() {
		return alumni_designation;
	}
	public static void setDesignation(String designation) {
		alumni_designation = designation;
	}
	
	public String getContact() {
		return alumni_contact;
	}
	public static void setContact(String contact) {
		alumni_contact = contact;
	}
	
	public String getEmail() {
		return alumni_email;
	}
	public static void setEmail(String email) {
		alumni_email = email;
	}
	
	public int getYear() {
		return alumni_year;
	}
	public static void setYear(int year) {
		alumni_year = year;
	}
}
