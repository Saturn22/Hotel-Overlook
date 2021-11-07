import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

public class AddGuestGui extends JFrame
{
   private JPanel addGuestPanel;
   private JLabel addGuestLabel, firstnameLabel, lastNameLabel, addressLabel, birthdayLabel,
         phoneLabel, countriesLabel, lblNumberOfGuest;
   private JTextField firstNameField, lastNameField, addressField, phoneField;
   private JComboBox<String> countries;
   private JDateChooser dateChooser_2;
   private JButton btnAddAnotherGuest, btnFinish;
   private ReservationFileAdapter adapter, checkInAdapter;
   private MyButtonListener buttonListener;
   private Reservation reservation;
   private HomeGui homePanel;
   
   
   public AddGuestGui(Reservation reservation)
   {
      this.reservation = reservation;
      
      buttonListener = new MyButtonListener();
      
       adapter = new ReservationFileAdapter("data/reservations.bin");
       
       checkInAdapter = new ReservationFileAdapter("data/checkIns.bin");
      
      addGuestPanel = new JPanel();
      addGuestPanel.setLayout(new GridBagLayout());
      GridBagConstraints c1 = new GridBagConstraints();
      c1.insets = new Insets(2,2,2,2);
      
      addGuestLabel = new JLabel("Add Guests", SwingConstants.CENTER);
      addGuestLabel.setFont(new Font("Serif", Font.BOLD, 18));
      
      c1.gridx = 0;
      c1.gridy = 0;
      c1.gridwidth= 2;
      c1.fill = GridBagConstraints.HORIZONTAL;
      addGuestPanel.add(addGuestLabel, c1);
      
      
      c1.anchor = GridBagConstraints.LINE_START;
      lblNumberOfGuest = new JLabel("Number Of Guests added: 0");
      c1.gridy = 1;
      addGuestPanel.add(lblNumberOfGuest, c1);
      
      
      c1.gridwidth= 1;
      c1.fill = GridBagConstraints.NONE;
      
      firstnameLabel = new JLabel("First Name: ");
      c1.gridy = 2;
      c1.gridx = 0;
      addGuestPanel.add(firstnameLabel, c1);

      firstNameField = new JTextField(13);
      c1.gridy = 3;
      c1.gridx = 0;
      addGuestPanel.add(firstNameField, c1);

      lastNameLabel = new JLabel("Last Name: ");
      c1.gridy = 2;
      c1.gridx = 1;
      addGuestPanel.add(lastNameLabel, c1);

      lastNameField = new JTextField(13);
      c1.gridy = 3;
      c1.gridx = 1;
      addGuestPanel.add(lastNameField, c1);

      addressLabel = new JLabel("Address: ");
      c1.gridy = 4;
      c1.gridx = 0;
      addGuestPanel.add(addressLabel, c1);

      addressField = new JTextField(13);
      c1.gridy = 5;
      c1.gridx = 0;
      addGuestPanel.add(addressField, c1);

      phoneLabel = new JLabel("Phone: ");
      c1.gridy = 4;
      c1.gridx = 1;
      addGuestPanel.add(phoneLabel, c1);

      phoneField = new JTextField(13);
      c1.gridy = 5;
      c1.gridx = 1;
      addGuestPanel.add(phoneField, c1);

      birthdayLabel = new JLabel("Birthday: ");
      c1.gridy = 6;
      c1.gridx = 0;
      addGuestPanel.add(birthdayLabel, c1);

      dateChooser_2 = new JDateChooser();
      dateChooser_2.setPreferredSize(new Dimension(145,20));
      c1.gridy = 7;
      c1.gridx = 0;
      addGuestPanel.add(dateChooser_2, c1);

      countriesLabel = new JLabel("Countries: ");
      c1.gridy = 6;
      c1.gridx = 1;
      addGuestPanel.add(countriesLabel, c1);
      
      
      String[] availableLocales = Locale.getISOCountries();
      
      countries = new JComboBox<String>();
      countries.addItem("");
      for(int i=0; i<availableLocales.length; i++){
         countries.addItem(new Locale("", Locale.getISOCountries()[i]).getDisplayCountry());
      }
      countries.setPreferredSize(new Dimension(145, 20));
      c1.gridy = 7;
      c1.gridx = 1;
      addGuestPanel.add(countries, c1);
      
      c1.anchor = GridBagConstraints.CENTER;
      
      btnAddAnotherGuest = new JButton("Add Another Guest");
      btnAddAnotherGuest.addActionListener(buttonListener);
      c1.gridy = 8;
      c1.gridx = 0;
      addGuestPanel.add(btnAddAnotherGuest, c1);
      
      btnFinish = new JButton("Finish Check-in ");
      btnFinish.addActionListener(buttonListener);
      c1.gridy = 8;
      c1.gridx = 1;
      addGuestPanel.add(btnFinish, c1);
      
      add(addGuestPanel);
      // add(footerPanel, BorderLayout.SOUTH);
      setSize(320, 250);
      setVisible(true);

      setLocationRelativeTo(null);
      
   }
   
   private class MyButtonListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         
         if(e.getSource() == btnAddAnotherGuest){
            addGuest();
            
            firstNameField.setText("");
            lastNameField.setText("");
            addressField.setText("");
            phoneField.setText("");
            countries.setSelectedItem("");
            
            dateChooser_2.setDate(null);
          
            lblNumberOfGuest.setText("Number of Guests added: " + ((reservation.getAllGuests().length)-1));
         }
         
         if(e.getSource() == btnFinish){
            addGuest();
            
            checkInAdapter.addReservation(reservation);
            
            adapter.removeReservation(reservation.getGuest().getFirstName(), reservation.getGuest().getLastName(), reservation.getRoom().getRoomNumber());
            
            setVisible(false);
            
            homePanel.updateArrivalTable();
         }
      }
   }
   
   public void addGuest(){
      String firstName = firstNameField.getText();
      String lastName = lastNameField.getText();
      String adress = addressField.getText();
      String phone = phoneField.getText();
      Date birthday = dateChooser_2.getDate();
      String nationality = (String) countries.getSelectedItem();
      
      Guest guest = new Guest(firstName, lastName, adress, phone, birthday, nationality);
      reservation.addGuest(guest);
   }
   
   public Reservation getReservation(){
      return reservation;
   }
   
}
