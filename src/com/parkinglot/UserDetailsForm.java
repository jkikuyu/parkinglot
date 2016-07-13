
package com.parkinglot;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;

/**
 *
 * @author Jude Kikuyu
 */
public class UserDetailsForm extends JFrame {

    protected JPanel panelusr,panelContact,panelSecurity;
    private JCheckBox chkActive;
    private JLabel lblFirstName, lblLastName, lblUserName,lblEmail,lblAddress,
            lblActive, statusMessageLabel,statusAnimationLabel;
    private  JTextField txtUserName, txtFirstName, txtLastName, txtEmail,
            txtAddress;
    private ResourceMap resourceMap;


    public UserDetailsForm(){
        initComponents();
    }
     private void initComponents() {
        GridBagConstraints grdBagConstraints;
        GridBagLayout grdBagLayout;
        grdBagConstraints = new GridBagConstraints();
        grdBagLayout = new GridBagLayout();
        panelusr = new JPanel();
        panelusr.setLayout(grdBagLayout);
        panelContact = new JPanel();
        panelSecurity = new JPanel();

        lblFirstName = new JLabel();
        lblLastName = new JLabel();
        lblUserName= new JLabel();
        lblAddress= new JLabel();
        lblEmail = new JLabel();

        lblActive = new JLabel();
        txtUserName = new JTextField(20);
        txtFirstName = new JTextField(25);
        txtLastName = new JTextField(25);
        txtEmail = new JTextField(30);
        txtAddress = new JTextField(25);
        chkActive = new JCheckBox();
        resourceMap = Application.getInstance(ParkingLotApp.class)
                .getContext().getResourceMap(ParkingLotView.class);

        lblFirstName.setFont(resourceMap.getFont("label.font"));
        lblFirstName.setText(resourceMap.getString("lblFirstName.text"));
        grdBagConstraints.gridx = 0;
        grdBagConstraints.gridy = 2;
        grdBagConstraints.insets = new Insets(9, 10, 0, 0);
        panelusr.add(lblFirstName, grdBagConstraints);

        lblFirstName.setName("lblFirstName");
        lblLastName.setFont(resourceMap.getFont("label.font"));
        lblLastName.setText(resourceMap.getString("lblLastName.text"));
        lblLastName.setName("lblLastName");

        lblUserName.setFont(resourceMap.getFont("label.font"));
        lblUserName.setText(resourceMap.getString("lblUserName.text"));
        lblUserName.setName("lblUserName");

        lblAddress.setFont(resourceMap.getFont("label.font"));
        lblAddress.setText(resourceMap.getString("lblAddress.text"));
        lblAddress.setName("lblAddress");

        lblEmail.setFont(resourceMap.getFont("label.font"));
        lblEmail.setText(resourceMap.getString("lblEmail.text"));
        lblEmail.setName("lblEmail");

        grdBagLayout = new GridBagLayout();
        txtFirstName.setName("txtFirstName");
        grdBagConstraints.gridx = 1;
        grdBagConstraints.gridy = 2;
        grdBagConstraints.gridheight = 2;
        grdBagConstraints.ipadx = 53;
        grdBagConstraints.insets = new Insets(6, 4, 11, 0);
        panelusr.add(txtFirstName, grdBagConstraints);

        txtLastName.setName("txtLastName");
        txtUserName.setName("txtUserName");
        txtEmail.setName("txtEmail");
        txtAddress.setName("txtAddress");

        GridBagLayout layout = new GridBagLayout();
        getContentPane().setLayout(layout);
      //  mainPanel.setBorder(BorderFactory.createEtchedBorder());
      //  mainPanel2.setBorder(BorderFactory.createEtchedBorder());


       //mainPanel.add(cinpanel,BorderLayout.WEST);
       //secPanel.add(ainpanel);
       setLayout(layout);
       add(panelusr);
       //add(ainpanel);
      //add(secPanel);

     }

    public String getTxtFirstName() {
        return txtFirstName.getText();
    }
    public String getTxtLastName() {
        return txtLastName.getText();
    }

    public void setTxtFirstName(JTextField txtFirstName) {
        this.txtFirstName = txtFirstName;
    }


    public void setTxtLastName(JTextField txtLastName) {
        this.txtLastName = txtLastName;
    }
       public void createAndShowWindow(JFrame c){
       c.pack();
      c.setDefaultCloseOperation(EXIT_ON_CLOSE);
        c.setVisible(true);

    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this Simple GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UserDetailsForm sgui = new UserDetailsForm();
                sgui.createAndShowWindow(sgui);
            }
        });
    }


}
