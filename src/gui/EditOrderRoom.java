package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import book_Hotel_Room.BookOperation;
import member.UserList;
import member.UserOperation;

import javax.swing.JComboBox;

public class EditOrderRoom extends JPanel {
	JButton btnConfirm;
	String bookId;
	int single = 0,dual = 0,quad = 0;
	
	/**
	 * Create the panel.
	 */
	public EditOrderRoom() {
		setBackground(new Color(95, 158, 160));
		
		setSize(540,720);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter book ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblNewLabel.setBounds(14, 13, 498, 73);
		add(lblNewLabel);

		JComboBox<String> comboBox_ID = new JComboBox<String>();
		comboBox_ID.setBackground(new Color(240, 255, 240));
		comboBox_ID.setForeground(new Color(102, 205, 170));
		comboBox_ID.setFont(new Font("Agency FB", Font.PLAIN, 48));
		comboBox_ID.setBounds(14, 99, 498, 73);
		ArrayList<String> idListUnsort = BookOperation.bookIdListOfUser(UserOperation.whoIsLoggedin());
		  ArrayList<String> idListsort = new ArrayList<String>();
		  int x = idListUnsort.size();
		  for (int i = 0; i < x; i++) {
		   String minId;
		   int min;
		   Iterator<String> iter = idListUnsort.iterator();
		   if (iter.hasNext()) {
		    minId = (String) iter.next();
		    min = new Integer(minId);
		    while (iter.hasNext()) {
		     String valId = (String) iter.next();
		     int val = new Integer(valId);
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
		btnConfirm.setBounds(147, 529, 218, 73);
		btnConfirm.setOpaque(true);
		add(btnConfirm);
		
		JComboBox<Integer> comboBox_Single = new JComboBox<Integer>();
		comboBox_Single.setBackground(new Color(240, 255, 240));
		comboBox_Single.setForeground(new Color(102, 205, 170));
		comboBox_Single.setFont(new Font("Agency FB", Font.PLAIN, 48));
		comboBox_Single.setBounds(147, 271, 365, 73);
		for (int i = 0; i < 100; i++) {
			comboBox_Single.addItem(i);
		}
		comboBox_Single.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					single = (Integer) e.getItem();
					System.out.println("Select " + single);
				}
			}
		});
		add(comboBox_Single);
		
		JLabel lblNewRoomNumber = new JLabel(" New Room Number");
		lblNewRoomNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewRoomNumber.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblNewRoomNumber.setBounds(14, 185, 498, 73);
		add(lblNewRoomNumber);
		
		JLabel lblSingle = new JLabel("Single");
		lblSingle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSingle.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblSingle.setBounds(14, 271, 133, 73);
		add(lblSingle);
		
		JComboBox<Integer> comboBox_Double = new JComboBox<Integer>();
		comboBox_Double.setBackground(new Color(240, 255, 240));
		comboBox_Double.setForeground(new Color(102, 205, 170));
		comboBox_Double.setFont(new Font("Agency FB", Font.PLAIN, 48));
		comboBox_Double.setBounds(147, 358, 365, 73);
		for (int i = 0; i < 100; i++) {
			comboBox_Double.addItem(i);
		}
		comboBox_Double.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					dual = (Integer) e.getItem();
					System.out.println("Select " + dual);
				}
			}
		});
		add(comboBox_Double);
		
		JLabel lblDual = new JLabel("Dual");
		lblDual.setHorizontalAlignment(SwingConstants.CENTER);
		lblDual.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblDual.setBounds(14, 357, 133, 73);
		add(lblDual);
		
		JComboBox<Integer> comboBox_Quad = new JComboBox<Integer>();
		comboBox_Quad.setBackground(new Color(240, 255, 240));
		comboBox_Quad.setForeground(new Color(102, 205, 170));
		comboBox_Quad.setFont(new Font("Agency FB", Font.PLAIN, 48));
		comboBox_Quad.setBounds(147, 443, 365, 73);
		for (int i = 0; i < 100; i++) {
			comboBox_Quad.addItem(i);
		}
		comboBox_Quad.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					quad = (Integer) e.getItem();
					System.out.println("Select " + quad);
				}
			}
		});
		add(comboBox_Quad);
		
		JLabel lblQuad = new JLabel("Quad");
		lblQuad.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuad.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblQuad.setBounds(14, 443, 133, 73);
		add(lblQuad);
	}

}
