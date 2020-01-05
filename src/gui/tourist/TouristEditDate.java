package gui.tourist;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.eltima.components.ui.DatePicker;

import book_Hotel_Room.BookOperation;
import gui.PopFrame;

import member.TouristOperation;

import javax.swing.JComboBox;

public class TouristEditDate extends JPanel {
	JButton btnConfirm;
	String bookId;
	final DatePicker datepick;
	int night = 1;

	String getCheckInDate() {
		if (!checkInput(datepick.getText())) {
			new PopFrame("error: check in date should not be in the past.");
			return "error: check in date should not be in the past.";
		}
		return datepick.getText();

	}

	private static boolean checkInput(String checkIn) {
		// check check-in and check-out date are not null
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date checkInDate = sdf.parse(checkIn, new ParsePosition(0));
		// check check-in date is later than today
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date today = calendar.getTime();
		if (today.after(checkInDate)) {
			return false;
		}
		return true;
	}

	public String getCheckOutDate() {
		setSize(540, 720);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date checkOut = sdf.parse(getCheckInDate(), new ParsePosition(0));
		String checkOutDate = null;
		if (checkOut != null) {
			for (int i = night; i > 0; i--) {
				checkOut = nextDate(checkOut);
			}
			checkOutDate = sdf.format(checkOut);
		}
		return checkOutDate;
	}

	public int getNight() {
		return night;
	}

	/**
	 * Create the panel.
	 */
	public TouristEditDate() {
		setBackground(new Color(95, 158, 160));
		setSize(600, 900);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter book ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblNewLabel.setBounds(14, 33, 498, 73);
		add(lblNewLabel);

		JComboBox<String> comboBox_ID = new JComboBox<String>();
		comboBox_ID.setForeground(new Color(102, 205, 170));
		comboBox_ID.setBackground(new Color(240, 255, 240));
		comboBox_ID.setFont(new Font("Agency FB", Font.PLAIN, 48));
		comboBox_ID.setBounds(14, 119, 498, 73);
		ArrayList<String> idListUnsort = BookOperation.bookIdListOfUser(TouristOperation.whoIsLoggedin());
		ArrayList<String> idListsort = new ArrayList<String>();
		int x = idListUnsort.size();
		for (int i = 0; i < x; i++) {
			String minId;
			int min;
			Iterator<String> iter = idListUnsort.iterator();
			if (iter.hasNext()) {
				minId = (String) iter.next();
				min = Integer.parseInt(minId);
				while (iter.hasNext()) {
					String valId = (String) iter.next();
					int val = Integer.parseInt(valId);
					minId = (val < min) ? valId : minId;
					min = (val < min) ? val : min;
				}
			} else
				break;
			idListsort.add(minId);
			idListUnsort.remove(minId);
		}
		bookId = idListsort.get(0);
		for (int i = 0; i < idListsort.size(); i++) {
			comboBox_ID.addItem(idListsort.get(i));
		}
		add(comboBox_ID);

		btnConfirm = new JButton("Confirm");
		btnConfirm.setBackground(new Color(32, 178, 170));
		btnConfirm.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnConfirm.setBounds(173, 584, 200, 64);
		btnConfirm.setOpaque(true);
		add(btnConfirm);

		datepick = getDatePicker();
		add(datepick);

		JLabel lblNewCheckIn = new JLabel("New Check in Date");
		lblNewCheckIn.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblNewCheckIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewCheckIn.setBounds(14, 219, 498, 73);
		add(lblNewCheckIn);

		JComboBox<Integer> comboBox_Night = new JComboBox<Integer>();
		comboBox_Night.setForeground(new Color(102, 205, 170));
		comboBox_Night.setBackground(new Color(240, 255, 240));
		comboBox_Night.setFont(new Font("Agency FB", Font.PLAIN, 48));
		comboBox_Night.setBounds(14, 482, 498, 73);
		for (int i = 0; i < 100; i++) {
			comboBox_Night.addItem(i + 1);
		}
		night = BookOperation.getBook(bookId).getNights();
		comboBox_Night.setSelectedItem(night);
		comboBox_Night.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					night = (Integer) e.getItem();
					System.out.println("Select " + bookId);
				}
			}
		});
		add(comboBox_Night);

		JLabel lblNewNights = new JLabel("New Nights");
		lblNewNights.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblNewNights.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewNights.setBounds(14, 396, 498, 73);
		add(lblNewNights);

		comboBox_ID.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					bookId = (String) e.getItem();
					night = BookOperation.getBook(bookId).getNights();
					comboBox_Night.setSelectedItem(night);
					System.out.println("Select " + bookId);
				}
			}
		});
	}

	private static DatePicker getDatePicker() {
		final DatePicker datepick;
		String DefaultFormat = "MM/dd/yyyy";
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		Font font = new Font("Agent FB", Font.BOLD, 48);
		Dimension dimension = new Dimension(200, 50);
		datepick = new DatePicker(date, DefaultFormat, font, dimension);
		datepick.setBounds(14, 314, 498, 73);
		datepick.setLocale(Locale.TAIWAN);
		return datepick;
	}

	private static Date nextDate(Date thisDate) {
		// https://stackoverflow.com/questions/1005523/how-to-add-one-day-to-a-date
		Calendar c = Calendar.getInstance();
		c.setTime(thisDate);
		c.add(Calendar.DATE, 1);
		Date nextDate = c.getTime();
		return nextDate;
	}

}
