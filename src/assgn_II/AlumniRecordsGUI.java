package assgn_II;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.TextField;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.CardLayout;

public class AlumniRecordsGUI extends JFrame {

	private static JPanel contentPane;
	
	private static JPanel buttonsPane;
	private static JButton add_record;
	private static JButton delete_record;
	private static JButton update_record;
	private static JButton search_record;
	
	private static JPanel formsPane;
	private static JLabel alumni_name;
	private static TextField name;
	private static JLabel alumni_address;
	private static TextField address;
	private static JLabel alumni_designation;
	private static TextField designation;
	private static JLabel alumni_contact;
	private static TextField contact;
	private static JLabel alumni_email;
	private static TextField email;
	private static JLabel alumni_year;
	private static TextField year;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlumniRecordsGUI frame = new AlumniRecordsGUI();
					frame.setVisible(true);
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
		contentPane = new JPanel();
		buttonsPane = new JPanel();
		formsPane = new JPanel();
		initComponent();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(formsPane, BorderLayout.NORTH);
		contentPane.add(buttonsPane, BorderLayout.SOUTH);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		setContentPane(contentPane);
	}
	
	public static void initComponent() {
		add_record = new JButton("Add");
		add_record.setHorizontalAlignment(SwingConstants.TRAILING);
		delete_record = new JButton("Delete");
		delete_record.setHorizontalAlignment(SwingConstants.TRAILING);
		update_record = new JButton("Update");
		update_record.setHorizontalAlignment(SwingConstants.TRAILING);
		search_record = new JButton("Search");
		search_record.setHorizontalAlignment(SwingConstants.TRAILING);
		buttonsPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonsPane.add(add_record);
		buttonsPane.add(delete_record);
		buttonsPane.add(update_record);
		buttonsPane.add(search_record);
		
		alumni_name = new JLabel("Name");
		name = new TextField("Enter Name");
		name.setColumns(45);
		
		
		alumni_address = new JLabel("Address");
		address = new TextField("Enter Address");
		address.setColumns(45);
		
		alumni_designation = new JLabel("Designation");
		designation = new TextField("Enter Designation");
		designation.setColumns(45);
		
		alumni_contact = new JLabel("Contact");
		contact = new TextField("Enter Contact Number");
		contact.setColumns(45);
		
		alumni_email = new JLabel("email");
		email = new TextField("Enter email");
		email.setColumns(45);
		
		alumni_year = new JLabel("Year");
		year = new TextField("Enter Year");
		year.setColumns(45);
		
		formsPane.setLayout(new BoxLayout(formsPane, BoxLayout.Y_AXIS) );
		formsPane.add(alumni_name);
		formsPane.add(name);
		formsPane.add(alumni_address);
		formsPane.add(address);
		formsPane.add(alumni_designation);
		formsPane.add(designation);
		formsPane.add(alumni_contact);
		formsPane.add(contact);
		formsPane.add(alumni_email);
		formsPane.add(email);
		formsPane.add(alumni_year);
		formsPane.add(year);
	}

}
