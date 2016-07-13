/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.parkinglot;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;

/**
 *
 * @author Jude Kikuyu
 */
public class LoginForm extends JDialog implements ActionListener  {
    
    private JButton btnOk, btnCancel;
    private JLabel lblUserName, lblPassword;
    private JTextField txtUserName;
    private JPasswordField txtPassword;
    private JPanel panel;
    private JFrame parent;
    private static String OK = "ok";
    private static String CANCEL = "cancel";
    private int cnt; // keep login counter
    private boolean loginSuccess;
    ParkingLotApp dapp;
    public LoginForm(){
      this(null, true);
    }
    public LoginForm(final JFrame parent, boolean model) {
        super(parent, model );
        this.parent = parent;
        initComponents();
    }
    private void initComponents() {
        cnt = 0;
        lblUserName = new JLabel();
        lblPassword = new JLabel();
        txtUserName = new JTextField(20);
        txtPassword = new JPasswordField(20);
        btnOk = new JButton();
        btnCancel = new JButton();
        panel = new JPanel();
        loginSuccess = false;
        //frame= new JFrame();
        panel.setName("panel");

        // use resource to obtain label titles and properties
        ResourceMap resourceMap = Application.getInstance(ParkingLotApp.class).getContext().
                getResourceMap(LoginForm.class);

        // label set up
        lblUserName.setFont(resourceMap.getFont("label.font"));
        lblUserName.setText("User Name");
        lblUserName.setName("lblUsername");
        lblPassword.setFont(resourceMap.getFont("label.font"));
        lblPassword.setText(resourceMap.getString("lblPassword.text"));
        lblPassword.setName("lblPassword");
        // text field setup

        txtUserName.setFont(resourceMap.getFont("inputfield.font"));
        txtUserName.setText(resourceMap.getString(""));
        txtUserName.setName("txtUsername");

        txtPassword.setFont(resourceMap.getFont("inputfield.font"));
        txtPassword.setText(resourceMap.getString(""));
        txtPassword.setName("txtPassword");
        txtPassword.setActionCommand(OK);
        txtPassword.addActionListener(this);
         // button set up

        btnOk.setText(resourceMap.getString("btnOk.text")); 
        btnOk.setName("btnOk");
        btnOk.setActionCommand(OK);
        btnOk.addActionListener(this);
        btnCancel.setText(resourceMap.getString("btnCancel.text")); 
        btnCancel.setName("btnCancel");
        btnCancel.setActionCommand(CANCEL);
        btnCancel.addActionListener(this);

        GridBagLayout layout= new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);

        gc.gridx = 0;
        gc.gridy = 0;
        panel.add(lblUserName, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        panel.add(txtUserName, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        panel.add(lblPassword, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        panel.add(txtPassword, gc);
        JPanel bp = new JPanel();
        bp.add(btnOk);
        bp.add(btnCancel);
        add(panel, BorderLayout.CENTER);
        add(bp, BorderLayout.PAGE_END);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Enter your password");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();

        if (OK.equals(cmd)) { //Process the password.
            char[] pass = txtPassword.getPassword();
            String userName = txtUserName.getText();
            dapp = new ParkingLotApp();
            if (isPasswordCorrect(userName, pass)) {
                //new DrugfactsView(parent).setVisible(true);
                parent.dispose();
            } else {
                cnt++;
                if(cnt >= 3)
                    System.exit(0);
                JOptionPane.showMessageDialog(this,"Invalid password. " +
                "Try again.","Error Message",JOptionPane.ERROR_MESSAGE);
            }

            //Zero out the possible password, for security.
            Arrays.fill(pass, '0');
        } else { //The user has canceled
                setVisible(false);
                //parent.dispose();
                System.exit(0);
        }

    }
    private boolean isPasswordCorrect(String userName, char[] input ) {
        char[] correctPassword =new char[]{};
        if (userName.equals("admin")){
            correctPassword = new char[]{ '1', '2', '3' };
            if (input.length != correctPassword.length) {
                loginSuccess = false;
            } else {
                loginSuccess = Arrays.equals (input, correctPassword);
            }
        }
        else{

        }
        //Zero out the password.
        Arrays.fill(correctPassword,'0');

        return loginSuccess;
    }
}
