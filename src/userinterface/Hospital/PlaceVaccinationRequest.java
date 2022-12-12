/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.Hospital;

import Business.City.City;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Enterprise.Hospital;
import Business.Organization.Organization;
import Business.Patient.Patient;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.Order;
import Business.WorkQueue.VaccinatePatient;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author meghi
 */
public class PlaceVaccinationRequest extends javax.swing.JPanel {

    /**
     * Creates new form PlaceVaccinationRequest
     */
    JPanel userProcessContainer;
    EcoSystem system;
    UserAccount userAccount;
    Organization organization;
    Enterprise enterprise;
    City city;
    public PlaceVaccinationRequest(JPanel userProcessContainer, City city, UserAccount userAccount, Organization organization, 
            Enterprise enterprise, EcoSystem system) {
        initComponents();
        this.userProcessContainer=userProcessContainer;
        this.system=system;  
        this.userAccount=userAccount;
        this.enterprise=enterprise;
        this.city=city;
        this.organization=organization;
        populateComboBox();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbVaccinationCenters = new javax.swing.JComboBox<>();
        btnSendRequest = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel1.setText("Select Test Center:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 121, 32));

        cmbVaccinationCenters.setBackground(new java.awt.Color(214, 229, 244));
        cmbVaccinationCenters.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cmbVaccinationCenters, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 170, 30));

        btnSendRequest.setBackground(new java.awt.Color(0, 0, 0));
        btnSendRequest.setForeground(new java.awt.Color(255, 255, 255));
        btnSendRequest.setText("Send Request");
        btnSendRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendRequestActionPerformed(evt);
            }
        });
        add(btnSendRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, -1));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SEND VACCINATION REPORT");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-100, 10, 1000, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendRequestActionPerformed
     //   String vaccineName;
    //int qty;
    try{
    String message = "Please Vaccinate";
    String status = "Active";
    
    //vaccineName = txtVaccineName.getText();
    //qty = Integer.parseInt(txtQty.getText());
    ArrayList<Patient> pd = new ArrayList<>();
    Hospital hospital = (Hospital)enterprise;
    for(Patient p : hospital.getPatientDirectory().getPatientDir()){
        if(!p.getVaccinationStatus().equals("Fully Vaccinated")){
            pd.add(p);
        }
    }
    String VaccineCenterName = (String) cmbVaccinationCenters.getSelectedItem();
    for(City city : system.getCityList()){
        for (Enterprise e : city.getEnterpriseDirectory().getEnterpriseList()) {
            if((e.getName()).equals(VaccineCenterName)){
                UserAccount receiver = e.getUserAccountDirectory().getUserAccountList().get(0);
                VaccinatePatient vp = e.getWorkQueue().addWorkRequestList2(message, userAccount, receiver, status);
                vp.setNonVaccPatientList(pd);
                //System.out.println(e.getWorkQueue().getWorkRequestList().get(0));
            }
            
        }
        }
    JOptionPane.showMessageDialog(this,"Request sent");
    }
catch(Exception e){
        JOptionPane.showMessageDialog(this,"Vaccination request cannot be sent");// TODO add your handling code here:
}
    }//GEN-LAST:event_btnSendRequestActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSendRequest;
    private javax.swing.JComboBox<String> cmbVaccinationCenters;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
private void populateComboBox() {
        cmbVaccinationCenters.removeAllItems();
        for(City city : system.getCityList()){
        for (Enterprise e : city.getEnterpriseDirectory().getEnterpriseList()) {
            //System.out.println(e.getClass().getName());
            if((e.getClass().getName()).equals("Business.Enterprise.VaccinationCenter"))
            cmbVaccinationCenters.addItem(e.getName());
        } //To change body of generated methods, choose Tools | Templates.
    }
    }


}

