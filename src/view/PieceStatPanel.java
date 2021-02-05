package view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controller.GameController;

public class PieceStatPanel extends JPanel {
	private JLabel lblPieceName;
	private JLabel lblPieceHP;
	private JLabel lblStance;
	private Border border;
	public PieceStatPanel( String name, int healthPoints, String stance) {

		border = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(border);

		setLayout(new GridLayout(1, 3));
		lblPieceName = new JLabel();
		lblPieceHP = new JLabel();
		lblStance = new JLabel();

		lblPieceName.setText(name + "    ");
		lblPieceHP.setText("HP:" + Integer.toString(healthPoints));
		lblStance.setText("Stance: " + stance);

		add(lblPieceName);
		add(lblPieceHP);
		add(lblStance);
	}

	public void updateStatus(int healthPoints, String stance) {
		lblPieceHP.setText("HP:" + Integer.toString(healthPoints));
		lblStance.setText("Stance: " + stance);
        this.validate();
        this.repaint();
	}

}
