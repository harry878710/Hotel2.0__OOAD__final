package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.eltima.components.ui.DatePicker;

import bookAndUser.UserOperation;
import gui.BookDeposit;
import gui.PopFrame;
import operation.InputException;

import operation.SearchAndBook;

public class SearchPanel extends JPanel {

	boolean hasSearch = false;
	int numOfPeople = 1;
	int numOfNight = 1;
	int numOfRoom = 1;
	int hotelId = -1;
	int[] roomCombination = { 1, 0, 0 };
	String city = "SomeWhere";
	String sort = "Sorted by Hotel ID(small to large)";
	JButton btnBackToMenu;
	JButton btnBook;
	JButton btnSearch;
	JTextPane textHotelDetail;
	final DatePicker datepick;

	public String getDate() throws InputException {
		new SearchAndBook().validCheckInDate(stringToDate(datepick.getText()));
		return datepick.getText();
	}

	/**
	 * Create the panel.
	 */
	public SearchPanel() {

		setSize(1080, 720);
		setBackground(new Color(135, 206, 250));
		setLayout(null);

		btnBackToMenu = new JButton("BACK");
		btnBackToMenu.setBackground(new Color(153, 204, 255));
		btnBackToMenu.setOpaque(true);
		btnBackToMenu.setFont(new Font("Agency FB", Font.PLAIN, 24));
		btnBackToMenu.setBounds(803, 13, 220, 29);
		add(btnBackToMenu);

		btnSearch = new JButton("SEARCH");
		btnSearch.setBackground(new Color(153, 204, 255));
		btnSearch.setFont(new Font("Agency FB", Font.PLAIN, 24));
		btnSearch.setBounds(803, 53, 220, 29);
		add(btnSearch);

		/*
		 * Choose which city to look for
		 */
		JLabel lblWhere = new JLabel("Where");
		lblWhere.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblWhere.setBounds(24, 14, 140, 27);
		add(lblWhere);

		JComboBox<String> comboBox_City = new JComboBox<String>();
		comboBox_City.setFont(new Font("Agency FB", Font.PLAIN, 18));
		comboBox_City.setBackground(SystemColor.inactiveCaptionBorder);
		comboBox_City.setBounds(24, 54, 140, 27);
		comboBox_City.addItem("SomeWhere");
		comboBox_City.addItem("Taipei");
		comboBox_City.addItem("Taichung");
		comboBox_City.addItem("Kaohsiung");
		comboBox_City.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					city = (String) e.getItem();
					System.out.println("Select " + city);
					hasSearch = false;
				}
			}

		});
		add(comboBox_City);

		/*
		 * Choose the number of people with scroll bar
		 */
		JLabel lblPeople = new JLabel("People");
		lblPeople.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblPeople.setBounds(203, 14, 117, 27);
		add(lblPeople);

		JComboBox<Integer> comboBox_People = new JComboBox<Integer>();
		comboBox_People.setFont(new Font("Agency FB", Font.PLAIN, 18));
		comboBox_People.setBackground(SystemColor.inactiveCaptionBorder);
		comboBox_People.setBounds(203, 54, 117, 27);
		for (int i = 0; i < 210; i++) {
			comboBox_People.addItem(i + 1);
		}
		add(comboBox_People);

		/*
		 * 
		 */
		JLabel lblRooms = new JLabel("Rooms");
		lblRooms.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblRooms.setBounds(364, 13, 99, 28);
		add(lblRooms);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(364, 102, 99, 29);
		comboBox.addItem("1,0,0");
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					StringTokenizer st = new StringTokenizer((String) e.getItem(), ",");
					int i_str = 0;
					while (st.hasMoreElements()) {
						String str;
						str = st.nextToken(",");
						roomCombination[i_str] = Integer.parseInt(str);
						System.out.println(roomCombination[i_str]);
						i_str++;
					}
					hasSearch = false;
				}
			}
		});

		add(comboBox);

		JComboBox<Integer> comboBox_Rooms = new JComboBox<Integer>();
		comboBox_Rooms.setFont(new Font("Agency FB", Font.PLAIN, 18));
		comboBox_Rooms.setBackground(SystemColor.inactiveCaptionBorder);
		comboBox_Rooms.setBounds(364, 54, 99, 27);
		comboBox_Rooms.addItem(1);
		comboBox_Rooms.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					numOfRoom = (Integer) e.getItem();
					comboBox.removeAllItems();
					ArrayList<Integer[]> tmp = (new SearchAndBook()).possibleRoomCombination(numOfPeople, numOfRoom);
					for (int i = 0; i < 3; i++) {
						roomCombination[i] = tmp.get(0)[i];
					}
					for (int i = 0; i < tmp.size(); i++) {
						comboBox.addItem(tmp.get(i)[0] + "," + tmp.get(i)[1] + "," + tmp.get(i)[2]);
					}
					System.out.println("Select " + numOfRoom);
					hasSearch = false;
				}
			}
		});

		comboBox_People.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					numOfPeople = (Integer) e.getItem();
					comboBox_Rooms.removeAllItems();
					int[] x = (new SearchAndBook()).possibleRoomNumber(numOfPeople);
					numOfRoom = x[0];
					for (int i = 0; i < x.length; i++) {
						comboBox_Rooms.addItem(x[i]);
					}
					comboBox.removeAllItems();
					ArrayList<Integer[]> tmp = (new SearchAndBook()).possibleRoomCombination(numOfPeople, numOfRoom);
					for (int i = 0; i < 3; i++) {
						roomCombination[i] = tmp.get(0)[i];
					}
					for (int i = 0; i < tmp.size(); i++) {
						comboBox.addItem(tmp.get(i)[0] + "," + tmp.get(i)[1] + "," + tmp.get(i)[2]);
					}
					System.out.println("Select " + numOfPeople);
					hasSearch = false;
				}
			}
		});
		add(comboBox_Rooms);

		/**
		 * Pick the date to check in
		 */
		JLabel lblCheckInDate = new JLabel("Check in date");
		lblCheckInDate.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblCheckInDate.setBounds(507, 13, 157, 28);
		add(lblCheckInDate);

		datepick = getDatePicker();
		add(datepick);

		/*
		 * How many nights staying
		 */
		JLabel lblNewLabel = new JLabel("Nights");
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblNewLabel.setBounds(678, 14, 111, 27);
		add(lblNewLabel);

		JComboBox<Integer> comboBox_Nights = new JComboBox<Integer>();
		comboBox_Nights.setFont(new Font("Agency FB", Font.PLAIN, 18));
		comboBox_Nights.setBackground(SystemColor.inactiveCaptionBorder);
		comboBox_Nights.setBounds(678, 54, 85, 27);
		for (int i = 0; i < 100; i++) {
			comboBox_Nights.addItem(i + 1);
		}

		comboBox_Nights.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					numOfNight = (Integer) e.getItem();
					System.out.println("Select " + numOfNight);
					hasSearch = false;
				}
			}
		});
		add(comboBox_Nights);

		/*
		 * Choose the sorting type of the listed hotel
		 */
		JLabel lblOrderingBy = new JLabel("Ordering by");
		lblOrderingBy.setFont(new Font("Agency FB", Font.PLAIN, 28));
		lblOrderingBy.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderingBy.setBounds(783, 95, 240, 32);
		add(lblOrderingBy);

		JComboBox<String> comboBox_Sorting = new JComboBox<String>();
		comboBox_Sorting.setFont(new Font("Agency FB", Font.PLAIN, 18));
		comboBox_Sorting.setBackground(SystemColor.inactiveCaptionBorder);
		comboBox_Sorting.setBounds(783, 140, 240, 43);
		comboBox_Sorting.addItem("Sorted by Hotel ID(small to large)");
		comboBox_Sorting.addItem("Sorted by Hotel ID(large to small)");
		comboBox_Sorting.addItem("Sorted by Price(small to large)");
		comboBox_Sorting.addItem("Sorted by Price(large to small)");
		add(comboBox_Sorting);

		/*
		 * List all the available hotels that meets the requirement
		 */
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(240, 255, 255));
		textArea.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		textArea.setEditable(false);
		JScrollPane scroller = new JScrollPane(textArea);
		scroller.setBounds(24, 139, 741, 510);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scroller);

		/*
		 * Choose the hotel to book
		 */
		JLabel labelHotelId = new JLabel("Hotel ID");
		labelHotelId.setFont(new Font("Agency FB", Font.PLAIN, 28));
		labelHotelId.setHorizontalAlignment(SwingConstants.CENTER);
		labelHotelId.setBounds(783, 196, 240, 32);
		add(labelHotelId);

		JComboBox<Integer> comboBox_ID = new JComboBox<Integer>();
		comboBox_ID.setFont(new Font("Agency FB", Font.PLAIN, 18));
		comboBox_ID.setBackground(SystemColor.inactiveCaptionBorder);
		comboBox_ID.setBounds(783, 241, 240, 39);
		add(comboBox_ID);

		btnBook = new JButton("BOOK !");
		btnBook.setBackground(new Color(153, 204, 255));
		btnBook.setOpaque(true);
		btnBook.setFont(new Font("Agency FB", Font.PLAIN, 36));
		btnBook.setBounds(783, 596, 240, 53);
		add(btnBook);

		textHotelDetail = new JTextPane();
		textHotelDetail.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		textHotelDetail.setBackground(new Color(240, 255, 255));
		textHotelDetail.setEditable(false);
		textHotelDetail.setBounds(783, 335, 240, 248);
		textHotelDetail.setText("");
		add(textHotelDetail);

		JLabel lblQuickView = new JLabel("Quick View");
		lblQuickView.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuickView.setFont(new Font("Agency FB", Font.PLAIN, 28));
		lblQuickView.setBounds(783, 293, 240, 32);
		add(lblQuickView);

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<Integer> valid = new SearchAndBook().vacancyHotels(datepick.getText(), numOfNight,
							roomCombination, city);
					if (valid.size() > 0) {
						textArea.setText(new SearchAndBook().checkVacancy(datepick.getText(), numOfNight,
								roomCombination, city, sort));
						textArea.setSelectionStart(0);
						textArea.setSelectionEnd(0);
						comboBox_ID.removeAllItems();
						if (valid.size() > 0) {
							hotelId = valid.get(1);
							textHotelDetail.setText(
									new BookDeposit(city, roomCombination, getDate(), numOfNight, hotelId).toString());
						}
						for (int i = 0; i < valid.size(); i++) {
							comboBox_ID.addItem(valid.get(i));
						}
						hasSearch = true;
					} else {
						textArea.setText("Oops! No Room!");
					}
				} catch (InputException e1) {
					new PopFrame(e1.getMessage());
				}
			}
		});
		comboBox_ID.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					try {
						getDate();
						hotelId = (Integer) e.getItem();
						textHotelDetail.setText(
								new BookDeposit(city, roomCombination, getDate(), numOfNight, hotelId).toString());
						System.out.println("Select " + hotelId);
					} catch (InputException e1) {
						new PopFrame(e1.getMessage());
					}
				}
			}
		});
		comboBox_Sorting.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					sort = (String) e.getItem();
					System.out.println("Select " + sort);
					if (hasSearch) {
						try {
							ArrayList<Integer> valid = new SearchAndBook().vacancyHotels(datepick.getText(), numOfNight,
									roomCombination, city);
							if (valid.size() > 0) {
								textArea.setText(new BookDeposit(city, roomCombination, getDate(), numOfNight, hotelId)
										.toString());
								textArea.setSelectionStart(0);
								textArea.setSelectionEnd(0);
								comboBox_ID.removeAllItems();
								if (valid.size() > 0) {
									hotelId = valid.get(1);
									textHotelDetail.setText(
											new BookDeposit(city, roomCombination, getDate(), numOfNight, hotelId)
													.toString());
								}
								for (int i = 0; i < valid.size(); i++) {
									comboBox_ID.addItem(valid.get(i));
								}
								hasSearch = true;
							} else {
								textArea.setText("Oops! No Room!");
							}
						} catch (InputException e1) {
							new PopFrame(e1.getMessage());
						}
					}
				}
			}
		});
	}

	private static DatePicker getDatePicker() {
		final DatePicker datepick;
		String DefaultFormat = "yyyy/MM/dd";
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		Font font = new Font("Times New Roman", Font.BOLD, 14);
		Dimension dimension = new Dimension(177, 24);
		datepick = new DatePicker(date, DefaultFormat, font, dimension);
		datepick.setBounds(507, 54, 140, 30);
		datepick.setBackground(new Color(0, 191, 255));
		;
		datepick.setLocale(Locale.TAIWAN);
		return datepick;
	}

	public void activateSearchPanel(MainFrame mainframe) {
		mainframe.getContentPane().add(this, BorderLayout.CENTER);
		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (UserOperation.anyoneLoggedin()) {
					mainframe.activateUserMenuPanel();
				} else {
					mainframe.initialize();
				}
				setVisible(false);
			}
		});
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hotelId != -1) {
					try {
						getDate();
						ArrayList<Integer> valid = new SearchAndBook().vacancyHotels(getDate(), numOfNight,
								roomCombination, city);
						boolean hasRoom = false;
						for (int i = 0; i < valid.size(); i++) {
							if (valid.get(i) == hotelId) {
								hasRoom = true;
							}
						}
						if (hasRoom) {

							mainframe.activateToBookPanel(
									new BookDeposit(city, roomCombination, getDate(), numOfNight, hotelId));
							setVisible(false);
						} else {
							new PopFrame("There's NO room");
						}
					} catch (InputException e1) {
						new PopFrame(e1.getMessage());
					}
				} else {
					new PopFrame("Make your choice = = ");
				}
			}
		});
		setVisible(true);
	}
	
	private Date stringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.parse(str, new ParsePosition(0));
	}

}
