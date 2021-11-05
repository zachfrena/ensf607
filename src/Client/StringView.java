package Client;

import javax.swing.*;
import java.awt.event.ActionListener;

public class StringView extends JFrame{
	
	
	private JTextField firstString = new JTextField(10);
	private JLabel addLabel = new JLabel ("+");
	
	private JTextField secondString = new JTextField(10);
	private JButton UpperConcatButton = new JButton ("Uppercase concatenate"); // added 2 buttons to specify uppercase or lowercase
	private JButton LowerConcatButton = new JButton ("Lowercase concatenate");
	
	private JTextField result = new JTextField(20);
    
	public StringView(){
		JPanel calcPanel = new JPanel ();
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		calcPanel.add(firstString);
		calcPanel.add(addLabel);
		calcPanel.add(secondString);
		calcPanel.add(UpperConcatButton);
		calcPanel.add(LowerConcatButton);
		calcPanel.add(result);
		
		this.add(calcPanel);
	}
	public String getFirstString ()
	{
		return firstString.getText();
	}
	public String getSecString ()
	{
		return secondString.getText();
	}
	public void setResult (String res)
	{
		result.setText(res);
	}

	void addStringListener (ActionListener listenForUpperConcatButton, ActionListener listenForLowerConcatButton)
	{
		UpperConcatButton.addActionListener(listenForUpperConcatButton);
		LowerConcatButton.addActionListener(listenForLowerConcatButton);
	}
	void displayErrorMessage (String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
	
	
	
}
