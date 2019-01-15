package draw;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class About extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public About() {
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLbltext = new JLabel("<html>About: <br> An application that draws lines on a canvas. Could either use buttons or type commands to make lines.<br><br>Commands available:<br>penup - creates lines<br>pendown - stops creating lines<br>turnleft - turns cursor left<br>turnright - turns cursor right<br>forward <distance> - moves the cursor forward by the amount specified<br>backward <distance> -  moves the cursor backwards by the amount specified<br>black - makes lines black<br>green - makes lines green<br>red - makes lines red<br>reset - resets the canvas<br>square - makes a square<br>triangle- makes a triangle<br> <br>Created By: </html");
		contentPanel.add(lblLbltext, BorderLayout.CENTER);
		

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
			getRootPane().setDefaultButton(cancelButton);
		}
	}
}
