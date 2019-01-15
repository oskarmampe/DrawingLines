package draw;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import draw.LinesComponent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;


public class GUI extends JFrame {
	private final LinesComponent comp = new LinesComponent();
	private LinesComponent arrow = new LinesComponent();
	private JTextField textField;
	private String input;
	private boolean penup;
	private BufferedImage bImg;
	private boolean saved;
	private JLabel lblSaved;
	
	
	public GUI () {
		 penup = true;
		 saved = false;
		 
		 GUI frame = (GUI) this;
		 
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 
		 comp.setPreferredSize(new Dimension(800, 800));
		 getContentPane().add(comp, BorderLayout.CENTER);
		 
		 comp.addArrow();
		 
		 JPanel buttonsPanel = new JPanel();
		 
		 lblSaved = new JLabel("Canvas is not saved");
		 buttonsPanel.add(lblSaved);
		 
		 textField = new JTextField();
		 textField.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyPressed(KeyEvent arg0) {
		 		if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
		        {
		 			boolean initSaved = saved;
		 			saved = false;
		 			saved();
		 			input = textField.getText();
		 			
		 			 if (input.contains("forward")) {
			            	String token[] = input.split(" ");
			            	comp.moveForward(Integer.parseInt(token[1]), penup);
			            } else if(input.contains("backward")) {
			            	String token[] = input.split(" ");
			            	comp.moveForward(-Integer.parseInt(token[1]), penup);
			            } else {
		 			switch (input) {
		 			case "penup":  
	            		 penup = false;
	                	 break;
		 			case "pendown":  
		 				penup = true;
	                	 break;
		            case "turnleft":  
		            	 comp.rotateLeft();
		                 break;
		            case "turnright":  
		            	 comp.rotateRight();
		                 break;
		            case "black":  
	            		 comp.setColour(Color.BLACK);
	                	 break;
		            case "green":  
	            		 comp.setColour(Color.GREEN);
	                	 break;
		            case "red":  
	            		 comp.setColour(Color.RED);
	                	 break;
		            case "reset":  
		            	 comp.clearLines();
		            	 comp.addArrow();
	                	 break;
		            case "square":  
		            	 comp.addLine(comp.getCenterX(), comp.getCenterY(), comp.getCenterX()+50, comp.getCenterY(), comp.getColour());
		            	 comp.addLine(comp.getCenterX(), comp.getCenterY(), comp.getCenterX(), comp.getCenterY()+50, comp.getColour());
		            	 comp.addLine(comp.getCenterX(), comp.getCenterY()+50, comp.getCenterX()+50, comp.getCenterY()+50, comp.getColour());
		            	 comp.addLine(comp.getCenterX()+50, comp.getCenterY(), comp.getCenterX()+50, comp.getCenterY()+50, comp.getColour());
		            	 comp.moveForward(0, penup);
	                	 break;
		            case "triangle":  
		            	 comp.addLine(comp.getCenterX(), comp.getCenterY(), comp.getCenterX()+30, comp.getCenterY()+30, comp.getColour());
		            	 comp.addLine(comp.getCenterX(), comp.getCenterY(), comp.getCenterX(), comp.getCenterY()+60, comp.getColour());
		            	 comp.addLine(comp.getCenterX()+30, comp.getCenterY()+30, comp.getCenterX(), comp.getCenterY()+60, comp.getColour());
		            	 comp.moveForward(0, penup);
	                	 break;
		            default: 
		            	 input = "Invalid command";
		            	 saved = initSaved;
		            	 saved();
		                 break;
		        }
			            }
		 			
	           
		 			textField.setText("");
		        }
		 		
		 	}
		 });
		 
		 
		 buttonsPanel.add(textField);
		 textField.setColumns(10);
		 
		 //Making new line button
		 JButton rotateRight = new JButton("rotate right");
		 buttonsPanel.add(rotateRight);
		 rotateRight.addActionListener(new ActionListener() {
			 
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	saved = false;
		        	saved();
		        	comp.rotateRight();
		        }
		    });
		 
		 getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		 
		//Making new line button
		 JButton rotateLeft = new JButton("rotate left");
		 buttonsPanel.add(rotateLeft);
		 rotateLeft.addActionListener(new ActionListener() {
			 
			 

		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	saved = false;
		        	saved();
		        	comp.rotateLeft();
		        }
		    });
		 
		 
		//Making new line button
		 JButton newLineButton = new JButton("New Line");
		 buttonsPanel.add(newLineButton);
		 newLineButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	saved = false;
		        	saved();
		        	comp.moveForward(50, penup);
		        }
		    });
		 
		 getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		 
		 
		 
		 JMenuBar menuBar = new JMenuBar();
		 getContentPane().add(menuBar, BorderLayout.NORTH);
		 
		 JMenu mnFile = new JMenu("File");
		 menuBar.add(mnFile);
		 
		 JMenuItem mntmNew = new JMenuItem("New");
		 mntmNew.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if (saved == false) {
		 			int input = JOptionPane.showOptionDialog(null, "The canvas has not been saved. Want to save before continuing?", "Unsaved Changes", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

		 			if(input == JOptionPane.OK_OPTION)
		 			{
		 				bImg = new BufferedImage(comp.getWidth(), comp.getHeight(), BufferedImage.TYPE_INT_RGB);
				 	    Graphics2D cg = bImg.createGraphics();
				 	    comp.getRootPane().paintAll(cg);
				 	    try {
				 	            if (ImageIO.write(bImg, "png", new File("./src/resources/output_image.png")))
				 	            {
				 	                saved = true;
				 	            }
				 	    } catch (IOException exc) {
				 	            // TODO Auto-generated catch block
				 	            exc.printStackTrace();
				 	    }
		 			}
		 		} else {
		 			saved = false;
		 		}
		 		
		 		saved();
		 		comp.clearLines();
		 		comp.addArrow();
		 		
		 	}
		 });
		 mnFile.add(mntmNew);
		 
		 
		 JMenuItem mntmLoad = new JMenuItem("Load");
		 mntmLoad.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if (saved == false) {
		 			int input = JOptionPane.showOptionDialog(null, "The canvas has not been saved. Want to save before continuing?", "Unsaved Changes", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

		 			if(input == JOptionPane.OK_OPTION)
		 			{
		 				bImg = new BufferedImage(comp.getWidth(), comp.getHeight(), BufferedImage.TYPE_INT_RGB);
				 	    Graphics2D cg = bImg.createGraphics();
				 	    comp.getRootPane().paintAll(cg);
				 	    try {
				 	            if (ImageIO.write(bImg, "png", new File("./src/resources/output_image.png")))
				 	            {
				 	                saved = true;
				 	            }
				 	    } catch (IOException exc) {
				 	            // TODO Auto-generated catch block
				 	            exc.printStackTrace();
				 	    }
		 			}
		 		} else {
		 			saved = false;
		 		}
		 		saved();
		 		JFileChooser fc = new JFileChooser();
		 		fc.setCurrentDirectory(new java.io.File(".")); // start at application current directory
		 		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		 		int returnVal = fc.showSaveDialog(frame);
		 		String fileName = "";
		 		if(returnVal == JFileChooser.APPROVE_OPTION) {
		 		    fileName = fc.getSelectedFile().getPath();
		 		}
		 	    Image img = getToolkit().getImage(fileName);
		 	    frame.getGraphics().drawImage(img, 0, 0, frame);
		 	    
		 	}
		 });
		 mnFile.add(mntmLoad);
		
		 
		 
		 JMenuItem mntmSave = new JMenuItem("Save");
		 mntmSave.addActionListener(new ActionListener() {
			 
		 	public void actionPerformed(ActionEvent e) {
		 		bImg = new BufferedImage(comp.getWidth(), comp.getHeight(), BufferedImage.TYPE_INT_RGB);
		 	    Graphics2D cg = bImg.createGraphics();
		 	    comp.getRootPane().paintAll(cg);
		 	    try {
		 	            if (ImageIO.write(bImg, "png", new File("./src/resources/output_image.png")))
		 	            {
		 	                saved = true;
		 	               saved();
		 	            }
		 	    } catch (IOException exc) {
		 	            // TODO Auto-generated catch block
		 	            exc.printStackTrace();
		 	    }
		 	}
		 });
		 mnFile.add(mntmSave);
		  
		 JMenuItem mntmExit = new JMenuItem("Exit");
		 mntmExit.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		if (saved == false) {
		 			int input = JOptionPane.showOptionDialog(null, "The canvas has not been saved. Want to save before continuing?", "Unsaved Changes", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

		 			if(input == JOptionPane.OK_OPTION)
		 			{
		 				bImg = new BufferedImage(comp.getWidth(), comp.getHeight(), BufferedImage.TYPE_INT_RGB);
				 	    Graphics2D cg = bImg.createGraphics();
				 	    comp.getRootPane().paintAll(cg);
				 	    try {
				 	            if (ImageIO.write(bImg, "png", new File("./src/resources/output_image.png")))
				 	            {
				 	                saved = true;
				 	            }
				 	    } catch (IOException exc) {
				 	            // TODO Auto-generated catch block
				 	            exc.printStackTrace();
				 	    }
		 			}
		 		} else {
		 			saved = false;
		 		}
		 		System.exit(0);
		 	}
		 });
		 mnFile.add(mntmExit);
		 
		 JMenu mnHelp = new JMenu("Help");
		 menuBar.add(mnHelp);
		 
		 JMenuItem mntmAbout = new JMenuItem("About");
		 mntmAbout.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		About popup = new About();
				popup.setModal(true);
				popup.setVisible(true);
		 	}
		 });
		 mnHelp.add(mntmAbout);
		 
		
		    
		    
		 pack();
		 
		 setVisible(true);
	}
	
	public void saved(){
		if (saved == true){
			lblSaved.setText("Saved");
		} else {
			lblSaved.setText("Canvas is not saved");
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
