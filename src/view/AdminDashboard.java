/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import dao.FlightDao;
import dao.UserDao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class AdminDashboard extends javax.swing.JFrame {

    public AdminDashboard() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Admin Dashboard - Uunchai Airlines");

        // Initialize everything
        populateComboBoxes();
        loadRegisteredUsers();
        setupButtonActions();

        // Set date format for calendar
        departureDateChooser.setDateFormatString("yyyy-MM-dd HH:mm");
        arrivalDateChooser.setDateFormatString("yyyy-MM-dd HH:mm");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        FlightID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        AirlineName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Source = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        Destination = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Price = new javax.swing.JTextField();
        btnAddFlight = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        departureDateChooser = new com.toedter.calendar.JDateChooser();
        arrivalDateChooser = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        RegisteredUsers = new javax.swing.JLabel();
        jScrollBar1 = new javax.swing.JScrollBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(null);

        jToolBar1.setBackground(new java.awt.Color(51, 153, 255));
        jToolBar1.setRollover(true);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/company _logo.png"))); // NOI18N
        jToolBar1.add(jLabel16);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Welcome Admin!");
        jToolBar1.add(jLabel2);

        jPanel1.add(jToolBar1);
        jToolBar1.setBounds(0, 0, 800, 50);

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 51), 3, true));
        jPanel2.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-dashboard-24.png"))); // NOI18N
        jLabel3.setText("Dashboard");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(20, 50, 100, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-airplane-24.png"))); // NOI18N
        jLabel4.setText("Add Flight");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(20, 110, 90, 24);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-users-30.png"))); // NOI18N
        jLabel5.setText("View Users");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(10, 160, 100, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-logout-30.png"))); // NOI18N
        jLabel6.setText("Logout");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel6);
        jLabel6.setBounds(20, 490, 80, 30);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 50, 210, 550);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Add Flight");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(10, 0, 100, 30);

        jLabel8.setText("Flight ID");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(20, 50, 80, 16);

        FlightID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FlightIDActionPerformed(evt);
            }
        });
        jPanel3.add(FlightID);
        FlightID.setBounds(20, 70, 190, 30);

        jLabel9.setText("Airline Name");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(290, 40, 80, 16);
        jPanel3.add(jTextField2);
        jTextField2.setBounds(20, 70, 160, 30);
        jPanel3.add(AirlineName);
        AirlineName.setBounds(290, 70, 190, 30);

        jLabel10.setText("Source");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(20, 110, 50, 16);

        Source.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(Source);
        Source.setBounds(20, 130, 190, 30);

        jLabel11.setText("Destination");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(290, 110, 70, 16);

        Destination.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(Destination);
        Destination.setBounds(290, 130, 190, 30);

        jLabel12.setText("Departure Time");
        jPanel3.add(jLabel12);
        jLabel12.setBounds(70, 340, 100, 16);

        jLabel13.setText("Departure Time");
        jPanel3.add(jLabel13);
        jLabel13.setBounds(20, 170, 90, 16);

        jLabel14.setText("Arrival Time");
        jPanel3.add(jLabel14);
        jLabel14.setBounds(290, 170, 80, 20);

        jLabel15.setText("Price");
        jPanel3.add(jLabel15);
        jLabel15.setBounds(20, 240, 50, 16);
        jPanel3.add(Price);
        Price.setBounds(20, 260, 190, 30);

        btnAddFlight.setBackground(new java.awt.Color(51, 153, 255));
        btnAddFlight.setText("Add Flight");
        jPanel3.add(btnAddFlight);
        btnAddFlight.setBounds(290, 260, 90, 30);

        btnClear.setBackground(new java.awt.Color(204, 204, 255));
        btnClear.setText("Clear");
        jPanel3.add(btnClear);
        btnClear.setBounds(410, 260, 80, 30);
        jPanel3.add(departureDateChooser);
        departureDateChooser.setBounds(20, 200, 190, 30);
        jPanel3.add(arrivalDateChooser);
        arrivalDateChooser.setBounds(290, 200, 190, 30);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(220, 60, 540, 310);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setLayout(null);

        RegisteredUsers.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        RegisteredUsers.setText("Registered  Users");
        jPanel4.add(RegisteredUsers);
        RegisteredUsers.setBounds(10, 10, 110, 16);
        jPanel4.add(jScrollBar1);
        jScrollBar1.setBounds(530, 0, 10, 190);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "username", "email", "phone", "role"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel4.add(jScrollPane1);
        jScrollPane1.setBounds(10, 30, 510, 170);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(220, 380, 540, 210);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FlightIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FlightIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FlightIDActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
       Login log = new Login();
       log.setVisible(true);
   
    }//GEN-LAST:event_jLabel6MouseClicked

    /**
     * @param args the command line arguments
     */
    private void populateComboBoxes() {
        String[] cities = {
            "Kathmandu", "Pokhara", "Bharatpur", "Biratnagar",
            "Nepalgunj", "Janakpur", "Lukla", "Simara", "Dhangadhi"
        };
        Source.removeAllItems();
        Destination.removeAllItems();
        for (String city : cities) {
            Source.addItem(city);
            Destination.addItem(city);
        }
        // sensible defaults
        Source.setSelectedIndex(0);
        if (cities.length > 1) Destination.setSelectedIndex(1);
    }

    private void setupButtonActions() {
        btnAddFlight.addActionListener(evt -> addFlight());
        btnClear.addActionListener(evt -> clearForm());
    }

   private void addFlight() {
        String flightId = FlightID.getText().trim();
        String airlineName = AirlineName.getText().trim();
        String source = Source.getSelectedItem().toString();
        String destination = Destination.getSelectedItem().toString();
        String priceStr = Price.getText().trim();

        // Validation
        if (flightId.isEmpty() || airlineName.isEmpty() || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill Flight ID, Airline Name and Price", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (source.equals(destination)) {
            JOptionPane.showMessageDialog(this, "Source and destination cannot be the same", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        java.util.Date depDate = departureDateChooser.getDate();
        java.util.Date arrDate = arrivalDateChooser.getDate();

        if (depDate == null || arrDate == null) {
            JOptionPane.showMessageDialog(this, "Please select both departure and arrival dates", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) throw new Exception();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Price must be a positive number", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String departure = sdf.format(depDate);
        String arrival = sdf.format(arrDate);

        FlightDao dao = new FlightDao();
        boolean success = dao.addFlight(flightId, airlineName, source, destination, departure, arrival, price, 100);

        if (success) {
            JOptionPane.showMessageDialog(this, "Flight added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add flight. Flight ID may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

   private void clearForm() {
        FlightID.setText("");
        AirlineName.setText("");
        Price.setText("");
        departureDateChooser.setDate(null);
        arrivalDateChooser.setDate(null);
        Source.setSelectedIndex(0);
        if (Destination.getItemCount() > 1) {
            Destination.setSelectedIndex(1);
        }
    }

    private void loadRegisteredUsers() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        UserDao dao = new UserDao();
        try (ResultSet rs = dao.getAllUsers()) {
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("role")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load users", "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new AdminDashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AirlineName;
    private javax.swing.JComboBox<String> Destination;
    private javax.swing.JTextField FlightID;
    private javax.swing.JTextField Price;
    private javax.swing.JLabel RegisteredUsers;
    private javax.swing.JComboBox<String> Source;
    private com.toedter.calendar.JDateChooser arrivalDateChooser;
    private javax.swing.JButton btnAddFlight;
    private javax.swing.JButton btnClear;
    private com.toedter.calendar.JDateChooser departureDateChooser;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
