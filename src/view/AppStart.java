package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import controller.GameController;
import controller.listeners.AppStartActionListener;

public class AppStart extends JFrame {
	private JLabel lblBoardDimensions;
	private JLabel lblPlayers;
	private JLabel lblNumberOfPieces;
	
	private JComboBox cmbBoardDimensions;
	private JComboBox cmbPlayers;
	private JSpinner cmbNumberOfPieces;
	
	private JButton btnStart;
	
	private JRadioButton rbtnEasy;
	private JRadioButton rbtnMedium;
	private JRadioButton rbtnHard;
	
	private JPanel inputPanel;
	private JPanel radioPanel;
	
	private ButtonGroup radioGroup;
	
	
	  public AppStart(GameController gameController) {
		     
      	
          //sets up default frame
          super("Knights VS Samurai Game");
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setBounds(100, 100, 250, 450);
          setLayout(new GridLayout(3,1));

          inputPanel = new JPanel();
          inputPanel.setLayout(new GridLayout(3,2));
          
          radioPanel = new JPanel();
          radioPanel.setLayout(new GridLayout(1,3));
          
          btnStart = new JButton("Start Game");
          
          lblBoardDimensions = new JLabel("Board Dimensions: ");
          lblPlayers = new JLabel("Number of players: ");
          lblNumberOfPieces = new JLabel("Pieces per player:");
          
          String[] boardValues = {"5x5","6x6","7x7","8x8","9x9","10x10","11x11","12x12","13x13","14x14","15x15"};
          cmbBoardDimensions = new JComboBox(boardValues);
          String[] playerValues = {"2"};
          cmbPlayers = new JComboBox(playerValues);
          SpinnerModel piecesValues = new SpinnerNumberModel(3,3,6,1);
          cmbNumberOfPieces = new JSpinner(piecesValues);
          
          rbtnEasy = new JRadioButton("Easy");
          rbtnEasy.setSelected(true);
          rbtnMedium = new JRadioButton("Medium");
          rbtnHard = new JRadioButton("Hard");
          radioGroup = new ButtonGroup();
          
          radioGroup.add(rbtnEasy);
          radioGroup.add(rbtnMedium);
          radioGroup.add(rbtnHard);
          
          add(inputPanel);
          add(radioPanel);
          

          inputPanel.add(lblBoardDimensions);
          inputPanel.add(cmbBoardDimensions);
          inputPanel.add(lblPlayers);
          inputPanel.add(cmbPlayers);
          inputPanel.add(lblNumberOfPieces);
          inputPanel.add(cmbNumberOfPieces);
          
          radioPanel.add(rbtnEasy);
          radioPanel.add(rbtnMedium);
          radioPanel.add(rbtnHard);
          
          add(btnStart);
          btnStart.addActionListener(new AppStartActionListener(gameController, this));

          setVisible(true);

      }
	  
	  public int getBoardDimensions() {
		  return cmbBoardDimensions.getSelectedIndex() + 5;
		
	  }
	  

	  public int getNumberOfPlayers() {
		  return cmbPlayers.getSelectedIndex() + 2;
	  }
	  
	  public int getNumberOfPieces() {
			try {
				cmbNumberOfPieces.commitEdit();
			} catch (Exception exception) {
				throw new IllegalArgumentException("Could not recognize piece count per player");
			}
		  return (int) cmbNumberOfPieces.getValue();
	  }
	  
	  public int getDifficulty() {
		  int diff = 0;
		  
		  if (rbtnEasy.isSelected()) {
			  diff = 1;
		  } else if (rbtnMedium.isSelected()) {
			  diff = 2;
		  } else if (rbtnHard.isSelected()) {
			  diff = 3;
		  }
		  
		  return diff;
	  }
}
