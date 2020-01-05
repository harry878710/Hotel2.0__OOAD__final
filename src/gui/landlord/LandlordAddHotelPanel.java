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
import javax.swing.SwingConstants;

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
		btnBack.setBounds(25, 14, 130, 78);
		add(btnBack);

		btnOk = new JButton("OK");
		btnOk.setFont(new Font("Agency FB", Font.PLAIN, 48));
		btnOk.setBounds(896, 444, 130, 75);
		add(btnOk);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumber.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblNumber.setBounds(543, 18, 150, 75);
		add(lblNumber);

	}

	private void initializeLabels() {
		JLabel lblHotelId = new JLabel("Hotel ID will be");
		lblHotelId.setFont(new Font("Agency FB", Font.PLAIN, 32));
		lblHotelId.setBounds(25, 103, 150, 75);
		add(lblHotelId);

		JLabel lblStar = new JLabel("Star");
		lblStar.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblStar.setBounds(25, 204, 150, 75);
		add(lblStar);

		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblCity.setBounds(25, 306, 150, 75);
		add(lblCity);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblAddress.setBounds(25, 444, 150, 75);
		add(lblAddress);

		JLabel lblSingle = new JLabel("Single");
		lblSingle.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblSingle.setBounds(383, 103, 150, 75);
		add(lblSingle);

		JLabel lblDouble = new JLabel("Double");
		lblDouble.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblDouble.setBounds(383, 204, 150, 75);
		add(lblDouble);

		JLabel lblQuadro = new JLabel("Quadro");
		lblQuadro.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblQuadro.setBounds(383, 306, 150, 75);
		add(lblQuadro);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Agency FB", Font.PLAIN, 48));
		lblPrice.setBounds(719, 18, 150, 75);
		add(lblPrice);

	}

	private void initializeComboBoxes() {
		comboBox_Star = new JComboBox<Integer>();
		comboBox_Star.setFont(new Font("Agency FB", Font.PLAIN, 48));
		comboBox_Star.setBounds(185, 204, 150, 75);
		add(comboBox_Star);

		comboBox_City = new JComboBox<String>();
		comboBox_City.setFont(new Font("微軟正黑體", Font.PLAIN, 32));
		comboBox_City.setBounds(185, 306, 150, 75);
		add(comboBox_City);

		comboBox_Single = new JComboBox<Integer>();
		comboBox_Single.setFont(new Font("Agency FB", Font.PLAIN, 48));
		comboBox_Single.setBounds(543, 104, 150, 75);
		add(comboBox_Single);

		comboBox_Double = new JComboBox<Integer>();
		comboBox_Double.setFont(new Font("Agency FB", Font.PLAIN, 48));
		comboBox_Double.setBounds(543, 204, 150, 75);
		add(comboBox_Double);

		comboBox_Quadro = new JComboBox<Integer>();
		comboBox_Quadro.setFont(new Font("Agency FB", Font.PLAIN, 48));
		comboBox_Quadro.setBounds(543, 306, 150, 75);
		add(comboBox_Quadro);
	}

	private void initializeTextAreas() {
		textField_S_price = new JTextField();
		textField_S_price.setFont(new Font("Agency FB", Font.PLAIN, 48));
		textField_S_price.setText("0");
		textField_S_price.setBounds(719, 103, 150, 75);
		add(textField_S_price);
		textField_S_price.setColumns(10);

		textField_D_price = new JTextField();
		textField_D_price.setFont(new Font("Agency FB", Font.PLAIN, 48));
		textField_D_price.setText("0");
		textField_D_price.setBounds(719, 204, 150, 75);
		add(textField_D_price);
		textField_D_price.setColumns(10);

		textField_Q_price = new JTextField();
		textField_Q_price.setFont(new Font("Agency FB", Font.PLAIN, 48));
		textField_Q_price.setText("0");
		textField_Q_price.setBounds(719, 306, 150, 75);
		add(textField_Q_price);
		textField_Q_price.setColumns(10);

		textField_Hotel_ID = new JTextField();
		textField_Hotel_ID.setFont(new Font("Agency FB", Font.PLAIN, 48));
		textField_Hotel_ID.setText(Integer.toString(HotelOperation.nextHotelId()));
		textField_Hotel_ID.setEditable(false);
		textField_Hotel_ID.setBounds(185, 103, 150, 75);
		add(textField_Hotel_ID);
		textField_Hotel_ID.setColumns(10);

		textField_Address = new JTextField();
		textField_Address.setFont(new Font("微軟正黑體", Font.PLAIN, 32));
		textField_Address.setBounds(177, 444, 692, 75);
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
		setVisible(true);
	}
}