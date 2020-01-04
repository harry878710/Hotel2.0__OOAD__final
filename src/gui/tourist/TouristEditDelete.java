package gui.tourist;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.SwingConstants;

import book_Hotel_Room.BookOperation;
import member.TouristList;
import member.TouristOperation;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;

public class TouristEditDelete extends JPanel {
	JButton btnConfirm;
	String bookId;

	/**
	 * Create the panel.
	 */
	public TouristEditDelete() {
		setBackground(new Color(95, 158, 160));
		setSize(540, 720);
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
		comboBox_ID.setBounds(14, 159, 498, 73);
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
		comboBox_ID.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					bookId = (String) e.getItem();
					System.out.println("Select " + bookId);
				}
			}
		});
		add(comboBox_ID);

		btnConfirm = new JButton("CONFIRM");
		btnConfirm.setBackground(new Color(32, 178, 170));
		btnConfirm.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnConfirm.setBounds(168, 306, 193, 53);
		btnConfirm.setOpaque(true);
		add(btnConfirm);

	}

}
