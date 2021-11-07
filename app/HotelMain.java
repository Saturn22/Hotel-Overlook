import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HotelMain {
	private JFrame frame;
	private JLabel hotelLabel;
	private JButton homeBtn, bookingBtn, changeBtn, searchBtn, exitBtn, settingBtn;
	private JPanel btnPanelMain, mainPanel;
	private ImageIcon homeIcon, bookingIcon, changeIcon, settingIcon, exitIcon;
	private MyButtonListener listener;
	private JPanel homePanel;
	private JPanel changePanel;
	private JPanel allPanels;
	private JPanel bookingPanel;
	private JPanel optionPanel;

	public HotelMain() {

		listener = new MyButtonListener();
		/*
		 * Hotel Logo/Lebel
		 */

		hotelLabel = new JLabel("Overlook Hotel", SwingConstants.CENTER);
		hotelLabel.setFont(new Font("Serif", Font.BOLD, 30));
		hotelLabel.setBorder(new LineBorder(new Color(100, 100, 100), 2));

		/*
		 * Icons
		 */
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		homeIcon = new ImageIcon("img/Home.png");
		bookingIcon = new ImageIcon("img/Bed.png");
		changeIcon = new ImageIcon("img/Search.png");
		// searchIcon = new
		// ImageIcon(this.getClass().getResource("img/Search.png"));
		exitIcon = new ImageIcon("img/Delete.png");
		settingIcon = new ImageIcon("img/Settings.png");

		/*
		 * Menu Buttons
		 */

		homeBtn = new JButton(homeIcon);
		homeBtn.addActionListener(listener);
		homeBtn.setToolTipText("Check In / Check Out");
		homeBtn.setBorder(new EmptyBorder(15, 15, 15, 15));

		bookingBtn = new JButton(bookingIcon);
		bookingBtn.addActionListener(listener);
		bookingBtn.setToolTipText("Make a reservation");
		bookingBtn.setBorder(new EmptyBorder(15, 15, 15, 15));

		changeBtn = new JButton(changeIcon);
		changeBtn.addActionListener(listener);
		changeBtn.setToolTipText("Search / Change Reservation");
		changeBtn.setBorder(new EmptyBorder(15, 15, 15, 15));

		settingBtn = new JButton(settingIcon);
		settingBtn.addActionListener(listener);
		settingBtn.setToolTipText("Options");
		settingBtn.setBorder(new EmptyBorder(15, 15, 15, 15));

		exitBtn = new JButton(exitIcon);
		exitBtn.addActionListener(listener);
		exitBtn.setToolTipText("Exit");
		exitBtn.setBorder(new EmptyBorder(15, 15, 15, 15));

		/*
		 * Button/Menu Panel
		 */
		btnPanelMain = new JPanel();
		btnPanelMain.setLayout(new BoxLayout(btnPanelMain, BoxLayout.Y_AXIS));
		btnPanelMain.setBorder(new LineBorder(new Color(100, 100, 100), 2));
		btnPanelMain.add(homeBtn);
		btnPanelMain.add(bookingBtn);
		btnPanelMain.add(changeBtn);
		btnPanelMain.add(settingBtn);
		btnPanelMain.add(exitBtn);

		/*
		 * Main
		 */
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		mainPanel.add(hotelLabel, BorderLayout.NORTH);
		mainPanel.add(btnPanelMain, BorderLayout.WEST);

		// adding all Panels from other Gui classes
		
		homePanel = new HomeGui().getHomePanel();
		homePanel.setVisible(true);
		
		
		changePanel = new ChangeGui().getChangePanel();
		changePanel.setVisible(false);

		bookingPanel = new ReservationGui().getReservationPanel();
		bookingPanel.setVisible(false);

		optionPanel = new OptionGui().getOptionPanel();
		optionPanel.setVisible(false);

		allPanels = new JPanel();
		allPanels.add(homePanel);
		allPanels.add(changePanel);
		allPanels.add(bookingPanel);
		allPanels.add(optionPanel);

		mainPanel.add(allPanels, BorderLayout.CENTER);

		// Frame
		frame = new JFrame("Hotel Overlook - Booking System");
		frame.setSize(new Dimension(1250, 720));
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLocationRelativeTo(null);

		// Adding panels to Frame

		frame.add(mainPanel);
	}

	private class MyButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == homeBtn) { 
				homePanel.setVisible(true);
				changePanel.setVisible(false);
				bookingPanel.setVisible(false);
				optionPanel.setVisible(false);
				HomeGui.updateArrivalTable();
				HomeGui.updateDepartureTable();
			}
			if (e.getSource() == bookingBtn) {
				homePanel.setVisible(false);
				changePanel.setVisible(false);
				bookingPanel.setVisible(true);
				optionPanel.setVisible(false);
			}
			if (e.getSource() == exitBtn) {
				System.exit(1);
			}
			if (e.getSource() == changeBtn) {
				homePanel.setVisible(false);
				changePanel.setVisible(true);
				bookingPanel.setVisible(false);
				optionPanel.setVisible(false);
			}
			if (e.getSource() == settingBtn) {
				homePanel.setVisible(false);
				changePanel.setVisible(false);
				bookingPanel.setVisible(false);
				optionPanel.setVisible(true);
			}
		}
	}

	public static void main(String[] arg) {
		new HotelMain();
	}

}
