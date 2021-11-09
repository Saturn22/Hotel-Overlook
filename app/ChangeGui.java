import com.toedter.calendar.JDateChooser;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;





import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ChangeGui
{
   private JDateChooser dateChooser, dateChooser_1, dateChooser_2;
   private JPanel formPanel;
   private JLabel headerLabel, arrivalLabel, departureLabel,
         lastNameSearchLabel, listLabel, firstnameLabel, lastNameLabel,
         addressLabel, birthdayLabel, phoneLabel, countriesLabel,
         roomTypeLabel, roomNrLabel, expectedGuestLabel, discountLabel,
         guestInfoLabel, optionLabel;
   private JTextField firstNameField, lastNameField, addressField, phoneField,
         lastNameSearchField, roomTypeField, expectedGuestField, discountField,roomNumberField;
   private JCheckBox extraBox, arrivallateBox;
   private JButton changeGuestBtn, addGuestBtn, deleteGuestBtn, changeBtn,
         deleteBtn, searchBtn, btnChangeReservation;
   private ReservationFileAdapter adapter;
   private ReservationFileAdapter checkInAdapter;
   private MyButtonListener buttonListener;
   private JComboBox<String> comboBox_2;
   private JComboBox comboBox_1;
   private JComboBox<String> comboBox;
   private JScrollPane scrollPane;
   private String[] columnNames;
   private DefaultTableModel dtm;
   private JTable table;
   private Reservation reservationChange;
   private ReservationFileAdapter fileAdapter;
   private ReservationFileAdapter fileAdapterChange;
   private ReservationFileAdapter checkOutAdapter;
   private JPanel tablePanel;

   
   public ChangeGui()
   {
      adapter = new ReservationFileAdapter("data/reservations.bin");
      checkInAdapter = new ReservationFileAdapter("data/checkIns.bin");
      checkOutAdapter = new ReservationFileAdapter("data/checkOuts.bin");

      buttonListener = new MyButtonListener();

      formPanel = new JPanel();
      formPanel.setLayout(new GridBagLayout());
      GridBagConstraints c1 = new GridBagConstraints();

      /*
       * Header Label
       */

      c1.insets = new Insets(2, 2, 2, 2);
      headerLabel = new JLabel("Change Reservation",
            SwingConstants.CENTER);
      headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
      c1.gridwidth = 3;
      c1.fill = GridBagConstraints.HORIZONTAL;
      c1.gridy = 0;
      c1.gridx = 2;
      formPanel.add(headerLabel, c1);
      c1.gridwidth = 1;

      /*
       * Booking Search
       */
      c1.anchor = GridBagConstraints.LINE_END;
      listLabel = new JLabel("List: ");
      c1.gridy = 1;
      c1.gridx = 0;
      formPanel.add(listLabel, c1);

      comboBox = new JComboBox<String>();
      comboBox.addItem("Upcoming Reservations");
      comboBox.addItem("Current Reservations");
      comboBox.addItem("Old Reservations");
      c1.gridy = 1;
      c1.gridx = 1;
      formPanel.add(comboBox, c1);

      lastNameSearchField = new JTextField(13);
      c1.gridy = 1;
      c1.gridx = 3;
      formPanel.add(lastNameSearchField, c1);

      lastNameSearchLabel = new JLabel("Last Name: ");
      c1.gridy = 1;
      c1.gridx = 2;
      formPanel.add(lastNameSearchLabel, c1);

      c1.anchor = GridBagConstraints.LINE_START;

      searchBtn = new JButton("Search");
      searchBtn.addActionListener(buttonListener);
      c1.gridy = 1;
      c1.gridx = 4;
      formPanel.add(searchBtn, c1);

      /*
       * Booking Table and Change-Delete Button
       */

      columnNames = new String[10];
      columnNames[0] = "Firstname";
      columnNames[1] = "Lastname";
      columnNames[2] = "Arrival";
      columnNames[3] = "Departure";
      columnNames[4] = "Roomtype";
      columnNames[5] = "Room number";
      columnNames[6] = "Expected Guest";
      columnNames[7] = "Late Arrival";
      columnNames[8] = "Extra Bed";
      columnNames[9] = "Discount";
      dtm = new DefaultTableModel(columnNames, 0);
      table = new JTable(dtm);
      table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      // table.addListSelectionListener(listListener);
      // model = table.getSelectionModel();
      scrollPane = new JScrollPane();
      scrollPane.setViewportView(table);

      tablePanel = new JPanel();
      tablePanel.setPreferredSize(new Dimension(500, 250));
      tablePanel.add(scrollPane);
      c1.gridy = 2;
      c1.gridx = 0;
      c1.gridwidth = 6;
      c1.gridheight = 4;
      c1.fill = GridBagConstraints.BOTH;
      c1.anchor = GridBagConstraints.LINE_START;
      formPanel.add(tablePanel, c1);
      c1.gridwidth = 1;
      c1.gridheight = 1;

      changeBtn = new JButton("Change");
      changeBtn.addActionListener(buttonListener);
      c1.gridy = 7;
      c1.gridx = 1;
      formPanel.add(changeBtn, c1);

      deleteBtn = new JButton("Delete");
      deleteBtn.addActionListener(buttonListener);
      c1.gridy = 7;
      c1.gridx = 2;
      formPanel.add(deleteBtn, c1);

      /*
       * Guest Information
       */

      c1.insets = new Insets(2, 2, 2, 2);
      guestInfoLabel = new JLabel("Change Guest Information");
      guestInfoLabel.setFont(new Font("Serif", Font.BOLD, 16));
      c1.gridy = 12;
      c1.gridx = 7;
      c1.gridwidth = 2;
      c1.fill = GridBagConstraints.HORIZONTAL;
      formPanel.add(guestInfoLabel, c1);
      c1.insets = new Insets(2, 2, 2, 2);

      comboBox_1 = new JComboBox();
      comboBox_1.addItem("Pick Guest");
      comboBox_1.addActionListener(buttonListener);
      c1.gridy = 13;
      c1.gridx = 7;
      formPanel.add(comboBox_1, c1);

      c1.fill = GridBagConstraints.NONE;
      c1.gridwidth = 1;

      firstnameLabel = new JLabel("First Name: ");
      c1.gridy = 14;
      c1.gridx = 7;
      formPanel.add(firstnameLabel, c1);

      firstNameField = new JTextField(13);
      c1.gridy = 15;
      c1.gridx = 7;
      formPanel.add(firstNameField, c1);

      lastNameLabel = new JLabel("Last Name: ");
      c1.gridy = 14;
      c1.gridx = 8;
      formPanel.add(lastNameLabel, c1);

      lastNameField = new JTextField(13);
      c1.gridy = 15;
      c1.gridx = 8;
      formPanel.add(lastNameField, c1);

      addressLabel = new JLabel("Address: ");
      c1.gridy = 16;
      c1.gridx = 7;
      formPanel.add(addressLabel, c1);

      addressField = new JTextField(13);
      c1.gridy = 17;
      c1.gridx = 7;
      formPanel.add(addressField, c1);

      phoneLabel = new JLabel("Phone: ");
      c1.gridy = 16;
      c1.gridx = 8;
      formPanel.add(phoneLabel, c1);

      phoneField = new JTextField(13);
      c1.gridy = 17;
      c1.gridx = 8;
      formPanel.add(phoneField, c1);

      birthdayLabel = new JLabel("Birthday: ");
      c1.gridy = 18;
      c1.gridx = 7;
      formPanel.add(birthdayLabel, c1);

      dateChooser_2 = new JDateChooser();
      dateChooser_2.setPreferredSize(new Dimension(145, 20));
      c1.gridy = 19;
      c1.gridx = 7;
      formPanel.add(dateChooser_2, c1);

      countriesLabel = new JLabel("Countries: ");
      c1.gridy = 18;
      c1.gridx = 8;
      formPanel.add(countriesLabel, c1);

      String[] availableLocales = Locale.getISOCountries();

      comboBox_2 = new JComboBox<String>();
      comboBox_2.addItem("");
      for (int i = 0; i < availableLocales.length; i++)
      {
         comboBox_2.addItem(new Locale("en", Locale.getISOCountries()[i])
               .getDisplayCountry());
      }
      comboBox_2.setPreferredSize(new Dimension(145, 20));

      c1.gridy = 19;
      c1.gridx = 8;
      formPanel.add(comboBox_2, c1);

      changeGuestBtn = new JButton("Change Guest");
      changeGuestBtn.addActionListener(buttonListener);
      c1.gridy = 20;
      c1.gridx = 7;
      c1.gridwidth = 2;
      c1.fill = GridBagConstraints.HORIZONTAL;
      formPanel.add(changeGuestBtn, c1);

      addGuestBtn = new JButton("Add Guest");
      addGuestBtn.addActionListener(buttonListener);
      c1.gridy = 21;
      c1.gridx = 7;
      c1.gridwidth = 2;
      c1.fill = GridBagConstraints.HORIZONTAL;
      formPanel.add(addGuestBtn, c1);

      deleteGuestBtn = new JButton("Delete Guest");
      deleteGuestBtn.addActionListener(buttonListener);
      c1.gridy = 22;
      c1.gridx = 7;
      c1.gridwidth = 2;
      c1.fill = GridBagConstraints.HORIZONTAL;
      formPanel.add(deleteGuestBtn, c1);

      /*
       * Option Panel
       */
      c1.anchor = GridBagConstraints.LINE_END;

      optionLabel = new JLabel("Change Booking Options");
      optionLabel.setFont(new Font("Serif", Font.BOLD, 16));
      c1.gridwidth = 2;
      c1.gridy = 12;
      c1.gridx = 0;
      c1.fill = GridBagConstraints.HORIZONTAL;
      formPanel.add(optionLabel, c1);

      c1.gridwidth = 1;
      c1.fill = GridBagConstraints.NONE;

      arrivalLabel = new JLabel("Arrival Date: ");
      c1.gridy = 13;
      c1.gridx = 0;
      formPanel.add(arrivalLabel, c1);

      dateChooser = new JDateChooser();
      dateChooser.setPreferredSize(new Dimension(145, 20));
      c1.gridy = 13;
      c1.gridx = 1;
      formPanel.add(dateChooser, c1);

      departureLabel = new JLabel("Departure Date: ");
      c1.gridy = 13;
      c1.gridx = 2;
      formPanel.add(departureLabel, c1);

      dateChooser_1 = new JDateChooser();
      dateChooser_1.setPreferredSize(new Dimension(145, 20));
      c1.gridy = 13;
      c1.gridx = 3;
      formPanel.add(dateChooser_1, c1);

      roomTypeLabel = new JLabel("Room Type: ");
      c1.gridy = 14;
      c1.gridx = 0;
      formPanel.add(roomTypeLabel, c1);

      roomTypeField = new JTextField(13);
      roomTypeField.setEditable(false);
      c1.gridy = 14;
      c1.gridx = 1;
      formPanel.add(roomTypeField, c1);

      roomNrLabel = new JLabel("Room Number: ");
      c1.gridy = 14;
      c1.gridx = 2;
      formPanel.add(roomNrLabel, c1);

      roomNumberField = new JTextField(13);
      roomNumberField.setEditable(false);
      c1.gridy = 14;
      c1.gridx = 3;
      formPanel.add(roomNumberField, c1);

      /*
       * smookingAllLabel = new JLabel("Smoking: "); c1.gridy = 15; c1.gridx =
       * 0; formPanel.add(smookingAllLabel, c1); smokingAllField = new
       * JTextField(13); c1.gridy = 15; c1.gridx = 1;
       * formPanel.add(smokingAllField, c1); priceLabel = new
       * JLabel("Price/Night: "); c1.gridy = 15; c1.gridx = 2;
       * formPanel.add(priceLabel, c1); priceField = new JTextField(13);
       * c1.gridy = 15; c1.gridx = 3; formPanel.add(priceField, c1);
       */
      expectedGuestLabel = new JLabel("Expected Guests: ");
      c1.gridy = 16;
      c1.gridx = 0;
      formPanel.add(expectedGuestLabel, c1);

      expectedGuestField = new JTextField(13);
      c1.gridy = 16;
      c1.gridx = 1;
      formPanel.add(expectedGuestField, c1);

      discountLabel = new JLabel("Discount: ");
      c1.gridy = 16;
      c1.gridx = 2;
      formPanel.add(discountLabel, c1);

      discountField = new JTextField(13);
      c1.gridy = 16;
      c1.gridx = 3;
      formPanel.add(discountField, c1);

      c1.anchor = GridBagConstraints.LINE_START;
      extraBox = new JCheckBox("ExtraBed");
      c1.gridy = 17;
      c1.gridx = 0;
      formPanel.add(extraBox, c1);

      arrivallateBox = new JCheckBox("Late Arrival");
      c1.gridy = 17;
      c1.gridx = 1;
      formPanel.add(arrivallateBox, c1);

      btnChangeReservation = new JButton("Done");
      btnChangeReservation.addActionListener(buttonListener);
      c1.gridy = 18;
      c1.gridx = 2;
      c1.gridwidth = 2;
      c1.fill = GridBagConstraints.HORIZONTAL;
      formPanel.add(btnChangeReservation, c1);

      /*
       * right Panel rightPanel = new JPanel(); rightPanel.setLayout(new
       * BoxLayout(rightPanel, BoxLayout.Y_AXIS)); rightPanel.add(listPanel);
       * rightPanel.add(optionPanel); List Panel
       */

      /*
       * making Main Panel of booking Panel mainPanel = new JPanel();
       * mainPanel.setLayout(new BorderLayout()); mainPanel.add(headerLabel,
       * BorderLayout.NORTH); mainPanel.add(formPanel, BorderLayout.WEST); /*
       * frame = new JFrame(); frame.add(mainPanel); // add(footerPanel,
       * BorderLayout.SOUTH); frame.setSize(600, 700); frame.setVisible(true);
       * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       * frame.setLocationRelativeTo(null);
       */
   }

   public JPanel getChangePanel()
   {
      return formPanel;
   }

   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == searchBtn)
         {
            updateTable();
         }

         if (e.getSource() == btnChangeReservation)
         {
            try
            {
               Reservation oldReservation = reservationChange;

               Date arrival = dateChooser.getDate();
               arrival.setHours(0);
               arrival.setMinutes(0);
               arrival.setSeconds(0);
               Date departure = dateChooser_1.getDate();
               departure.setHours(0);
               departure.setMinutes(0);
               departure.setSeconds(0);
               
               if(!arrival.equals(reservationChange.getArrival()))
               {
                 Exceptions.InvalidChangeArrivalDateException();
               }
               if(departure.after(reservationChange.getDeparture()))
               {
                 Exceptions.InvalidChangeDepartureDateException();
               }   
               if(Integer.parseInt(expectedGuestField.getText()) < 0)
               {
                 Exceptions.InvalidNumberExpectedGuestException();
               }
               if(Double.parseDouble(discountField.getText()) < 0)
               {
                 Exceptions.InvalidNumberDiscountException();
               }
               

               
               reservationChange.setDeparture(departure);
               reservationChange.setExpectedGuests(Integer.parseInt(expectedGuestField.getText()));
               reservationChange.setDiscount(Double.parseDouble(discountField.getText()));

               
               
               if (extraBox.isSelected())
               {
                  reservationChange.setExtrabed(true);
               }
               else
               {
                  reservationChange.setExtrabed(false);
               }
               if (arrivallateBox.isSelected())
               {
                  reservationChange.setLateArrival(true);
               }
               else
               {
                  reservationChange.setLateArrival(false);
               }

               fileAdapterChange.removeReservation(oldReservation);
               fileAdapterChange.addReservation(reservationChange);

               updateTable();
               HomeGui.updateDepartureTable();

               deleteFields();
               
               JOptionPane.showMessageDialog(null,
                       "Reservation has been changed.");
            }
            catch (ArrayIndexOutOfBoundsException err)
            {
               JOptionPane.showMessageDialog(null,
                     "Choose a reservation in the table to change it.");
            }
            catch (Exceptions err)
            {
               JOptionPane.showMessageDialog(null, err.getMessage());
            }
            catch (NumberFormatException err)
            {
               JOptionPane
                     .showMessageDialog(null,
                           "Discount and number of expected guests must be a number.");
            }
         }
         if (e.getSource() == changeBtn)
         {
            try
            {
               deleteFields();

               fileAdapterChange = fileAdapter;

               String firstName = (String) table.getValueAt(
                     table.getSelectedRow(), 0);
               String lastName = (String) table.getValueAt(
                     table.getSelectedRow(), 1);
               int roomNumber = (int) table.getValueAt(table.getSelectedRow(),
                     5);

               reservationChange = fileAdapterChange.getReservation(firstName,
                     lastName, roomNumber);

               Guest[] guests = reservationChange.getAllGuests();
               for (int i = 0; i < guests.length; i++)
               {
                  comboBox_1.addItem(guests[i]);
               }

               dateChooser.setDate(reservationChange.getArrival());
               dateChooser_1.setDate(reservationChange.getDeparture());

               roomTypeField.setText(reservationChange.getRoom().getRoomType());
               roomNumberField.setText(String.valueOf(reservationChange
                     .getRoom().getRoomNumber()));

               expectedGuestField.setText(String.valueOf(reservationChange
                     .getExpectedGuests()));
               discountField.setText(String.valueOf(reservationChange
                     .getDiscount()));

               if (reservationChange.getExtrabed())
               {
                  extraBox.setSelected(true);
               }
               if (reservationChange.getLateArrival())
               {
                  arrivallateBox.setSelected(true);
               }
               
            }
            catch (ArrayIndexOutOfBoundsException err)
            {
               JOptionPane.showMessageDialog(null,
                     "Choose a reservation in the table to change it.");
            }
         }

         if (e.getSource() == deleteBtn)
         {
            try
            {

               String firstName = (String) table.getValueAt(
                     table.getSelectedRow(), 0);
               String lastName = (String) table.getValueAt(
                     table.getSelectedRow(), 1);
               int roomNumber = (int) table.getValueAt(table.getSelectedRow(),
                     5);

               fileAdapter.removeReservation(firstName, lastName, roomNumber);

               updateTable();
               deleteFields();
               HomeGui.updateArrivalTable();
               HomeGui.updateDepartureTable();

               JOptionPane.showMessageDialog(null, "Reservation has been removed.");
               
            }
            catch (ArrayIndexOutOfBoundsException err)
            {
               JOptionPane.showMessageDialog(null,
                     "Choose a reservation in the table to delete it.");
            }
         }

         if (e.getSource() == comboBox_1)
         {

            try
            {
               if (!comboBox_1.getSelectedItem().equals("Pick Guest"))
               {
                  Guest[] guests = reservationChange.getAllGuests();
                  String[] name = comboBox_1.getSelectedItem().toString()
                        .split(" ");
                  for (int i = 0; i < guests.length; i++)
                  {
                     if (guests[i].getFirstName().equals(name[0])
                           && guests[i].getLastName().equals(name[1]))
                     {
                        firstNameField.setText(guests[i].getFirstName());
                        lastNameField.setText(guests[i].getLastName());
                        phoneField.setText(guests[i].getPhone());
                        addressField.setText(guests[i].getAddress());
                        comboBox_2.setSelectedItem(guests[i].getNationality());
                        dateChooser_2.setDate(guests[i].getBirthday());
                        break;
                     }
                  }
               }
            }
            catch (NullPointerException e1)
            {
               comboBox_1.addItem("Pick Guest");
            }
         }

         if (e.getSource() == deleteGuestBtn)
         {
            try
            {
               Reservation oldReservation = reservationChange;

               if (comboBox_1.getSelectedItem().equals("Pick Guest"))
               {
                  Exceptions.InvalidDeleteGuestExcpetion();
               }

               Guest[] guests = reservationChange.getAllGuests();
               String[] name = comboBox_1.getSelectedItem().toString()
                     .split(" ");
               for (int i = 0; i < guests.length; i++)
               {
                  if (guests[i].getFirstName().equals(name[0])
                        && guests[i].getLastName().equals(name[1]))
                  {
                     reservationChange.removeGuest(i);
                  }
               }
               fileAdapterChange.removeReservation(oldReservation);
               fileAdapterChange.addReservation(reservationChange);

               comboBox_1.removeAllItems();
               Guest[] guests2 = reservationChange.getAllGuests();
               for (int i = 0; i < guests2.length; i++)
               {
                  comboBox_1.addItem(guests[i]);
               }

               comboBox_1.setSelectedItem("Pick Guest");

               firstNameField.setText("");
               lastNameField.setText("");
               addressField.setText("");
               phoneField.setText("");
               comboBox_2.setSelectedItem("");
               dateChooser_2.setDate(null);
               
               updateTable();
               
               JOptionPane.showMessageDialog(null, "Guest has been removed.");

            }
            catch (Exceptions err)
            {
               JOptionPane.showMessageDialog(null, err.getMessage());
            }
         }

         if (e.getSource() == changeGuestBtn)
         {
            try
            {
               fileAdapterChange.removeReservation(reservationChange);
               
               if (firstNameField.getText().equals("")
                     || lastNameField.getText().equals("")
                     || phoneField.getText().equals("")
                     || addressField.getText().equals("")
                     || dateChooser_2.getDate().equals(null)
                     || comboBox_2.getSelectedItem().equals(""))
               {
                  Exceptions.InvalidStringException();
               }
               
               Guest[] guests = reservationChange.getAllGuests();
               String[] name = comboBox_1.getSelectedItem().toString().split(" ");
               
               for (int i = 0; i < guests.length; i++)
               {
                  if (guests[i].getFirstName().equals(name[0])
                        && guests[i].getLastName().equals(name[1]))
                  {
                     guests[i].setFirstName(firstNameField.getText());
                     guests[i].setLastName(lastNameField.getText());
                     guests[i].setAddress(addressField.getText());
                     guests[i].setPhone(phoneField.getText());
                     guests[i].setBirthday(dateChooser_2.getDate());
                     guests[i].setNationality((String) comboBox_2
                           .getSelectedItem());
                  }
               }
               
               fileAdapterChange.addReservation(reservationChange);

               comboBox_1.removeAllItems();
               Guest[] guests2 = reservationChange.getAllGuests();
               for (int i = 0; i < guests2.length; i++)
               {
                  comboBox_1.addItem(guests[i]);
               }

               comboBox_1.setSelectedItem("Pick Guest");

               firstNameField.setText("");
               lastNameField.setText("");
               addressField.setText("");
               phoneField.setText("");
               comboBox_2.setSelectedItem("");
               dateChooser_2.setDate(null);
               
               updateTable();
               
               JOptionPane.showMessageDialog(null, "Guest has been changed.");
               
            }
            catch (Exceptions err)
            {
               JOptionPane.showMessageDialog(null, err.getMessage());
            }
         }

         if (e.getSource() == addGuestBtn)
         {
            try
            {
               Reservation oldReservation = reservationChange;

               if (firstNameField.getText().equals("")
                     || lastNameField.getText().equals("")
                     || phoneField.getText().equals("")
                     || addressField.getText().equals("")
                     || dateChooser_2.getDate().equals(null)
                     || comboBox_2.getSelectedItem().equals(""))
               {
                  Exceptions.InvalidStringException();
               }

               Guest guest = new Guest(firstNameField.getText(),
                     lastNameField.getText(), addressField.getText(),
                     phoneField.getText(), dateChooser_2.getDate(),
                     (String) comboBox_2.getSelectedItem());

             
               fileAdapterChange.removeReservation(oldReservation);
               reservationChange.addGuest(guest);
               fileAdapterChange.addReservation(reservationChange);

               comboBox_1.addItem(firstNameField.getText() + " "
                     + lastNameField.getText());
               comboBox_1.setSelectedItem("Pick Guest");

               firstNameField.setText("");
               lastNameField.setText("");
               addressField.setText("");
               phoneField.setText("");
               comboBox_2.setSelectedItem("");
               dateChooser_2.setDate(null);
               
               updateTable();
               
               JOptionPane.showMessageDialog(null, "Guest has been added.");
               
            }
            catch (Exceptions err)
            {
               JOptionPane.showMessageDialog(null, err.getMessage());
            }
         }
      }
   }

   public void updateTable()
   {
      ReservationList list = null;

      if (comboBox.getSelectedItem().equals("Upcoming Reservations"))
      {
         fileAdapter = adapter;
      }
      else if (comboBox.getSelectedItem().equals("Current Reservations"))
      {
         fileAdapter = checkInAdapter;
      }
      else if (comboBox.getSelectedItem().equals("Old Reservations"))
      {
         fileAdapter = checkOutAdapter;
      }

      
      if (!lastNameSearchField.getText().equals(""))
      {
         list = fileAdapter.getAllReservationsWithLastname(lastNameSearchField.getText());
      }
      else
      {
         list = fileAdapter.getAllReservations();
      }

      SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

      Object[][] data = new Object[list.size()][10];

      for (int i = 0; i < list.size(); i++)
      {
         data[i][0] = list.getReservation(i).getGuest().getFirstName();
         data[i][1] = list.getReservation(i).getGuest().getLastName();
         data[i][2] = sdf.format(list.getReservation(i).getArrival());
         data[i][3] = sdf.format(list.getReservation(i).getDeparture());
         data[i][4] = list.getReservation(i).getRoom().getRoomType();
         data[i][5] = list.getReservation(i).getRoom().getRoomNumber();
         data[i][6] = list.getReservation(i).getExpectedGuests();
         data[i][7] = list.getReservation(i).getLateArrival();
         data[i][8] = list.getReservation(i).getExtrabed();
         data[i][9] = list.getReservation(i).getDiscount();

      }
      dtm = new DefaultTableModel(data, columnNames);
      table.setModel(dtm);
      
      if(list.size() == 0){
    	  JOptionPane.showMessageDialog(null, "Search was empty");
      }
   }

   public void deleteFields()
   {
      dateChooser.setDate(null);
      dateChooser_1.setDate(null);
      dateChooser_2.setDate(null);

      expectedGuestField.setText("");
      firstNameField.setText("");
      lastNameField.setText("");
      addressField.setText("");
      phoneField.setText("");
      comboBox_2.setSelectedItem("");
      discountField.setText("");
      roomNumberField.setText("");
      roomTypeField.setText("");

      comboBox_1.removeAllItems();

      arrivallateBox.setSelected(false);
      extraBox.setSelected(false);
   }

}
