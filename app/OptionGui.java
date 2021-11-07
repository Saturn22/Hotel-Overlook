import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;







import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class OptionGui
{
   private JPanel optionPanel,leftPanel,rightPanel;
   private JLabel optionLabel, changePriceLabel, changeRoomtypeLabel, selectRoomtypeLabel, newPriceLabel, newRoomtypeLabel;
   private JTextField newPriceField, newRoomTypeField;
   private JButton changePrice, changeType;
   private JComboBox<String> roomTypeBox, roomTypeBox1;
   private JLabel selectRoomtypeLabel1;
   private MyButtonListener buttonListener;
   private RoomFileAdapter adapter;
   
   public OptionGui()
   {
      buttonListener = new MyButtonListener();
      
      adapter = new RoomFileAdapter("data/rooms.bin");
      optionPanel = new JPanel();
      optionPanel.setPreferredSize(new Dimension(800, 200));
      optionPanel.setLayout(new BorderLayout());
      
      
      optionLabel = new JLabel("Options", SwingConstants.CENTER);
      optionLabel.setFont(new Font("Serif", Font.BOLD, 24));
      optionPanel.add(optionLabel, BorderLayout.NORTH);
      leftPanel = new JPanel();
      leftPanel.setPreferredSize(new Dimension(400, 500));
      leftPanel.setLayout(new GridBagLayout());
      
      GridBagConstraints c = new GridBagConstraints();
      changePriceLabel = new JLabel("Change Room Price");
      changePriceLabel.setFont(new Font("Serif", Font.BOLD, 18));
      c.gridy = 0;
      c.gridx = 0;
      leftPanel.add(changePriceLabel, c);
      
      selectRoomtypeLabel = new JLabel("Select Room Type: ");
      c.gridy = 1;
      c.gridx = 0;
      leftPanel.add(selectRoomtypeLabel, c);
      
      roomTypeBox = new JComboBox<String>();
      c.gridy = 1;
      c.gridx = 1;
      leftPanel.add(roomTypeBox, c);
      
      newPriceLabel = new JLabel("New Room Price: ");
      c.gridy = 2;
      c.gridx = 0;
      leftPanel.add(newPriceLabel, c);
      
      newPriceField = new JTextField(20);
      c.gridy = 2;
      c.gridx = 1;
      leftPanel.add(newPriceField, c);
      
      changePrice = new JButton("Change Room Price");
      changePrice.addActionListener(buttonListener);
      c.gridy = 3;
      c.gridx = 0;
      leftPanel.add(changePrice, c);
      
      rightPanel = new JPanel();
      rightPanel.setLayout(new GridBagLayout());
      rightPanel.setPreferredSize(new Dimension(400, 500));
      GridBagConstraints c1 = new GridBagConstraints();
      
      changeRoomtypeLabel = new JLabel("Change Room Type");
      changeRoomtypeLabel.setFont(new Font("Serif", Font.BOLD, 18));
      c1.gridy = 0;
      c1.gridx = 0;
      rightPanel.add(changeRoomtypeLabel, c1);
      
      selectRoomtypeLabel1 = new JLabel("Select Room Type: ");
      c1.gridy = 1;
      c1.gridx = 0;
      rightPanel.add(selectRoomtypeLabel1, c1);
      
      roomTypeBox1 = new JComboBox<String>();
      c1.gridy = 1;
      c1.gridx = 1;
      rightPanel.add(roomTypeBox1, c1);
      
      newRoomtypeLabel = new JLabel("New Room Type: ");
      c1.gridy = 2;
      c1.gridx = 0;
      rightPanel.add(newRoomtypeLabel, c1);
      
      newRoomTypeField = new JTextField(20);
      c1.gridy = 2;
      c1.gridx = 1;
      rightPanel.add(newRoomTypeField, c1);
      
      
      changeType= new JButton("Change Room Type");
      changeType.addActionListener(buttonListener);
      c1.gridy = 3;
      c1.gridx = 0;
      rightPanel.add(changeType, c1);
      
      
      optionPanel.add(leftPanel, BorderLayout.WEST);
      optionPanel.add(rightPanel, BorderLayout.EAST);
      /*frame = new JFrame("Home");
      frame.setSize(new Dimension(1250, 520));
      frame.setVisible(true);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      frame.setLocationRelativeTo(null);

      // Adding panels to Frame

      frame.add(optionPanel);*/
      getRoomTypes();
   }
   public JPanel getOptionPanel()
   {
      return optionPanel;
   }
   private class MyButtonListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         if(e.getSource() == changePrice){
            
            String[] arr = roomTypeBox.getSelectedItem().toString().split(" \\(");

            adapter.changeRoomPrice(arr[0], Double.parseDouble(newPriceField.getText()));
            getRoomTypes();
            newPriceField.setText("");
            
            JOptionPane.showMessageDialog(null, "Room price has been changed.");
         }
         
         if(e.getSource() == changeType){

            adapter.changeRoomType((String) roomTypeBox1.getSelectedItem(), newRoomTypeField.getText());  
            getRoomTypes();
            newRoomTypeField.setText("");
            
            JOptionPane.showMessageDialog(null,"Room type has been changed.");
         }
      }
      
   }  
   
   
   public void getRoomTypes(){
      RoomList list = adapter.getAllRooms();
      String[] rooms = list.getAllRoomTypes();
      roomTypeBox.removeAllItems();
      roomTypeBox1.removeAllItems();
      
      for(int i=0; i<rooms.length; i++){
         roomTypeBox.addItem(rooms[i] + " (" + list.getPriceFromRoomType(rooms[i]) + "�)");
         roomTypeBox1.addItem(rooms[i]);
      }
      
   }
}
