package testing.gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;

public class Test1 extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnButton;
	private JComboBox<String> comboBox;
	private String comboSelect = "0";//初始設成預設選項
	

	/**
	 * Create the panel.
	 */
	public Test1() {
		setBackground(Color.RED);
		setLayout(null);
		
		textField = new JTextField(10);
		textField.setColumns(20);
		textField.setBounds(90, 58, 160, 21);
		add(textField);
		
		
		comboBox = new JComboBox<String>();
		comboBox.setBackground(Color.YELLOW);
		comboBox.setBounds(90, 122, 160, 21);
		comboBox.addItem("0");
		comboBox.addItem("1");
		comboBox.addItem("2");
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					comboSelect = (String)e.getItem();
				}else if(e.getStateChange()==ItemEvent.DESELECTED) {
					//do nothing
				}
			}
		});
		add(comboBox);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(90, 89, 160, 21);
		add(passwordField);
		
		btnButton = new JButton("enter");
		btnButton.setBounds(90, 149, 160, 90);
		ImageIcon abcIcon = new ImageIcon("abc.jpg");
		btnButton.setIcon(abcIcon);
		//btnButton.setMargin(new Insets(5,5,5,5));
		add(btnButton);
		
		JLabel lblNumberId = new JLabel("number ID");
		lblNumberId.setBackground(Color.YELLOW);
		lblNumberId.setBounds(10, 61, 70, 15);
		add(lblNumberId);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(10, 92, 46, 15);
		add(lblPassword);
		
		
	}

	public JButton getBtnButton() {
		return btnButton;
	}

	public String getComboSelect() {
		return comboSelect;
	}


	public String getTextEnter() {
		return textField.getText();
	}


	public String getPassEnter() {
		StringBuffer tmp = new StringBuffer();
		for(char c:passwordField.getPassword()) {
			tmp.append(c);
		}
		String str = new String(tmp);
		return str;
	}
}
