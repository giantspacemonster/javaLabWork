package assgn_I;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ListCellRenderer;

import java.util.Collections;
import java.util.Vector;

public class PersonsGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel buttonsPane;
	private JPanel resultsPane;
	
	private static PersonsGUI frame;
	
	private static JDialog add_record_dialog;
	private static JLabel person_name;
	private static JTextField person_name_input;
	private static JButton submit_person_name;
	
	private static JDialog search_record_dialog;
	private static JTextField search_term;
	private static JButton submit_search;
	
	private static JButton addRecords;
	private static JButton sortRecords;
	private static JButton searchRecords;
	private static JList<String> person_list;
	
	private static Vector<String> persons = new Vector<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new PersonsGUI();
					//add_record_dialog = new JDialog(frame,"Add New Record",false);
					
					
					frame.setVisible(true);
					addRecords.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							add_record_dialog.setVisible(true);
						}
						
					});
					submit_person_name.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							String person_name = person_name_input.getText();
							persons.add(person_name);
							System.out.println("persons Vector is : " + persons);
							person_list.updateUI();
						}
					});
					sortRecords.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							Collections.sort(persons);
							person_list.updateUI();
						}
						
					});
					
					searchRecords.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							search_record_dialog.setVisible(true);
						}
						
					});
					submit_search.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							String search_name = search_term.getText();
							if(persons.contains(search_name)) {
								showNotification("Search Successful!");
								int index = persons.indexOf(search_name);
								person_list.setCellRenderer(getHighlightRenderer(index));
							}
							else {
								showNotification("Search Failure!");
							}
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
	public PersonsGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		setSize(640,480);
		setMaximumSize(getSize());
		contentPane = new JPanel();
		buttonsPane = new JPanel();
		resultsPane = new JPanel();
		person_list = new JList<String>(persons);
		person_list.setCellRenderer(getRenderer());
		add_record_dialog = new JDialog(this,"Add New Record",false);
		add_record_dialog.setLayout(new FlowLayout());
		add_record_dialog.setSize(320,240);
		submit_person_name = new JButton("Submit");
		person_name = new JLabel("Name : ");
		person_name_input = new JTextField("Enter Name");
		add_record_dialog.add(person_name);
		add_record_dialog.add(person_name_input);
		add_record_dialog.add(submit_person_name);
		
		search_record_dialog = new JDialog(this,"Search By Name",false);
		search_record_dialog.setLayout(new FlowLayout());
		search_record_dialog.setSize(320,240);
		search_term = new JTextField("Search By Name");
		submit_search = new JButton("Search");
		search_record_dialog.add(search_term);
		search_record_dialog.add(submit_search);
		
		buttonsPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		buttonsPane.setLayout(new BorderLayout(10,10));
		
		addRecords = new JButton("Add New Record");
		buttonsPane.add(addRecords, BorderLayout.LINE_START);
		sortRecords = new JButton("Sort Records");
		buttonsPane.add(sortRecords, BorderLayout.CENTER);
		searchRecords = new JButton("Search Record");
		buttonsPane.add(searchRecords, BorderLayout.LINE_END);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		resultsPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		resultsPane.setLayout(new BorderLayout(10,10));
		resultsPane.setSize(640, 384);
		resultsPane.setPreferredSize(getSize());
		resultsPane.add(person_list);
		contentPane.add(buttonsPane, BorderLayout.NORTH);
		contentPane.add(resultsPane, BorderLayout.CENTER);
		setContentPane(contentPane);
	}

	private ListCellRenderer<? super String> getRenderer() {
		return new DefaultListCellRenderer() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,cellHasFocus);
                listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
                return listCellRendererComponent;
			}
		};
	}
	private static ListCellRenderer<? super String> getHighlightRenderer(int i){
		return new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,cellHasFocus);
				if(index == i) {
					listCellRendererComponent.setBackground(Color.CYAN);
				}
				return listCellRendererComponent;
			}
		};
	}
	
	private static void showNotification(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}
	
	

}
