package testing.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.UIManager;

import com.eltima.components.ui.DatePicker;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestSearchPanel extends JPanel {
	private String city = "SomeWhere";
	private int numOfPeople = 1;
	private int numOfNight = 1;
	private int numOfRooms = 1;
	private final DatePicker datepick;
	private JButton btnBackToMenu;
	private JButton btnBook;
	private JButton btnSearch;//***
	private JTextArea textArea;//
	public JButton getBtnBook() {
		return btnBook;
	}
	public JButton getBtnBackToMenu() {
		return btnBackToMenu;
	}
	public JButton getBtnSearch() {
		return btnSearch;
	}
	

	/**
	 * Create the panel.
	 */
	public TestSearchPanel() {
		setSize(1200, 900);
		setBackground(Color.GRAY);
		setLayout(null);

		btnBackToMenu = new JButton("Back To Menu");
		btnBackToMenu.setBounds(14, 13, 117, 29);
		add(btnBackToMenu);
		
		btnSearch = new JButton("SEARCH"); //***
		btnSearch.setBounds(853, 96, 117, 29);
		add(btnSearch);

		/*
		 * Choose which city to look for
		 */
		JLabel lblWhere = new JLabel("Where?");
		lblWhere.setBounds(74, 68, 61, 16);
		add(lblWhere);

		JComboBox<String> comboBox_City = new JComboBox<String>();
		comboBox_City.setBounds(74, 97, 140, 27);
		comboBox_City.addItem("SomeWhere");
		comboBox_City.addItem("Taipei");
		comboBox_City.addItem("Taichung");
		comboBox_City.addItem("Kaohsiung");
		comboBox_City.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					city = (String) e.getItem();
					System.out.println("Select " + city);
				}
			}

		});
		add(comboBox_City);

		/*
		 * Choose the number of people with scroll bar
		 */
		JLabel lblPeople = new JLabel("People");
		lblPeople.setBounds(253, 68, 61, 16);
		add(lblPeople);

		JComboBox<Integer> comboBox_People = new JComboBox<Integer>();
		comboBox_People.setBounds(253, 97, 117, 27);
		for (int i = 0; i < 1000; i++) {
			comboBox_People.addItem(i + 1);
		}
		add(comboBox_People);

		/*
		 * 
		 */
		JLabel lblRooms = new JLabel("Rooms");
		lblRooms.setBounds(414, 67, 57, 19);
		add(lblRooms);

		JComboBox<Integer> comboBox_Rooms = new JComboBox<Integer>();
		comboBox_Rooms.setBounds(414, 97, 99, 27);
		comboBox_Rooms.addItem(1);
		comboBox_Rooms.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					numOfRooms = (Integer) e.getItem();
					System.out.println("Select " + numOfRooms);
				}
			}
		});

		comboBox_People.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					numOfPeople = (Integer) e.getItem();
					comboBox_Rooms.removeAllItems();
					if (numOfPeople % 4 == 0) {
						numOfRooms = numOfPeople / 4;
						for (int i = numOfPeople / 4; i <= numOfPeople; i++) {
							comboBox_Rooms.addItem(i);
						}
					} else {
						numOfRooms = numOfPeople / 4 + 1;
						for (int i = numOfPeople / 4 + 1; i <= numOfPeople; i++) {
							comboBox_Rooms.addItem(i);
						}
					}

					System.out.println("Select " + numOfPeople);

				}
			}
		});
		add(comboBox_Rooms);

		/**
		 * Pick the date to check in
		 */
		JLabel lblCheckInDate = new JLabel("Check in date");
		lblCheckInDate.setBounds(557, 67, 99, 19);
		add(lblCheckInDate);

		datepick = getDatePicker();
		add(datepick);

		/*
		 * How many nights staying
		 */
		JLabel lblNewLabel = new JLabel("How many nights?");
		lblNewLabel.setBounds(696, 68, 117, 16);
		add(lblNewLabel);

		JComboBox<Integer> comboBox_Nights = new JComboBox<Integer>();
		comboBox_Nights.setBounds(696, 97, 111, 27);
		for (int i = 0; i < 100; i++) {
			comboBox_Nights.addItem(i + 1);
		}

		comboBox_Nights.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					numOfNight = (Integer) e.getItem();
					System.out.println("Select " + numOfNight);
				}
			}
		});
		add(comboBox_Nights);

		/*
		 * List all the available hotels that meets the requirement
		 */
		textArea = new JTextArea();
		textArea.setEditable(false);

		JScrollPane scroller = new JScrollPane(textArea);
		scroller.setBounds(74, 192, 736, 486);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scroller);

		/*
		 * Choose the hotel to book
		 */
		JComboBox<Integer> comboBox_ID = new JComboBox<Integer>();
		comboBox_ID.setBounds(853, 192, 117, 25);
		add(comboBox_ID);

		btnBook = new JButton("BOOK!");
		btnBook.setBounds(853, 245, 117, 27);
		add(btnBook);
	}

	public String getCity() {
		return city;
	}
	public int getNumOfPeople() {
		return numOfPeople;
	}
	public int getNumOfNight() {
		return numOfNight;
	}
	public int getNumOfRooms() {
		return numOfRooms;
	}
	public String getCheckInDate() {
		return datepick.getText();
	}
	public String getOrder() {
		return "Sorted by ID(large to small)";
	}
	public void setTextArea(String str) {
		textArea.setText(str);
	}
	
	private static DatePicker getDatePicker() {
		final DatePicker datepick;
		String DefaultFormat = "MM/dd/yyyy";
		long currentTime = System.currentTimeMillis();
		Date date = new Date(currentTime);
//		Calendar c = Calendar.getInstance();
//		c.setTime(date);
//		c.add(Calendar.DATE, 1);
//		date = c.getTime();
		Font font = new Font("Times New Roman", Font.BOLD, 14);
		Dimension dimension = new Dimension(177, 24);
		datepick = new DatePicker(date, DefaultFormat, font, dimension);
		datepick.setBounds(556, 97, 100, 30);
		datepick.setLocale(Locale.TAIWAN);
		return datepick;
	}
}