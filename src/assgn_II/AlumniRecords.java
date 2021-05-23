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
	
	protected String alumni_name;
	protected String alumni_address;
	protected String alumni_designation;
	protected String alumni_contact;
	protected String alumni_email;
	protected String alumni_year;
	AlumniRecords(){
		this.db_url = null;
		this.db_username = null;
		this.db_password = null;
	}
	AlumniRecords(
			String url,
			String username,
			String password
			){
		this.db_url = url;
		this.db_username = username;
		this.db_password = password;
		try {
			this.db_conn = DriverManager.getConnection(this.db_url, this.db_username, this.db_password);
			this.db_statement = db_conn.createStatement();
		}
		catch (SQLException e) {
			Logger _logger = Logger.getLogger(DatabaseDescriptor.class.getName());
			_logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	public static void main(String[] args) throws SQLException{
		AlumniRecords db_serve = new AlumniRecords("jdbc:postgresql://localhost/giantspacemonster","giantspacemonster","Pm148e6#");
	}
	public String getName() {
		return this.alumni_name;
	}
	public void setName(String name) {
		this.alumni_name = name;
	}
	
	public String getAddress() {
		return this.alumni_address;
	}
	public void setAddress(String address) {
		this.alumni_address = address;
	}
	
	public String getDesignation() {
		return this.alumni_designation;
	}
	public void setDesignation(String designation) {
		this.alumni_designation = designation;
	}
	
	public String getContact() {
		return this.alumni_contact;
	}
	public void setContact(String contact) {
		this.alumni_contact = contact;
	}
	
	public String getEmail() {
		return this.alumni_email;
	}
	public void setEmail(String email) {
		this.alumni_email = email;
	}
	
	public String getYEar() {
		return this.alumni_year;
	}
	public void setYear(String year) {
		this.alumni_year = year;
	}
}
