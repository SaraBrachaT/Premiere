package JavaPremiereDBSQLSecurityApp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import JavaPremiereDBSQLSecurityApp.CustomerEntry.CancelListener;
import JavaPremiereDBSQLSecurityApp.CustomerEntry.SubmitListener;

public class EmployeeEntry extends JFrame
{

    private Connection dbConnection;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 400;
    private JLabel instructions;
    private JLabel fNameLbl;
    private JTextField firstName;
    private JLabel lNameLbl;
    private JTextField lastName;
    private JLabel streetLbl;
    private JTextField street;
    private JLabel cityLbl;
    private JTextField city;
    private JLabel stateLbl;
    private JComboBox<String> state;
    private JLabel zipLbl;
    private JTextField zip;
    private JLabel phoneLbl;
    private JTextField phone;
    private JLabel birthdateLbl;
    private JTextField birthdate;
    private JLabel empTypeIDLbl;
    private JTextField empTypeID;
    private JButton submitBtn;
    private JButton cancelBtn;

    public EmployeeEntry(Connection dbConnection)
    {
	this.dbConnection = dbConnection;

	// verify that a databse connection exists
	if (this.dbConnection == null)
	{
	    JOptionPane.showMessageDialog(null, "missing database connection --- contact IT");

	} else
	{ // continue with this process

	    setTitle("New Employee Entry Form");
	    // set window size
	    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

	    // Specify an action for the close button.
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    // set up layout of the window
	    setLayout(new GridLayout(10, 2));
	    instructions = new JLabel("Please enter the information below");
	    add(instructions);
	    add(new JLabel());
	    fNameLbl = new JLabel("First Name: ");
	    add(fNameLbl);
	    firstName = new JTextField();
	    add(firstName);
	    lNameLbl = new JLabel("Last Name:");
	    add(lNameLbl);
	    lastName = new JTextField();
	    add(lastName);
	    streetLbl = new JLabel("Street Address:");
	    add(streetLbl);
	    street = new JTextField();
	    add(street);
	    cityLbl = new JLabel("City: ");
	    add(cityLbl);
	    city = new JTextField();
	    add(city);
	    stateLbl = new JLabel("State:");
	    String[] states = new String[] { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU",
		    "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MH", "MI", "MN", "MO", "MS",
		    "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "PW", "RI",
		    "SC", "SD", "TN", "TX", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY" };
	    add(stateLbl);
	    state = new JComboBox<String>(states);
	    add(state);
	    zipLbl = new JLabel("Zip Code:");
	    add(zipLbl);
	    phoneLbl = new JLabel("Phone Number (no spaces or dashes)");
	    add(phoneLbl);
	    birthdateLbl = new JLabel("Birthdate(mm-dd-yyyy): ");
	    add(birthdateLbl);
	    birthdate = new JTextField();
	    add(birthdate);
	    empTypeIDLbl = new JLabel("Employee ID");
	    add(empTypeIDLbl);
	    empTypeID = new JTextField();
	    add(empTypeID);
	    
	    add(new JLabel());
	    add(new JLabel());
	    
	    submitBtn = new JButton("Submit");
	    add(submitBtn);
	    submitBtn.addActionListener(new SubmitListener(this));
	    cancelBtn = new JButton("Cancel");
	    add(cancelBtn);
	    cancelBtn.addActionListener(new CancelListener(this));
	    setVisible(true);
	}
    }
    
    private class SubmitListener implements ActionListener
    {
	JFrame theWindow;
	
	public void actionPerformed(ActionEvent arg0)
	{
	    String fName = firstName.getText();
	    String lName = lastName.getText();
	    String strt = street.getText();
	    String cit = city.getText();
	    String stte = (String) state.getSelectedItem();
	    String zipC = zip.getText();
	    String phoneN = phone.getText();
	    String birthday = birthdate.getText();
	    Integer empType = Integer.parseInt(empTypeID.getText());
	}
    }
}
