/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Administrator;

import Constants.ClientCst;
import Models.User;
import Threads.ManageTaskThread;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vinta
 */
public class AdminControl extends javax.swing.JFrame {

    int id;
    User userSelected;
    private DefaultTableModel model;

    /**
     * Creates new form AdminControl
     */
    public AdminControl() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpPanels = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btActivateUser = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setViewportView(jScrollPane1);

        btActivateUser.setText("Activate User");
        btActivateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActivateUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btActivateUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btActivateUser)
                .addContainerGap(245, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpPanels.addTab("Users", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 633, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );

        tpPanels.addTab("Grades", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpPanels, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tpPanels)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        new ManageTaskThread(null, ClientCst.GET_USERS, this).start();
        
    }//GEN-LAST:event_formWindowOpened

    private void btActivateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActivateUserActionPerformed
        // TODO add your handling code here:
        new ManageTaskThread(null, ClientCst.GET_ROLES, this, userSelected).start();
    }//GEN-LAST:event_btActivateUserActionPerformed

    public synchronized void buildTable(ArrayList<User> userList) {
        //Build the data and set to table       
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (userList != null) {
                    model = new DefaultTableModel();

                    model.addColumn("ID");
                    model.addColumn("USER");
                    model.addColumn("PHONE");
                    model.addColumn("ADDRESS");
                    model.addColumn("AGE");
                    model.addColumn("ROL");
                    for (int i = 0; i < userList.size(); i++) {
                        User u = (User) userList.get(i);
                        model.addRow(new Object[]{u.getId(), u.getName(), u.getPhoneNumber(), u.getAddress(), u.getAge(), u.getRol()});
                    }
                        
                    jTable1.setModel(model);
                    model.fireTableDataChanged();
                    ListSelectionModel cell = jTable1.getSelectionModel();
                    cell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    cell.addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            int fila = jTable1.getSelectedRow();
                            id = (int) jTable1.getValueAt(fila, 0);
                            btActivateUser.setEnabled(true);
                            userSelected = new User();
                            userSelected.setId(id);
                            userSelected.setRol((byte)jTable1.getValueAt(fila, 5));
                        }
                    });
                }
            }
        }, 200);

    }
    
    

    /**
     * @param args the command line arguments
     */

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btActivateUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTabbedPane tpPanels;
    // End of variables declaration//GEN-END:variables


}
