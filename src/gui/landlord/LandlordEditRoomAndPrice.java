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

public class LandlordEditRoomAndPrice extends JPanel {
	JButton btnConfirm;
	int hotelId;
	int single = 0, dual = 0, quad = 0;
	int[] myHotelId = LandlordOperation.listMyHotelId(LandlordOperation.whoIsLoggedin());
	private JTextField textField_S_price;
	private JTextField textField_D_price;
	private JTextField textField_Q_price;

	/**
	 * Create the panel.
	 */
	public LandlordEditRoomAndPrice() {
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

		JComboBox<Integer> comboBox_Single = new JComboBox<Integer>();
		comboBox_Single.setBackground(new Color(240, 255, 240));
		comboBox_Single.setForeground(new Color(102, 205, 170));
		comboBox_Single.setFont(new Font("Agency FB", Font.PLAIN, 48));
		comboBox_Single.setBounds(147, 271, 180, 73);
		for (int i = 0; i < 31; i++) {
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

		JComboBox<Integer> comboBox_Double = new JComboBox<Integer>();
		comboBox_Double.setBackground(new Color(240, 255, 240));
		comboBox_Double.setForeground(new Color(102, 205, 170));
		comboBox_Double.setFont(new Font("Agency FB", Font.PLAIN, 48));
		comboBox_Double.setBounds(147, 358, 180, 73);
		for (int i = 0; i < 31; i++) {
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

		JComboBox<Integer> comboBox_Quad = new JComboBox<Integer>();
		comboBox_Quad.setBackground(new Color(240, 255, 240));
		comboBox_Quad.setForeground(new Color(102, 205, 170));
		comboBox_Quad.setFont(new Font("Agency FB", Font.PLAIN, 48));
		comboBox_Quad.setBounds(147, 443, 180, 73);
		for (int i = 0; i < 31; i++) {
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

		textField_S_price = new JTextField();
		textField_S_price.setBounds(147 + 185, 271, 180, 73);
		add(textField_S_price);
		textField_S_price.setColumns(10);

		textField_D_price = new JTextField();
		textField_D_price.setBounds(147 + 185, 358, 180, 73);
		add(textField_D_price);
		textField_D_price.setColumns(10);

		textField_Q_price = new JTextField();
		textField_Q_price.setBounds(147 + 185, 443, 180, 73);
		add(textField_Q_price);
		textField_Q_price.setColumns(10);

		if (myHotelId.length != 0) {
			hotelId = myHotelId[0];
			single = Hotel.ALLHOTEL[hotelId].getRoomCombination()[0];
			single = Hotel.ALLHOTEL[hotelId].getRoomCombination()[0];
			comboBox_Single.setSelectedItem(single);
			dual = Hotel.ALLHOTEL[hotelId].getRoomCombination()[1];
			comboBox_Double.setSelectedItem(dual);
			quad = Hotel.ALLHOTEL[hotelId].getRoomCombination()[2];
			comboBox_Quad.setSelectedItem(quad);
			textField_S_price.setText(Integer.toString(Hotel.ALLHOTEL[hotelId].getRoomInfo()[0].getPrice()));
			textField_D_price.setText(Integer.toString(Hotel.ALLHOTEL[hotelId].getRoomInfo()[1].getPrice()));
			textField_Q_price.setText(Integer.toString(Hotel.ALLHOTEL[hotelId].getRoomInfo()[2].getPrice()));
		}

		comboBox_ID.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					hotelId = (Integer) e.getItem();
					comboBox_Single.removeAllItems();
					for (int i = 0; i < 31; i++) {
						comboBox_Single.addItem(i);
					}
					single = Hotel.ALLHOTEL[hotelId].getRoomCombination()[0];
					comboBox_Single.setSelectedItem(single);
					comboBox_Double.removeAllItems();
					for (int i = 0; i < 31; i++) {
						comboBox_Double.addItem(i);
					}
					dual = Hotel.ALLHOTEL[hotelId].getRoomCombination()[1];
					comboBox_Double.setSelectedItem(dual);
					comboBox_Quad.removeAllItems();
					for (int i = 0; i < 31; i++) {
						comboBox_Quad.addItem(i);
					}
					quad = Hotel.ALLHOTEL[hotelId].getRoomCombination()[2];
					comboBox_Quad.setSelectedItem(quad);

					textField_S_price.setText(Integer.toString(Hotel.ALLHOTEL[hotelId].getRoomInfo()[0].getPrice()));
					textField_D_price.setText(Integer.toString(Hotel.ALLHOTEL[hotelId].getRoomInfo()[1].getPrice()));
					textField_Q_price.setText(Integer.toString(Hotel.ALLHOTEL[hotelId].getRoomInfo()[2].getPrice()));
					System.out.println("Select " + hotelId);
				}
			}
		});
	}

	public String[] getPrice() {
		String[] toReturn = { textField_S_price.getText(), textField_D_price.getText(), textField_Q_price.getText() };
		return toReturn;
	}

	public int[] getRoomCombination() {
		int[] toReturn = { single, dual, quad };
		return toReturn;
	}

}
