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
import java.sql.SQLException;
import java.text.NumberFormat;

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
					add_record.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
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
							//input_string.setVisible(true);
							
							multi_purpose_button.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
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
								
							});
							message.setText("Add Records Mode:\nEnter the details in the form above and press submit when ready.\n");
						}
						
					});
					
					
					
					
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
		contact = new JFormattedTextField(numericFormat);
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
}