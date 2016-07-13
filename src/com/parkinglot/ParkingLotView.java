/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.parkinglot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.Action;
import java.net.URL;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JToolBar;
import org.jdesktop.application.Application;
//import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.TaskMonitor;

/**
 *
 * @author Spring park Hotel
 */
public class ParkingLotView extends JFrame{
    private JScrollPane masterScrollPane;
    protected  JPanel statusPanel,toolPanel;
    GridBagConstraints grdBagConstraints;
    private JTable masterTable;
    private JLabel statusMessageLabel,statusAnimationLabel;
    private JButton btnSave, btnRefresh, btnEdit, btnDelete, btnNext,
        btnBack,btnFind, btnExit;
    private JMenu fileMenu;
    private JMenuBar   menuBar;
    private JMenuItem mitClientInfo, mitUserInfo, mitNewRecord,
            mitSaveRecord, mitRefesh, mitExit;
    private JSeparator spt1, spt2;
    private JProgressBar progressBar;
    private final Timer messageTimer, busyIconTimer;
    private final Icon idleIcon;Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private ResourceMap resourceMap;
    private JToolBar toolbar;
    private JDialog aboutBox;
    private boolean saveNeeded;
    private JFrame parent;
    private Action aClientInfo,aUserInfo, aSave, aDelete, aBack, aNext;
    private UserDetailsForm userDetailPanel ;
    private ParkingLotConnect dbConnect;
    private ActionMap actionMap;
    int messageTimeout;
    ParkingLotApp dapp;

    public ParkingLotView(final JFrame parent) {

        initComponents();

        messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");

        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
	messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");

        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);
        TaskMonitor taskMonitor = new TaskMonitor(dapp.getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });

    }
    private void initComponents() {
        dapp = new ParkingLotApp();

        masterScrollPane = new JScrollPane();
        masterTable = new JTable();
        //userDetailPanel = new JPanel();

        btnSave = new JButton();
        btnRefresh = new JButton();
        btnEdit = new JButton();
        btnDelete = new JButton();
        btnNext= new JButton();
        btnBack= new JButton();
        btnFind = new JButton();
        btnExit = new JButton();
        toolPanel = new JPanel();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        mitNewRecord = new JMenuItem();
        mitUserInfo = new JMenuItem();
        mitSaveRecord = new JMenuItem();
        //mitDrugfacts = new JMenuItem();
        mitUserInfo = new JMenuItem();
        mitRefesh = new JMenuItem();
        mitExit = new JMenuItem();
        spt1 = new JSeparator();
        actionMap = Application.getInstance(ParkingLotApp.class)
                .getContext().getActionMap(ParkingLotView.class, this);
        resourceMap = Application.getInstance(ParkingLotApp.class)
                .getContext().getResourceMap(ParkingLotView.class);
        aClientInfo = actionMap.get("clientInfo");
        aUserInfo = actionMap.get("userInfo");
        aSave= actionMap.get("aSave");


        setTitle( "ParkIT" );
        setSize( 400, 250 );
        setBackground( Color.gray );

        JToolBar stdtbar = new JToolBar();
        toolPanel.add( stdtbar, BorderLayout.NORTH );

        // Add some buttons to the toolbar
        //btnEdit = addToolbarButton( stdtbar, true, "Edit","edit", "Edit record" );
        btnSave = addToolbarButton( stdtbar, true, "Save","save", "Save record", aSave);
        btnSave.setEnabled(false);
        stdtbar.addSeparator();
        btnDelete = addToolbarButton( stdtbar, true, "delete", "Delete", "Delete record",aDelete );
        btnBack = addToolbarButton( stdtbar, true, "back", "Back", "previous record",aBack );

        btnNext = addToolbarButton( stdtbar, true, "forward", "Forward", "next record",aNext);

        spt2 = new JSeparator();
        //JMenuItem exitMenuItem = new JMenuItem();
        JMenu helpMenu = new JMenu();
        JMenuItem mitAbout = new JMenuItem();
        statusPanel = new JPanel();
       
        JSeparator statusPanelSeparator = new JSeparator();
        //toolbar.add(btnNew);
        statusMessageLabel = new JLabel();
        statusAnimationLabel = new JLabel();
        progressBar = new JProgressBar();
        fileMenu.setText(resourceMap.getString("fileMenu.text"));
        fileMenu.setName("fileMenu");

        mitClientInfo.setAction(aClientInfo);
        mitClientInfo.setName("mitClientInfo");
        fileMenu.add(mitClientInfo);

        spt1.setName("spt1");
        fileMenu.add(spt1);

        mitUserInfo.setAction(aUserInfo);
        mitUserInfo.setName("mitUserInfo");
        fileMenu.add(mitUserInfo);


        spt2.setName("spt2");
        fileMenu.add(spt2);

        mitExit.setAction(actionMap.get("quit"));
        mitExit.setName("mitExit");
        fileMenu.add(mitExit);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text"));
        helpMenu.setName("helpMenu");

        mitAbout.setAction(actionMap.get("showAboutBox"));
        mitAbout.setName("mitAbout");
        helpMenu.add(mitAbout);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel");

        statusPanelSeparator.setName("statusPanelSeparator");

        statusMessageLabel.setName("statusMessageLabel");

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel");

        progressBar.setName("progressBar");



        setJMenuBar(menuBar);
        add(stdtbar,BorderLayout.PAGE_START);
        setLocationRelativeTo(null);
         userDetailPanel = new UserDetailsForm();

         setLayout(new BorderLayout());
         add(userDetailPanel);
         userDetailPanel.setVisible(false);


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dbConnect = new ParkingLotConnect();
    }
    @org.jdesktop.application.Action
    public void aSave(){

        if (userDetailPanel != null){
            String firstName = userDetailPanel.getTxtFirstName();
            String lastName = userDetailPanel.getTxtLastName();
            String sql = "INSERT INTO PUBLIC.USERS(FNAME, LNAME, USERNAME, " +
                    "PASSWORD, ACTIVE) VALUES (" + firstName+',' + lastName +
                    "jkikuyu"+','+ "abc" + ','+ 1 + ")";
            dbConnect.update(sql);

        }
        else{
            
        }

    }
    @org.jdesktop.application.Action
    public void aDelete(){

    }
    @org.jdesktop.application.Action
    public void aBack(){

    }

    @org.jdesktop.application.Action
    public void drugFactsDetail() {
        JOptionPane.showMessageDialog(this,"Invalid password. " +
                "Try again.","Error Message",JOptionPane.ERROR_MESSAGE);
    }
    @org.jdesktop.application.Action
    public void showUserDetail() {

        if(userDetailPanel != null){
         userDetailPanel.setVisible(true);
         btnSave.setEnabled(true);

        }
    }

    @org.jdesktop.application.Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = ParkingLotApp.getApplication().getMainFrame();
            aboutBox = new ParkingLotAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        ParkingLotApp.getApplication().show(aboutBox);
    }
	public JButton addToolbarButton( JToolBar toolBar, boolean bUseImage,
            String sButtonText,String sButton, String sToolHelp,javax.swing.Action action)
	{
		JButton b;

		// Create a new button
		if( bUseImage ){
                        URL url = getClass().getResource("resources/" + sButton + ".gif");
                        //System.out.println(url.toString());
			b = new JButton();
                        b.setAction(action);
                        b.setText("");

                        b.setName(sButton);
                        b.setIcon(new ImageIcon(url));
                }
		else{
			b = (JButton)toolBar.add( new JButton());
                }
                            
		// Add the button to the toolbar
                //b.setAction(action);

		toolBar.add( b );

		// Add optional button text
		if( b.getIcon() instanceof Icon )
                    b.setMargin( new Insets( 0, 0, 0, 0 ) );

		else
		{

                    // graphics not preset use text
                    b.setText( sButtonText );
		}


		// Add optional tooltip help
		if( sToolHelp != null )
			b.setToolTipText( sToolHelp );


		return b;
	}

}

