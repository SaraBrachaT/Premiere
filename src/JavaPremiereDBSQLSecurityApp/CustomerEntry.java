package JavaPremiereDBSQLSecurityApp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CustomerEntry extends JFrame
{

    private Connection dbConnection;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 400;
    private JLabel instructions;
    private JLabel nameLbl;
    private JTextField custName;
    private JLabel streetLbl;
    private JTextField custStreet;
    private JLabel cityLbl;
    private JTextField custCity;
    private JLabel stateLbl;
    private JComboBox<String> custState;
    private JLabel zipLbl;
    private JTextField custZip;
    private JLabel phoneLbl;
    private JTextField custPhone;
    private JLabel repLbl;
    private JTextField repNum;
    private JButton submitBtn;
    private JButton cancelBtn;
    

    public CustomerEntry(Connection dbConnection)
    {
	// store the reference to the database --- back end
	this.dbConnection = dbConnection;

	// verify that a databse connection exists
	if (this.dbConnection == null)
	{
	    JOptionPane.showMessageDialog(null, "missing database connection --- contact IT");

	} else
	{ // continue with this process

	    setTitle("New Customer Entry Form");
	    // set window size
	    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

	    // Specify an action for the close button.
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    // set up layout of the window
	    setLayout(new GridLayout(10, 2));
	    instructions = new JLabel("Please enter the information below");
	    add(instructions);
	    add(new JLabel());
	    nameLbl = new JLabel("Name:");
	    add(nameLbl);
	    custName = new JTextField();
	    add(custName);
	    streetLbl = new JLabel("Street Address:");
	    add(streetLbl);
	    custStreet = new JTextField();
	    add(custStreet);
	    cityLbl = new JLabel("City: ");
	    add(cityLbl);
	    custCity = new JTextField();
	    add(custCity);
	    stateLbl = new JLabel("State:");
	    add(stateLbl);
	    String[] states = new String[] { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU",
		    "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MH", "MI", "MN", "MO", "MS",
		    "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "PW", "RI",
		    "SC", "SD", "TN", "TX", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY" };
	    custState = new JComboBox<String>(states);
	    add(custState);
	    zipLbl = new JLabel("Zip Code:");
	    add(zipLbl);
	    custZip = new JTextField();
	    add(custZip);
	    phoneLbl = new JLabel("Phone Number (no spaces or dashes)");
	    add(phoneLbl);
	    custPhone = new JTextField();
	    add(custPhone);
	    repLbl = new JLabel("Sales Rep Number (To see sales rep numbers, click list employees): ");
	    add(repLbl);
	    repNum = new JTextField();
	    add(repNum);

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
	@Override
	public void actionPerformed(ActionEvent arg0)
	{

	    String name = custName.getText();
	    String street = custStreet.getText();
	    String city = custCity.getText();
	    String state = (String) custState.getSelectedItem();
	    String zip = custZip.getText();
	    String phone = custPhone.getText();
	    Integer repNo = Integer.parseInt(repNum.getText());
	   
	    // TODO Try catch it
	    

	    try
	    {
		CallableStatement insertCustomer = dbConnection.prepareCall("{call spAddCustomer(?, ?, ?, ?, ?, ?, ?)}");
		
		insertCustomer.setString(1, name);
		insertCustomer.setString(2, street);
		    insertCustomer.setString(3, city);
		    insertCustomer.setString(4, state);
		    insertCustomer.setString(5, zip);
		    insertCustomer.setInt(6, repNo);
		    insertCustomer.setString(7, phone);

		    insertCustomer.execute();
		    dbConnection.commit();
		    System.out.println("data commited");
	    } catch (SQLException e)
	    {
		try
		{
		    dbConnection.rollback();
		    System.out.println("rollback");
		} catch (SQLException e1)
		{
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }
	    theWindow.dispose();
	}
	
	public SubmitListener(JFrame window)
	{
	    theWindow = window;
	}
    }


}
