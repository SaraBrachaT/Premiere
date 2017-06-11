package JavaPremiereDBSQLSecurityApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class CancelListener implements ActionListener
{

    private JFrame theWindow;

    public CancelListener(JFrame window)
    {
	theWindow = window;
    }

    public void actionPerformed(ActionEvent arg0)
    {

	theWindow.dispose();
    }
}
