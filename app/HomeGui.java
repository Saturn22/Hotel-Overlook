import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.border.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class HomeGui {
	private static ReservationFileAdapter adapter, checkInAdapter, checkOutAdapter;
	private JLabel labelHome, labelArrival, labelDeparture;
	private JPanel borderPanel, arrivalPanel, departurePanel,
			arrivalButtonPanel, departureButtonPanel;
	private MyButtonListener buttonListener;
	private JButton btnCheckIn, btnAddGuest, btnCheckOut, btnAddDiscount;
	private JTextField discountField;
	private static JTable allArrivalsTable, allDeparturesTable;
	private static DefaultTableModel dtm, dtm1;
	private static JScrollPane allArrivalsTableScroll,
			allDeparturesTableScroll;
	private static String[] columnsArrival;
	private static String[] columnsDeparture;
	private AddGuestGui addGuest;

	public HomeGui() {
		buttonListener = new MyButtonListener();
		adapter = new ReservationFileAdapter("data/reservations.bin");
		checkInAdapter = new ReservationFileAdapter("data/checkIns.bin");
		checkOutAdapter = new ReservationFileAdapter("data/checkOuts.bin");
		/**
		 * HOME PANEL
		 */

		btnCheckIn = new JButton("Check-In");
		btnCheckIn.addActionListener(buttonListener);
		btnCheckOut = new JButton("Check-Out");
		btnCheckOut.addActionListener(buttonListener);
		btnAddGuest = new JButton("Add Guests");
		btnAddGuest.addActionListener(buttonListener);
		btnAddDiscount = new JButton("Discount");
		btnAddDiscount.addActionListener(buttonListener);
		discountField = new JTextField(20);

		// PANELS

		borderPanel = new JPanel();
		borderPanel.setPreferredSize(new Dimension(1150, 450));
		borderPanel.setLayout(new BorderLayout());

		arrivalPanel = new JPanel();
		arrivalPanel.setLayout(new BoxLayout(arrivalPanel, BoxLayout.Y_AXIS));

		departurePanel = new JPanel();
		departurePanel
				.setLayout(new BoxLayout(departurePanel, BoxLayout.Y_AXIS));

		labelHome = new JLabel("Home", SwingConstants.CENTER);
		labelHome.setFont(new Font("Serif", Font.BOLD, 24));
		borderPanel.add(labelHome, BorderLayout.NORTH);

		labelArrival = new JLabel("Arrivals", SwingConstants.CENTER);
		labelArrival.setFont(new Font("Serif", Font.BOLD, 15));
		arrivalPanel.add(labelArrival);

		arrivalButtonPanel = new JPanel();
		departureButtonPanel = new JPanel();

		// Arrival and departure Tables
		// Arrival Table

		columnsArrival = new String[10];
		columnsArrival[0] = "Nr.";
		columnsArrival[1] = "Firstname";
		columnsArrival[2] = "Lastname";
		columnsArrival[3] = "DOB";
		columnsArrival[4] = "Room";
		columnsArrival[5] = "Room number";
		columnsArrival[6] = "Expected Guest";
		columnsArrival[7] = "Late Arrival";
		columnsArrival[8] = "Departure Date";
		columnsArrival[9] = "Extra Bed";
		dtm = new DefaultTableModel(columnsArrival, 0);
		allArrivalsTable = new JTable(dtm);
		allArrivalsTable.setPreferredScrollableViewportSize(new Dimension(550,300));
		allArrivalsTable.setFillsViewportHeight(true);
		allArrivalsTableScroll = new JScrollPane(allArrivalsTable);
		allArrivalsTableScroll.setBorder(new EmptyBorder(5, 5, 5, 5));

		arrivalPanel.add(allArrivalsTableScroll);
		arrivalButtonPanel.add(btnCheckIn);
		arrivalButtonPanel.add(btnAddGuest);

		arrivalPanel.add(arrivalButtonPanel);

		// Departure Table

		labelDeparture = new JLabel("Departures", SwingConstants.CENTER);
		labelDeparture.setFont(new Font("Serif", Font.BOLD, 15));
		departurePanel.add(labelDeparture);

		columnsDeparture = new String[9];
		columnsDeparture[0] = "Nr.";
		columnsDeparture[1] = "Firstame";
		columnsDeparture[2] = "Lastname";
		columnsDeparture[3] = "DOB";
		columnsDeparture[4] = "Room";
		columnsDeparture[5] = "Room number";
		columnsDeparture[6] = "Days stayed";
		columnsDeparture[7] = "Price";
		columnsDeparture[8] = "Discount";

		dtm1 = new DefaultTableModel(columnsDeparture, 0);
		allDeparturesTable = new JTable(dtm1);
		allDeparturesTable.setPreferredScrollableViewportSize(new Dimension(
				550, 300));
		allDeparturesTable.setFillsViewportHeight(true);
		allDeparturesTableScroll = new JScrollPane(allDeparturesTable);
		allDeparturesTableScroll.setBorder(new EmptyBorder(5, 5, 5, 5));
		departurePanel.add(allDeparturesTableScroll);
		departureButtonPanel.add(btnCheckOut);
		departureButtonPanel.add(btnAddDiscount);
		departureButtonPanel.add(discountField);
		departurePanel.add(departureButtonPanel);

		// panel organizing

		borderPanel.add(arrivalPanel, BorderLayout.WEST);

		borderPanel.add(departurePanel, BorderLayout.EAST);

		updateArrivalTable();
		updateDepartureTable();

	}

	public JPanel getHomePanel() {
		return borderPanel;
	}

	// Method to update the arrival table.
	// Made static so we can assess it from other classes.
	public static void updateArrivalTable() {

		ReservationList list = adapter.getArrivalsFromToday();

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

		Object[][] data = new Object[list.size()][10];

		for (int i = 0; i < list.size(); i++) {
			data[i][0] = i + 1;
			data[i][1] = list.getReservation(i).getGuest().getFirstName();
			data[i][2] = list.getReservation(i).getGuest().getLastName();
			data[i][3] = sdf.format(list.getReservation(i).getGuest().getBirthday());
			data[i][4] = list.getReservation(i).getRoom().getRoomType();
			data[i][5] = list.getReservation(i).getRoom().getRoomNumber();
			data[i][6] = list.getReservation(i).getExpectedGuests();
			data[i][7] = list.getReservation(i).getLateArrival();
			data[i][8] = sdf.format(list.getReservation(i).getDeparture());
			data[i][9] = list.getReservation(i).getExtrabed();

		}
		dtm = new DefaultTableModel(data, columnsArrival);
		allArrivalsTable.setModel(dtm);
	}

	// Method to update the departure table.
	// Made static so we can assess it from other classes.
	public static void updateDepartureTable() {
		ReservationList list = checkInAdapter.getDeparturesFromToday();

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

		Object[][] data = new Object[list.size()][9];

		for (int i = 0; i < list.size(); i++) {
			data[i][0] = i + 1;
			data[i][1] = list.getReservation(i).getGuest().getFirstName();
			data[i][2] = list.getReservation(i).getGuest().getLastName();
			data[i][3] = sdf.format(list.getReservation(i).getGuest().getBirthday());
			data[i][4] = list.getReservation(i).getRoom().getRoomType();
			data[i][5] = list.getReservation(i).getRoom().getRoomNumber();
			data[i][6] = list.getReservation(i).getNumberOfDaysStayed();
			data[i][7] = list.getReservation(i).getPrice();
			data[i][8] = list.getReservation(i).getDiscount();

		}
		dtm1 = new DefaultTableModel(data, columnsDeparture);
		allDeparturesTable.setModel(dtm1);
	}

	private class MyButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnAddGuest) {

				try {
					String firstName = allArrivalsTable.getValueAt(allArrivalsTable.getSelectedRow(), 1).toString();
					String lastName = allArrivalsTable.getValueAt(allArrivalsTable.getSelectedRow(), 2).toString();
					int roomNumber = (int) allArrivalsTable.getValueAt(allArrivalsTable.getSelectedRow(), 5);

					// The reservation that has been choosen in the table is
					// being used in the new window to add more guests.
					addGuest = new AddGuestGui(adapter.getReservation(firstName, lastName, roomNumber));

					updateArrivalTable();

				} catch (ArrayIndexOutOfBoundsException err) {
					JOptionPane.showMessageDialog(null,"Choose a reservation in the table to add new guests.");
				}
			}

			if (e.getSource() == btnCheckIn) {
				try {
					String firstName = allArrivalsTable.getValueAt(allArrivalsTable.getSelectedRow(), 1).toString();
					String lastName = allArrivalsTable.getValueAt(allArrivalsTable.getSelectedRow(), 2).toString();
					int roomNumber = (int) allArrivalsTable.getValueAt(allArrivalsTable.getSelectedRow(), 5);

					// The choosen reservation is being check in.
					Reservation reservation = adapter.getReservation(firstName,lastName, roomNumber);
					adapter.checkInReservation(reservation);

					updateArrivalTable();

					JOptionPane.showMessageDialog(null,"Reservation has been checked in.");
				} catch (ArrayIndexOutOfBoundsException err) {
					JOptionPane.showMessageDialog(null,"Choose a reservation in the table to check in.");
				}

			}

			if (e.getSource() == btnAddDiscount) {
				try {
					String firstName = allDeparturesTable.getValueAt(allDeparturesTable.getSelectedRow(), 1).toString();
					String lastName = allDeparturesTable.getValueAt(allDeparturesTable.getSelectedRow(), 2).toString();
					int roomNumber = (int) allDeparturesTable.getValueAt(allDeparturesTable.getSelectedRow(), 5);

					Reservation reservation = checkInAdapter.getReservation(firstName, lastName, roomNumber);
					double discount = Double.parseDouble(discountField.getText());

					if(Double.parseDouble(discountField.getText()) < 0)
		            {
		              MyExceptions.InvalidNumberDiscountException();
		            }
					
					checkInAdapter.addDiscount(reservation, discount);

					updateDepartureTable();

				} catch (ArrayIndexOutOfBoundsException err) {
					JOptionPane.showMessageDialog(null,"Choose a reservation in the table to add discount.");
				} catch (NumberFormatException err) {
					JOptionPane.showMessageDialog(null,"Discount must be a number.");
				}catch(MyExceptions err){
					JOptionPane.showMessageDialog(null, err.getMessage());
				}

			}

			if (e.getSource() == btnCheckOut) {
				try {
					String firstName = allDeparturesTable.getValueAt(
							allDeparturesTable.getSelectedRow(), 1).toString();
					String lastName = allDeparturesTable.getValueAt(
							allDeparturesTable.getSelectedRow(), 2).toString();
					int roomNumber = (int) allDeparturesTable.getValueAt(
							allDeparturesTable.getSelectedRow(), 5);

					// The choosen reservation is being check in.
					Reservation reservation = checkInAdapter.getReservation(firstName, lastName, roomNumber);
					checkInAdapter.checkOutReservation(reservation);

					updateDepartureTable();
					
					JOptionPane.showMessageDialog(null,"Reservation has been checked out.\n" + 
					"Number of days stayed: " + reservation.getNumberOfDaysStayed() + "\nTotal price: " + reservation.getPrice());
					
				} catch (ArrayIndexOutOfBoundsException err) {
					JOptionPane.showMessageDialog(null,"Choose a reservation in the table to check out.");
				}
			}
		}
	}
}
