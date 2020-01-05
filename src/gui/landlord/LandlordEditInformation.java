package gui.landlord;

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
import book_Hotel_Room.Hotel;
import member.LandlordOperation;
import member.TouristOperation;

import javax.swing.JComboBox;

public class LandlordEditInformation extends JPanel {

	JButton btnConfirm;
	int hotelId;
	int star = 0;
	String locality = "";
	int[] myHotelId = LandlordOperation.listMyHotelId(LandlordOperation.whoIsLoggedin());

	private JTextField textField_Q_price;

	/**
	 * Create the panel.
	 */
	public LandlordEditInformation() {
		setBackground(new Color(95, 158, 160));

		setSize(540, 720);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter hotel ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblNewLabel.setBounds(14, 13, 498, 73);
		add(lblNewLabel);

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

		JLabel lblQuad = new JLabel("Quad");
		lblQuad.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuad.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblQuad.setBounds(14, 443, 133, 73);
		add(lblQuad);

		JLabel lblDual = new JLabel("Dual");
		lblDual.setHorizontalAlignment(SwingConstants.CENTER);
		lblDual.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblDual.setBounds(14, 357, 133, 73);
		add(lblDual);

		btnConfirm = new JButton("CONFIRM");
		btnConfirm.setBackground(new Color(32, 178, 170));
		btnConfirm.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnConfirm.setBounds(147, 529, 218, 73);
		btnConfirm.setOpaque(true);
		add(btnConfirm);

		JComboBox<Integer> comboBox_ID = new JComboBox<Integer>();
		comboBox_ID.setBackground(new Color(240, 255, 240));
		comboBox_ID.setForeground(new Color(102, 205, 170));
		comboBox_ID.setFont(new Font("Agency FB", Font.PLAIN, 48));
		comboBox_ID.setBounds(14, 99, 498, 73);
		for (int i = 0; i < myHotelId.length; i++) {
			comboBox_ID.addItem(myHotelId[i]);
		}
		add(comboBox_ID);

		JComboBox<Integer> comboBox_Star = new JComboBox<Integer>();
		comboBox_Star.setBackground(new Color(240, 255, 240));
		comboBox_Star.setForeground(new Color(102, 205, 170));
		comboBox_Star.setFont(new Font("Agency FB", Font.PLAIN, 48));
		comboBox_Star.setBounds(147, 271, 365, 73);
		for (int i = 5; i > 0; i--) {
			comboBox_Star.addItem(i);
		}
		comboBox_Star.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					star = (Integer) e.getItem();
					System.out.println("Select " + star);
				}
			}
		});
		add(comboBox_Star);

		JComboBox<String> comboBox_Locality = new JComboBox<String>();
		comboBox_Locality.setBackground(new Color(240, 255, 240));
		comboBox_Locality.setForeground(new Color(102, 205, 170));
		comboBox_Locality.setFont(new Font("Agency FB", Font.PLAIN, 48));
		comboBox_Locality.setBounds(147, 358, 365, 73);
		comboBox_Locality.addItem("台北");
		comboBox_Locality.addItem("台中");
		comboBox_Locality.addItem("高雄");
		comboBox_Locality.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					locality = (String) e.getItem();
					System.out.println("Select " + locality);
				}
			}
		});
		add(comboBox_Locality);

		textField_Q_price = new JTextField();
		textField_Q_price.setBounds(147, 443, 365, 73);
		add(textField_Q_price);
		textField_Q_price.setColumns(10);

		if (myHotelId.length != 0) {
//			hotelId = myHotelId[0];
//			star = 
//			comboBox_Star.setSelectedItem(star);
//			locality =
//			comboBox_Locality.setSelectedItem(locality);
//			textField_Q_price.setText();
		}

		comboBox_ID.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					hotelId = (Integer) e.getItem();
					comboBox_Star.removeAllItems();
					for (int i = 0; i < 31; i++) {
						comboBox_Star.addItem(i);
					}
					star = Hotel.ALLHOTEL[hotelId].getRoomCombination()[0];
					comboBox_Star.setSelectedItem(star);
					comboBox_Locality.removeAllItems();
//					for (int i = 0; i < 31; i++) {
//						comboBox_Locality.addItem(i);
//					}
//					locality = comboBox_Locality.setSelectedItem(locality);
//
//					TextField_Q_price.setText(Integer.toString(Hotel.ALLHOTEL[hotelId].getRoomInfo()[2].getPrice()));
//					System.out.println("Select " + hotelId);
				}
			}
		});
	}

}
