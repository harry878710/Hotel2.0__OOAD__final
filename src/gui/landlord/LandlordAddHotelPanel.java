package gui.landlord;

import javax.swing.JPanel;

import book_Hotel_Room.HotelOperation;
import gui.MainFrame;
import gui.PopFrame;
import member.LandlordOperation;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class LandlordAddHotelPanel extends JPanel {
	JButton btnBack;
	private JButton btnOk;
	private JTextField textField_S_price;
	private JTextField textField_D_price;
	private JTextField textField_Q_price;
	private JTextField textField_Hotel_ID;
	private JTextField textField_Address;
	private JComboBox<Integer> comboBox_Star;
	private JComboBox<String> comboBox_City;
	private JComboBox<Integer> comboBox_Single;
	private JComboBox<Integer> comboBox_Double;
	private JComboBox<Integer> comboBox_Quadro;

	/**
	 * Create the panel.
	 */
	public LandlordAddHotelPanel() {
		setBackground(new Color(95, 158, 160));
		setSize(1080, 720);
		setLayout(null);
		initializeLabels();
		initializeComboBoxes();
		initializeTextAreas();
		// Add options of hotel star
		for (int i = 5; i > 0; i--) {
			comboBox_Star.addItem(i);
		}
		// Add options of hotel locality
		comboBox_City.addItem("台北");
		comboBox_City.addItem("台中");
		comboBox_City.addItem("高雄");

		// Add options of single room number
		for (int i = 0; i < 31; i++) {
			comboBox_Single.addItem(i);
		}

		// Add options of double room number
		for (int i = 0; i < 31; i++) {
			comboBox_Double.addItem(i);
		}

		// Add options of quadro room number
		for (int i = 0; i < 31; i++) {
			comboBox_Quadro.addItem(i);
		}

		btnBack = new JButton("Back");
		btnBack.setOpaque(true);
		btnBack.setBackground(new Color(32, 178, 170));
		btnBack.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnBack.setBounds(25, 14, 150, 60);
		add(btnBack);

		btnOk = new JButton("OK");
		btnOk.setBounds(965, 499, 85, 23);
		add(btnOk);

		System.out.println(comboBox_Star.getSelectedIndex());
	}

	private void initializeLabels() {
		JLabel lblHotelId = new JLabel("Hotel ID will be");
		lblHotelId.setBounds(25, 98, 96, 60);
		add(lblHotelId);

		JLabel lblStar = new JLabel("Star");
		lblStar.setBounds(25, 180, 96, 60);
		add(lblStar);

		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(25, 304, 96, 60);
		add(lblCity);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(25, 424, 96, 60);
		add(lblAddress);

		JLabel lblSingle = new JLabel("Single");
		lblSingle.setBounds(417, 89, 150, 60);
		add(lblSingle);

		JLabel lblDouble = new JLabel("Double");
		lblDouble.setBounds(417, 196, 150, 60);
		add(lblDouble);

		JLabel lblQuadro = new JLabel("Quadro");
		lblQuadro.setBounds(417, 299, 150, 60);
		add(lblQuadro);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(816, 70, 47, 15);
		add(lblPrice);

	}

	private void initializeComboBoxes() {
		comboBox_Star = new JComboBox<Integer>();
		comboBox_Star.setBounds(131, 180, 150, 91);
		add(comboBox_Star);

		comboBox_City = new JComboBox<String>();
		comboBox_City.setBounds(131, 304, 150, 91);
		add(comboBox_City);

		comboBox_Single = new JComboBox<Integer>();
		comboBox_Single.setBounds(539, 74, 150, 91);
		add(comboBox_Single);

		comboBox_Double = new JComboBox<Integer>();
		comboBox_Double.setBounds(539, 198, 150, 91);
		add(comboBox_Double);

		comboBox_Quadro = new JComboBox<Integer>();
		comboBox_Quadro.setBounds(539, 318, 150, 91);
		add(comboBox_Quadro);
	}

	private void initializeTextAreas() {
		textField_S_price = new JTextField();
		textField_S_price.setText("0");
		textField_S_price.setBounds(796, 109, 96, 21);
		add(textField_S_price);
		textField_S_price.setColumns(10);

		textField_D_price = new JTextField();
		textField_D_price.setText("0");
		textField_D_price.setBounds(796, 233, 96, 21);
		add(textField_D_price);
		textField_D_price.setColumns(10);

		textField_Q_price = new JTextField();
		textField_Q_price.setText("0");
		textField_Q_price.setBounds(796, 353, 96, 21);
		add(textField_Q_price);
		textField_Q_price.setColumns(10);

		textField_Hotel_ID = new JTextField();
		textField_Hotel_ID.setText(Integer.toString(HotelOperation.nextHotelId()));
		textField_Hotel_ID.setEditable(false);
		textField_Hotel_ID.setBounds(131, 98, 150, 60);
		add(textField_Hotel_ID);
		textField_Hotel_ID.setColumns(10);

		textField_Address = new JTextField();
		textField_Address.setBounds(131, 444, 761, 78);
		add(textField_Address);
		textField_Address.setColumns(10);
	}

	public void activateLandlordAddHotelPanel(MainFrame mainframe) {
		mainframe.getContentPane().add(this, BorderLayout.CENTER);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.activateLandlordHotelsPanel();
				setVisible(false);
			}
		});
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] roomCombination = { (int) comboBox_Single.getSelectedItem(),
						(int) comboBox_Double.getSelectedItem(), (int) comboBox_Quadro.getSelectedItem() };
				String[] priceCombination = { textField_S_price.getText() , textField_D_price.getText() ,
						textField_Q_price.getText() };
				int op = HotelOperation.addHotelToDB((int) comboBox_Star.getSelectedItem(),
						(String) comboBox_City.getSelectedItem(), textField_Address.getText(), roomCombination,
						priceCombination, LandlordOperation.whoIsLoggedin());
				switch (op) {
				case 0:
					mainframe.activateLandlordHotelsPanel();
					setVisible(false);
					break;
				case 1:
					new PopFrame("error: Please fill all blanks");
					break;
				case 2:
					new PopFrame("error: Hotel with no room is illogical.");
					break;
				case 3:
					new PopFrame("error: Invalid room price.");
					break;
				case 4:
					new PopFrame("error: Cant charge for invisible room.");
					break;
				case 5:
					new PopFrame("error:Input price is not a number");
					break;

				}

			}
		});
	}
}