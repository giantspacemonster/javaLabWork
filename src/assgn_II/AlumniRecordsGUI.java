package assgn_II;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import javax.swing.SwingConstants;

//import assgn_II.AlumniRecords.*;

public class AlumniRecordsGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static JPanel contentPane;
	
	private static JPanel buttonsPane;
	private static JButton add_record;
	private static JButton delete_record;
	private static JButton update_record;
	private static JButton search_record;
	
	private static JPanel formsPane;
	private static JLabel alumni_name;
	private static JTextField name;
	private static JLabel alumni_address;
	private static JTextField address;
	private static JLabel alumni_designation;
	private static JTextField designation;
	private static JLabel alumni_contact;
	private static JFormattedTextField contact;
	private static JLabel alumni_email;
	private static JTextField email;
	private static JLabel alumni_year;
	private static JComboBox<Integer> year;
	
	private static JTextArea alumni_title;
	
	private static JPanel messagePane;
	private static JTextArea message;
	private static JTextField input_string;
	private static JButton multi_purpose_button;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlumniRecords db_serve = new AlumniRecords();
					AlumniRecordsGUI frame = new AlumniRecordsGUI();
					frame.setVisible(true);
					setActionsSpecifier(db_serve);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AlumniRecordsGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		setResizable(false);
		contentPane = new JPanel();
		buttonsPane = new JPanel();
		messagePane = new JPanel();
		formsPane = new JPanel();
		initComponents();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(formsPane, BorderLayout.NORTH);
		contentPane.add(buttonsPane, BorderLayout.SOUTH);
		contentPane.add(messagePane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		setContentPane(contentPane);
	}
	
	protected static MaskFormatter createFormatter(String s) {
		//used to format phone numbers
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch(java.text.ParseException exc) {
			System.err.println(exc.getMessage());
		}
		return formatter;
	}
	
	public static void initComponents() {
		
		add_record = new JButton("Add");
		add_record.setHorizontalAlignment(SwingConstants.TRAILING);
		delete_record = new JButton("Delete");
		delete_record.setHorizontalAlignment(SwingConstants.TRAILING);
		update_record = new JButton("Update");
		update_record.setHorizontalAlignment(SwingConstants.TRAILING);
		update_record.setEnabled(false);
		search_record = new JButton("Search");
		search_record.setHorizontalAlignment(SwingConstants.TRAILING);
		buttonsPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonsPane.add(add_record);
		buttonsPane.add(delete_record);
		buttonsPane.add(update_record);
		buttonsPane.add(search_record);
		
		JPanel namePane =new JPanel();
		namePane.setLayout(new FlowLayout(FlowLayout.LEFT));
		alumni_name = new JLabel("Name" + String.format("%12s", " "));
		name = new JTextField("Enter Name");
		name.setEnabled(false);
		name.setEditable(false);
		name.setColumns(45);
		namePane.add(alumni_name);
		namePane.add(name);
		
		JPanel addressPane =new JPanel();
		addressPane.setLayout(new FlowLayout(FlowLayout.LEADING));
		alumni_address = new JLabel("Address" + String.format("%8s", " "));
		address = new JTextField("Enter Address");
		address.setColumns(45);
		address.setEnabled(false);
		address.setEditable(false);
		addressPane.add(alumni_address);
		addressPane.add(address);
		
		JPanel designationPane =new JPanel();
		designationPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		alumni_designation = new JLabel("Designation" + String.format("%s"," "));
		designation = new JTextField("Enter Designation");
		designation.setColumns(45);
		designation.setEnabled(false);
		designation.setEditable(false);
		designationPane.add(alumni_designation);
		designationPane.add(designation);
		
		//Numeric Formatter for contact and year input
		NumberFormat numericFilter = NumberFormat.getInstance();
		NumberFormatter numericFormat = new NumberFormatter(numericFilter);
		numericFormat.setValueClass(Integer.class);
		numericFormat.setMinimum(0);
		numericFormat.setMaximum(1000000000);
		//numericFormat.setAllowsInvalid(false);
		numericFormat.setCommitsOnValidEdit(true);
		
		
		JPanel contactPane =new JPanel();
		contactPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		alumni_contact = new JLabel("Contact" + String.format("%8s"," "));
		contact = new JFormattedTextField(createFormatter("##########"));
		contact.setColumns(10);
		contact.setEnabled(false);
		contact.setEditable(false);
		contactPane.add(alumni_contact);
		contactPane.add(contact);
		
		JPanel emailPane =new JPanel();
		emailPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		alumni_email = new JLabel("email" + String.format("%12s"," "));
		email = new JTextField("Enter email");
		email.setColumns(45);
		email.setEnabled(false);
		email.setEditable(false);
		emailPane.add(alumni_email);
		emailPane.add(email);
		
		JPanel yearPane =new JPanel();
		yearPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		alumni_year = new JLabel("Year" + String.format("%13s"," "));
		Integer[] year_list = new Integer[50];
		
		for(int i = 1972, j= 0; i <= 2021; i++, j++) {
			year_list[j] = i;  
		}
		year = new JComboBox<Integer>(year_list);
		year.setEnabled(false);
		year.setEditable(false);
		yearPane.add(alumni_year);
		yearPane.add(year);
		
		alumni_title = new JTextArea("ALUMNI RECORDS");
		Font font = new Font("Times New Roman", Font.BOLD, 20);
		alumni_title.setBackground(Color.BLACK);
		alumni_title.setForeground(Color.WHITE);
		alumni_title.setFont(font);
		formsPane.add(alumni_title);
		formsPane.setLayout(new BoxLayout(formsPane, BoxLayout.Y_AXIS) );
		formsPane.add(namePane);
		formsPane.add(addressPane);
		formsPane.add(designationPane);
		formsPane.add(contactPane);
		formsPane.add(emailPane);
		formsPane.add(yearPane);
		
		message = new JTextArea("Welcome to Alumni Module.\nSelect form the buttons below.\n "
				+ "Add Record enables the above form, on which you can insert a record into the database.\n"
				+ "Delete Record by name deletes the record from the database.\n"
				+ "Use search button to search a record by name, and then you may update the record as per your requirement.");
		message.setSize(640, 382);
		message.setMargin(new Insets(10,10,10,10));
		message.setBackground(Color.BLACK);
		message.setForeground(Color.WHITE);
		message.setEnabled(false);
		message.setEditable(false);
		message.setLineWrap(true);
		message.setWrapStyleWord(true);
		messagePane.add(message);
		
		input_string = new JTextField();
		input_string.setColumns(45);
		input_string.setVisible(false);
		multi_purpose_button = new JButton("Submit");
		multi_purpose_button.setVisible(false);
		messagePane.add(input_string);
		messagePane.add(multi_purpose_button);


	}

	public static void setActionsSpecifier(AlumniRecords db_serve) {
		AbstractAction add_record_button = new AbstractAction("Add Record") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent event) {
						String name_of_alumni = name.getText();
						String address_of_alumni = address.getText();
						String contact_of_alumni = contact.getText();
						String designation_of_alumni = designation.getText();
						String email_of_alumni = email.getText();
						int year_of_alumni = (Integer)year.getSelectedItem();
						AlumniRecords.setName(name_of_alumni);
						AlumniRecords.setAddress(address_of_alumni);
						AlumniRecords.setDesignation(designation_of_alumni);
						AlumniRecords.setContact(contact_of_alumni);
						AlumniRecords.setEmail(email_of_alumni);
						AlumniRecords.setYear(year_of_alumni);
						try {
							message.setText(db_serve.insertRow(db_serve));
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
		};
		AbstractAction delete_record_button = new AbstractAction("Delete Record") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String rec_delete = input_string.getText();
				try {
					message.setText(db_serve.deleteRow(db_serve, rec_delete));
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		};
		AbstractAction search_record_button = new AbstractAction("Search Record") {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String rec_search = input_string.getText();
				try {
					ResultSet res = db_serve.searchRecord(db_serve, rec_search);
					//ResultSet rec_count = db_serve.db_statement.executeQuery("SELECT COUNT(*) FROM alumni WHERE name LIKE '"+rec_search+"%';");
					//if((db_serve.db_statement.executeQuery("SELECT COUNT(*) FROM alumni WHERE name='"+rec_search+"';").equals("0"))) {
						//message.setText("Search Unsuccessful!");
					//}
					//rec_count.next();
					int rowCount =-1;
					if(res.last()) {
						rowCount = 	res.getRow();
						res.beforeFirst();
					}
					if(rowCount == -1) {
						message.setText("Search Unsuccessful!");
					}
					else {
						message.setText("Search Successful!");
					}
					//if(res.getRow() > 0) {
						//message.setText("Search Successful!");
					//}
					//else {
						//message.setText("Search Unsuccessful!");
					//}
					while(res != null && res.next()) {
						name.setText(res.getString(1));
						address.setText(res.getString(2));
						designation.setText(res.getString(3));
						contact.setText(res.getString(4));
						email.setText(res.getString(5));
						year.setSelectedItem(Integer.parseInt(res.getString(6)));
					}
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		};
		add_record.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				multi_purpose_button.setAction(add_record_button);
				
				update_record.setEnabled(false);
				
				name.setEnabled(true);
				name.setEditable(true);
				name.addMouseListener(new MouseAdapter() {
					  @Override
					  public void mouseClicked(MouseEvent e) {
					    name.setText("");
					  }
					});
				
				address.setEnabled(true);
				address.setEditable(true);
				address.addMouseListener(new MouseAdapter() {
					  @Override
					  public void mouseClicked(MouseEvent e) {
					    address.setText("");
					  }
					});
				
				designation.setEnabled(true);
				designation.setEditable(true);
				designation.addMouseListener(new MouseAdapter() {
					  @Override
					  public void mouseClicked(MouseEvent e) {
						  designation.setText("");
					  }
					});
				
				contact.setEnabled(true);
				contact.setEditable(true);
				contact.addMouseListener(new MouseAdapter() {
					  @Override
					  public void mouseClicked(MouseEvent e) {
						  contact.setText("");
					  }
					});
				
				email.setEnabled(true);
				email.setEditable(true);
				email.addMouseListener(new MouseAdapter() {
					  @Override
					  public void mouseClicked(MouseEvent e) {
						  email.setText("");
					  }
					});
				
				year.setEnabled(true);
				year.setEditable(true);
				multi_purpose_button.setVisible(true);
				input_string.setVisible(false);
				message.setText("Add Records Mode:\nEnter the details in the form above and press submit when ready.\n");
			}
			
		});
		
		delete_record.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				multi_purpose_button.setAction(delete_record_button);
				multi_purpose_button.setVisible(false);
				input_string.setVisible(false);
				input_string.setVisible(true);
				multi_purpose_button.setVisible(true);
				
				update_record.setEnabled(false);
				
				input_string.setColumns(45);
				message.setText("Delete Records Mode:\nEnter the Name of the person whose record you want deleted and click on submit.");
				
				//name.setEnabled(false);
				name.setEditable(false);
				//address.setEnabled(false);
				address.setEditable(false);
				//designation.setEnabled(false);
				designation.setEditable(false);
				//contact.setEnabled(false);
				contact.setEditable(false);
				//email.setEnabled(false);
				email.setEditable(false);
				//year.setEnabled(false);
				year.setEditable(false);
				
							}
			
		});
		
		search_record.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				multi_purpose_button.setAction(search_record_button);
				multi_purpose_button.setVisible(false);
				input_string.setVisible(false);
				input_string.setVisible(true);
				multi_purpose_button.setVisible(true);
				
				update_record.setEnabled(true);
				input_string.setColumns(45);
				message.setText("Search Records Mode:\n"
						+ "Enter the Name of the person whose record you want to search and click on submit."
						+ "A successful Search also enable user to Update the record. Change the text field and click Update"
						+ "to update record in the database.");
				name.setEnabled(true);
				name.setEditable(true);
				address.setEnabled(true);
				address.setEditable(true);
				designation.setEnabled(true);
				designation.setEditable(true);
				contact.setEnabled(true);
				contact.setEditable(true);
				email.setEnabled(true);
				email.setEditable(true);
				year.setEnabled(true);
				year.setEditable(true);
			}
			
		});
	}
}
