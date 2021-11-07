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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ReservationGui
{
   private JDateChooser dateChooser;
   private JDateChooser dateChooser_1;
   private JDateChooser dateChooser_2;

   private JPanel formPanel;

   private JLabel headerLabel;

   private JLabel arrivalLabel, departureLabel;

   private JLabel firstnameLabel, lastNameLabel, addressLabel, birthdayLabel,
         phoneLabel, countriesLabel;

   private JLabel roomTypeLabel, roomNrLabel, smookingAllLabel, priceLabel,
         expectedGuestLabel, discountLabel, guestInfoLabel, optionLabel;

   private JTextField firstNameField, lastNameField, addressField, phoneField;

   private JTextField roomTypeField, smokingAllField, priceField,
         expectedGuestField, discountField;

   private JCheckBox extraBox, arrivallateBox;

   private JButton addAnotherReservationBtn, registerBtn, checkAvBtn;
   private MyListSelectionListener listListener;
   private DefaultListModel<String> listModel;
   private JList<String> reservationList;
   private JComboBox<Integer> roomNumberBox;
   private ReservationFileAdapter adapter;
   private ReservationFileAdapter checkInAdapter;
   private RoomFileAdapter roomAdapter;
   private MyButtonListener buttonListener;
   private JComboBox<String> comboBox_1;
   private boolean error = false;
   private RoomList availableRooms;

   @SuppressWarnings("deprecation")
   public ReservationGui()
   {
	   
      adapter = new ReservationFileAdapter("data/reservations.bin");

      roomAdapter = new RoomFileAdapter("data/rooms.bin");

      checkInAdapter = new ReservationFileAdapter("data/checkIns.bin");

      buttonListener = new MyButtonListener();
      listListener = new MyListSelectionListener();

      formPanel = new JPanel();
      formPanel.setLayout(new GridBagLayout());
      GridBagConstraints c1 = new GridBagConstraints();
      c1.insets = new Insets(2, 2, 2, 2);
      headerLabel = new JLabel("Make A Reservation", SwingConstants.CENTER);
      headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
      c1.gridy = 0;
      c1.gridx = 2;
      formPanel.add(headerLabel, c1);

      /*
       * Check Availability panel
       */
      c1.anchor = GridBagConstraints.LINE_START;
      arrivalLabel = new JLabel("Arrival Date: ");
      c1.gridy = 1;
      c1.gridx = 0;
      formPanel.add(arrivalLabel, c1);

      Date today = new Date();
      
      dateChooser = new JDateChooser();
      dateChooser.setDate(today);
      dateChooser.setPreferredSize(new Dimension(145, 20));
      c1.gridy = 1;
      c1.gridx = 1;
      formPanel.add(dateChooser, c1);

      departureLabel = new JLabel("Departure Date: ");
      c1.gridy = 1;
      c1.gridx = 2;
      formPanel.add(departureLabel, c1);

      today.setDate(today.getDate()+5);
      dateChooser_1 = new JDateChooser();
      dateChooser_1.setDate(today);
      dateChooser_1.setPreferredSize(new Dimension(145, 20));
      c1.gridy = 1;
      c1.gridx = 3;
      formPanel.add(dateChooser_1, c1);

      checkAvBtn = new JButton("Check Availability");
      checkAvBtn.addActionListener(buttonListener);
      c1.gridy = 1;
      c1.gridx = 4;
      formPanel.add(checkAvBtn, c1);

      listListener = new MyListSelectionListener();

      listModel = new DefaultListModel<String>();
      reservationList = new JList<String>(listModel);
      reservationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      reservationList.addListSelectionListener(listListener);
      reservationList.setVisible(true);
      reservationList.setVisibleRowCount(12);
      reservationList.setPreferredSize(new Dimension(150, 120));
      c1.gridwidth = 3;
      c1.gridheight = 6;
      c1.gridy = 2;
      c1.gridx = 0;
      c1.fill = GridBagConstraints.BOTH;
      formPanel.add(reservationList, c1);
      c1.gridwidth = 1;
      c1.gridheight = 1;

      /*
       * Form Panel
       */

      c1.fill = GridBagConstraints.NONE;

      c1.insets = new Insets(25, 2, 2, 2);
      guestInfoLabel = new JLabel("Guest Information");
      guestInfoLabel.setFont(new Font("Serif", Font.BOLD, 16));
      c1.gridy = 4;
      c1.gridx = 3;
      formPanel.add(guestInfoLabel, c1);
      c1.insets = new Insets(2, 2, 2, 2);

      firstnameLabel = new JLabel("First Name: ");
      c1.gridy = 5;
      c1.gridx = 3;
      formPanel.add(firstnameLabel, c1);

      firstNameField = new JTextField(13);
      c1.gridy = 6;
      c1.gridx = 3;
      formPanel.add(firstNameField, c1);

      lastNameLabel = new JLabel("Last Name: ");
      c1.gridy = 5;
      c1.gridx = 4;
      formPanel.add(lastNameLabel, c1);

      lastNameField = new JTextField(13);
      c1.gridy = 6;
      c1.gridx = 4;
      formPanel.add(lastNameField, c1);

      addressLabel = new JLabel("Address: ");
      c1.gridy = 7;
      c1.gridx = 3;
      formPanel.add(addressLabel, c1);

      addressField = new JTextField(13);
      c1.gridy = 8;
      c1.gridx = 3;
      formPanel.add(addressField, c1);

      phoneLabel = new JLabel("Phone: ");
      c1.gridy = 7;
      c1.gridx = 4;
      formPanel.add(phoneLabel, c1);

      phoneField = new JTextField(13);
      c1.gridy = 8;
      c1.gridx = 4;
      formPanel.add(phoneField, c1);

      birthdayLabel = new JLabel("Birthday: ");
      c1.gridy = 9;
      c1.gridx = 3;
      formPanel.add(birthdayLabel, c1);

      dateChooser_2 = new JDateChooser();
      dateChooser_2.setPreferredSize(new Dimension(145, 20));
      c1.gridy = 10;
      c1.gridx = 3;
      formPanel.add(dateChooser_2, c1);

      countriesLabel = new JLabel("Countries: ");
      c1.gridy = 9;
      c1.gridx = 4;
      formPanel.add(countriesLabel, c1);

      String[] availableLocales = Locale.getISOCountries();

      comboBox_1 = new JComboBox<String>();
      comboBox_1.addItem("");
      for (int i = 0; i < availableLocales.length; i++)
      {
         comboBox_1.addItem(new Locale("", Locale.getISOCountries()[i])
               .getDisplayCountry());
      }
      comboBox_1.setPreferredSize(new Dimension(145, 20));

      c1.gridy = 10;
      c1.gridx = 4;
      formPanel.add(comboBox_1, c1);

      addAnotherReservationBtn = new JButton("Add Another Reservation");
      addAnotherReservationBtn.addActionListener(buttonListener);
      c1.gridy = 11;
      c1.gridx = 3;
      c1.gridwidth = 2;
      c1.fill = GridBagConstraints.HORIZONTAL;
      formPanel.add(addAnotherReservationBtn, c1);

      registerBtn = new JButton("Finish");
      registerBtn.addActionListener(buttonListener);
      c1.gridy = 12;
      c1.gridx = 3;
      c1.gridwidth = 2;
      c1.fill = GridBagConstraints.HORIZONTAL;
      formPanel.add(registerBtn, c1);

      /*
       * Option Panel
       */
      c1.gridwidth = 1;
      c1.fill = GridBagConstraints.NONE;
      optionLabel = new JLabel("Options");
      optionLabel.setFont(new Font("Serif", Font.BOLD, 16));
      c1.gridy = 8;
      c1.gridx = 0;
      formPanel.add(optionLabel, c1);

      c1.anchor = GridBagConstraints.LINE_END;
      roomTypeLabel = new JLabel("Room Type: ");
      c1.gridy = 9;
      c1.gridx = 0;
      formPanel.add(roomTypeLabel, c1);

      roomTypeField = new JTextField(13);
      roomTypeField.setEditable(false);
      c1.gridy = 9;
      c1.gridx = 1;
      formPanel.add(roomTypeField, c1);

      roomNrLabel = new JLabel("Room Number: ");
      c1.gridy = 10;
      c1.gridx = 0;
      formPanel.add(roomNrLabel, c1);

      roomNumberBox = new JComboBox<Integer>();
      roomNumberBox.setPreferredSize(new Dimension(145, 20));
      c1.gridy = 10;
      c1.gridx = 1;
      formPanel.add(roomNumberBox, c1);

      smookingAllLabel = new JLabel("Smoking: ");
      c1.gridy = 11;
      c1.gridx = 0;
      formPanel.add(smookingAllLabel, c1);

      smokingAllField = new JTextField(13);
      smokingAllField.setEditable(false);
      c1.gridy = 11;
      c1.gridx = 1;
      formPanel.add(smokingAllField, c1);

      priceLabel = new JLabel("Price/Night: ");
      c1.gridy = 12;
      c1.gridx = 0;
      formPanel.add(priceLabel, c1);

      priceField = new JTextField(13);
      priceField.setEditable(false);
      c1.gridy = 12;
      c1.gridx = 1;
      formPanel.add(priceField, c1);

      expectedGuestLabel = new JLabel("Expected Guests: ");
      c1.gridy = 13;
      c1.gridx = 0;
      formPanel.add(expectedGuestLabel, c1);

      expectedGuestField = new JTextField(13);
      c1.gridy = 13;
      c1.gridx = 1;
      formPanel.add(expectedGuestField, c1);

      discountLabel = new JLabel("Discount: ");
      c1.gridy = 14;
      c1.gridx = 0;
      formPanel.add(discountLabel, c1);

      discountField = new JTextField(13);
      c1.gridy = 14;
      c1.gridx = 1;
      formPanel.add(discountField, c1);

      c1.anchor = GridBagConstraints.LINE_START;
      extraBox = new JCheckBox("ExtraBed");
      c1.gridy = 15;
      c1.gridx = 1;
      formPanel.add(extraBox, c1);

      arrivallateBox = new JCheckBox("Late Arrival");
      c1.gridy = 16;
      c1.gridx = 1;
      formPanel.add(arrivallateBox, c1);
      
      
   }

   private class MyListSelectionListener implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent e)
      {
         if (e.getSource() == reservationList)
         {
            try
            {
            	
         	   RoomList list = availableRooms;
         	
               roomNumberBox.removeAllItems();

               String[] arr = reservationList.getSelectedValue().split(" \\(");
               String roomType = arr[0];

               roomTypeField.setText(roomType);
               priceField.setText(String.valueOf(list
                     .getPriceFromRoomType(roomType)));
               smokingAllField.setText(list.smokingAllowed(roomType));
               int[] roomNumber = list.getAllNumbersFromRoomType(roomType);

               for (int i = 0; i < roomNumber[i]; i++)
               {
                  roomNumberBox.addItem(roomNumber[i]);
               }

            }
            catch (ArrayIndexOutOfBoundsException err){
            }
            catch(NullPointerException err){
            }

         }
      }
   }

   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == checkAvBtn)
         {
            try
            {
            	listModel.removeAllElements();
            	roomTypeField.setText("");
                priceField.setText("");
                smokingAllField.setText("");
            	
                Date arrival = dateChooser.getDate();
                Date departure = dateChooser_1.getDate();

                arrival.setHours(0);
                arrival.setMinutes(0);
        		arrival.setSeconds(0);
        		 
        		departure.setHours(0);
        		departure.setMinutes(0);
        		departure.setSeconds(0);

                Date today = new Date();
        		today.setHours(0);
        		today.setMinutes(0);
        		today.setSeconds(0);
        		
        		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        		
                if (departure.before(arrival) || departure.equals(arrival))
                {
                   MyExceptions.InvalidDateException();
                }
                else if (!arrival.after(today) && !sdf.format(arrival).equals(sdf.format(today)))
                {
                   MyExceptions.DateBeforeTodaysDateException();
                }
            
                
               availableRooms = roomAdapter.getAvailableRooms(arrival, departure);
               RoomList list = availableRooms;
               
               String[] arr = list.getAllRoomTypes();

               for (int i = 0; i < arr.length; i++)
               {
                  int count = list.getNumberOfRooms(arr[i]);
                  if (count != 0)
                  {
                     listModel.addElement(arr[i] + " " + "(" + count + "x)");
                  }
               }
            }
            
            
            catch (MyExceptions err)
            {
            	JOptionPane.showMessageDialog(null, err.getMessage());
            }
            catch (NullPointerException e1)
            {
               listModel.clear();
            }
         }
         
         if (e.getSource() == addAnotherReservationBtn)
         {
            addReservation();

            if (!error)
            {
               listModel.clear();
               roomTypeField.setText("");
               priceField.setText("");
               smokingAllField.setText("");
               expectedGuestField.setText("");
               discountField.setText("");
               extraBox.setSelected(false);
               arrivallateBox.setSelected(false);
               roomNumberBox.removeAllItems();

               HomeGui.updateArrivalTable();
            }
         }
         if (e.getSource() == registerBtn)
         {
            addReservation();

            HomeGui.updateArrivalTable();
            listModel.clear();
            firstNameField.setText("");
            lastNameField.setText("");
            addressField.setText("");
            phoneField.setText("");
            comboBox_1.setSelectedItem("");
            roomTypeField.setText("");
            smokingAllField.setText("");
            priceField.setText("");
            dateChooser_2.setDate(null);
            roomNumberBox.removeAllItems();
            extraBox.setSelected(false);
            arrivallateBox.setSelected(false);
            //getAvailableRooms();
         }
      }
   }

   public void addReservation()
   {
      // Guest object
      try
      {
         error = false;

         if (firstNameField.getText().isEmpty()
               || lastNameField.getText().isEmpty()
               || addressField.getText().isEmpty()
               || phoneField.getText().isEmpty()
               || comboBox_1.getSelectedItem().equals("")
               || dateChooser_2.getDate().equals(null))
         {
            MyExceptions.InvalidStringException();
         }
         String firstName = firstNameField.getText();
         String lastName = lastNameField.getText();
         String adress = addressField.getText();
         String phone = phoneField.getText();
         Date birthday = dateChooser_2.getDate();
         String nationality = (String) comboBox_1.getSelectedItem();
         Guest guest = new Guest(firstName, lastName, adress, phone, birthday,
               nationality);

         // Room Object
         if (roomTypeField.getText().equals(""))
         {
            MyExceptions.InvalidRoomExceptiom();
         }
         String roomType = roomTypeField.getText();
         double price = Double.parseDouble(priceField.getText());
         int roomNumber = Integer.parseInt(roomNumberBox.getSelectedItem()
               .toString());
         boolean smokingAllowed;
         if (smokingAllField.getText().equals("Yes"))
         {
            smokingAllowed = true;
         }
         else
         {
            smokingAllowed = false;
         }
         Room room = new Room(price, roomType, roomNumber, smokingAllowed);

         // Reservation object
         Date arrival = dateChooser.getDate();
         Date departure = dateChooser_1.getDate();

         arrival.setHours(0);
         arrival.setMinutes(0);
 		 arrival.setSeconds(0);
 		 
 		 departure.setHours(0);
 		 departure.setMinutes(0);
 		 departure.setSeconds(0);
         
         boolean extraBed = extraBox.isSelected();
         int expectedGuests = 0;
         if (!expectedGuestField.getText().equals(""))
         {
            expectedGuests = Integer.parseInt(expectedGuestField.getText());
         }
         if (expectedGuests < 0)
         {
            MyExceptions.InvalidNumberExpectedGuestException();
         }

         double discount = 0;
         if (!discountField.getText().equals(""))
         {
            discount = Double.parseDouble(discountField.getText());
         }
         if (discount < 0)
         {
            MyExceptions.InvalidNumberDiscountException();
         }

         boolean lateArrival = arrivallateBox.isSelected();
         Reservation reservation = new Reservation(arrival, departure, guest,
               room, extraBed, expectedGuests, lateArrival, discount);

         adapter.addReservation(reservation);

         JOptionPane.showMessageDialog(null,"Reservation has been added.");
      }
      catch (MyExceptions err)
      {
         JOptionPane.showMessageDialog(null, err.getMessage());
         error = true;
      }
      catch (NumberFormatException err)
      {
         JOptionPane.showMessageDialog(null,
               "Discount and number of expected guests must be a number.");
         error = true;
      }
   }

   public JPanel getReservationPanel()
   {
      return formPanel;
   }

}
