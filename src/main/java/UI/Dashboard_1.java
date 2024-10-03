/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import UI.InventoryPanel;
import UI.InventoryPanel;
import UI.SaleCreationPanel;
import UI.SaleCreationPanel;

/**
 *
 * @author dazzl
 */
//Now this right here is not developed, it's two buttons. What on earth are you even doing here
public class Dashboard_1 extends javax.swing.JFrame {

    /**
     * Creates new form dashboard
     */
    public Dashboard_1() {
        
        //Initializes the UI
        initComponents();
        
        //Set's it to the centre of the screen
        setLocationRelativeTo(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inventoryButton = new javax.swing.JButton();
        saleCreatorButton = new javax.swing.JButton();
        clientEditorButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        helpScreenButton = new javax.swing.JButton();
        partCreatorButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        inventoryButton.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        inventoryButton.setText("INVENTORY");
        inventoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventoryButtonActionPerformed(evt);
            }
        });

        saleCreatorButton.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        saleCreatorButton.setText("SALE CREATOR");
        saleCreatorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleCreatorButtonActionPerformed(evt);
            }
        });

        clientEditorButton.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        clientEditorButton.setText("CLIENT EDITOR");
        clientEditorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientEditorButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Candara Light", 1, 48)); // NOI18N
        jLabel1.setText("INVENTORY MANAGEMENT");

        helpScreenButton.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        helpScreenButton.setText("HELP");
        helpScreenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpScreenButtonActionPerformed(evt);
            }
        });

        partCreatorButton.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        partCreatorButton.setText("PART CREATOR");
        partCreatorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partCreatorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(158, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(partCreatorButton, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                            .addComponent(inventoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(saleCreatorButton, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                            .addComponent(clientEditorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(helpScreenButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(260, 260, 260))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(111, 111, 111))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(inventoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(saleCreatorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(clientEditorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(partCreatorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(helpScreenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Makes the inventory screen visible
    private void inventoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventoryButtonActionPerformed
        new InventoryPanel().setVisible(true);
    }//GEN-LAST:event_inventoryButtonActionPerformed
    
    //Makes the saleCreationPanel visible
    private void saleCreatorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saleCreatorButtonActionPerformed
        new SaleCreationPanel().setVisible(true);

    }//GEN-LAST:event_saleCreatorButtonActionPerformed
    
    //Makes the clientEditorPanel visble
    private void clientEditorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientEditorButtonActionPerformed
        ClientEditPanel clientPanelInstance = new ClientEditPanel();
        clientPanelInstance.setVisible(true);
    }//GEN-LAST:event_clientEditorButtonActionPerformed

    //Makes the helpScreen visble
    private void helpScreenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpScreenButtonActionPerformed
        new helpScreen().setVisible(true);
    }//GEN-LAST:event_helpScreenButtonActionPerformed

    //Makes the part PartCreationPanel visible
    private void partCreatorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partCreatorButtonActionPerformed
        PartCreationPanel partCreationPanel = new PartCreationPanel();
        partCreationPanel.setVisible(true);
    }//GEN-LAST:event_partCreatorButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard_1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clientEditorButton;
    private javax.swing.JButton helpScreenButton;
    private javax.swing.JButton inventoryButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton partCreatorButton;
    private javax.swing.JButton saleCreatorButton;
    // End of variables declaration//GEN-END:variables
}
