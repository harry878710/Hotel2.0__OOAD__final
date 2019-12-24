package testing.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Test2 extends JPanel {

	private JTextArea txtrDefaultText;
	private JButton btnBack;

	/**
	 * Create the panel.
	 */
	public Test2() {
		setBackground(Color.BLUE);
		setLayout(null);

		txtrDefaultText = new JTextArea(5, 20);
		txtrDefaultText.setText("default text");
		txtrDefaultText.setColumns(10);
		txtrDefaultText.setEditable(false);
		//txtrDefaultText.setBounds(64, 46, 332, 184);
		txtrDefaultText.setLineWrap(false);// 多的字會跑到下一行

		JScrollPane scrolledText = new JScrollPane(txtrDefaultText);
		scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrolledText.setBounds(64, 46, 332, 184);
		add(scrolledText);

		btnBack = new JButton("back");
		btnBack.setText("go back");
		btnBack.setBounds(107, 244, 87, 23);
		add(btnBack);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 470, 21);
		add(menuBar);

		JMenu mnTest = new JMenu("test");
		menuBar.add(mnTest);

		ActionListener a = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = arg0.getActionCommand();
				// default: the string written on the button or menuItem. code:
				// button.setText(str); or new JButton(str);
				// user defined: code: button.setActionCommand(str);
				if (str == "clear")
					setTextArea("");
				else if (str == "show example")
					setTextArea("example");
			}
		};

		JMenuItem menuItem = new JMenuItem("clear");
		menuItem.addActionListener(a);
		mnTest.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("show example");
		menuItem_1.addActionListener(a);
		mnTest.add(menuItem_1);

		JButton btnExit = new JButton("exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Test3().setVisible(true);
				;
			}
		});
		btnExit.setBounds(228, 244, 87, 23);
		add(btnExit);
	}

	public void setTextArea(String text) {
		this.txtrDefaultText.setText(text);
	}

	public JButton getBtnBack() {
		return btnBack;
	}
}
